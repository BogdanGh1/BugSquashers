package bug.squashers.RestAPI.business;

import bug.squashers.RestAPI.infrastructure.UserRepository;
import bug.squashers.RestAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
