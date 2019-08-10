package ru.javabegin.training.webservices.testservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "TestWS")
public class TestWS {

    @WebMethod(operationName = "correctName")
    public String correctName(@WebParam(name = "name") String name) {
        return "My name is "+name;  
    }
}
