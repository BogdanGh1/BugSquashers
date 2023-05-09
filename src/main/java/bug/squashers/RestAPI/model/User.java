package bug.squashers.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String description;
    private String birthdate;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String description, String birthdate) {
        this.username = username;
        this.password = password;
        this.description = description;
        this.birthdate = birthdate;
    }
}
