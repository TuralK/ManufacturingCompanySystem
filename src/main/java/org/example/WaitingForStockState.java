package org.example;

import java.util.Map;

/**
 * WaitingForStockState: product waits until all required stock is available.
 */
class WaitingForStockState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        Product product = process.getProduct();
        Inventory inventory = Inventory.getInstance();
        boolean stockAvailable = true;
        // Check if all required components have sufficient stock.
        for (Map.Entry<String, Double> entry : product.getRequirements().entrySet()) {
            BasicComponent comp = inventory.getComponent(entry.getKey());
            double requiredAmount = entry.getValue();
            if (comp == null || comp.getStockQuantity() < requiredAmount) {
                stockAvailable = false;
                break;
            }
        }
        if (!stockAvailable) {
            process.setFailureReason("Stock Shortage");
            process.setState(new FailedState());
        } else {
            // Deduct stock for one unit of product before proceeding.
            for (Map.Entry<String, Double> entry : product.getRequirements().entrySet()) {
                inventory.updateStock(entry.getKey(), entry.getValue());
            }
            process.setState(new InManufacturingState());
        }
    }
}
