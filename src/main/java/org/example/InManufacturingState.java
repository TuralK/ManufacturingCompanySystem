package org.example;

import java.util.Random;

/**
 * Implements and represents a state that simulates a manufacturing in process.
 * <p>
 * This state simulates the state of a manufacturing process, where the outcome
 * is determined randomly to reflect real-world uncertainties of system errors
 * or damaged components.
 * </p>
 */
public class InManufacturingState implements ManufacturingState {
     /**
    * Simulates the manufacturing process by generating a random outcome.
    * Depending on the result, transitions the {@link ManufacturingProcess}
    * to either a successful or failed state, and sets the appropriate failure reason
    * when applicable.
    * 
    * <p>
    * The outcome state can be determined randomly in three ways:
    * <ul>
    *   <li><b>1</b> → Success: Transitions to {@link CompletedState}</li>
    *   <li><b>2</b> — Failure due to a system error: Sets the {@link FailureType} to {@code SYSTEM_ERROR} and transitions to {@link FailedState}.</li>
    *   <li><b>3</b> — Failure due to a damaged component: Sets the {@link FailureType} to {@code DAMAGED_COMPONENT} and transitions to {@link FailedState}.</li>
    * </ul>
    *</p>
    * 
    * If the outcome is a failure then the {@link FailureType} enum is also set to the appropriate
    * enum type.
    * 
    * @param process the manufacturing process context being progressed
    */
    @Override
    public void proceed(ManufacturingProcess proc) {
        int outcome = new Random().nextInt(3) + 1;  // Generates 1, 2 or 3.
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

    /**
     * Returns the name of the current in-progress state.
     * This implementation always returns "In Manufacturing" to indicate that the 
     * manufacturing process is ongoing and hasn't reached a final state.
     *
     * @param process the manufacturing process context that is in progress
     * @return the human-readable name of the state, always "In Manufacturing"
     */
    @Override
    public String getStateName(ManufacturingProcess process) {
        return "In Manufacturing";
    }
}