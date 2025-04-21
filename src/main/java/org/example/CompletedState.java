package org.example;

/**
 * CompletedState: Indicates manufacturing was successful.
 * Adds the newly produced product to the inventory.
 */
public class CompletedState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        // Get the information`s of product from the process:
        Product product = process.getProduct();
        product.increaseQuantity();
        Inventory.getInstance().addComponent(product);
        // No further action is needed, as the product is already in the inventory.
    }

    @Override
    public String getStateName(ManufacturingProcess process) {
        return "Completed";
    }
}
