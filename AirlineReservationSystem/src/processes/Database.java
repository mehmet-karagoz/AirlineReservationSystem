package processes;

import java.sql.SQLException;

/**
 *
 * @author Mehmet Karagoz
 * @since 01.03.2021
 */
public class Database {

    private static final String userName = "";
    private static final String password = "";
    private static final String databaseName = "";
    private static final String host = "";
    private static final int port;

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static void showError(SQLException exception) {

        System.out.println("Error : " + exception.getMessage());
        System.out.println("Error code : " + exception.getErrorCode());
    }
}
