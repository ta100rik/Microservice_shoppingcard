package com.CartService.cartService.dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoConnectionPoolingMySQL {
    private static final BaseDaoConnectionPoolingMySQL INSTANCE = new BaseDaoConnectionPoolingMySQL();
    private static List<Connection> openConnections;
    private static List<Connection> usedConnections = new ArrayList<>();
    private int INITIAL_POOL_SIZE = 10;
    private String dbuserName = "iac";
    private String dbpassword = "I@c202020";
    private String url = "jdbc:mysql://85.214.169.10:3306/checkoutService?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String driverName = "com.mysql.cj.jdbc.Driver";
    private  Connection conn = null;

    private BaseDaoConnectionPoolingMySQL(){
        if(INSTANCE == null) {
            openConnections = createConnection(url, dbuserName, dbpassword);
        }
    }

    private List<Connection> createConnection(String url, String user, String password) {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        try {
            //mysql database connectivity
            Class.forName(driverName);
            long totaltime = System.currentTimeMillis();
            System.out.println("[INFO] Starting connection pool");
            for(int i = 0; i< INITIAL_POOL_SIZE; i++) {
                long time = System.currentTimeMillis();
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("[INFO] Database connection established " +
                        "("+i+" of "+(INITIAL_POOL_SIZE-1)+") " +
                        "["+(System.currentTimeMillis()-time)+"ms]");
                pool.add(conn);
            }
            System.out.println("[DONE] "+(System.currentTimeMillis()-totaltime)+"ms");
            return pool;
        } catch (Exception e) {
            System.out.println("[ERROR] Couldn't make a connection!");
            System.out.println("[STACKTRACE]");
            e.printStackTrace();
            System.out.println("[STACKTRACE]");
            return null;
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = openConnections.get(openConnections.size() - 1);
            usedConnections.add(connection);
            openConnections.remove(connection);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean releaseConnection(Connection connection) {
        try {
            openConnections.add(connection);
            usedConnections.remove(connection);
            return true;
        } catch (Exception e){
            System.out.println("[ERROR] An error has occurred while trying to release a connection!");
            System.out.println("[STACKTRACE]");
            e.printStackTrace();
            System.out.println("[STACKTRACE]");
            return false;
        }
    }

    public static BaseDaoConnectionPoolingMySQL getInstance(){
        return INSTANCE;
    }
}
