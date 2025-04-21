package org.example;

/**
 * Implements and represents a state that indicates failure in the manufacturing process.
 * <p>
 * Once a {@link ManufacturingProcess} reaches this state, no further state transitions
 * are performed. This state marks the failure of the process.
 * </p>
 */
public class FailedState implements ManufacturingState {
    /**
     * Does nothing, as the process has already failed.
     * This method intentionally leaves the state of {@link ManufacturingProcess} unchanged,
     * signifying that no further transitions occur.
     *
     * @param process the manufacturing process context that has reached failure
     */
    @Override
    public void proceed(ManufacturingProcess process) {
        // No further processing needed.
    }

    /**
     * Returns the name of the current failed state.
     * This implementation returns "Failed (<failureType>)", where <failureType> is
     * the specific reason why that the manufacturing process has reached failure.
     *
     * @param process the manufacturing process context that has reached failure
     * @return the human-readable name of the state, including the failure type
     */
    @Override
    public String getStateName(ManufacturingProcess process) {
        return "Failed (" + process.getFailureType() + ")";
    }
}
