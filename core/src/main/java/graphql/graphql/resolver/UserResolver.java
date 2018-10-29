package graphql.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.model.Role;
import graphql.model.User;
import graphql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserResolver implements GraphQLResolver<User> {
    private final RoleRepository roleRepository;

    @Autowired
    public UserResolver(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(User user) {
        return roleRepository.findById(user.getRole().getId()).orElse(null);
    }
}
