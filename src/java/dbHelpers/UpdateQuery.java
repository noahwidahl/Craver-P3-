/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;
import java.sql.*;
/**
 *
 * @author Bokaj
 */
/*
public class UpdateQuery extends CRUD {
    public Update(String url, String user, String password) throws SQLException {
        super(url, user, password);
    }

    public ResultSet readAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM users;";
        return statement.executeQuery(query);
    }

    public ResultSet readUserById(int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeQuery();
        }
    }

    // Example usage
    public static void main(String[] args) {
        String url = "jdbc:mysql://your_host:3306/your_database";
        String user = "your_user";
        String password = "your_password";

        try {
            Read readInstance = new Read(url, user, password);

            // Reading all users
            ResultSet allUsersResultSet = readInstance.readAllUsers();
            while (allUsersResultSet.next()) {
                int id = allUsersResultSet.getInt("id");
                String username = allUsersResultSet.getString("username");
                String email = allUsersResultSet.getString("email");
                System.out.println("User ID: " + id + ", Username: " + username + ", Email: " + email);
            }

            // Reading a user by ID
            int userIdToRead = 1;
            ResultSet userResultSet = readInstance.readUserById(userIdToRead);
            while (userResultSet.next()) {
                int id = userResultSet.getInt("id");
                String username = userResultSet.getString("username");
                String email = userResultSet.getString("email");
                System.out.println("User ID: " + id + ", Username: " + username + ", Email: " + email);
            }

            // Disconnecting from the database
            readInstance.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/