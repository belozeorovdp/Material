import java.sql.*;

public class SelectQuery
{
    public static void sendingQuery(String nameBD, String authorname)
    {
        String query = "SELECT id FROM photos WHERE name = '" + authorname + "';";

        System.out.println(query);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database/" +  nameBD, "sa","");
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

    public static int sendingQueryId(String nameBD, String name)
    {
        String query = "SELECT id FROM Users WHERE name = '" + name + "';";
        System.out.println(query);
        int id_name = -1;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            // int columns = rs.getMetaData().getColumnCount();
            if (!rs.isBeforeFirst())
            {
                System.out.println("No data");
            }
            while(rs.next())
            {
                id_name = rs.getInt(1);
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
        return id_name;
    }

    public static int sendingQueryIdPhotos(String nameBD, String Title, String Author)
    {
        String query = "SELECT id FROM photos WHERE title = '" + Title + "' AND authorname = '" + Author + "';";
        System.out.println(query);

        int id_name = -1;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            // int columns = rs.getMetaData().getColumnCount();
            if (!rs.isBeforeFirst())
            {
                System.out.println("No data");
                id_name = -2;
            }
            else
            {
                while(rs.next())
                {
                    id_name = rs.getInt(1);
                    System.out.println();
                }

            }
            rs.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Не найдено.");
            e.printStackTrace();
        }
        return id_name;
    }

    public static boolean sendingQueryCheckRow(String nameBD, int id_name, int id_photo)
    {
        String query = "SELECT id FROM likephotos WHERE id_photos = " + id_photo + " AND id_users = " + id_name + ";";
        System.out.println(query);
        boolean result = true;
        int id = -1;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            // int columns = rs.getMetaData().getColumnCount();
            if (!rs.isBeforeFirst())
            {
                System.out.println("No data");
                result = false;
                id = -2;
            }
            else
            {
                while(rs.next())
                {
                    id = rs.getInt(1);
                    System.out.println();
                }

            }
            rs.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Не найдено.");
            e.printStackTrace();
        }

        return result;
    }

    public static int sendingQueryCheckRowLikePhoto (String nameBD, int id_name, int id_photo)
    {
        String query = "SELECT like_ FROM likephotos WHERE id_photos = " + id_photo + " AND id_users = " + id_name + ";";
        int like = -1;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            // int columns = rs.getMetaData().getColumnCount();
            if (!rs.isBeforeFirst())
            {
                System.out.println("No data");
                like = -2;
            }
            else
            {
                while(rs.next())
                {
                    like = rs.getInt(1);
                    System.out.println();
                }

            }
            rs.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Не найдено.");
            e.printStackTrace();
        }

        return like;
    }

    public static int sendingQueryIdlikephotos(String nameBD, int id_name, int id_photo)
    {
        String query = "SELECT id FROM likephotos WHERE id_photos = " + id_photo + " AND id_users = " + id_name + ";";

        System.out.println(query);

        int id = -1;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:database" +  nameBD, "sa","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            // int columns = rs.getMetaData().getColumnCount();
            if (!rs.isBeforeFirst())
            {
                System.out.println("No data");
                id = -2;
            }
            else
            {
                while(rs.next())
                {
                    id = rs.getInt(1);
                    System.out.println();
                }

            }
            rs.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Не найдено.");
            e.printStackTrace();
        }
        return id;
    }

    public static int sendingQuerySumlikephotos(String nameBD)
    {
        String query = "SELECT users.name, SUM(likephotos.like_) FROM likephotos RIGHT OUTER JOIN users ON users.id = likephotos.id_users AND likephotos.like_ > 0;";

        System.out.println(query);

        int id = -1;
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
        return id;
    }
}