package login.service;

import login.model.UserModel;

import java.util.Collection;

public interface IUserService {
    Collection<UserModel> getAll();
    UserModel findByUsername(String userToDeactivate);

    boolean addUser(UserModel newUser);
}