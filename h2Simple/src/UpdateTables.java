import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTables
{
    public static void updateLikePhotos(String nameBD, String nameTable, int id, int like)
    {

        String Query = "UPDATE " + nameTable + " SET like = " + like + " WHERE id = " + id;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate(Query);
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




}