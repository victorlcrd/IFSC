package ads.seg;

import java.nio.CharBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private final UserRepository userRepository;
    private final String hashAlgorithm;

    private static final SecureRandom S_RANDOM = new SecureRandom();

    private final PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this(userRepository, "BCrypt");
    }


    public UserService(UserRepository userRepository, String hashAlgorithm) {
        this.userRepository = userRepository;
        this.hashAlgorithm = hashAlgorithm;
    }

    public void register(String login, char[] password) throws UserAlreadyExistsException, Exception {
        if (userRepository.findByLogin(login) != null) {
            throw new UserAlreadyExistsException("Login '" + login + "' já existe.");
        }

        String hashedPassword = hash(password);
        User user = new User(login, hashedPassword);
        userRepository.save(user);
    }


    public boolean authenticate(String login, char[] password) throws InvalidLoginException, Exception {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new InvalidLoginException("Login ou senha inválidos.");
        }

        boolean matches = verify(password, user.getPassword());
        if (!matches) {
            throw new InvalidLoginException("Login ou senha inválidos.");
        }
        return true;
    }


    public void updatePassword(String login, char[] oldPassword, char[] newPassword)
            throws UserNotFoundException, InvalidPasswordException, Exception {

        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException("Usuário '" + login + "' não encontrado.");
        }

        boolean matches = verify(oldPassword, user.getPassword());
        if (!matches) {
            throw new InvalidPasswordException("Senha atual inválida.");
        }

        String newHashedPassword = hash(newPassword);
        user.setPassword(newHashedPassword);
        userRepository.update(user);
    }

    private String hash(char[] password) throws Exception {
        switch (this.hashAlgorithm) {
            case "BCrypt":
                return bcryptEncoder.encode(CharBuffer.wrap(password));

            case "PBKDF2WithHmacSHA512":
                byte[] salt = new byte[16];
                S_RANDOM.nextBytes(salt);
                int iterations = 600000;
                int keyLength = 128;

                byte[] hash = PasswordHashing.hashPasswordWithPBKDF2(
                        password, salt, this.hashAlgorithm, iterations, keyLength);

                throw new UnsupportedOperationException("PBKDF2 não totalmente implementado neste exemplo.");

            default:
                throw new NoSuchAlgorithmException("Algoritmo de hash não suportado: " + this.hashAlgorithm);
        }
    }

    private boolean verify(char[] password, String storedHash) throws Exception {
        switch (this.hashAlgorithm) {
            case "BCrypt":
                return bcryptEncoder.matches(CharBuffer.wrap(password), storedHash);

            case "PBKDF2WithHmacSHA512":
                throw new UnsupportedOperationException("PBKDF2 não totalmente implementado neste exemplo.");

            default:
                throw new NoSuchAlgorithmException("Algoritmo de hash não suportado: " + this.hashAlgorithm);
        }
    }
}