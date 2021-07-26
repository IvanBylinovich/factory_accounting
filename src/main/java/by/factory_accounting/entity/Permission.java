package by.factory_accounting.entity;

public enum Permission {
    USER_READ("read"),
    USER_WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
