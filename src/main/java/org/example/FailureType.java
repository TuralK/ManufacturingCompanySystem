package org.example;

/**
 * Enumeration of possible failure types that can occur during a manufacturing process.
 * <p>
 * Each failure type has a name that is returned by the {@link #toString()} method,
 * for use in logs or reports.
 * </p>
 *
 * <ul>
 *   <li>{@link #SYSTEM_ERROR} — Indicates a failure due to internal system malfunction.</li>
 *   <li>{@link #DAMAGED_COMPONENT} — Indicates a failure caused by a damaged or unusable component.</li>
 *   <li>{@link #STOCK_SHORTAGE} — Indicates a failure due to insufficient component stock.</li>
 * </ul>
 */
public enum FailureType {
    SYSTEM_ERROR      ("System Error"),
    DAMAGED_COMPONENT ("Damaged Component"),
    STOCK_SHORTAGE    ("Stock Shortage");

    private final String displayName;

    /**
     * Constructs a failure type with the given name.
     *
     * @param displayName the name for the failure type
     */
    FailureType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the name of the failure type.
     *
     * @return the name of this failure type
     */
    @Override
    public String toString() {
        return displayName;
    }
}
