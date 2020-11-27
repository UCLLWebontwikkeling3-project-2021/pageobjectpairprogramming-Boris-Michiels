package ui.view;

import util.Secret;

import java.sql.*;
import java.util.Properties;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        // set properties for the db connection
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:62021/webontwerp";
        try {
            Class.forName("util.Secret"); // implementation of abstract class Credentials
            Secret.setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class util.Secret with credentials not found!");
        }
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode", "prefer");

        // open the db connection
        Connection connection = DriverManager.getConnection(url, properties);

        // first set the search_path for this connection
        String querySetSearchPath = "SET search_path = web3;";
        Statement statement = connection.createStatement();
        statement.execute(querySetSearchPath);

        // get all the countries
        statement.executeQuery("SELECT * from country;");
        ResultSet result = statement.getResultSet();

        System.out.println("All countries");
        while (result.next()) {
            String name = result.getString("name");
            String capital = result.getString("capital");
            int numberOfVotes = result.getInt("votes");
            int numberOfInhabitants = result.getInt("inhabitants");
            try {    // validation of data stored in database
                System.out.println(name + ", " + capital + ", " + numberOfVotes + ", " + numberOfInhabitants);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        statement.close();

        // close the db connection
        connection.close();
    }
}