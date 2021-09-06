package logic;


import java.sql.*;

public class InsertDao {

    public int registerEmployee(Insert insert) throws ClassNotFoundException {


        String INSERT_USERS_SQL = "INSERT INTO books" + "(title, author, quantity) VALUES" +
                "(?, ?, ?);";

        int result = 0;


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_ee_db",
                "root", "321bazed789!");
            PreparedStatement statement = connection.prepareStatement(INSERT_USERS_SQL)) {
            statement.setString(1, insert.getTitle());
            statement.setString(2, insert.getAuthor());
            statement.setString(3, insert.getQuantity());

            System.out.println(statement);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }
}
