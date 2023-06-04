package bug.squashers.RestAPI.business;

import bug.squashers.RestAPI.infrastructure.ActivityRepository;
import bug.squashers.RestAPI.infrastructure.ChildRepository;
import bug.squashers.RestAPI.infrastructure.UserRepository;
import bug.squashers.RestAPI.model.Activity;
import bug.squashers.RestAPI.model.Child;
import bug.squashers.RestAPI.model.User;
import bug.squashers.RestAPI.utils.Utils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ChildRepository childRepository;
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<Activity> getActivitiesForUserById(ObjectId userId) {
        return activityRepository.findByChild_IdOrAdult_Id(userId, userId);
    }

    public List<Activity> getActivitiesForUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        ObjectId userId = user.get().getId();
        return activityRepository.findByChild_IdOrAdult_Id(userId, userId);
    }
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Activity saveActivity(Activity activity){return  activityRepository.save(activity);}

    public User createUser(String username,String description, String password,String date) {

        String formattedDate = Utils.getFormattedDate(date);
        return userRepository.insert(new User(username,password,description,formattedDate));
    }

    public List<Child> findAllChildren() {
        return childRepository.findAll();
    }

    public Optional<Child> findChild(ObjectId childID) { return childRepository.findById(childID);
    }

    public Optional<User> findUser(ObjectId userID) {
        return userRepository.findById(userID);
    }

    public Optional<Child> findChild(String childName) { return childRepository.findByName(childName);
    }

    public Optional<User> findUser(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Optional<Child> findChildByName(String name) {
        return childRepository.findByName(name);
    }
}