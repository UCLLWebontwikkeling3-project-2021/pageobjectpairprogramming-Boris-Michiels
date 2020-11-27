package domain.model;

public enum Role {
    USER("user"),
    ADMIN("admin");

    private String stringValue;

    Role(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}