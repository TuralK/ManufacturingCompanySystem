package org.example;

/**
 * Defines the Component interface used in the Composite Design Pattern.
 * <p>
 * This interface is implemented by both basic components (leaves) and products (composites),
 * providing a common structure for getting the component's name,
 * calculating total cost, total weight, decreasing the quantity
 * and printing component details.
 * </p>
 */
public interface Component {
    /**
     * Gets the name of this component.
     * 
     * @return the name
     */
    String getName();

    /**
     * Calculates the total cost of this component.
     * For a basic component, this is its individual cost.
     * For a composite product, this includes the cost of all contained components.
     *
     * @return the total cost
     */
    double getTotalCost();

     /**
     * Calculates the total weight of this component.
     * For a basic component, this is its individual weight.
     * For a composite product, this includes the weight of all contained components.
     *
     * @return the total weight
     */
    double getTotalWeight();

     /**
     * Decreases the quantity of this component.
     * For a basic component, this is its current stock.
     * For a composite product, this is its number to manufacture.
     *
     * @param quantityUsed the quantity to deduct from the component's stock or number to manufacture
     */
    void decreaseQuantity(double quantityUsed);

    /**
     * Prints the details of the component, including cost, weight, and name.
     * Implementations may add more details and format this output differently depending on the component type.
     */
    void printDetail();
}
