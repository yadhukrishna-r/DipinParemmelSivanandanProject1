package com.uase.shipping.method;

import com.uase.shipping.constant.Constants;
import com.uase.shipping.constant.ContainerType;
import com.uase.shipping.constant.Product;
import com.uase.shipping.containers.Container;
import com.uase.shipping.containers.ShippingContainer;
import com.uase.shipping.dto.ShippingRequestDTO;
import com.uase.shipping.dto.ShippingResponseDTO;
import com.uase.shipping.item.Item;
import com.uase.shipping.item.ShippingItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Calcutator {

    public static double getVolume(double length, double height, double width) {
        return length * height * width;
    }


    public static ResponseEntity<ShippingResponseDTO> findSuitableShipment(ShippingRequestDTO request) {
        if ((request != null) && request.getDesktop() > 0 || request.getLaptop() > 0 || request.getLcdSreen() > 0 || request.getMouse() > 0) {
            Map<Product, Integer> itemCount = getItemCount(request);
            ShippingResponseDTO shippingResponse = new ShippingResponseDTO();
            List<ShippingContainer> shippingContainers = new ArrayList<>();
            findSuitableContainers(itemCount, shippingContainers);
            shippingResponse.setShippingContainers(shippingContainers);
            return ResponseEntity.ok(shippingResponse);
        }

        return (ResponseEntity<ShippingResponseDTO>) ResponseEntity.noContent();

    }

    private static List<Item> getItemList(Map<Product, Integer> itemCount) {
        List<Item> items = new ArrayList<>();
        itemCount.forEach((k, v) -> {
            items.add(new Item(k, v));
        });
        return items;
    }

    private static Map<Product, Integer> getItemCount(ShippingRequestDTO request) {
        Map<Product, Integer> itemCount = new HashMap<>();
        if (request.getMouse() > 0) {
            itemCount.put(Product.MOUSE, request.getMouse());
        }
        if (request.getDesktop() > 0) {
            itemCount.put(Product.DESKTOP, request.getDesktop());
        }
        if (request.getLcdSreen() > 0) {
            itemCount.put(Product.LCD_SCREEN, request.getLcdSreen());
        }
        if (request.getLaptop() > 0) {
            itemCount.put(Product.LAPTOP, request.getLaptop());
        }
        return itemCount;
    }

    private static void findSuitableContainers(Map<Product, Integer> itemCount, List<ShippingContainer> shippingContainers) {
        List<Item> items = getItemList(itemCount);
        List<ShippingItem> shippingItems = new ArrayList<>();
        double totalVolume = items.stream().mapToDouble(item -> (item.getVolume() * item.getNumberOfItems())).sum();
        double totalWeight = items.stream().mapToDouble(item -> (item.getWeight() * item.getNumberOfItems())).sum();
        Container bigContainer = new Container(ContainerType.BIG_CONTAINER);
        Container smallContainer = new Container(ContainerType.SMALL_CONTAINER);
        if (totalVolume > smallContainer.getVolume()) {
            if (totalVolume > bigContainer.getVolume()) {
                splitShipmentToMultipleContainers(items, itemCount, shippingContainers);

            } else {
                items.forEach(item ->
                        shippingItems.add(new ShippingItem(item.getName(), item.getNumberOfItems(), (item.getVolume() * item.getNumberOfItems()), (item.getWeight() * item.getNumberOfItems())))
                );
                shippingContainers.add(new ShippingContainer(bigContainer.getName(), totalVolume, totalWeight, Constants.BIG_CONTAINER_COST, shippingItems));
            }

        } else {
            items.forEach(item ->
                    shippingItems.add(new ShippingItem(item.getName(), item.getNumberOfItems(), (item.getVolume() * item.getNumberOfItems()), (item.getWeight() * item.getNumberOfItems())))
            );
            if (totalWeight > Constants.SMALL_CONTAINER_WEIGHT_SLAB) {
                shippingContainers.add(new ShippingContainer(smallContainer.getName(), totalVolume, totalWeight, Constants.SMALL_CONTAINER_COST_ABOVE_SLAB, shippingItems));
            } else {
                shippingContainers.add(new ShippingContainer(smallContainer.getName(), totalVolume, totalWeight, Constants.SMALL_CONTAINER_WEIGHT_FOR_SLAB, shippingItems));
            }
        }


    }

    private static void splitShipmentToMultipleContainers(List<Item> items, Map<Product, Integer> itemCount, List<ShippingContainer> shippingContainers) {

        List<ShippingItem> shippingItems = new ArrayList<>();
        double shippingVolume = 0;
        double shippingWeight = 0;
        Container bigContainer = new Container(ContainerType.BIG_CONTAINER);
        double bigContainerVolume = bigContainer.getVolume();
        for (Item item : items) {
            Product product = Product.findByName(item.getName());
            int count = itemCount.get(product);
            if ((item.getVolume() * count) > bigContainerVolume) {
                if (item.getVolume() < bigContainerVolume) {
                    int packageCount = 1;
                    while (item.getVolume() <= bigContainerVolume) {
                        bigContainerVolume = bigContainerVolume - item.getVolume();
                        packageCount++;
                        shippingVolume = shippingVolume + item.getVolume();
                        shippingWeight = shippingWeight + item.getWeight();
                    }
                    shippingItems.add(new ShippingItem(item.getName(), packageCount, (item.getVolume() * packageCount), (item.getWeight() * packageCount)));
                    itemCount.put(product, (itemCount.get(product) - packageCount));
                    shippingContainers.add(new ShippingContainer(bigContainer.getName(), shippingVolume, shippingWeight, Constants.BIG_CONTAINER_COST, shippingItems));
                    findSuitableContainers(itemCount, shippingContainers);
                    break;

                } else {
                    shippingContainers.add(new ShippingContainer(bigContainer.getName(), shippingVolume, shippingWeight, Constants.BIG_CONTAINER_COST, shippingItems));
                    findSuitableContainers(itemCount, shippingContainers);
                    break;
                }

            } else {
                shippingItems.add(new ShippingItem(item.getName(), item.getNumberOfItems(), (item.getVolume() * item.getNumberOfItems()), (item.getWeight() * item.getNumberOfItems())));
                bigContainerVolume = bigContainerVolume - (item.getVolume() * item.getNumberOfItems());
                itemCount.put(product, 0);

                shippingVolume = shippingVolume + (item.getVolume() * item.getNumberOfItems());
                shippingWeight = shippingWeight + (item.getWeight() * item.getNumberOfItems());
            }

        }


    }
}
