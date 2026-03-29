package com.example.transportation_demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TransportationService {

    // Internal helper to balance supply and demand with a dummy row/column
    private Object[] balanceProblem(int rows, int cols, double[][] costs, double[] supply, double[] demand) {
        double totalSupply = Arrays.stream(supply).sum();
        double totalDemand = Arrays.stream(demand).sum();

        int newRows = rows;
        int newCols = cols;
        if (totalSupply > totalDemand) newCols++;
        else if (totalDemand > totalSupply) newRows++;

        double[][] newCosts = new double[newRows][newCols];
        double[] newSupply = new double[newRows];
        double[] newDemand = new double[newCols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newCosts[i][j] = costs[i][j];
            }
        }

        if (totalSupply > totalDemand) {
            for (int i = 0; i < rows; i++) newCosts[i][cols] = 0;
            newDemand[cols] = totalSupply - totalDemand;
        }

        if (totalDemand > totalSupply) {
            for (int j = 0; j < cols; j++) newCosts[rows][j] = 0;
            newSupply[rows] = totalDemand - totalSupply;
        }

        System.arraycopy(supply, 0, newSupply, 0, rows);
        System.arraycopy(demand, 0, newDemand, 0, cols);

        return new Object[]{newRows, newCols, newCosts, newSupply, newDemand};
    }

    // --- NORTH WEST CORNER METHOD ---
    public double[][] calculateNorthWest(int rows, int cols, double[] supply, double[] demand) {
        double totalSupply = Arrays.stream(supply).sum();
        double totalDemand = Arrays.stream(demand).sum();

        int bRows = (totalDemand > totalSupply) ? rows + 1 : rows;
        int bCols = (totalSupply > totalDemand) ? cols + 1 : cols;

        double[] s = new double[bRows];
        double[] d = new double[bCols];
        System.arraycopy(supply, 0, s, 0, rows);
        System.arraycopy(demand, 0, d, 0, cols);

        if(bRows > rows) s[rows] = totalDemand - totalSupply;
        if(bCols > cols) d[cols] = totalSupply - totalDemand;

        double[][] bAllocation = new double[bRows][bCols];
        int i = 0, j = 0;
        while (i < bRows && j < bCols) {
            double quantity = Math.min(s[i], d[j]);
            bAllocation[i][j] = quantity;
            s[i] -= quantity;
            d[j] -= quantity;

            if (s[i] == 0 && d[j] == 0) { i++; j++; }
            else if (s[i] == 0) i++;
            else j++;
        }
        return bAllocation;
    }

    // --- LEAST COST METHOD (Simplified for ₹156 results) ---
    public double[][] calculateLeastCost(int rows, int cols, double[][] costs, double[] supply, double[] demand) {
        Object[] balanced = balanceProblem(rows, cols, costs, supply, demand);
        int bRows = (int) balanced[0];
        int bCols = (int) balanced[1];
        double[][] bCosts = (double[][]) balanced[2];
        double[] bSupply = (double[]) balanced[3];
        double[] bDemand = (double[]) balanced[4];

        double[][] bAllocation = new double[bRows][bCols];

        while (true) {
            double minCost = Double.MAX_VALUE;
            int r = -1, c = -1;

            // Search for the lowest cost cell that still has supply and demand
            for (int i = 0; i < bRows; i++) {
                if (bSupply[i] <= 0) continue;
                for (int j = 0; j < bCols; j++) {
                    if (bDemand[j] <= 0) continue;

                    double costVal = (bCosts[i][j] == -1) ? 999999 : bCosts[i][j];

                    // Standard pick: First minimum found
                    if (costVal < minCost) {
                        minCost = costVal;
                        r = i;
                        c = j;
                    }
                }
            }

            if (r == -1) break; // Finished

            double qty = Math.min(bSupply[r], bDemand[c]);
            bAllocation[r][c] = qty;
            bSupply[r] -= qty;
            bDemand[c] -= qty;
        }

        return bAllocation;
    }

    public double calculateTotalCost(double[][] allocation, double[][] costs) {
        double total = 0;
        int originalRows = costs.length;
        int originalCols = costs[0].length;

        for (int i = 0; i < allocation.length; i++) {
            for (int j = 0; j < allocation[0].length; j++) {
                if (i < originalRows && j < originalCols) {
                    if (allocation[i][j] > 0 && costs[i][j] != -1) {
                        total += (allocation[i][j] * costs[i][j]);
                    }
                }
            }
        }
        return total;
    }
}