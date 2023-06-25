package ch.ffhs.library.library.service;

import ch.ffhs.library.library.model.Role;
/**
 * The RoleService interface thus defines the basic
 * operations required for managing roles
 */
public interface RoleService {
    Role findByName(String name);
}
