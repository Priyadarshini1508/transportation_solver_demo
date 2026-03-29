package com.example.transportation_demo.model; // Updated package name

public class TransportationRequest {
    private int suppliers;
    private int customers;
    private double[][] costs;
    private double[] supply;
    private double[] demand;
    private String method;

    // Default constructor for Spring Boot JSON parsing
    public TransportationRequest() {}

    // Standard Getters and Setters
    public int getSuppliers() { return suppliers; }
    public void setSuppliers(int suppliers) { this.suppliers = suppliers; }

    public int getCustomers() { return customers; }
    public void setCustomers(int customers) { this.customers = customers; }

    public double[][] getCosts() { return costs; }
    public void setCosts(double[][] costs) { this.costs = costs; }

    public double[] getSupply() { return supply; }
    public void setSupply(double[] supply) { this.supply = supply; }

    public double[] getDemand() { return demand; }
    public void setDemand(double[] demand) { this.demand = demand; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}