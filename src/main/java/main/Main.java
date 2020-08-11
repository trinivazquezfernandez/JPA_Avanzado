package main;

import domain.Person;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

        System.out.println("----- consulta -----");

        //Según la formación estás dos sentencias deberían provocar outputs diferentes pero no es así
        //Si que encontramos diferencia cuando el id no existe. En el caso de getReference el error da cuadno vamos a acceder al campo name
        //En el caso de find el error da cuando intentamos acceder al dni
        Person person1 = em.getReference(Person.class, "123456789A");
        //Person person1 = em.find(Person.class, "123456789A");

        System.out.println(person1.getDni());
        System.out.println(person1.getName());
        System.out.println(person1.getClass().getName());

        em.close();
        emf.close();
    }
}
