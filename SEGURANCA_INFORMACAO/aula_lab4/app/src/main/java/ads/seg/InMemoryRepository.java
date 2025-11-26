package ads.seg;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void update(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public User findByLogin(String login) {
        return users.get(login);
    }
}