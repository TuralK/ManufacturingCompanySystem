/**
 * src/main/java/org/example/ManufacturingCompanyController.java
 */
package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Acts as the central orchestration component for the manufacturing application.
 * 
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Load raw materials and product definitions from CSV files.</li>
 *   <li>Initialize inventory with loaded components.</li>
 *   <li>Delegate manufacturing execution to ManufacturingService.</li>
 *   <li>Delegate reporting to ReportService.</li>
 * </ul>
 * </p>
 * 
 * This class adheres to the Single Responsibility Principle by focusing solely on
 * application flow and not on direct I/O or business logic implementation.
 */
public class ManufacturingCompanyController {
    private final CSVDataLoader loader;
    private final Inventory inventory;
    private final List<Product> products;
    private final ManufacturingService service;
    private final ReportService reporter;

    /**
     * Constructs the controller by loading data and preparing services.
     *
     * @param componentsCsv the filename (relative to resources) for component definitions
     * @param productsCsv   the filename (relative to resources) for product definitions
     */
    public ManufacturingCompanyController(String componentsCsv, String productsCsv) {
        // Initialize CSV loader with file names
        this.loader    = new CSVDataLoader(componentsCsv, productsCsv);
        // Retrieve the singleton inventory instance
        this.inventory = Inventory.getInstance();

        // Load basic components and register them into inventory
        List<BasicComponent> comps = loader.loadComponents();
        comps.forEach(inventory::addComponent);

        // Build a lookup table for component name to Component instance
        Map<String, Component> lookup = comps.stream()
            .collect(Collectors.toMap(Component::getName, c -> c));
        // Load products using the lookup for required components
        this.products = loader.loadProducts(lookup);

        // Initialize service and report handlers
        this.service   = new ManufacturingService(inventory);
        this.reporter  = new ReportService(inventory);
    }

    /**
     * Executes the end-to-end manufacturing workflow:
     * <ol>
     *   <li>Run production on each product.</li>
     *   <li>Print inventory details and process states.</li>
     *   <li>Print final summary report.</li>
     * </ol>
     */
    public void run() {
        // Execute manufacturing processes for all products
        List<ManufacturingProcess> processes = service.manufacture(products);

        // Display current inventory levels and manufacturing states
        reporter.printProcessDetails(service.getManager());

        // Display a consolidated final report of successes and failures
        reporter.printFinalReport(processes);
    }
}