package org.example;

/**
 * Entry point of the Manufacturing Company System.
 * <p>
 * This class initializes and coordinates the {@link ManufacturingCompanyController} 
 * to run the company's manufacturing processes and print the processes' results.
 * </p>
 */
public class ManufacturingCompanySystem {
    /**
     * The main method that starts the manufacturing system.
     * It initializes the {@link ManufacturingCompanyController} with the component
     * and product resource files, and runs it.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ManufacturingCompanyController controller =
            new ManufacturingCompanyController("components.csv", "products.csv");
        controller.run();
    }
}


