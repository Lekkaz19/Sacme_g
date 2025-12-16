package com.tuempresa.bean;

import com.tuempresa.dao.JPAFactory;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

@Named
@ViewScoped
public class DebugBean implements Serializable {

    private String dbStatus;
    private String driverStatus;
    private String jpaStatus;

    @PostConstruct
    public void init() {
        checkDriver();
        checkJdbcConnection();
        checkJpaFactory();
    }

    public void checkDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.driverStatus = "OK: Driver loaded successfully.";
        } catch (ClassNotFoundException e) {
            this.driverStatus = "ERROR: Driver not found. " + e.getMessage();
        }
    }

    public void checkJdbcConnection() {
        String url = "jdbc:mysql://localhost:3306/db_inventario";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                this.dbStatus = "OK: Connection successful!";
            }
        } catch (Exception e) {
            this.dbStatus = "ERROR: " + e.getMessage();
        }
    }

    public void checkJpaFactory() {
        try {
            if (JPAFactory.getFactory() != null) {
                // Try to create an EntityManager
                JPAFactory.getFactory().createEntityManager().close();
                this.jpaStatus = "OK: JPA Factory initialized and EntityManager created.";
            }
        } catch (Throwable e) {
            this.jpaStatus = "ERROR: " + e.getMessage();
            e.printStackTrace(); // Look at server console for full trace if needed
        }
    }

    public String getDbStatus() {
        return dbStatus;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public String getJpaStatus() {
        return jpaStatus;
    }
}
