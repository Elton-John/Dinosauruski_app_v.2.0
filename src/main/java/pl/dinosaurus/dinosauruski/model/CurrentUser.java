package pl.dinosaurus.dinosauruski.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final pl.dinosaurus.dinosauruski.model.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.dinosaurus.dinosauruski.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.dinosaurus.dinosauruski.model.User getUser() {
        return user;
    }

}