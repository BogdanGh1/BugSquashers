package bug.squashers.RestAPI.controllers;

import bug.squashers.RestAPI.business.ActivityService;
import bug.squashers.RestAPI.business.UserService;
import bug.squashers.RestAPI.model.Activity;
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
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<Activity>> getActivities() {
        return new ResponseEntity<List<Activity>>(activityService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<Activity>> getActivitiesByUsername(@PathVariable String username){
        return new ResponseEntity<List<Activity>>(activityService.findAllByChildUsername(username), HttpStatus.OK);
    }
}
