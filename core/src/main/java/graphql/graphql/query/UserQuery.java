package graphql.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.model.User;
import graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQuery implements GraphQLQueryResolver {
    private final UserRepository userRepository;

    @Autowired
    public UserQuery(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public long countUsers() {
        return userRepository.count();
    }
}
