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

		String [] resourcesAdmin = new String[] {
				"/role/addRole/**", "/role/edit/**", "/role/delete", // Roles
				"/user/addUser/**", "/user/edit/**", "/user/delete", // Usuarios
				"/building/addBuilding/**", "/building/edit/**", "/building/delete", // Edificios
				"/space/addSpace/**", "/space/edit/**", "/space/delete", // Espacios
				"/classroom/edit/**", "/classroom/delete", // Aulas
				"/classroom/addLaboratory/**", "/classroom/editLaboratory", // Aula: Laboratorio
				"/classroom/addTraditional/**", "/classroom/editTraditional", // Aula: Tradicional
				"/department/addDepartment/**", "/department/edit/**", "/department/delete", // Departamentos
				"/career/addCareer/**", "/career/edit/**", "/career/delete", // Carreras
				"/matter/addMatter/**", "/matter/edit/**", "/matter/delete", // Materias
				"/orderNote/edit/**", "/orderNote/delete", // NotaPedido
				"/orderNote/addQuarterOrder/**", "/orderNote/editQuarterOrder", // NotaPedido: Cuatrimestre
				"/orderNote/addDayOrder/**", "/orderNote/editDayOrder", // NotaPedido: Por dia
		};
		
		String [] resourcesAnyRole = new String[] {
				"/",
				"/role/roles", // Roles
				"/user/users",  // Usuarios
				"building/buildings", // Edificios
				"/space/spaces", // Espacio
				"/department/departments", // Departamentos
				"/career/careers", // Carreras
				"/matter/matters", // Materias
				"/orderNote/orderNotes", "orderNote/orderNote/**", // Nota Pedido
		};

		http
		.authorizeHttpRequests()
		.antMatchers(resourcesAdmin) // /** Es para TODA ruta siguiente a esa
		.hasRole("ADMIN") // ↑↑ Quien puede acceder a estas rutas
		.antMatchers(resourcesAnyRole) // Ingreso al inicio (publico)
		.hasAnyRole("AUDIT", "ADMIN") // ↑↑ Cualquier Usuario
		.and().formLogin() // Agrega el formulario de Login
		.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=true")  // Ruta de Login y en caso de credenciales invalidas
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout?logout=true") // Ruta de logout + a donde va cuando deslogea correctamente
		.deleteCookies("JSESSIONID") // Para borrar la sesion y no quede 'invalida' por defecto (para el tratamiento de expired e invalid correcto)
		.and().authorizeRequests().antMatchers("/logout").anonymous() // NO queremos que un usuario logeado acceda al Logout si sigue en sesion
		.and().authorizeRequests().antMatchers("/login").anonymous() // NO queremos que un usuario logeado acceda al Login si sigue en sesion
		.and().exceptionHandling().accessDeniedPage("/error/403") // Pagina de Acceso Denegado		
		.and().sessionManagement().invalidSessionUrl("/logout?expired=true") // Cuando pase el tiempo de inactividad
		.maximumSessions(1).expiredUrl("/logout?maximum=true"); // Solamente puede haber 1 sesion activa por usuario
	}
	
	public static String Encrypt(String password) // Encripta texto
	{
		return new BCryptPasswordEncoder().encode(password);
	}
}
