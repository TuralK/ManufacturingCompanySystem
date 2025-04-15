package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ManufactureManager acts as the controller, iterating over manufacturing processes
 * and generating the final report. This follows GRASP's Controller and Low Coupling principles.
 */
class ManufactureManager {
    private List<ManufacturingProcess> processes;

    public ManufactureManager() {
        processes = new ArrayList<>();
    }

    public void addProcess(ManufacturingProcess process) {
        processes.add(process);
    }

    // Print process details including product name and manufacturing state.
    public void printProcessDetails() {
        for (ManufacturingProcess process : processes) {
            System.out.println("Product: " + process.getProduct().getName()
                    + " | State: " + process.getStateName());
        }
    }

    // Generate and print report.
    public void printReport() {
        // Using counters for each outcome.
        Map<String, Integer> successCount = new HashMap<>();

        // For successful manufacturing, also compute total cost and weight.
        Map<String, Double> totalCost = new HashMap<>();
        Map<String, Double> totalWeight = new HashMap<>();

        // Failure types: System Error, Damaged Component, Stock Shortage.
        int systemErrorFailures = 0;
        int damagedFailures = 0;
        int stockShortageFailures = 0;

        for (ManufacturingProcess process : processes) {
            String prodName = process.getProduct().getName();
            String stateName = process.getStateName();
            if (stateName.startsWith("Completed")) {
                successCount.put(prodName, successCount.getOrDefault(prodName, 0) + 1);
                totalCost.put(prodName, totalCost.getOrDefault(prodName, 0.0) + process.getProduct().getTotalCost());
                totalWeight.put(prodName, totalWeight.getOrDefault(prodName, 0.0) + process.getProduct().getTotalWeight());
            } else if (stateName.startsWith("Failed")) {
                if (stateName.contains("System Error")) {
                    systemErrorFailures++;
                } else if (stateName.contains("Damaged Component")) {
                    damagedFailures++;
                } else if (stateName.contains("Stock Shortage")) {
                    stockShortageFailures++;
                }
            }
        }
        System.out.println("Manufactured Products:");
        for (String prodName : successCount.keySet()) {
            System.out.println(" - " + prodName + ": Successfully manufactured "
                    + successCount.get(prodName) + " units | Total Cost: " + totalCost.get(prodName)
                    + " | Total Weight: " + totalWeight.get(prodName));
        }
        System.out.println("Failures due to System Error: " + systemErrorFailures);
        System.out.println("Failures due to Damaged Component: " + damagedFailures);
        System.out.println("Failures due to Stock Shortage: " + stockShortageFailures);
    }
}
