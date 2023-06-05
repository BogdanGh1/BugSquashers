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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private final static Logger log= LogManager.getLogger(Service.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ChildRepository childRepository;
    public List<User> findAllUsers() {
        System.out.println("findAllUsers");
        log.info("Service - findAllUsers");
        return userRepository.findAll();
    }
    public User findUserByUsername(String username) {
        log.info("Service - findByUsername : {}",username);
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<Activity> getActivitiesForUserById(ObjectId userId) {
        log.info("Service - getActivitiesForUserById : {}",userId);
        return activityRepository.findByChild_IdOrAdult_Id(userId, userId);
    }

    public List<Activity> getActivitiesForUserByUsername(String username) {
        log.info("Service - getActivitiesForUserByUsername : {}",username);
        Optional<User> user = userRepository.findByUsername(username);
        ObjectId userId = user.get().getId();
        return activityRepository.findByChild_IdOrAdult_Id(userId, userId);
    }
    public List<Activity> findAll() {
        log.info("Service - findAll");
        return activityRepository.findAll();
    }

    public Activity saveActivity(Activity activity){
        log.info("Service - saveActivity : {}",activity);
        return  activityRepository.save(activity);}

    public User createUser(String username,String description, String password,String date) {
        log.info("Service - createUser : {},{},{},{}",username,description,password,date);
        String formattedDate = Utils.getFormattedDate(date);
        return userRepository.insert(new User(username,password,description,formattedDate));
    }

    public List<Child> findAllChildren() {
        log.info("Service - findAllChildren");
        return childRepository.findAll();
    }

    public Optional<Child> findChild(ObjectId childID) { return childRepository.findById(childID);
    }

    public Optional<User> findUser(ObjectId userID) {
        return userRepository.findById(userID);
    }

    public Optional<Child> findChild(String childName) {
        log.info("Service - findChild : {}",childName);
        return childRepository.findByName(childName);
    }

    public Optional<User> findUser(String userName) {
        log.info("Service - findUser : {}",userName);
        return userRepository.findByUsername(userName);
    }

    public Optional<Child> findChildByName(String name) {
        log.info("Service - findChildByName : {}",name);
        return childRepository.findByName(name);
    }

    public Optional<User> login(String username, String password) {
        log.info("Service - login : {}, {}",username,password);
        Optional<User> user = userRepository.findUserByUsernameAndPassword(username,password);
        return user;
    }
}