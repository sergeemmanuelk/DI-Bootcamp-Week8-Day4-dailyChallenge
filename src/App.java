import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import db.DbConnect;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(new App().callBuilInFunction("I am a student of Developer Institute"));
    }

    private String callBuilInFunction(String sentence) {
        String result = sentence;
        final String sql = "{ ? = call initcap( ? ) }";

        try {
            Connection conn = new DbConnect().connect();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, sentence);
            stmt.execute();

            result = stmt.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
