package ru.javabegin.training.webservices.testws;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class CallWS {

    private String name;
    private String correctedName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorrectedName() {
        return correctedName;
    }

    public void setCorrectedName(String correctedName) {
        this.correctedName = correctedName;
    }

    public void correctName() {
        TestWS_Service service = new TestWS_Service();
        TestWS port = service.getTestWSPort();
        correctedName = port.correctName(name);
    }
    
    
}
