package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Inventory is implemented as a singleton. It holds a mapping of component names
 * to BasicComponent objects and handles stock updating.
 */
public class Inventory {
    private static Inventory instance = new Inventory();
    // Map of component name to BasicComponent.
    private Map<String, Component> components;

    private Inventory() {
        components = new HashMap<>();
    }

    public static Inventory getInstance() {
        return instance;
    }

    public void addComponent(Component component) {
        components.put(component.getName(), component);
    }

    public Component getComponent(String name) {
        return components.get(name);
    }

    // Update stock: deduct quantityUsed from the component stock.
    public void removeStock(String name, double quantityUsed) {
        Component component = components.get(name);
        if (component != null) {
            component.decreaseQuantity(quantityUsed);
        }
    }

    // Print details for all components.
    public void printInventory() {
        System.out.println("\n=== INVENTORY COMPONENT DETAILS ===");
        for (Component comp : components.values()) {
            comp.printDetail();
        }
    }
}
