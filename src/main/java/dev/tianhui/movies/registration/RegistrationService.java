package dev.tianhui.movies.registration;

import dev.tianhui.movies.user.User;
import dev.tianhui.movies.user.UserRole;
import dev.tianhui.movies.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email is not valid!!");
        }
        return userService.signUpUser(
                new User(request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER)
        );
    }
}
