package graphql.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.model.Role;
import graphql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleQuery implements GraphQLQueryResolver {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleQuery(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Iterable<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public long countRoles() {
        return roleRepository.count();
    }
}
