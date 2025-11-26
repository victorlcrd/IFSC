package ads.seg;

public interface UserRepository {

    void save(User user);

    void update(User user);

    User findByLogin(String login);
}