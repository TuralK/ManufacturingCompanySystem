package org.example;

/**
 * Represents a state in the manufacturing process as part of the State Design Pattern.
 * <p>
 * Implementations of this interface define the behavior of a {@link ManufacturingProcess}
 * in a particular state and the transition logic to the next state.
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
}
