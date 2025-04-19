package org.example;

/**
 * Component interface for Composite pattern.
 * Both basic components (leaves) and products (composites) implement this.
 */
public interface Component {
    String getName();

    double getTotalCost();

    double getTotalWeight();

    void decreaseQuantity(double quantityUsed);

    void printDetail();
}
