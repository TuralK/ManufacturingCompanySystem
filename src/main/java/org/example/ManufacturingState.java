package org.example;

/**
 * ManufacturingState interface for the State Pattern.
 * All manufacturing process states implement this.
 */
interface ManufacturingState {
    void proceed(ManufacturingProcess process);
}
