package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Inventory is implemented as a singleton. It holds a mapping of component names
 * to BasicComponent objects and handles stock updating.
 */
class Inventory {
    private static Inventory instance = new Inventory();
    // Map of component name to BasicComponent.
    private Map<String, BasicComponent> components;

    private Inventory() {
        components = new HashMap<>();
    }

    public static Inventory getInstance() {
        return instance;
    }

    public void addComponent(BasicComponent component) {
        components.put(component.getName(), component);
    }

    public BasicComponent getComponent(String name) {
        return components.get(name);
    }

    // Update stock: deduct quantityUsed from the component stock.
    public void updateStock(String name, double quantityUsed) {
        BasicComponent component = components.get(name);
        if (component != null) {
            component.updateStock(quantityUsed);
        }
    }

    // Print details for all components.
    public void printInventory() {
        System.out.println("=== INVENTORY DETAILS ===");
        for (BasicComponent comp : components.values()) {
            comp.printDetail();
        }
    }
}
