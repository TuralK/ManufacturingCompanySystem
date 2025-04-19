package org.example;

import java.util.Map;

/**
 * WaitingForStockState: product waits until all required stock is available.
 */
public class WaitingForStockState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess proc) {
        Product product = proc.getProduct();
        Inventory inv   = Inventory.getInstance();
        boolean ok = true;
        for (Map.Entry<Component, Double> e : product.getRequirements().entrySet()) {
            BasicComponent comp = (BasicComponent) e.getKey();
            if (comp == null || comp.getStockQuantity() < e.getValue()) {
                ok = false;
                break;
            }
        }
        if (!ok) {
            proc.setFailureType(FailureType.STOCK_SHORTAGE);
            proc.setState(new FailedState());
        } else {
            product.getRequirements()
                   .forEach((comp, qty) -> inv.removeStock(comp.getName(), qty));
            proc.setState(new InManufacturingState());
        }
    }
}
