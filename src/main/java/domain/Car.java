package domain;

import javax.persistence.*;

@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;

    //Comportamiento por defecto "Eager", se trae la entidad relacionada.
    //Si queremos forzar que la carga sea lazy, debemos añadir el atributo fetch de tipo lazy
    //A la hora de persistir los objetos en la bbdd es necesario que el objeto relacionado esté persistido previamente.
    //La estategia de propagacion de persistencia por defecto es ALL.
    // Si queremos modificarla deberemos hacerlo modiciando el atributo cascade
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Person owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
