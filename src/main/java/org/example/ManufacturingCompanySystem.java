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

// ManufacturingCompanySystem.java
public class ManufacturingCompanySystem {
    public static void main(String[] args) {
        ManufacturingCompanyController controller = new ManufacturingCompanyController();
        controller.runManufacturingProcesses();
        controller.printResults();
    }
}


