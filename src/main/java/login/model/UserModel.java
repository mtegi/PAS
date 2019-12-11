package login.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class UserModel {
    @NotNull(message = "Please enter username")
    private String username;
    @Size(min=6, message = "{user.password.size}")
    private String password;
    private String[] roles;

    public UserModel(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
        this.setRoles("USER");
    }

    public UserModel(){}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = roles;
    }

    public boolean hasRole(String role)
    {
        return Arrays.asList(roles).contains(role);
    }
}
