package processes;

import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Mehmet Karagoz
 * @since 01.03.2021
 */
public class UserProcesses {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public UserProcesses() {

        String url = "jdbc:mysql://" + Database.getHost() + ":" + Database.getPort()
                + "/" + Database.getDatabaseName() + "?useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, Database.getUserName(), Database.getPassword());

            System.out.println("Connection has successfull.");

        } catch (ClassNotFoundException ex) {
            System.out.println("Error : " + ex.getMessage());
        } catch (SQLException ex) {
            Database.showError(ex);
        }
    }

    public ArrayList<User> readUsers() {

        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM users";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                users.add(new User(id, name, surname, username, password));
            }

            return users;

        } catch (SQLException ex) {
            Database.showError(ex);
            return null;
        } catch (NullPointerException ex) {
            System.out.println("Error : " + ex.getMessage());
            return null;
        }

    }

    public void addUser(User user) {

        try {
            String query = "INSERT INTO users (name, surname, username, password) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Database.showError(ex);
        }

    }

    public static void main(String[] args) {

        UserProcesses up = new UserProcesses();
        ArrayList<User> list = up.readUsers();

        list.stream().forEach(e -> System.out.println("id: " + e.getId()
                + "\nname: " + e.getName() + "\nsurname: " + e.getSurname()
                + "\nusername: " + e.getUsername() + "\npassword: " + e.getPassword() + "\n"));
    }
}
