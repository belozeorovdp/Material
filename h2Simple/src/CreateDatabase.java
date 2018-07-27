import java.sql.*;

public class CreateDatabase
{
    public static void create(String nameDatabase)
    {
        try
        {
            Class.forName("org.h2.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database", "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE SCHEMA database IF NOT EXISTS AUTHORIZATION sa;" /*nameDatabase + " AUTHORIZATION sa;" + */);
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}