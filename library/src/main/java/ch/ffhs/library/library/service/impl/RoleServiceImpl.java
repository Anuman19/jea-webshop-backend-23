package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.model.Role;
import ch.ffhs.library.library.repository.RoleRepository;
import ch.ffhs.library.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    /**
     * @param name
     * @return
     */

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

}
