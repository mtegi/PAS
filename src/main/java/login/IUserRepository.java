package login;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {
    void addUser(UserModel user);
    UserModel findByUsername(String username);
}
