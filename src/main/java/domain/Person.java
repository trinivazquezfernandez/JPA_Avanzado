package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    public String dni;
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
