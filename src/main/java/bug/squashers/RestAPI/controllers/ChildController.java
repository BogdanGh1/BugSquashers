package bug.squashers.RestAPI.controllers;

import bug.squashers.RestAPI.business.Service;
import bug.squashers.RestAPI.model.Child;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api/children")
public class ChildController {
    private final Service service;
    public ChildController(Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Child>> getChildren() {
        return new ResponseEntity<List<Child>>(service.findAllChildren(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<Child>> getChildByUsername(@PathVariable String name){
        return new ResponseEntity<Optional<Child>>(service.findChildByName(name), HttpStatus.OK);
    }
}