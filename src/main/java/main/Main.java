package main;

import domain.Car;
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

        Person ana = new Person();
        ana.setName("Ana");

        Car car = new Car();
        car.setModel("Suzuki");
        car.setOwner(ana);

        Person juan = new Person();
        juan.setName("Juan");
        juan.getCars().add(car);//Esto provoca una incoherencia. Vemos que al hacer commit ignora este add

        em.persist(ana);
        em.persist(car);
        em.persist(juan);

        em.refresh(car);
        em.refresh(juan);

        System.out.println("propietario " + car.getOwner().getName());
        System.out.println("coches juan " + juan.getCars().size());

        //Este c aso solo fucnina si especificamos como estrategia de persistencia PERSIST en la entidad Car
        Person trini = new Person();
        trini.setName("Trini");
        Car alfa = new Car();
        alfa.setModel("Alfa");
        alfa.setOwner(trini);

        em.persist(alfa);

        car.setOwner(new Person("Antonio"));
        em.merge(car);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
