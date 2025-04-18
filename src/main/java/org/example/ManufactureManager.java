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
        processes.forEach(p ->
            System.out.println("Product: " + p.getProduct().getName()
                + " | State: " + p.getStateName()));
    }

    public List<ManufacturingProcess> getProcesses() {
        return Collections.unmodifiableList(processes);
    }
}
