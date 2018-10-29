package graphql.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.exception.NotFoundException;
import graphql.model.Role;
import graphql.model.User;
import graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMutation implements GraphQLMutationResolver {
    private final UserRepository userRepository;

    @Autowired
    public UserMutation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User newUser(String username, String email, Integer requestCount, String roleId) {
        User user = new User();
        user.setRole(new Role(roleId));
        user.setUsername(username);
        user.setEmail(email);
        user.setRequestCount(requestCount != null ? requestCount : 0);
        user.setState(User.State.ACTIVE);

        return userRepository.save(user);
    }

    public boolean deleteUser(String id) {
        userRepository.deleteById(id);

        return true;
    }

    public User updateUserRequestCount(Integer pageCount, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User to be updated was not found", id));

        user.setRequestCount(pageCount);

        return userRepository.save(user);
    }
}
