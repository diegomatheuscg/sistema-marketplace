package com.marketplace.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    // CORREÇÃO: O nome da unidade de persistência foi ajustado para "persistence",
    // para corresponder ao que está definido no arquivo persistence.xml.
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
