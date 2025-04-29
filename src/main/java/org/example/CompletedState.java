package org.example;

/**
 * Implements and represents a state that indicates success in the manufacturing process.
 * <p>
 * Once a {@link ManufacturingProcess} reaches this state, the newly manufactured
 * product is added to the inventory. This state marks the completion of the process.
 * </p>
 */
public class CompletedState implements ManufacturingState {
    /**
     * Finalizes the manufacturing process by updating the inventory.
     * <p>
     * This method is called when the manufacturing process reaches the {@code CompletedState}.
     * It increases the quantity of the manufactured {@link Product} and adds it to the {@link Inventory}.
     * No further processing is performed, as this state represents the end of the process.
     * </p>
     *
     * @param process the {@link ManufacturingProcess} instance containing the product to be finalized
     */
    @Override
    public void proceed(ManufacturingProcess process) {
        // Get the information`s of product from the process:
        Product product = process.getProduct();
        product.increaseQuantity();
        Inventory.getInstance().addComponent(product);
        // No further action is needed, as the product is already in the inventory.
    }

    /**
     * Returns the name of the current complete state.
     * This implementation always returns "Completed" as it represents the final state
     * in the manufacturing process.
     *
     * @param process the manufacturing process context that has reached completion
     * @return the human-readable name of the state, always "Completed"
     */
    @Override
    public String getStateName(ManufacturingProcess process) {
        return "Completed";
    }
}
