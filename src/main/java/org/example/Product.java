package org.example;

import java.util.Map;

/**
 * Product class (composite) represents a manufactured product made up of basic
 * components. It uses a Map to store required components and their amounts.
 */
public class Product implements Component {

    private final String name;
    // Mapping of component name (to fetch from Inventory) and required quantity per one unit of product.
    private final Map<Component, Double> requirements;
    private double quantity; // number of this product to manufacture

    public Product(String name, Map<Component, Double> requirements, int quantity) {
        this.name = name;
        this.requirements = requirements;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<Component, Double> getRequirements() {
        return requirements;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity += 1;
    }

    @Override
    public void decreaseQuantity(double quantityUsed) {
        this.quantity -= quantityUsed;
    }

    // Compute the total cost for one unit (summing over components).
    @Override
    public double getTotalCost() {
        double total = 0.0;
        for (Map.Entry<Component, Double> entry : requirements.entrySet()) {
            Component component = entry.getKey();
            total += component.getTotalCost() * entry.getValue();
        }
        return total;
    }

    // Compute the total weight for one unit (summing over components).
    @Override
    public double getTotalWeight() {
        double total = 0.0;
        for (Map.Entry<Component, Double> entry : requirements.entrySet()) {
            Component component = entry.getKey();
            if (component != null) {
                total += component.getTotalWeight() * entry.getValue();
            }
        }
        return total;
    }

    @Override
    public void printDetail() {
        System.out.printf("Product: %-28s | Unit Cost: %9.1f | Unit Weight: %6.2f | Quantity : %7.1f%n",
                name, getTotalCost(), getTotalWeight(), quantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product: ")
                .append(name)
                .append(" | Quantity to Manufacture: ")
                .append(quantity)
                .append("\nRequirements:\n");

        for (Map.Entry<Component, Double> entry : requirements.entrySet()) {
            sb.append("  - ")
                    .append(entry.getKey().getName())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }

        sb.append("Total Cost (per unit): ")
                .append(getTotalCost())
                .append(" | Total Weight (per unit): ")
                .append(getTotalWeight());

        return sb.toString();
    }

}
