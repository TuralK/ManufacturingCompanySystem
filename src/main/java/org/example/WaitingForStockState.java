package org.example;

import java.util.Map;

/**
 * Implements and represents a state where the manufacturing process is waiting for all required
 * components to be available in stock before proceeding.
 * <p>
 * In this state, the system checks the {@link Inventory} singleton to verify whether
 * all components needed to manufacture the product are available in sufficient quantities.
 * </p>
 * <p>
 * If the stock is sufficient, the required quantities are deducted and the process
 * transitions to {@link InManufacturingState}. Otherwise, it sets the
 * {@link FailureType} to {@code STOCK_SHORTAGE} and transitions to {@link FailedState}.
 * </p>
 */
public class WaitingForStockState implements ManufacturingState {
    /**
     * Checks the availability of all required components for the product in {@link Inventory}.
     * <p>
     * If all components are available in sufficient quantity, the components are removed from
     * the inventory and the process transitions to {@link InManufacturingState}. 
     * If not, the process fails, sets the {@link FailureType} to {@code STOCK_SHORTAGE}
     * and transitions to {@link FailedState}.
     * </p>
     *
     * @param proc the manufacturing process context waiting for stock availability
     */
    @Override
    public void proceed(ManufacturingProcess proc) {
        Product product = proc.getProduct();
        Inventory inv   = Inventory.getInstance();
        boolean ok = true;
        // Check if all required components have sufficient stock.
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
             // Deduct stock for the all required basic components before proceeding.
            product.getRequirements()
                   .forEach((comp, qty) -> inv.removeStock(comp.getName(), qty));
            proc.setState(new InManufacturingState());
        }
    }

    /**
     * Returns the name of the current waiting for stock state.
     * This implementation always returns "Waiting For Stock" to indicate that the 
     * manufacturing process is stopped until the required components are available.
     *
     * @param process the manufacturing process context that is waiting for stocks
     * @return the human-readable name of the state, always "Waiting For Stock"
     */
    @Override
    public String getStateName(ManufacturingProcess process) {
        return "Waiting For Stock";
    }
}
