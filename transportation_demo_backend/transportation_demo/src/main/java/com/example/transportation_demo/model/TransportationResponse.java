package com.example.transportation_demo.model; // Updated package name

import java.util.List;

public class TransportationResponse {
    private double[][] allocation;
    private double totalCost;
    private List<String> steps;

    // Default constructor for JSON serialization
    public TransportationResponse() {}

    // Main constructor
    public TransportationResponse(double[][] allocation, double totalCost, List<String> steps) {
        this.allocation = allocation;
        this.totalCost = totalCost;
        this.steps = steps;
    }

    // Getters and Setters
    public double[][] getAllocation() { return allocation; }
    public void setAllocation(double[][] allocation) { this.allocation = allocation; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public List<String> getSteps() { return steps; }
    public void setSteps(List<String> steps) { this.steps = steps; }
}