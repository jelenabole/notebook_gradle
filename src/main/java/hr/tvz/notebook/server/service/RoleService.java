package hr.tvz.notebook.server.service;

import java.util.List;

import hr.tvz.notebook.exceptions.RoleExistsForUser;
import hr.tvz.notebook.model.UserRole;

public interface RoleService {

	public List<UserRole> findAllRoles();

	public List<UserRole> findAllRolesForUser(Integer id);

	public UserRole findOneRoleForUser(Integer userId, String role);

	public UserRole saveRole(UserRole role) throws RoleExistsForUser;

	public void deleteRole(Integer id);

	void deleteAllRolesForUser(Integer userId);

	public boolean hasRole(Integer userId, String userRole);

	public boolean hasAdminRole(String username);
}
