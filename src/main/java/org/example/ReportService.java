package org.example;

import java.util.List;

/**
 * A service class responsible for generating and printing reports
 * related to the the inventory of components and final report of products manufactured.
 * <p>
 * This class provides reporting methods that delegate to
 * the appropriate inventory and final report generation.
 * </p>
 */
public class ReportService {
    private final Inventory inventory;

    /**
     * Constructs a new {@code ReportService} with the given inventory instance.
     *
     * @param inventory the {@link Inventory} singleton used to retrieve and print inventory data
     */
    public ReportService(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Prints the inventory component details and
     * detailed information about the product manufacturing processes.
     * <p>
     * This method first prints the inventory component details, 
     * then delegates to the provided {@link ManufactureManager} to 
     * print product manufacturing state information for each manufacturing process.
     * </p>
     *
     * @param manager the {@link ManufactureManager} responsible for managing and printing product manufacturing processes
     */
    public void printProcessDetails(ManufactureManager manager) {
        inventory.printInventory();
        manager.printProcessDetails();
    }

    /**
     * Prints a final report summarizing the provided manufacturing processes.
     * <p>
     * This method uses a {@link ReportGenerator} to format and print the final report.
     * </p>
     *
     * @param processes a list of {@link ManufacturingProcess} for the final report
     */
    public void printFinalReport(List<ManufacturingProcess> processes) {
        System.out.println("\n=== FINAL REPORT ===");
        new ReportGenerator(processes).printReport();
    }
}