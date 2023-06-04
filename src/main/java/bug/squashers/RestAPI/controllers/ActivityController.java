package bug.squashers.RestAPI.controllers;

import bug.squashers.RestAPI.business.Service;
import bug.squashers.RestAPI.model.Activity;
import bug.squashers.RestAPI.model.Child;
import bug.squashers.RestAPI.model.DTO;
import bug.squashers.RestAPI.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private Service service;

    @GetMapping
    public ResponseEntity<List<Activity>> getActivities() {
        return new ResponseEntity<List<Activity>>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/id/{userId}")
    public List<Activity> getActivitiesForUserById(@PathVariable String userId) {
        return service.getActivitiesForUserById(new ObjectId(userId));
    }
    @GetMapping("/{username}")
    public List<Activity> getActivitiesForUserByUsernmae(@PathVariable String username) {
        return service.getActivitiesForUserByUsername(username);
    }
    @PostMapping()
    public ResponseEntity<?> bookAppointment(@RequestBody DTO dto)
    {
        System.out.println(dto.getChildName());
        Child child= service.findChild(dto.getChildName()).orElse(null);
        User user = service.findUser(dto.getAdultName()).orElse(null);
        Activity activity = Activity.builder()
                .child(child)
                .adult(user)
                .date(dto.getActivityDate())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .build();
        Activity savedActivity = this.service.saveActivity(activity);
        this.service.saveActivity(savedActivity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
