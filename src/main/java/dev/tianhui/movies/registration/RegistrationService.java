package dev.tianhui.movies.registration;

import dev.tianhui.movies.registration.token.ConfirmationToken;
import dev.tianhui.movies.registration.token.ConfirmationTokenService;
import dev.tianhui.movies.user.User;
import dev.tianhui.movies.user.UserRole;
import dev.tianhui.movies.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

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

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getConfirmationToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found!"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Token already confirmed!!");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiredAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token already expired!!");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getUsername());
        return "confirmed token!";
    }
}
