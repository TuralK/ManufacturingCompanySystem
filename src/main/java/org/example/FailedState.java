package org.example;

/**
 * FailedState: Indicates manufacturing failed.
 */
public class FailedState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        // No further processing needed.
    }

    @Override
    public String getStateName(ManufacturingProcess process) {
        return "Failed (" + process.getFailureType() + ")";
    }
}
