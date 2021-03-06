package hr.tvz.notebook.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.notebook.exceptions.RoleExistsForUser;
import hr.tvz.notebook.exceptions.UserExistsException;
import hr.tvz.notebook.model.User;
import hr.tvz.notebook.other.mapper.UserMapper;
import hr.tvz.notebook.server.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	UserService userService;

	/**** REST - POSTMAN ****/

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public User findOne(@PathVariable Integer id) {
		return userService.findOne(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User save(@RequestBody User user) throws UserExistsException, RoleExistsForUser {
		return userService.save(user);
	}

	@PutMapping("/{id}")
	public User update(@RequestBody User user) throws UserExistsException, RoleExistsForUser {
		return userService.update(UserMapper.mapUserToUserForm(user));
	}

	/**** AJAX ***/

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		userService.delete(id);
	}

	@GetMapping("/changeStatus/{id}")
	public void changeEnabledStatus(@PathVariable Integer id) {
		userService.changeEnabledStatus(id);
	}

}
