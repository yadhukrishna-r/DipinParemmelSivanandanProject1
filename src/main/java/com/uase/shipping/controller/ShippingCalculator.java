package com.uase.shipping.controller;

import com.uase.shipping.dto.ShippingRequestDTO;
import com.uase.shipping.dto.ShippingResponseDTO;
import com.uase.shipping.method.Calcutator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/shipping")
public class ShippingCalculator {

    @Autowired
    Calcutator calcutator;

    @PostMapping(value = "/calculate")
    public ResponseEntity<ShippingResponseDTO> shippingCostCalculator(@RequestBody ShippingRequestDTO request) {
        ResponseEntity<ShippingResponseDTO> response = calcutator.findSuitableShipment(request);
        return response;
    }

}
