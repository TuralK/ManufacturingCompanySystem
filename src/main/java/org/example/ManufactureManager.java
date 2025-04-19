// ManufactureManager.java
package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the manufacturing processes and can print out the states of all product manufacturing processes.
 * <p>
 * The {@link ManufactureManager} class follows the GRASP principles of Controller and Low Coupling patterns.
 * It acts as a controller over multiple manufacturing processes and provides an overview of the manufacturing results.
 * </p>
 */
public class ManufactureManager {
    private final List<ManufacturingProcess> processes = new ArrayList<>();

    /**
     * Adds a {@link ManufacturingProcess} to the manager's list.
     *
     * @param p the {@link ManufacturingProcess} to add
     */
    public void addProcess(ManufacturingProcess p) {
        processes.add(p);
    }

    /**
     * Returns an unmodifiable view of the list of manufacturing processes to ensure
     * the internal list isn't modified by external code.
     * </p>
     *
     * @return an unmodifiable list of {@link ManufacturingProcess} instances
     */
    public List<ManufacturingProcess> getProcesses() {
        return Collections.unmodifiableList(processes);
    }

    /**
     * Prints details of all products manufacturing states of all processes which are the product name and the state of the process.
     * The state indicates whether the process is in manufacturing, completed, or failed.
     */
    public void printProcessDetails() {
        System.out.println("\n=== PRODUCT MANUFACTURING STATES ===");
        processes.forEach(p ->
            System.out.println("Product: " + p.getProduct().getName()
                + " | State: " + p.getStateName()));
    }
}
