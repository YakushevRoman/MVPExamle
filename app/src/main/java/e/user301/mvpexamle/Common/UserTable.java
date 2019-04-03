package e.user301.mvpexamle.Common;

public class UserTable {

    public static final String TABLE = "user";

    public static class COLUMNS{
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
                +"%s integer primary key autoincrement,"
                + "%s text,"
                +"%s text"+");", TABLE, COLUMNS.ID, COLUMNS.NAME, COLUMNS.EMAIL);
}
