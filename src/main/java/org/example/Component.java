package org.example;

/**
 * Component interface for Composite pattern.
 * Both basic components (leaves) and products (composites) implement this.
 */
interface Component {
    double getTotalCost();

    double getTotalWeight();

    void printDetail();
}
