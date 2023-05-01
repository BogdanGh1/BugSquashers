package bug.squashers.RestAPI.controllers;

import bug.squashers.RestAPI.business.UserService;
import bug.squashers.RestAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<Optional<User>>(userService.findUserByUsername(username), HttpStatus.OK);
    }

}
