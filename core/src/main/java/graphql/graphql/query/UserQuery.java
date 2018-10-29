package graphql.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.exception.NotFoundException;
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

    public Iterable<User> users() {
        return userRepository.findAll();
    }

    public long usersCount() {
        return userRepository.count();
    }

    public User user(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found", id));
    }

    public User userByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found", username));
    }
}
