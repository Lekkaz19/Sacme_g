package com.tuempresa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAFactory {
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("bdipa");
        } catch (Exception e) {
            System.err.println("ERROR al inicializar EntityManagerFactory:");
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar JPA", e);
        }
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory no está inicializado");
        }
        return factory.createEntityManager();
    }

    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
