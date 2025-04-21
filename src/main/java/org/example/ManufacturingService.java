package org.example;
import java.util.*;

/**
 * A service class responsible for processing the manufacturing processes
 * of a list of products using a {@link ManufactureManager}.
 * <p>
 * This class manages the lifecycle of each {@link ManufacturingProcess},
 * tracks progress, and ensures the correct number of products are manufactured.
 * </p>
 */
public class ManufacturingService {
    private final ManufactureManager manager = new ManufactureManager();

    /**
     * Returns the {@link ManufactureManager} instance used by this service.
     *
     * @return the manufacture manager managing all manufacturing processes
     */
    public ManufactureManager getManager() {
        return manager;
    }
    
    /**
     * Initiates the manufacturing process for each product in the given list.
     * <p>
     * Each product is processed unit-by-unit until its original quantity is fulfilled.
     * The method creates and processes a {@link ManufacturingProcess} for each unit,
     * updates the manager with each process, processes each process with the manager
     * and finally returns the full list of processes.
     * </p>
     *
     * @param products the list of products to be manufactured
     * @return a list of {@link ManufacturingProcess} instances representing all manufacturing work
     */
    public List<ManufacturingProcess> manufacture(List<Product> products) {
        
        // Create a map to track how many units remain to be produced for each product
        Map<Product, Double> remaining = new LinkedHashMap<>();
        for (Product p : products) {
            remaining.put(p, (double) p.getQuantity());
            p.setQuantity(0); // Reset quantity for tracking during manufacturing
        }

        // Iteratively process units of products until all processes are finished
        boolean workLeft;
        do {
            workLeft = false;
            for (Product prod : products) {
                double left = remaining.get(prod);
                if (left > 0) {
                    ManufacturingProcess proc = new ManufacturingProcess(prod);
                    proc.processManufacturing();
                    manager.addProcess(proc);
                    remaining.put(prod, left - 1);
                    workLeft = true;
                }
            }
        } while (workLeft);

        return manager.getProcesses();
    }
}
