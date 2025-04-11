package org.example;

/**
 * CompletedState: Indicates manufacturing was successful.
 */
class CompletedState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        // No further processing needed.
    }
}
