package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //Comportamiento por defecto "Lazy", no se tre las entidades relacionadas
    @OneToMany(mappedBy = "owner")
    private Set<Car> cars = new HashSet<>();

    public Person(){

    }

    public Person(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
