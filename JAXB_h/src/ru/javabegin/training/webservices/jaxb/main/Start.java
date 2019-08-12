package ru.javabegin.training.webservices.jaxb.main;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ru.javabegin.training.webservices.jaxb.example.People;
import ru.javabegin.training.webservices.jaxb.example.Person;

public class Start {

    public static void main(String[] args) {

        Person person1 = new Person(1, "Ivan");
        Person person2 = new Person(2, "Nikolay");
        Person person3 = new Person(3, "David");
        Person person4 = new Person(4, "Michele");
        Person person5 = new Person(5, "Катя");
        
        People people = new People();
        people.getPersons().add(person1);
        people.getPersons().add(person2);
        people.getPersons().add(person3);
        people.getPersons().add(person4);
        people.getPersons().add(person5);


        try {

            File file = new File("C:\\test\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(People.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(people, file);
            jaxbMarshaller.marshal(people, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
