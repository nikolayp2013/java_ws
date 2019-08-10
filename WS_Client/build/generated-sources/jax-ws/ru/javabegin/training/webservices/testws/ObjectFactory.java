
package ru.javabegin.training.webservices.testws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.javabegin.training.webservices.testws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CorrectNameResponse_QNAME = new QName("http://testservice.webservices.training.javabegin.ru/", "correctNameResponse");
    private final static QName _CorrectName_QNAME = new QName("http://testservice.webservices.training.javabegin.ru/", "correctName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.javabegin.training.webservices.testws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CorrectName }
     * 
     */
    public CorrectName createCorrectName() {
        return new CorrectName();
    }

    /**
     * Create an instance of {@link CorrectNameResponse }
     * 
     */
    public CorrectNameResponse createCorrectNameResponse() {
        return new CorrectNameResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CorrectNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testservice.webservices.training.javabegin.ru/", name = "correctNameResponse")
    public JAXBElement<CorrectNameResponse> createCorrectNameResponse(CorrectNameResponse value) {
        return new JAXBElement<CorrectNameResponse>(_CorrectNameResponse_QNAME, CorrectNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CorrectName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testservice.webservices.training.javabegin.ru/", name = "correctName")
    public JAXBElement<CorrectName> createCorrectName(CorrectName value) {
        return new JAXBElement<CorrectName>(_CorrectName_QNAME, CorrectName.class, null, value);
    }

}
