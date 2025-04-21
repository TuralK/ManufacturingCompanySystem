// ReportGenerator.java
package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Formats and prints the final manufacturing report.
 */
public class ReportGenerator {

    private final List<ManufacturingProcess> processes;

    public ReportGenerator(List<ManufacturingProcess> processes) {
        this.processes = processes;
    }

    public void printReport() {
        Map<String, Integer> success = new HashMap<>();
        Map<String, Double> cost = new HashMap<>();
        Map<String, Double> weight = new HashMap<>();
        int sysErr = 0, dmg = 0, stk = 0;

        for (ManufacturingProcess p : processes) {
            String name = p.getProduct().getName();
            String state = p.getStateName();
            if (state.equals("Completed")) {
                success.merge(name, 1, Integer::sum);
                cost.merge(name, p.getProduct().getTotalCost(), Double::sum);
                weight.merge(name, p.getProduct().getTotalWeight(), Double::sum);
            } else {
                switch (p.getFailureType()) {
                    case SYSTEM_ERROR:
                        sysErr++;
                        break;
                    case DAMAGED_COMPONENT:
                        dmg++;
                        break;
                    case STOCK_SHORTAGE:
                        stk++;
                        break;
                }
            }
        }

        System.out.println("Manufactured Products:");
        success.forEach((prod, cnt)
                -> System.out.printf(" - %-12s: %2d units | Cost: %8.2f | Weight: %7.2f%n",
                        prod, cnt, cost.get(prod), weight.get(prod)));

        System.out.printf("Failures due to %-18s %d%n", "System Error:", sysErr);
        System.out.printf("Failures due to %-18s %d%n", "Damaged Component:", dmg);
        System.out.printf("Failures due to %-18s %d%n", "Stock Shortage:", stk);
    }
}
