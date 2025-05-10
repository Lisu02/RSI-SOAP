package org.example.wssoapprojekt.controller;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name = "HelloWorldSerwis")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface HelloWorld {

    @WebMethod
    String wzorcowaMetoda(String wiadomosc);

}
