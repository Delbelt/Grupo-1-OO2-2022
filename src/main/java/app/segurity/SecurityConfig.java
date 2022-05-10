package app.segurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // Clase de configuracion Spring
@EnableWebSecurity // Habilita la seguridad Web
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// WebSecurityConfigurerAdapter : Para configurar los usuarios que vamos a utilizar
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired //Para tener disponible: AuthenticationManagerBuilder
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder()); // Encode the password
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception // Configura las auth (Concepto) a partir de los roles
	{

		String [] resources = new String[]{"/role/addRole/**", "/user/addUser/**", "/classroom/addLaboratory/**", 
				"/classroom/addTraditional/**", "/role/edit/**", "/user/edit/**", "/classroom/edit/**",  
				"/role/delete", "/user/delete", "/classroom/delete"};
		
		//	http://localhost:8080/space/spaces
		//	http://localhost:8080/space/edit/1
		//	http://localhost:8080/space/addSpace
		//	http://localhost:8080/space/spaces

		http
		.authorizeHttpRequests()
		.antMatchers(resources) // /** Es para TODA ruta siguiente a esa
		.hasRole("ADMIN") // ↑↑ Quien puede acceder a estas rutas
		.antMatchers("/", "/user/users", "/role/roles") // Ingreso al inicio (publico)
		.hasAnyRole("AUDIT", "ADMIN") // ↑↑ Cualquier Usuario
		.and()
		.formLogin() // Agrega el formulario de Login
		.loginPage("/login").defaultSuccessUrl("/") // Ruta de Login + a donde va si inicia sesion (home)
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout") // Ruta de logout + a donde va cuando deslogea
		.and().authorizeRequests().antMatchers("/logout").anonymous() // NO queremos que un usuario logeado acceda al logout si esta en sesion
		.and().authorizeRequests().antMatchers("/login").anonymous() // NO queremos que un usuario logeado acceda al login
		.and().exceptionHandling().accessDeniedPage("/error/403"); // Acceso Denegado - Ruta("/")	
	}
	
	public static String Encrypt(String password) // Encripta texto
	{
		return new BCryptPasswordEncoder().encode(password);
	}
}
