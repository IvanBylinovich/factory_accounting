package by.factory_accounting.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, SUPPLIER, MASTER, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
    //необходимо определить гетор с логикой по которой будут определяться роли


}
