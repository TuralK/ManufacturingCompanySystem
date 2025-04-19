package org.example;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * ManufacturingCompanyController is the controller for 
 * loading component/product, managing the manufacturing process and
 * printing the inventory and manufacturing status within a company.
 */
public class ManufacturingCompanyController {
    private Inventory inventory = Inventory.getInstance();
    private ManufactureManager manager = new ManufactureManager();
    private List<Product> products;

    ManufacturingCompanyController() {
        List<BasicComponent> components = CSVLoader.loadComponents("components.csv");
        components.forEach(inventory::addComponent);
        products = CSVLoader.loadProducts("products.csv");
    }

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

    public void printResults() {
        System.out.println("=== COMPONENT DETAILS AND MANUFACTURING STATES ===");
        inventory.printInventory();
        manager.printProcessDetails();

        System.out.println("\n=== FINAL REPORT ===");
        new ReportGenerator(manager.getProcesses()).printReport();
    }
}
