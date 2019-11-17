package login.service;

import login.model.IUserRepository;
import login.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService {
    private IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<UserModel> getAll() {
        return repository.getAll();
    }

    @Override
    public UserModel findByUsername(String userToDeactivate) {
        return repository.findByUsername(userToDeactivate);
    }

    @Override
    public boolean addUser(UserModel newUser) {
        newUser.setRoles("USER");
        return repository.addUser(newUser);
    }
}
