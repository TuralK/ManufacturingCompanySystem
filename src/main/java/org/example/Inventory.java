package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an inventory of {@link Component} objects and is implemented as a singleton.
 * <p>
 * The {@link Inventory} class provides methods for adding components, 
 * retrieving components by name, removing component stock, and printing the details of 
 * all components in the inventory.
 * </p>
 */
public class Inventory {
    private static Inventory instance = new Inventory();
    // Map of component name to BasicComponent.
    private Map<String, Component> components;

    
    private Inventory() {
        components = new HashMap<>();
    }

    /**
     * Retrieves the singleton instance of the {@link Inventory}.
     *
     * @return the single {@link Inventory} instance
     */
    public static Inventory getInstance() {
        return instance;
    }

    /**
     * Adds a new {@link Component} to the inventory.
     *
     * @param component the {@link Component} to add to the inventory
     */
    public void addComponent(Component component) {
        components.put(component.getName(), component);
    }

    /**
     * Retrieves a {@link Component} by its name.
     *
     * @param name the name of the component to retrieve
     * @return the {@link Component} associated with the given name, or null if not found
     */
    public Component getComponent(String name) {
        return components.get(name);
    }

     /**
     * Removes some given quantity of a component from the stock.
     *
     * @param name          the name of the component to update
     * @param quantityUsed  the quantity to deduct from the component's stock
     */
    public void removeStock(String name, double quantityUsed) {
        Component component = components.get(name);
        if (component != null) {
            component.decreaseQuantity(quantityUsed);
        }
    }

     /**
     * Prints the details of all components in the inventory.
     * <p>
     * This depends on the printDetail implementation of each component.
     * </p>
     */
    public void printInventory() {
        System.out.println("\n=== INVENTORY COMPONENT DETAILS ===");
        for (Component comp : components.values()) {
            comp.printDetail();
        }
    }
}
