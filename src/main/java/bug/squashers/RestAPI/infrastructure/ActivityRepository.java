package bug.squashers.RestAPI.infrastructure;

import bug.squashers.RestAPI.model.Activity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, ObjectId> {
    List<Activity> findAllByChildUsername(String username);

}
//644fe67e6c9e5e3ea9448a5d
//644fece26c9e5e3ea9448a66