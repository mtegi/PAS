package login.model;

import java.util.List;

public interface IUserRepository {
    boolean addUser(UserModel user);
    UserModel findByUsername(String username);
    List<UserModel> getAll();
}
