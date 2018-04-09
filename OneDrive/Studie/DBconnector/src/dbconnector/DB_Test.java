package DBconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_Test {

    public static void main(String[] args) {
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query = 
                      "SELECT * "
                    + "FROM `city` "
                    + "ORDER BY `CityName`;";
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                String name = res.getString("CityName");
                if(name.contains("INSERT INTO"))
                    System.out.println(name);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }
    
    
}