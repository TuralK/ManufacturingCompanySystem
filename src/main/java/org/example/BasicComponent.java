package org.example;

/**
 * Represents a basic component in the manufacturing system such as raw materials,
 * paint, or hardware.
 * <p>
 * This class is a leaf in the Composite Design Pattern and implements the
 * {@link Component} interface. It defines attributes such as cost, weight, type,
 * and available stock, and provides methods for accessing this data and updating stock levels.
 * </p>
 */
public class BasicComponent implements Component {

    private String name;
    private double unitCost;
    private double unitWeight;
    private String type;
    private double stockQuantity; // current stock

    /**
     * Constructs a new BasicComponent with the specified attributes.
     *
     * @param name          the name of the component
     * @param unitCost      the cost per unit
     * @param unitWeight    the weight per unit
     * @param type          the type/category of the component (raw material, paint, hardware)
     * @param stockQuantity the available stock quantity (can be different units)
     */
    public BasicComponent(String name, double unitCost, double unitWeight, String type, double stockQuantity) {
        this.name = name;
        this.unitCost = unitCost;
        this.unitWeight = unitWeight;
        this.type = type;
        this.stockQuantity = stockQuantity;
    }

    /**
     * Returns the name of the component.
     *
     * @return the component's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cost per unit of the component.
     *
     * @return the unit cost
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * Returns the weight per unit of the component.
     *
     * @return the unit weight
     */
    public double getUnitWeight() {
        return unitWeight;
    }

    /**
     * Returns the type or category of the component (e.g., "Paint", "Raw Material").
     *
     * @return the component's type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the current available stock quantity.
     *
     * @return the available stock
     */
    public double getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Deduct the quantity used during manufacturing.
     *
     * @param quantityUsed the amount of quantity used to deduct
     */
    public void decreaseQuantity(double quantityUsed) {
        this.stockQuantity = Math.round((this.stockQuantity - quantityUsed) * 100.0) / 100.0;
    }

    /**
     * Returns the total cost of this component.
     * Since this is a basic component, the total cost equals its unit cost.
     *
     * @return the total cost
     */
    @Override
    public double getTotalCost() {
        return unitCost;
    }

    /**
     * Returns the total weight of this component.
     * Since this is a basic component, the total weight equals its unit weight.
     *
     * @return the total weight
     */
    @Override
    public double getTotalWeight() {
        return unitWeight;
    }

    /**
     * Prints the details of this basic component, including its name, type, unit cost,
     * unit weight, and stock quantity.
     */
    @Override
    public void printDetail() {
        System.out.printf("Basic Component: %-20s | Type: %-14s | Unit Cost: %8.1f | Unit Weight: %6.2f | Stock: %6.1f%n",
                name, type, unitCost, unitWeight, stockQuantity);
    }
}
