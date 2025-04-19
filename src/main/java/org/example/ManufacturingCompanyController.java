package org.example;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Controls the setup and execution of the manufacturing system for the company.
 * <p>
 * The {@code ManufacturingCompanyController} is responsible for loading
 * components and products from CSV files initializes the {@link Inventory}
 * singleton manages the manufacturing of products using a round-robin strategy,
 * and prints the inventory components, manufacturing process details and a final report.
 * </p>
 */
public class ManufacturingCompanyController {
    private Inventory inventory = Inventory.getInstance();
    private ManufactureManager manager = new ManufactureManager();
    private List<Product> products;

    /**
     * Constructs the controller by loading component and 
     * product data (with a component lookup table) from CSV files
     * and initializing the inventory singleton with the loaded components.
     */
    ManufacturingCompanyController() {
        List<BasicComponent> components = CSVLoader.loadComponents("components.csv");
        components.forEach(inventory::addComponent);

        Map<String, Component> componentLookup = new HashMap<>();
        for (BasicComponent comp : components) {
            componentLookup.put(comp.getName(), comp);
        }
        products = CSVLoader.loadProducts("products.csv", componentLookup);
    }

    /**
     * Executes the manufacturing process using a round-robin strategy.
     * <p>
     * For each product, this method creates and runs manufacturing processes
     * one unit at a time in a loop until all product quantities are produced.
     * Each process is tracked and managed by the {@link ManufactureManager}.
     * </p>
     */
    public void runManufacturingProcesses() {
        Map<Product, Double> remaining = new LinkedHashMap<>();
        products.forEach(p -> remaining.put(p, (double)p.getQuantity()));
        products.forEach(p -> p.setQuantity(0));

        boolean done = false;
        while (!done) {
            done = true;
            for (Product prod : products) {
                double left = remaining.get(prod);
                if (left > 0) {
                    ManufacturingProcess proc = new ManufacturingProcess(prod);
                    proc.processManufacturing();
                    manager.addProcess(proc);
                    remaining.put(prod, left - 1);
                    done = false;
                }
            }
        }
    }

    /**
     * Prints the final results after all manufacturing processes have been completed.
     * <p>
     * This includes:
     * <ul>
     *   <li>Details of Inventory components</li>
     *   <li>Details of each product manufacturing process</li>
     *   <li>A final report summarizing production successes and failures</li>
     * </ul>
     * </p>
     */
    public void printResults() {
        inventory.printInventory();
        manager.printProcessDetails();

        System.out.println("\n=== FINAL REPORT ===");
        new ReportGenerator(manager.getProcesses()).printReport();
    }
}
