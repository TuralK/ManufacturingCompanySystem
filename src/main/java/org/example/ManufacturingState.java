package org.example;

/**
 * ManufacturingState interface for the State Pattern.
 * All manufacturing process states implement this.
 */
public interface ManufacturingState {
    void proceed(ManufacturingProcess process);

    /**
     * Returns the name of the current state.
     * @param process the manufacturing process
     * @return the human-readable name of the state
     */
    String getStateName(ManufacturingProcess process);
}
