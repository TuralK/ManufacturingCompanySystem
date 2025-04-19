package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates and prints a summary report for a list of {@link ManufacturingProcess} instances.
 * The report includes successfully manufactured products with their total quantities, costs, 
 * and weights and the number of failed processes grouped by failure type.
 */
public class ReportGenerator {
    private final List<ManufacturingProcess> processes;

    /**
     * Constructs a new {@link ReportGenerator} with a list of manufacturing processes.
     *
     * @param processes the list of {@link ManufacturingProcess} instances to summarize
     */
    public ReportGenerator(List<ManufacturingProcess> processes) {
        this.processes = processes;
    }

    /**
     * Analyzes the manufacturing processes and prints a summary report that includes:
     * <ul>
     *   <li>The number of units successfully manufactured per product with their
     *       total cost and total weight</li>
     *   <li>A count of failures grouped by their {@link FailureType}:
     *     system errors, damaged components, and stock shortages</li>
     * </ul>
     */
    public void printReport() {
        Map<String, Integer> success = new HashMap<>();
        Map<String, Double> cost    = new HashMap<>();
        Map<String, Double> weight  = new HashMap<>();
        int sysErr = 0, dmg = 0, stk = 0;

        for (ManufacturingProcess p : processes) {
            String name  = p.getProduct().getName();
            String state = p.getStateName();
            if (state.equals("Completed")) {
                success.merge(name, 1, Integer::sum);
                cost.merge(name, p.getProduct().getTotalCost(), Double::sum);
                weight.merge(name, p.getProduct().getTotalWeight(), Double::sum);
            } else {
                switch (p.getFailureType()) {
                    case SYSTEM_ERROR:      sysErr++; break;
                    case DAMAGED_COMPONENT: dmg++;    break;
                    case STOCK_SHORTAGE:    stk++;    break;
                }
            }
        }

        System.out.println("Manufactured Products:");
        success.forEach((prod, cnt) ->
            System.out.printf(" - %s: %d units | Cost: %.2f | Weight: %.2f%n",
                prod, cnt, cost.get(prod), weight.get(prod)));

        System.out.println("Failures due to System Error: "      + sysErr);
        System.out.println("Failures due to Damaged Component: " + dmg);
        System.out.println("Failures due to Stock Shortage: "    + stk);
    }
}
