package login;

import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class UserRepository {

    private HashSet<User> users;
    public void addUser(User user){
        this.users.add(user);
    }

    public User findByUsername(final String username){
        return this.users.stream().filter(user -> username.equals(user.getUsername())).findAny().orElse(null);
    }
}
