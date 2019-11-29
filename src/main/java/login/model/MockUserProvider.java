package login.model;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class MockUserProvider implements IUserProvider {
    @Override
    public HashSet<UserModel> provideUsers() {
        HashSet<UserModel> userSet = new HashSet<>();
        userSet.add(new UserModel("admin","admin","ADMIN"));
        userSet.add(new UserModel("user","user","USER"));
        userSet.add(new UserModel("user2","user","USER"));
        return userSet;
    }
}
