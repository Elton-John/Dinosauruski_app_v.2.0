package pl.dinosaurus.dinosauruski.registration;

import antlr.TokenStreamException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.User;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@AllArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken userToken = new VerificationToken(token, user, 24);
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
}
