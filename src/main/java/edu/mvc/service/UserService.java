package edu.mvc.service;

import edu.mvc.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User signupUser(User user);

    User getCurrentUser();

    boolean hasRole(String role);
}
