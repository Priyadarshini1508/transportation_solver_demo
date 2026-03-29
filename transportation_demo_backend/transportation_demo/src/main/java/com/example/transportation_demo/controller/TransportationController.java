package com.example.transportation_demo.controller; // Updated package name

import com.example.transportation_demo.model.TransportationRequest;
import com.example.transportation_demo.model.TransportationResponse;
import com.example.transportation_demo.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transportation")
@CrossOrigin(origins = "*")
public class TransportationController {

    @Autowired
    private TransportationService service;

    /**
     * TEST ENDPOINT
     */
    @GetMapping("/test")
    public String test() {
        return "The Receptionist is at her desk! Backend is ready.";
    }

    /**
     * MAIN CALCULATION ENDPOINT
     * Now only handles North-West and Least Cost
     */
    @PostMapping("/calculate")
    public TransportationResponse calculate(@RequestBody TransportationRequest request) {
        double[][] allocation;
        List<String> steps = new ArrayList<>();
        // Default to NW if method is null
        String method = (request.getMethod() != null) ? request.getMethod().toUpperCase() : "NW";

        // Removed Vogel's logic
        if (method.contains("LEAST")) {
            allocation = service.calculateLeastCost(
                    request.getSuppliers(),
                    request.getCustomers(),
                    request.getCosts(),
                    request.getSupply(),
                    request.getDemand()
            );
        } else {
            // Default is North-West
            allocation = service.calculateNorthWest(
                    request.getSuppliers(),
                    request.getCustomers(),
                    request.getSupply(),
                    request.getDemand()
            );
        }

        double finalCost = service.calculateTotalCost(allocation, request.getCosts());

        return new TransportationResponse(allocation, finalCost, steps);
    }
}