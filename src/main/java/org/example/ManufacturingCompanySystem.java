package org.example;

import java.util.*;

// ================================================================
// UML CLASS DIAGRAM (Description)
// ---------------------------------------------------------------
// Component (interface)
//    + getTotalCost(): double
//    + getTotalWeight(): double
//    + printDetail(): void
//
// BasicComponent implements Component
//    - name: String
//    - unitCost: double
//    - unitWeight: double
//    - type: String
//    - stockQuantity: double
//    + getTotalCost() [returns unitCost]
//    + getTotalWeight() [returns unitWeight]
//    + updateStock(amount): void
//
// Product implements Component  (Composite pattern)
//    - name: String
//    - requirements: Map<BasicComponent, Double>   // required amount per product
//    + getTotalCost() [sums cost of its basic components times required quantities]
//    + getTotalWeight() [sums weights similarly]
//    + printDetail()
//    + getRequirements(): Map<BasicComponent, Double>
//
// ManufacturingState (interface)
//    + proceed(ManufacturingProcess process): void
//
// WaitingForStockState, InManufacturingState, CompletedState,
// FailedState  (implements ManufacturingState)
//
// ManufacturingProcess
//    - product: Product
//    - currentState: ManufacturingState
//    - failureReason: String
//    + processManufacturing(): void
//    + setState(ManufacturingState state): void
//    + getProduct(): Product
//    + setFailureReason(String reason): void
//
// Inventory (Singleton)
//    - instance: Inventory
//    - components: Map<String, BasicComponent>
//    + getComponent(String name): BasicComponent
//    + updateStock(String name, double quantityUsed): void
//    + getInstance(): Inventory
//
// ManufactureManager
//    - manufacturingProcesses: List<ManufacturingProcess>
//    + processAll(): void
//    + printReport(): void
//
// (Additional helper classes and methods for CSV data simulation and random manufacturing outcomes)
// ================================================================

public class ManufacturingCompanySystem  {

    public static void main(String[] args) {
        // Load components from CSV and add them to Inventory.
        List<BasicComponent> components = CSVLoader.loadComponents("components.csv");
        Inventory inventory = Inventory.getInstance();
        for (BasicComponent comp : components) {
            inventory.addComponent(comp);
        }

        // Load products from CSV.
        List<Product> productList = CSVLoader.loadProducts("products.csv");

        // Create a ManufactureManager to record manufacturing processes.
        ManufactureManager manager = new ManufactureManager();

        // Create a mapping to keep track of remaining units per product (preserving CSV order).
        Map<Product, Integer> remainingUnits = new LinkedHashMap<>();
        for (Product product : productList) {
            remainingUnits.put(product, product.getQuantity());
        }

        // Process manufacturing in a round-robin manner until all units are attempted.
        boolean allProductsCompleted = false;
        while (!allProductsCompleted) {
            allProductsCompleted = true;
            for (Product product : productList) {
                int remaining = remainingUnits.get(product);
                if (remaining > 0) {
                    // Attempt to manufacture one unit.
                    ManufacturingProcess process = new ManufacturingProcess(product);
                    process.processManufacturing();
                    manager.addProcess(process);

                    // Decrement the count for this product.
                    remainingUnits.put(product, remaining - 1);

                    // At least one unit was processed, so we're not done.
                    allProductsCompleted = false;
                }
            }
        }

        // Print component details and manufacturing states.
        System.out.println("=== COMPONENT DETAILS AND MANUFACTURING STATES ===");
        Inventory.getInstance().printInventory();
        manager.printProcessDetails();
        

        // Print the final report.
        System.out.println("\n=== FINAL REPORT ===");
        manager.printReport();
    }
}


