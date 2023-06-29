package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.model.Admin;

/**
 * The AdminService interface thus defines the basic
 * operations required for managing admins
 */
public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);

    Admin findByEmail(String Email);

    AdminDto update(AdminDto adminDto);
}
