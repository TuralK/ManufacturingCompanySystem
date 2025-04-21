package org.example;

import java.util.List;

public class ReportService {
    private final Inventory inventory;

    public ReportService(Inventory inventory) {
        this.inventory = inventory;
    }

    
    public void printProcessDetails(ManufactureManager manager) {
        inventory.printInventory();
        manager.printProcessDetails();
    }

    
    public void printFinalReport(List<ManufacturingProcess> processes) {
        System.out.println("\n=== FINAL REPORT ===");
        new ReportGenerator(processes).printReport();
    }
}

