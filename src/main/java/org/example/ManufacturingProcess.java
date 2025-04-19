package org.example;
/**
 * Represents a manufacturing process for a single product unit.
 * <p>
 * The {@link ManufacturingProcess} class encapsulates the state and behavior of the 
 * manufacturing process. The state determines the current stage of the process, 
 * and the class delegates actions to the appropriate state. It also manages the 
 * product being manufactured and handles transitions between different process states.
 * It can also hold the failure reason when the class transitions to {@link FailedState}.
 * </p>
 */
public class ManufacturingProcess {
    private final Product product;
    private ManufacturingState state;
    private FailureType failureType;

    /**
     * Initializes a new manufacturing process for a product, with it
     * in the "waiting for stock" state.
     *
     * @param product the product to be manufactured
     */
    public ManufacturingProcess(Product product) {
        this.product = product;
        this.state   = new WaitingForStockState();
    }

    /**
     * Gets the product being manufactured in this process.
     *
     * @return the {@link Product} being manufactured
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets the failure type of the manufacturing process.
     *
     * @return the {@link FailureType} of the process, or {@code null} if the process isn't failed
     */
    public FailureType getFailureType() {
        return failureType;
    }

    /**
     * Sets the failure type for the manufacturing process.
     *
     * @param ft the {@link FailureType} to set
     */
    public void setFailureType(FailureType ft) {
        this.failureType = ft;
    }

    /**
     * Sets the current state of the manufacturing process.
     *
     * @param state the {@link ManufacturingState} to transition to
     */
    public void setState(ManufacturingState state) {
        this.state = state;
    }

    /**
     * Transitions the manufacturing process to the next state based on the current state
     * by delegating the state transition to the current state's {@code proceed} method.
     */
    public void proceed() {
        state.proceed(this);
    }

    /**
     * Processes the manufacturing by advancing through the states, 
     * if the state is {@link InManufacturingState} the state is advanced again.
     */
    public void processManufacturing() {
        state.proceed(this);
        if (state instanceof InManufacturingState) {
            state.proceed(this);
        }
    }

    /**
     * Gets the name of the current state of the manufacturing process.
     * <p>
     * The state name can be one of the following:
     * <ul>
     *   <li>{@code "Completed"} if the process has successfully finished</li>
     *   <li>{@code "Failed (<type>)"} if the process failed, including the {@link FailureType}</li>
     *   <li>The simple name of the current {@link ManufacturingState} class for other states</li>
     * </ul>
     * </p>
     *
     * @return the name of the current state
     */
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
