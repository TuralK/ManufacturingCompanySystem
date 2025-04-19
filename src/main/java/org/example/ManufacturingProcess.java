package org.example;

/**
 * ManufacturingProcess encapsulates the manufacturing of a single product unit.
 * It holds the current state and delegates behavior based on state.
 */
// ManufacturingProcess.java
public class ManufacturingProcess {
    private final Product product;
    private ManufacturingState state;
    private FailureType failureType;

    public ManufacturingProcess(Product product) {
        this.product = product;
        this.state   = new WaitingForStockState();
    }

    public Product getProduct() {
        return product;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public void setFailureType(FailureType ft) {
        this.failureType = ft;
    }

    public void setState(ManufacturingState state) {
        this.state = state;
    }

    public void proceed() {
        state.proceed(this);
    }

    public void processManufacturing() {
        state.proceed(this);
        if (state instanceof InManufacturingState) {
            state.proceed(this);
        }
    }

    public String getStateName() {
        if (state instanceof CompletedState) {
            return "Completed";
        } else if (state instanceof FailedState) {
            return "Failed (" + failureType + ")";
        } else {
            return state.getClass().getSimpleName();
        }
    }
}
