package ru.iokhin.tm;

public enum RoleType {
    ADMIN("Администратор"),
    USER("Обычный пользователь");

    private String title;

    RoleType(String title) {
        this.title = title;
    }

    public String displayName() {
        return this.title;
    }

}
