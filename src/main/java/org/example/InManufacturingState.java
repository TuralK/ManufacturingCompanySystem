package org.example;

import java.util.Random;

/**
 * InManufacturingState: Simulate manufacturing in progress.
 * Generates a random outcome:
 * 1 -> Successful manufacturing.
 * 2 -> Unsuccessful (system error).
 * 3 -> Unsuccessful (damaged component).
 */
class InManufacturingState implements ManufacturingState {
    @Override
    public void proceed(ManufacturingProcess process) {
        Random random = new Random();
        int outcome = random.nextInt(3) + 1; // Generates 1, 2 or 3.
        if (outcome == 1) {
            process.setState(new CompletedState());
        } else if (outcome == 2) {
            process.setFailureReason("System Error");
            process.setState(new FailedState());
        } else if (outcome == 3) {
            process.setFailureReason("Damaged Component");
            process.setState(new FailedState());
        }
    }
}
