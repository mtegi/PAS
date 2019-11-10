package login.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    boolean addUser(UserModel user);
    UserModel findByUsername(String username);
    List<UserModel> getAll();
}
