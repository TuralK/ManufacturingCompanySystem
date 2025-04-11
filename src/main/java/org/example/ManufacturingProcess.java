package org.example;

/**
 * ManufacturingProcess encapsulates the manufacturing of a single product unit.
 * It holds the current state and delegates behavior based on state.
 */
class ManufacturingProcess {
    private Product product;
    private ManufacturingState state;
    private String failureReason = "";

    public ManufacturingProcess(Product product) {
        this.product = product;
        // Initially the process is waiting for stock.
        this.state = new WaitingForStockState();
    }

    public Product getProduct() {
        return product;
    }

    public ManufacturingState getState() {
        return state;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public void setState(ManufacturingState state) {
        this.state = state;
    }

    // Process manufacturing through its states sequentially.
    public void processManufacturing() {
        // Waiting for stock check.
        state.proceed(this);
        // If transitioned to InManufacturing, process manufacturing.
        if (state instanceof InManufacturingState) {
            state.proceed(this);
        }
        // Completed or Failed: no further actions.
    }

    // For reporting purposes, return current state name.
    public String getStateName() {
        if (state instanceof CompletedState)
            return "Completed";
        else if (state instanceof FailedState)
            return "Failed (" + failureReason + ")";
        else if (state instanceof WaitingForStockState)
            return "WaitingForStock";
        else if (state instanceof InManufacturingState)
            return "InManufacturing";
        else
            return "Unknown";
    }
}
