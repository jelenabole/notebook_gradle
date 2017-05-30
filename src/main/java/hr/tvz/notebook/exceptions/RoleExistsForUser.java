package hr.tvz.notebook.exceptions;

//Runtime excetption ili pratit..
public class RoleExistsForUser extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RoleExistsForUser (String message) {
		super(message);
	}
	
	public RoleExistsForUser(String message, Throwable cause) {
		super(message, cause);
	}
}
