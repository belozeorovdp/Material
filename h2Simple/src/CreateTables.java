import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables
{
    public static void createTable1Users(String nameBD)
    {
        String nameTable = "users";
        String query = "CREATE TABLE " +
                nameTable +
                " (id INT, name VARCHAR(50) NOT NULL, PRIMARY KEY (id));";
        System.out.println(query);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void createTable2Photos(String nameBD)
    {
        String nameTable = "photos";
        String query = "CREATE TABLE " +
                nameTable +
                " (id INT, title VARCHAR(50), authorname VARCHAR(50), PRIMARY KEY (id));";
        System.out.println(query);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void createTable3CommentsToPhoto(String nameBD)
    {
        String nameTable = "commentstophoto";
        String query = "CREATE TABLE " +
                nameTable +
                " (id INT, text_ VARCHAR(255), authorname VARCHAR(50), PRIMARY KEY (id));";
        System.out.println(query);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void createTable4LikePhotos(String nameBD)
    {
        String nameTable = "likephotos";
        String query = "CREATE TABLE " +
                nameTable +
                " (id INT AUTO_INCREMENT, id_photos INT, id_users INT, like_ INT, PRIMARY KEY (id), " +
                " FOREIGN KEY (id_photos) REFERENCES photos (id)," +
                " FOREIGN KEY (id_users) REFERENCES users  (id));";
        System.out.println(query);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}