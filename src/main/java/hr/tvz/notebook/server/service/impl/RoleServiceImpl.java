package hr.tvz.notebook.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.notebook.exceptions.RoleExistsForUser;
import hr.tvz.notebook.model.UserRole;
import hr.tvz.notebook.server.repository.RoleRepository;
import hr.tvz.notebook.server.service.RoleService;
import hr.tvz.notebook.server.service.UserService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;

	// TODO - nepregledane metode ...
	public List<UserRole> findAllRoles() {
		return roleRepository.findAll();
	}

	public List<UserRole> findAllRolesForUser(Integer id) {
		return roleRepository.findAllByUserId(id);
	}

	public UserRole findOneRoleForUser(Integer userId, String role) {
		return roleRepository.findByUserIdAndRoleLike(userId, role);
		// TODO - obrisana funkcija
		// return new UserRole();
	}

	public UserRole saveRole(UserRole role) throws RoleExistsForUser {
		if (hasRole(role.getUser().getId(), role.getRole())) {
			throw new RoleExistsForUser(
					"Role " + role.getRole() + " exists for the user " + role.getUser());
		}

		roleRepository.save(role);
		return role;
	}

	// TODO - kod brisanja paziti da korisnik ima barem jednu rolu ?!
	public void deleteRole(Integer id) {
		roleRepository.deleteById(id);
	}

	public void deleteAllRolesForUser(Integer userId) {
		roleRepository.deleteAllByUser(userId);
	}

	public boolean hasRole(Integer userId, String userRole) {
		if (roleRepository.findByUserIdAndRoleLike(userId, userRole) == null)
			return false;
		return true;
	}

	public boolean hasAdminRole(String username) {
		return hasRole(userService.findOneByUsername(username).getId(), "ROLE_ADMIN");
	}

}
