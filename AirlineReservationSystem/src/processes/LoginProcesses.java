package processes;

import java.util.ArrayList;

/**
 *
 * @author Mehmet Karagoz
 * @since 01.03.2021
 */
public class LoginProcesses {

    public User isLogin(
            String username, String password, ArrayList<User> users) {
        if (users != null) {
            for (User user : users) {

                if (user.getUsername().equals(username)
                        && user.getPassword().equals(password)) {

                    return user;
                }
            }
        }
        return null;
    }
}
