package dev.tianhui.movies.user;

import com.mongodb.client.result.UpdateResult;
import dev.tianhui.movies.email.EmailService;
import dev.tianhui.movies.registration.token.ConfirmationToken;
import dev.tianhui.movies.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    public String signUpUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            // check if attributes are the same
            boolean sameUsername = optionalUser.get().getUsername().equals(user.getUsername());
            boolean isEnabled = optionalUser.get().getIsEnabled();
            // only throw exception if it is a different user
            // if it is the same user with email not confirmed, we continue to send confirmation email
            if (!sameUsername || isEnabled) {
                throw new IllegalStateException("email already taken!");
            }
        }
        boolean userNameExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (userNameExists) {
            throw new IllegalStateException("username already taken!");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public int enableUser(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set("isEnabled", true);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        return (int) result.getModifiedCount();
    }
}

