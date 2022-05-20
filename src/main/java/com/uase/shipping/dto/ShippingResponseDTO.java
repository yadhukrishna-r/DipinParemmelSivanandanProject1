package com.uase.shipping.dto;

import com.uase.shipping.containers.ShippingContainer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShippingResponseDTO {
    private List<ShippingContainer> shippingContainers;

    public ShippingResponseDTO(List<ShippingContainer> shippingContainers) {
        this.shippingContainers = shippingContainers;
    }

    public ShippingResponseDTO() {
    }
}
