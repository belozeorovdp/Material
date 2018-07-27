import java.sql.*;

public class InsertTables
{
    public static void insertTable1Users(String nameBD)
    {
        String strQuery [] = new String[5];
        strQuery[0] = "INSERT INTO Users VALUES( 1, 'Ivanov');";
        strQuery[1] = "INSERT INTO Users VALUES( 2, 'Petrov');";
        strQuery[2] = "INSERT INTO Users VALUES( 3, 'Vasilev');";
        strQuery[3] = "INSERT INTO Users VALUES( 4, 'Ivanenko');";
        strQuery[4] = "INSERT INTO Users VALUES( 5, 'Petrenko');";

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            for(int i = 0; i< strQuery.length; i++)
            {
                System.out.println(strQuery[i]);
                statement.executeUpdate(strQuery[i]);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void insertTable2Photos(String nameBD)
    {
        String strQuery [] = new String[5];
        strQuery[0] = "INSERT INTO photos VALUES( 1, 'foto_Ivanov', 'Ivanov');";
        strQuery[1] = "INSERT INTO photos VALUES( 2, 'foto_Petrov', 'Petrov');";
        strQuery[2] = "INSERT INTO photos VALUES( 3, 'foto_Vasilev', 'Vasilev');";
        strQuery[3] = "INSERT INTO photos VALUES( 4, 'foto_Ivanenko', 'Ivanenko');";
        strQuery[4] = "INSERT INTO photos VALUES( 5, 'foto_Petrenko', 'Petrenko');";

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            for(int i = 0; i< strQuery.length; i++)
            {
                System.out.println(strQuery[i]);
                statement.executeUpdate(strQuery[i]);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void insertTable3CommentsToPhoto(String nameBD)
    {
        String strQuery [] = new String[5];
        strQuery[0] = "INSERT INTO commentstophoto VALUES( 1, 'Comments_Ivanov', 'Ivanov');";
        strQuery[1] = "INSERT INTO commentstophoto VALUES( 2, 'Comments_Petrov', 'Petrov');";
        strQuery[2] = "INSERT INTO commentstophoto VALUES( 3, 'Comments_Vasilev', 'Vasilev');";
        strQuery[3] = "INSERT INTO commentstophoto VALUES( 4, 'Comments_Ivanenko', 'Ivanenko');";
        strQuery[4] = "INSERT INTO commentstophoto VALUES( 5, 'Comments_Petrenko', 'Petrenko');";

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            for(int i = 0; i< strQuery.length; i++)
            {
                System.out.println(strQuery[i]);
                statement.executeUpdate(strQuery[i]);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void insertTableLikePhotos(String nameBD, int id_photo, int id_users, int like)
    {
        String strQuery = "INSERT INTO likephotos (" + "id_photos, id_users, like_ " + ") VALUES(" + id_photo + ", " + id_users + ", " + like + ");";
        System.out.println(strQuery);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database", "sa","");
            Statement statement = connection.createStatement();
            statement.executeUpdate(strQuery);
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // проверка
        String query = "SELECT * FROM likephotos;";
        System.out.println(query);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int columns = rs.getMetaData().getColumnCount();
            if (!rs.isBeforeFirst())
            {
                System.out.println("No data");
            }
            while(rs.next())
            {
                for (int i = 1; i <= columns; i++)
                {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            System.out.println();
            rs.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Не найдено.");
            e.printStackTrace();
        }
    }
}