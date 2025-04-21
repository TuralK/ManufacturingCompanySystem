package org.example;
import java.util.*;

public class ManufacturingService {
    private final Inventory inventory;
    private final ManufactureManager manager = new ManufactureManager();

    public ManufacturingService(Inventory inventory) {
        this.inventory = inventory;
    }

    public ManufactureManager getManager() {
        return manager;
    }
    
    public List<ManufacturingProcess> manufacture(List<Product> products) {
        
        Map<Product, Double> remaining = new LinkedHashMap<>();
        for (Product p : products) {
            remaining.put(p, (double) p.getQuantity());
            p.setQuantity(0);  
        }

        // 2. Döngü
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
