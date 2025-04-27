package project.spring.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.transaction.Transactional;
import project.spring.entities.Role;
import project.spring.entities.Usuario;
import project.spring.repository.RoleRepository;
import project.spring.repository.UsuarioRepository;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UsuarioRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
    					   UsuarioRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

    	
    	var roleAdminBuscar = roleRepository.findFirstByName(Role.Values.ADMIN.name());

        var userAdmin = userRepository.findByEmail("admin@hotmail.com");
        
        if(userAdmin != null && roleAdminBuscar != null) {
        	 System.out.println("Admin já existe e role ja existem!");
        	return;
        }
        
        Role roleAdmin = new Role();
        roleAdmin.setName(Role.Values.ADMIN.name());
        roleRepository.save(roleAdmin);
        
        userAdmin.ifPresentOrElse(
                user-> {
                    var user1 = new Usuario();
                    user1.setEmail("admin@gmail.com");
                    user1.setPassword(passwordEncoder.encode("123"));
                    user1.setRoles(Set.of(roleAdminBuscar));
                    userRepository.save(user1);
                }, null
        );
    }
}