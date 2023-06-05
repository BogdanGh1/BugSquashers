package bug.squashers.RestAPI.controllers;

import bug.squashers.RestAPI.business.Service;
import bug.squashers.RestAPI.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api/users")
public class UserController {

    private final static Logger log= LogManager.getLogger(UserController.class);

    private final Service service;
    public UserController(Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("UserController - getUsers");
        return new ResponseEntity<List<User>>(service.findAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        log.info("UserController - getUserByUsername : {}",username);
        return new ResponseEntity<User>(service.findUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload) {
        log.info("UserController - createUser : {}",payload);
        return new ResponseEntity<User>(service.createUser(payload.get("username"),payload.get("description"), payload.get("password"),payload.get("date")), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody Map<String, String> payload) {
        log.info("UserController - login : {}",payload);
        Optional<User> user = service.login(payload.get("username"), payload.get("password"));

        if (user.isPresent()) {
            ResponseEntity<Optional<User>> response = new ResponseEntity<>(user, HttpStatus.OK);
            log.info("UserController - response : {}", response);
            return response;
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
