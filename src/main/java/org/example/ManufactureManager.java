// ManufactureManager.java
package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for manufacturing processes.
 */
public class ManufactureManager {
    private final List<ManufacturingProcess> processes = new ArrayList<>();

    public void addProcess(ManufacturingProcess p) {
        processes.add(p);
    }

    public void printProcessDetails() {
        System.out.println("\n=== PRODUCT MANUFACTURING STATES ===");
        processes.forEach(p ->
            System.out.printf("Product: %-15s | State: %-25s%n", 
                             p.getProduct().getName(), p.getStateName()));
    }

    public List<ManufacturingProcess> getProcesses() {
        return Collections.unmodifiableList(processes);
    }
}
