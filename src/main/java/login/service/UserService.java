package login.service;

import login.model.IUserRepository;
import login.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService {
    private IUserRepository repository;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<UserModel> getAll() {
        return repository.getAll();
    }

    @Override
    public UserModel findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void updateUser(String oldUserName, UserModel newUser) {
        repository.updateUser(oldUserName,newUser);
    }

    @Override
    public boolean addUser(UserModel newUser) {
        if(newUser.getRoles().length==0)
        newUser.setRoles("USER");

        return repository.addUser(newUser);
    }

    public void expireUserSessions(String username) {
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof User) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                        information.expireNow();
                    }
                }
            }
        }
    }
}
