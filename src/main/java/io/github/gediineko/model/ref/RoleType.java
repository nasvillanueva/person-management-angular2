package io.github.gediineko.model.ref;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public enum RoleType {

    ADMIN("Admin"),
    USER("User");

    private String desc;

    RoleType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
