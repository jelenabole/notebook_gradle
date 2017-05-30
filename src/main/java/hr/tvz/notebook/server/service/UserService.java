package hr.tvz.notebook.server.service;

import java.util.List;

import hr.tvz.notebook.exceptions.RoleExistsForUser;
import hr.tvz.notebook.exceptions.UserExistsException;
import hr.tvz.notebook.model.CurrentUser;
import hr.tvz.notebook.model.User;
import hr.tvz.notebook.web.form.FilterForm;
import hr.tvz.notebook.web.form.UserForm;

public interface UserService {

	public List<User> findAll();

	public User findOne(Integer id);

	public User findOneByUsername(String username);

	public User save(User user) throws UserExistsException, RoleExistsForUser;

	public User save(UserForm userForm);

	public User update(UserForm userForm);

	public User changeInfo(UserForm userForm);

	public void delete(Integer id);

	public boolean checkIfUserExists(String username);

	public void changeEnabledStatus(Integer id);

	public CurrentUser getCurrentUser(String username);

	public List<User> getFilteredUsers(FilterForm filterForm);

}
