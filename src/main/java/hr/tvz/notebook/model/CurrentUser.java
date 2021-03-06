package hr.tvz.notebook.model;

import java.io.Serializable;
import java.util.List;

import hr.tvz.notebook.enums.UserRoles;

public class CurrentUser implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer id;
	String username;
	List<UserRoles> roles;

	// XXX - easier check:
	public Boolean isAdmin() {
		return roles.stream().filter(e -> {
			if (e.name().equals(UserRoles.ROLE_ADMIN.name()))
				return true;
			else
				return false;
		}).count() == 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		String string = "USER - id: " + id + " - username: " + username + " - roles: ";
		for (int i = 0; i < roles.size(); i++) {
			string += roles.get(i).name();
			if (i < roles.size() - 1)
				string += ", ";
		}
		return string;
	}
}
