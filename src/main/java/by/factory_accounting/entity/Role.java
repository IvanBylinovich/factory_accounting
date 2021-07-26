package by.factory_accounting.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role  {
    USER(Set.of(Permission.USER_READ)),
    ADMIN(Set.of(Permission.USER_WRITE, Permission.USER_READ));

    //права доступа соответствующие роли пользователя
    public Set<Permission> getPermissions() {
        return permissions;
    }

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    //метод получения меток(прав) доступа к url приложения
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
