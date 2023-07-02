package ch.ffhs.admin.admin.config;

import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.repository.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * AdminServiceConfig is used to load user details of an administrator from the repository
 */
public class AdminServiceConfig implements UserDetailsService {
    // this interface is used to connect to DB and search for admin user by its username
    private AdminRepository adminRepository;

    /**
     * method is used to load the admin user by its username and
     * throws UsernameNotFoundException if there isn't a user with this name
     *
     * @param username of admin user
     * @return UserDetails of admin user
     * @throws UsernameNotFoundException if no user with this name exists
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if(admin == null){
            throw new UsernameNotFoundException("Couldn't find username");
        }
        return new User(
                admin.getUsername(),
                admin.getPassword(),
                admin.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
    }
}
