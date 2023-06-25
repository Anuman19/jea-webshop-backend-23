package ch.ffhs.customer.config;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

/**
 * CustomerConfigService is used to load user details of a customer from the repository
 */

public class CustomerConfigService implements UserDetailsService {
    // this interface is used to connect to DB and search for customer user by its username
    private CustomerRepository customerRepository;

    /**
     * method is used to load the customer user by its username and
     * throws UsernameNotFoundException if there isn't a user with this name
     *
     * @param username of customer user
     * @return UserDetails of customer user
     * @throws UsernameNotFoundException if no user with this name exists
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null){
            throw new UsernameNotFoundException("Could not find username");
        }
        return new User(customer.getUsername(), customer.getPassword(), customer.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}
