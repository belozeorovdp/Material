public class Main
{
    public static void main(String[] a) throws Exception
    {

        // Проверка работы
        // String NameBD = "baseh2";
        String NameBD = "";
        // CreateDatabase.create(NameBD);

        CreateTables.createTable1Users(NameBD);
        CreateTables.createTable2Photos(NameBD);
        CreateTables.createTable3CommentsToPhoto(NameBD);
        // CreateTables.createTable4LikePhotos(NameBD);

        InsertTables.insertTable1Users(NameBD);
        InsertTables.insertTable2Photos(NameBD);
        InsertTables.insertTable3CommentsToPhoto(NameBD);

        // Предполагаем, что имена уникальные
        // 1. Определяем id User-а
        System.out.println("---------");
        String strName = "Ivanov";
        int id_name = SelectQuery.sendingQueryId(NameBD, strName);
        System.out.println("Name: " + strName + ", " + id_name);

        // 2. Определяем id Photo (Title + Author - не повторяются)
        System.out.println("---------");
        System.out.print("Author:");
        // String strAuthor = scan.nextLine();
        String strAuthor = "Ivanov";
        System.out.println(strAuthor);
        System.out.print("Photo Title:");
        // String strTitle = scan.nextLine();
        String strTitle =  "foto_Ivanov";
        System.out.println(strTitle);
        int id_photo = SelectQuery.sendingQueryIdPhotos(NameBD, strTitle, strAuthor);
        System.out.println("id_photo: " + id_photo);
    }
}