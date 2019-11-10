package login.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private HashSet<UserModel> users;

    @Autowired
    public UserRepository(IUserProvider userProvider) {
        users = new HashSet<>(userProvider.provideUsers());
    }

    public boolean addUser(UserModel user){
        if(findByUsername(user.getUsername()) != null) return false;
        return this.users.add(user);
    }

    public UserModel findByUsername(final String username){
        return this.users.stream().filter(user -> username.equals(user.getUsername())).findAny().orElse(null);
    }

    @Override
    public List<UserModel> getAll() {
        return new ArrayList<>(users);
    }
}