package hr.tvz.notebook.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	/**** CONFIGURE ****/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").defaultSuccessUrl("/newNote")
				.failureUrl("/login?error=true").and().logout()
				.logoutSuccessUrl("/login?logout=true").and().authorizeRequests()
				.antMatchers("/css/**", "/js/**").permitAll()
				.antMatchers("/login").anonymous().antMatchers("/register").anonymous()
				.antMatchers("/**").authenticated().anyRequest().permitAll().and().csrf().disable();
		// .and().exceptionHandling().accessDeniedPage("/Access_Denied");

		// remember me function:
		// httpSecurity.rememberMe().rememberMeParameter("remember-me")
		// .rememberMeCookieName("my-remember-me")
		// .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400000);

		// <access-denied-handler error-page="/403" />
		// http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").and().formLogin()
		// .loginPage("/login").failureUrl("/login?error").usernameParameter("username")
		// .passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout").and()
		// .exceptionHandling().accessDeniedPage("/403");
	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"SELECT username, password, enabled FROM users WHERE username=?")
				.authoritiesByUsernameQuery(
						"SELECT u.username, r.role FROM users u, user_roles r WHERE u.id = r.user AND u.username =?")
				.passwordEncoder(bcryptEncoder);
	}

}
