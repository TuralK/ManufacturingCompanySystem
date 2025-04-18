// ReportGenerator.java
package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Formats and prints the manufacturing report.
 */
public class ReportGenerator {
    private final List<ManufacturingProcess> processes;

    public ReportGenerator(List<ManufacturingProcess> processes) {
        this.processes = processes;
    }

    public void printReport() {
        Map<String,Integer> success = new HashMap<>();
        Map<String,Double> cost = new HashMap<>();
        Map<String,Double> weight = new HashMap<>();
        int sysErr = 0, damaged = 0, shortage = 0;

        for (ManufacturingProcess p : processes) {
            String name = p.getProduct().getName();
            String state = p.getStateName();
            if (state.startsWith("Completed")) {
                success.merge(name, 1, Integer::sum);
                cost   .merge(name, p.getProduct().getTotalCost(),    Double::sum);
                weight .merge(name, p.getProduct().getTotalWeight(),  Double::sum);
            } else if (state.contains("System Error")) {
                sysErr++;
            } else if (state.contains("Damaged Component")) {
                damaged++;
            } else if (state.contains("Stock Shortage")) {
                shortage++;
            }
        }

        System.out.println("Manufactured Products:");
        success.forEach((prod, cnt) ->
            System.out.printf(" - %s: %d units | Cost: %.2f | Weight: %.2f%n",
                prod, cnt, cost.get(prod), weight.get(prod)));

        System.out.println("Failures due to System Error: "    + sysErr);
        System.out.println("Failures due to Damaged Component: " + damaged);
        System.out.println("Failures due to Stock Shortage: "   + shortage);
    }
}
