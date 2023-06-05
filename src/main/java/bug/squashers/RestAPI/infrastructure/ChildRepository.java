package bug.squashers.RestAPI.infrastructure;


import bug.squashers.RestAPI.model.Child;
import bug.squashers.RestAPI.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildRepository extends MongoRepository<Child, ObjectId> {
    Optional<Child> findByName(String name);
}
