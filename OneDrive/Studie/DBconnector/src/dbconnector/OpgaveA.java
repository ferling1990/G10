/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author jonab
 */
public class OpgaveA {

    public static void main(String[] args) {
        try {
            Connection c = new DBconnector.DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT DISTINCT(`countryName`)"
                    + "FROM `country`;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                String name = res.getString("CountryName");
                if (name.contains("INSERT INTO")) {
                    System.out.println(name);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }

}
