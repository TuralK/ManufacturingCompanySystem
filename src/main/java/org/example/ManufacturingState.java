package org.example;

/**
 * Represents a state in the manufacturing process as part of the State Design Pattern.
 * <p>
 * Implementations of this interface define the behavior of a {@link ManufacturingProcess}
 * in a particular state, including the logic for transitioning to the next state and 
 * retrieving a human-readable name for the state.
 * </p>
 */
public interface ManufacturingState {
    /**
     * Performs the logic associated with the current state and transitions
     * the {@link ManufacturingProcess} to the next appropriate state.
     *
     * @param process the manufacturing process context that is being transitioned
     */
    void proceed(ManufacturingProcess process);

    /**
     * Returns the name of the current state. 
     * The name returned is up to the implementation.
     * @param process the manufacturing process
     * @return the human-readable name of the state
     */
    String getStateName(ManufacturingProcess process);
}
