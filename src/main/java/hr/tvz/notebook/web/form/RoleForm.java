package hr.tvz.notebook.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import hr.tvz.notebook.model.User;
import hr.tvz.notebook.model.UserRole;

public class RoleForm {

	Integer id;

	@NotNull
	User user;
	@NotNull
	@Size(max = 50)
	UserRole role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}