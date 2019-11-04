package login;

import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class UserRepository implements IUserRepository{

    private HashSet<UserModel> users;

    public UserRepository() {
        users = new HashSet<UserModel>();
        addUser(new UserModel("admin","admin","ADMIN"));
        addUser(new UserModel("user","user","USER"));
        System.out.println(users.size());
    }

    public boolean addUser(UserModel user){
        if(findByUsername(user.getUsername()) != null) return false;
        return this.users.add(user);
    }

    public UserModel findByUsername(final String username){
        return this.users.stream().filter(user -> username.equals(user.getUsername())).findAny().orElse(null);
    }
}
