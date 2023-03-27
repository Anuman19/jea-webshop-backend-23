package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.repository.AdminRepository;
import ch.ffhs.library.library.repository.RoleRepository;
import ch.ffhs.library.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles();
        return null;
    }
}
