package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}
