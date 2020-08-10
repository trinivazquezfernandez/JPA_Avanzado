package main;

import domain.Person;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        PersistenceProvider hpp = new HibernatePersistenceProvider();
        PersistenceUnitInfo persistenceInfo = new SimplePersistenceInfo("no-main");

        Map<String, String> properties = new HashMap<>();

        properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:test");
        properties.put("javax.persistence.schema-generation.database.action", "create");
        properties.put("hibernate.show_sql", "true");

        EntityManagerFactory emf = hpp.createContainerEntityManagerFactory(persistenceInfo, properties);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person person = new Person();
        person.setDni("123456789A");
        person.setName("María");

        em.persist(person);
        em.getTransaction().commit();
        em.close();

        emf.close();
    }
}
