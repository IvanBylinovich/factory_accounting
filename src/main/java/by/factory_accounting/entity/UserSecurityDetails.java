package by.factory_accounting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserSecurityDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public UserSecurityDetails(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    //метод возвращает из моего user объект user который является классом Security
    public static UserDetails fromUser(User user){
        return new org.springframework.security.core.userdetails
                .User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.USER_ACTIVE),
                user.getStatus().equals(Status.USER_ACTIVE),
                user.getStatus().equals(Status.USER_ACTIVE),
                user.getStatus().equals(Status.USER_ACTIVE),
                user.getRole().getAuthorities());
    }
}
