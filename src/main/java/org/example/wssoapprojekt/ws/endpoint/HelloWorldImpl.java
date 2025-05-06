package org.example.wssoapprojekt.ws.endpoint;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import jakarta.xml.ws.soap.SOAPBinding;

@WebService(endpointInterface = "org.example.wssoapprojekt.ws.endpoint.HelloWorld")
//@HandlerChain(file = "handler-chain.xml") na razie bez handlera
@MTOM
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class HelloWorldImpl implements HelloWorld{

    @Override
    @WebMethod(operationName = "wzorcowaMetoda")
    @WebResult(name = "wzorcowaMetodaResponse")
    public String wzorcowaMetoda(String wiadomosc) {
        return "Wzorcowa wiadomosc: " + wiadomosc;
    }
}
