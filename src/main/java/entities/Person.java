package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fName")
    private String fName;
    @Column(name = "lName")
    private String lName;

    @Column(name = "phone")
    private int phone;
    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private Date created;
    @Temporal(TemporalType.DATE)
    @Column(name = "lastEdited")
    private Date lastEdited;
    
    public Person() {
    }

    public Person(String fName, String lName, int phone,Date created) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.created = new Date();
    }

    public Person(Long id, String fName, String lName, int phone, Date created) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.created = new Date();
    }

    public Person(Long id, String fName, String lName, int phone) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone=" + phone +
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                '}';
    }
}
