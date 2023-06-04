package bug.squashers.RestAPI.controllers;

import bug.squashers.RestAPI.business.Service;
import bug.squashers.RestAPI.model.Child;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
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
    @Autowired
    private Service service;

    @GetMapping()
    public ResponseEntity<List<Child>> findAll()
    {
        return new ResponseEntity<>(service.findAllChildren(), HttpStatus.OK);
    }
}
