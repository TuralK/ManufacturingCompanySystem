package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Acts as the controller for the manufacturing company.
 * 
 * <p>
 * The responsibilities of the controller are:
 * <ul>
 *   <li>Load raw materials and product definitions from CSV files with {@link CSVDataLoader}.</li>
 *   <li>Initialize {@link Inventory} singleton with loaded components.</li>
 *   <li>Delegate manufacturing of all products to {@link ManufacturingService}.</li>
 *   <li>Delegate printing out inventory status and final report to {@link ReportService}.</li>
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
     * Constructs the controller by loading data from resource files and preparing all services.
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
        this.service   = new ManufacturingService();
        this.reporter  = new ReportService(inventory);
    }

    /**
     * Executes the end-to-end manufacturing workflow 
     * by running the manufacturing process on 
     * each product and printing inventory details,
     * process states and the final summary report.
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