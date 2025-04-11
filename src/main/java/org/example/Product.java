package org.example;

import java.util.Map;

/**
 * Product class (composite) represents a manufactured product made up
 * of basic components. It uses a Map to store required components and their amounts.
 */
class Product implements Component {
    private String name;
    // Mapping of component name (to fetch from Inventory) and required quantity per one unit of product.
    private Map<String, Double> requirements;
    private int quantity; // number of this product to manufacture

    public Product(String name, Map<String, Double> requirements, int quantity) {
        this.name = name;
        this.requirements = requirements;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getRequirements() {
        return requirements;
    }

    public int getQuantity() {
        return quantity;
    }

    // Compute the total cost for one unit (summing over components).
    @Override
    public double getTotalCost() {
        double total = 0.0;
        Inventory inventory = Inventory.getInstance();
        for (Map.Entry<String, Double> entry : requirements.entrySet()) {
            BasicComponent component = inventory.getComponent(entry.getKey());
            if (component != null) {
                total += component.getUnitCost() * entry.getValue();
            }
        }
        return total;
    }

    // Compute the total weight for one unit (summing over components).
    @Override
    public double getTotalWeight() {
        double total = 0.0;
        Inventory inventory = Inventory.getInstance();
        for (Map.Entry<String, Double> entry : requirements.entrySet()) {
            BasicComponent component = inventory.getComponent(entry.getKey());
            if (component != null) {
                total += component.getUnitWeight() * entry.getValue();
            }
        }
        return total;
    }

    @Override
    public void printDetail() {
        System.out.println("Product: " + name + " | Quantity to Manufacture: " + quantity);
        System.out.println("Requirements:");
        for (Map.Entry<String, Double> entry : requirements.entrySet()) {
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Total Cost (per unit): " + getTotalCost() + " | Total Weight (per unit): " + getTotalWeight());
    }
}
