package org.example;

import java.util.Random;

/**
 * InManufacturingState: Simulate manufacturing in progress.
 * Generates a random outcome:
 * 1 -> Successful manufacturing.
 * 2 -> Unsuccessful (system error).
 * 3 -> Unsuccessful (damaged component).
 */
public class InManufacturingState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess proc) {
        int outcome = new Random().nextInt(3) + 1;
        if (outcome == 1) {
            proc.setState(new CompletedState());
            proc.proceed();
        } else if (outcome == 2) {
            proc.setFailureType(FailureType.SYSTEM_ERROR);
            proc.setState(new FailedState());
        } else {
            proc.setFailureType(FailureType.DAMAGED_COMPONENT);
            proc.setState(new FailedState());
        }
    }
}