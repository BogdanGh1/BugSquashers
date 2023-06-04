package bug.squashers.RestAPI.controllers;


import bug.squashers.RestAPI.business.Service;
import bug.squashers.RestAPI.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api/children")
public class ChildController {

    private final Service service;

    public ChildController(Service service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(service.findAllUsers(), HttpStatus.OK);
    }
}
