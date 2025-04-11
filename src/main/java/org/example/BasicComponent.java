package org.example;

/**
 * BasicComponent represents raw materials, paints, or hardware.
 */
class BasicComponent implements Component {
    private String name;
    private double unitCost;
    private double unitWeight;
    private String type;
    private double stockQuantity; // current stock

    public BasicComponent(String name, double unitCost, double unitWeight, String type, double stockQuantity) {
        this.name = name;
        this.unitCost = unitCost;
        this.unitWeight = unitWeight;
        this.type = type;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double getUnitWeight() {
        return unitWeight;
    }

    public String getType() {
        return type;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    // Deduct from stock (if manufacturing uses this component).
    public void updateStock(double quantityUsed) {
        this.stockQuantity -= quantityUsed;
    }

    @Override
    public double getTotalCost() {
        return unitCost;
    }

    @Override
    public double getTotalWeight() {
        return unitWeight;
    }

    @Override
    public void printDetail() {
        System.out.println("Component: " + name + " | Type: " + type + " | Unit Cost: " + unitCost
                + " | Unit Weight: " + unitWeight + " | Stock: " + stockQuantity);
    }
}

