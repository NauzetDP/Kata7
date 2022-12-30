package katas.kata7;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightListReaderDB {
    
    private final String dbName;
    private final String tableName;
    
    public FlightListReaderDB(String dbName, String tableName) {
        this.dbName = dbName;
        this.tableName = tableName;
    }
    
    public Iterable<Flight> flights() {
        return new Iterable<Flight>() {
            @Override
            public Iterator<Flight> iterator() {
                try {
                    return createIterator();
                } catch (FileNotFoundException ex) {
                    return null;
                }
            }
        };
    }
    
    private Iterator<Flight> createIterator() throws FileNotFoundException {
        return new Iterator<Flight>() {
            
            Iterator<Flight> ite = iteratorFlight();
            
            @Override
            public boolean hasNext() {
                return ite.hasNext();
            }

            @Override
            public Flight next() {
                return ite.next();
            }
            
            private Connection connect() {
                String url = "jdbc:sqlite:" + dbName;
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(url);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                return conn;
            }
            
            private Iterator<Flight> iteratorFlight() {
                List<Flight> listFlight = new ArrayList<>();
                String sql = "SELECT * FROM " + tableName;
                try (Connection conn = connect();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)){
                    while (rs.next()) {
                        listFlight.add(new Flight(rs.getInt("DAY_OF_WEEK"),
                                                rs.getInt("DEP_TIME"),
                                                rs.getInt("DEP_DELAY"),
                                                rs.getInt("ARR_TIME"),
                                                rs.getInt("ARR_DELAY"),
                                                rs.getInt("AIR_TIME"),
                                                rs.getInt("DISTANCE")
                                                ));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                Iterator<Flight> ite = listFlight.iterator();
                return ite;
            }
        };
    }
}