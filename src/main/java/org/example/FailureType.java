package org.example;

/** Types of manufacturing failures with user‑friendly names. */
public enum FailureType {
    SYSTEM_ERROR      ("System Error"),
    DAMAGED_COMPONENT ("Damaged Component"),
    STOCK_SHORTAGE    ("Stock Shortage");

    private final String displayName;

    FailureType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
