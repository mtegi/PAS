package login;

import java.util.HashSet;

public interface IUserProvider {
    HashSet<UserModel> provideUsers();
}
