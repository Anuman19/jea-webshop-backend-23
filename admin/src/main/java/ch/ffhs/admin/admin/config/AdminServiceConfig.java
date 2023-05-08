package ch.ffhs.admin.admin.config;

import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AdminServiceConfig implements UserDetailsService {
    //@Autowired -> will eventually turn into an error
    private AdminRepository adminRepository;
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
