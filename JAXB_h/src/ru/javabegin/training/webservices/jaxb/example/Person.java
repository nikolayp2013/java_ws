package ru.javabegin.training.webservices.jaxb.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Person {

    // обязательно должен быть конструктор без параметров!!
    public Person() {
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @XmlAttribute(name = "id", required = true)
    private int id;
    
    @XmlElement(required = true)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
