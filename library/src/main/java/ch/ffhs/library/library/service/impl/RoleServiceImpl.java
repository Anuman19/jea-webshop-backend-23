package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.model.Role;
import ch.ffhs.library.library.repository.RoleRepository;
import ch.ffhs.library.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleRepository roleRepository;

    /**
     * @param id of Role
     * @return the Role
     */
    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

}
