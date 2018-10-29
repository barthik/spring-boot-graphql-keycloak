package graphql.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.config.RequestSession;
import graphql.model.Role;
import graphql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoleQuery implements GraphQLQueryResolver {
    private final RoleRepository roleRepository;

    @Autowired
    private RequestSession session;

    @Autowired
    public RoleQuery(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Iterable<Role> roles(int page, int size) {
        // Consume page request
        Page<Role> result = roleRepository.findAll(PageRequest.of(page, size));
        // Resolve headers
        session.resolve(result);
        // Return content
        return result.getContent();
    }

    public long rolesCount() {
        return roleRepository.count();
    }
}
