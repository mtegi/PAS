package login.model;

import java.util.List;

public interface IUserRepository {
    boolean addUser(UserModel user);
    void updateUser(String oldUserName, UserModel newUser);
    UserModel findByUsername(String username);
    List<UserModel> getAll();
}
