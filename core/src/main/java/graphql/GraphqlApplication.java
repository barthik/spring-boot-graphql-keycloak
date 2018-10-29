package graphql;

import graphql.model.Role;
import graphql.model.User;
import graphql.repository.RoleRepository;
import graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GraphqlApplication {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @PostConstruct
    public void initData() {
        Role userRole = roleRepository.save(new Role("User", "Regular user role"));
        Role adminRole = roleRepository.save(new Role("Admin", "Admin user role"));

        userRepository.save(new User(null, "barthik", "barthik@domain.tld", 100, adminRole, User.State.ACTIVE));
        userRepository.save(new User(null, "pepazdepa", "pepazdepa@depo.tld", 0, userRole, User.State.BLOCKED));
    }
}