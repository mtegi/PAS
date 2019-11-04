package login;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {
    boolean addUser(UserModel user);
    UserModel findByUsername(String username);
}
