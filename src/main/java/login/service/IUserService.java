package login.service;

import login.model.UserModel;

import java.util.Collection;

public interface IUserService {
    Collection<UserModel> getAll();
    UserModel findByUsername(String username);

    void updateUser(String oldUserName, UserModel newUser);
    boolean addUser(UserModel newUser);
    void expireUserSessions(String username);
}
