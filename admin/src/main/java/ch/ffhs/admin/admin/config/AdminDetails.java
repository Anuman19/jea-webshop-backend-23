package ch.ffhs.admin.admin.config;

import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This Spring class implements the Spring Security Interface "UserDetails"
 * and provides administrator with details about the authentication and authorisation
 */

public class AdminDetails implements UserDetails {
    private Admin admin;

    /**
     * This method returns the administrator's authorizations.
     * @return collection of GrantedAuthority objects which represents administrator's permission
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // travers list of administrator roles and creates an instance
        // of SimpleGrantedAuthority with each role
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for(Role role : admin.getRoles()){
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorityList;
    }

    /**
     * method returns password
     * @return String with password
     */
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    /**
     * method returns username
     * @return String with username
     */
    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    /**
     * method indicates whether the administrator's account has expired
     * @return boolean (always returns true -> account has not expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * method indicates whether the administrator's account is locked
     * @return boolean (always returns true -> account isn't locked)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * method indicates whether administrator's credentials are expired
     * @return boolean (always returns true -> credential aren't expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * method indicates whether administrator's account is activated
     * @return boolean (always returns true -> account is activated)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
