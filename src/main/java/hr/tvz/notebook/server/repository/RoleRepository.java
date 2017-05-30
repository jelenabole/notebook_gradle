package hr.tvz.notebook.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.notebook.model.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

	public List<UserRole> findAll();

	public UserRole findByUserIdAndRoleLike(Integer userId, String role);

	public List<UserRole> findAllByUserId(Integer id);

	@SuppressWarnings("unchecked")
	public UserRole save(UserRole userRole);

	public void deleteById(Integer id);

	public void deleteAllByUser(Integer userId);
}
