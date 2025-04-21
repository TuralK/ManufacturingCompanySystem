package org.example;

import java.util.Map;

/**
 * Represents a manufactured product in the manufacturing system
 * composed of multiple basic components.
 * <p>
 * This class is a composite in the Composite Design Pattern and implements
 * the {@link Component} interface. It maintains a map of components
 * to the amount needed for one unit of the product.
 * </p>
 * <p>
 * The cost and weight of the product are computed dynamically by aggregating
 * the properties of its components.
 * </p>
 */
public class Product implements Component {

    private final String name;
    // Mapping of component name (to fetch from Inventory) and required quantity per one unit of product.
    private final Map<Component, Double> requirements;
    private double quantity; // number of this product to manufacture

    /**
     * Constructs a new Product with the specified name, required components,
     * and quantity to manufacture.
     *
     * @param name         the name of the product
     * @param requirements a map of components to their required quantities per unit
     * @param quantity     the quantity of the product to manufacture
     */
    public Product(String name, Map<Component, Double> requirements, int quantity) {
        this.name = name;
        this.requirements = requirements;
        this.quantity = quantity;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the component requirements for one unit of this product.
     *
     * @return a map of components to required quantities
     */
    public Map<Component, Double> getRequirements() {
        return requirements;
    }

    /**
     * Returns the number of units of this product to manufacture.
     *
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the number of units of this product to manufacture.
     *
     * @param quantity the quantity of the product to manufacture
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the number of units of this product to manufacture by one.
     */
    public void increaseQuantity() {
        this.quantity += 1;
    }

    /**
     * Decreases the number of units of this product to manufacture by the specified amount.
     * 
     * @param quantityUsed the number of units to subtract from the quantity
     */
    @Override
    public void decreaseQuantity(double quantityUsed) {
        this.quantity -= quantityUsed;
    }

    /**
     * Computes and returns the total cost of one unit of this product
     * by summing the unit costs of all required components multiplied
     * by their required quantities.
     *
     * @return the total cost per unit
     */
    @Override
    public double getTotalCost() {
        double total = 0.0;
        for (Map.Entry<Component, Double> entry : requirements.entrySet()) {
            Component component = entry.getKey();
            total += component.getTotalCost() * entry.getValue();
        }
        return total;
    }

    /**
     * Computes and returns the total weight of one unit of this product
     * by summing the unit weights of all required components multiplied
     * by their required quantities.
     *
     * @return the total weight per unit
     */
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

    /**
     * Prints detailed information about the product, including its name,
     * quantity to manufacture, unit cost and unit weight.
     */
    @Override
    public void printDetail() {
        System.out.printf("Product: %-28s | Unit Cost: %9.1f | Unit Weight: %6.2f | Quantity : %7.1f%n",
                name, getTotalCost(), getTotalWeight(), quantity);
    }

    /**
     * Returns a formatted string representation of the product which has the product name
     * quantity to manufacture, required components and their quantities,
     * total cost of manufacturing per unit and total weight per unit.
     *
     * @return a string summarizing the product's manufacturing details
     */
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
