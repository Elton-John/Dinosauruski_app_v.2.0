package pl.dinosaurus.dinosauruski.registration;

import antlr.TokenStreamException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.model.VerificationToken;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@AllArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken userToken = new VerificationToken(token, user, 48);
        tokenRepository.save(userToken);
        return userToken;
    }

    public VerificationToken getVerificationTokenOrThrow(String VerificationToken) throws TokenStreamException {
        VerificationToken token = tokenRepository.findByToken(VerificationToken);
        if (token == null || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenStreamException("token expired");
        }
        return token;
    }

    public boolean tokenExist(String token) {
        return tokenRepository.findByToken(token) != null;
    }

    public User getUser(String resetPasswordToken) {
        return tokenRepository.findByToken(resetPasswordToken).getUser();
    }

}
