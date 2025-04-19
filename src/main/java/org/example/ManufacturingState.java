package org.example;

/**
 * ManufacturingState interface for the State Pattern.
 * All manufacturing process states implement this.
 */
public interface ManufacturingState {
    void proceed(ManufacturingProcess process);
}
