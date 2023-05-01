package bug.squashers.RestAPI.business;

import bug.squashers.RestAPI.infrastructure.ActivityRepository;
import bug.squashers.RestAPI.infrastructure.UserRepository;
import bug.squashers.RestAPI.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> findAllByChildUsername(String username) {
        return activityRepository.findAllByChildUsername(username);
    }
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

}
