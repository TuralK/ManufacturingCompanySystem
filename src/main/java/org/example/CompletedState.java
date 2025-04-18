package org.example;

/**
 * CompletedState: Indicates manufacturing was successful.
 * Adds the newly produced product to the inventory.
 */
class CompletedState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        // Get the information`s of product from the process:
        Product product = process.getProduct();
        // Product is inserted into the inventory as a new component:
        BasicComponent produced = new BasicComponent(
            product.getName(),
            product.getTotalCost(),
            product.getTotalWeight(),
            "product",    // type of the component: product
            1.0           // stock quantity: 1.0 (1 unit of the product)
        );
        Inventory.getInstance().addComponent(produced);
        // No further action is needed, as the product is already in the inventory.
    }
}
