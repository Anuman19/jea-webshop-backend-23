package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.model.Role;
import ch.ffhs.library.library.repository.AdminRepository;
import ch.ffhs.library.library.repository.RoleRepository;
import ch.ffhs.library.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();

        admin.setFirstName(adminDto.getFirstName());
        admin.setEmail(adminDto.getEmail());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findRoleById(1L));
        admin.setRoles(roleList);
        return adminRepository.save(admin);
    }

    @Override
    public AdminDto update(AdminDto adminDto) {
        if (adminRepository.findById(adminDto.getId()).isPresent()) {
            Admin admin = adminRepository.findById(adminDto.getId()).get();
            admin.setFirstName(adminDto.getFirstName());
            admin.setEmail(adminDto.getEmail());
            admin.setLastName(adminDto.getLastName());
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(adminDto.getPassword());
            List<Role> roleList = new ArrayList<>();
            roleList.add(roleRepository.findRoleById(1L));
            System.out.println(roleList);
            admin.setRoles(roleList);
            return mapperDto(adminRepository.save(admin));
        } else {
            return new AdminDto();
        }
    }


    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

    private AdminDto mapperDto(Admin admin) {
        return new AdminDto(admin.getId(), admin.getFirstName(), admin.getLastName(), admin.getUsername(), admin.getEmail(), admin.getPassword());
    }
}
