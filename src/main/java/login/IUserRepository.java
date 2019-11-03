package login;

public interface IUserRepository {
    void addUser(User user);
    public User findByUsername(String username);
}
