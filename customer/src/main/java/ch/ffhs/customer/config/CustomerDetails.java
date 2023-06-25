package ch.ffhs.customer.config;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This Spring class implements the Spring Security Interface "UserDetails"
 * and provides customer with details about the authentication and authorisation
 */
public class CustomerDetails implements UserDetails {
    private Customer customer;

    /**
     * This method returns the customer's authorizations
     *
     * @return collection of GrantedAuthority objects which represents customer's permission
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : customer.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return null;
    }

    /**
     * method returns password
     * @return String with password
     */
    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    /**
     * method returns username
     * @return String with username
     */
    @Override
    public String getUsername() {
        return customer.getUsername();
    }

    /**
     * method indicates whether the customer's account has expired
     * @return boolean (always returns true -> account has not expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * method indicates whether the customer's account is locked
     * @return boolean (always returns true -> account isn't locked)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * method indicates whether customer's credentials are expired
     * @return boolean (always returns true -> credential aren't expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * method indicates whether customer's account is activated
     * @return boolean (always returns true -> account is activated)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
