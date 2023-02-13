import java.sql.*;
public class Main {
    public static void main(String[] args) {
        // Connect to MySQL database
        String url = "jdbc:mysql://localhost:3306/database_name";
        String username = "username";
        String password = "password";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to database");

            // Create table
            String createSql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), email VARCHAR(255))";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createSql);
                System.out.println("Table created");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            // Insert data
            String insertSql = "INSERT INTO users (first_name, last_name, email) VALUES ('John', 'Doe', 'john@example.com')";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
                System.out.println("Data inserted");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            // Select data
            String selectSql = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSql)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    System.out.println(id + ": " + firstName + " " + lastName + ", " + email);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
            System.out.println(e.getMessage());
        }
    }
}
