package graphql.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.model.Role;
import graphql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMutation implements GraphQLMutationResolver {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleMutation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name, String description) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);

        return roleRepository.save(role);
    }
}
