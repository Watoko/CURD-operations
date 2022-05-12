import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String username, password, role,username_current;

        int ch;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "qwerty12345");
             Statement statement = connection.createStatement()) {

            do {
                System.out.println("Select input choice:");
                System.out.println("1. INSERT");
                System.out.println("2. DISPLAY");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");

                ch = input.nextInt();

                switch (ch) {
                    case 1:
                        System.out.println("Enter username: ");
                        username = input.next();
                        System.out.println("Enter password: ");
                        password = input.next();
                        System.out.println("Enter role: ");
                        role = input.next();

                        String insertStr = "INSERT INTO users(username,password,role) VALUES (?,?,?)";
                        PreparedStatement _statement = connection.prepareStatement(insertStr);
                        _statement.setString(1,username);
                        _statement.setString(2, password);
                        _statement.setString(3,role);
                        int rowsinsterted = _statement.executeUpdate();
                        if (rowsinsterted > 0) {
                            System.out.println("A new user was insterted succesfully");
                        }
                        break;
                    case 2:
                        System.out.println("Enter username: ");
                        username = input.next();
                        String selectStr = "SELECT * FROM users WHERE (username = username)";
                        Statement displayStat = connection.createStatement();
                        ResultSet result = displayStat.executeQuery(selectStr);
                        while (result.next()) {
                            String D_username = result.getString("username");
                            String D_password = result.getString("password");
                            String D_role = result.getString("role");
                            System.out.println("Username:" + D_username + " Password: " + D_password + " Role: " + D_role);
                        }
                        break;
                    case 3:
                        System.out.println("Enter current username: ");
                        username_current = input.next();
                        System.out.println("Enter username: ");
                        username = input.next();
                        System.out.println("Enter password: ");
                        password = input.next();
                        System.out.println("Enter role: ");
                        role = input.next();

                        String updateStr = "UPDATE users SET username=?, password=?, role=? WHERE username=?";

                        _statement = connection.prepareStatement(updateStr);
                        _statement.setString(1,username);
                        _statement.setString(2, password);
                        _statement.setString(3,role);
                        _statement.setString(4,username_current);
                        int Rowsinsterted = _statement.executeUpdate();
                        if (Rowsinsterted > 0) {
                            System.out.println("A user was updated succesfully");
                        }


                        break;
                    case 4:
                        System.out.println("Enter username: ");
                        username = input.next();
                        String deleteStr = "DELETE FROM users WHERE username=?";

                        PreparedStatement delstatement = connection.prepareStatement(deleteStr);
                        delstatement.setString(1, username);
                        int Rowsdeleted = delstatement.executeUpdate();
                        if (Rowsdeleted > 0) {
                            System.out.println("A user was deleted succesfully");
                        }
                        break;
                }

            } while (ch != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

