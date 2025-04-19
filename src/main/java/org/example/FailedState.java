package org.example;

/**
 * FailedState: Indicates manufacturing failed.
 */
public class FailedState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        // No further processing needed.
    }
}
