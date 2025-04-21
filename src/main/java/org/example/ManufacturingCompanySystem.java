package org.example;

// ManufacturingCompanySystem.java
public class ManufacturingCompanySystem {
    public static void main(String[] args) {
        ManufacturingCompanyController controller =
            new ManufacturingCompanyController("components.csv", "products.csv");
        controller.run();
    }
}


