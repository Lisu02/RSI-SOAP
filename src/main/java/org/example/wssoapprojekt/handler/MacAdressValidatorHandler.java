package org.example.wssoapprojekt.handler;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import javax.xml.namespace.QName;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class MacAdressValidatorHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger log = Logger.getLogger(MacAdressValidatorHandler.class.getName());
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("Server : handleMassage()......");
        log.info("Server : handleMassage()......");
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // Tylko odpowiedzi (OUTBOUND)
        if (outbound) {
            try {
                SOAPMessage soapMessage = context.getMessage();
                SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();

                if (header == null) {
                    header = envelope.addHeader();
                }

                // Dodanie własnego nagłówka
                Name headerName = envelope.createName("MojNaglowek", "ns", "https://emarcins.github.io/drBartosiakQuotes/");
                SOAPHeaderElement customHeader = header.addHeaderElement(headerName);
                customHeader.addTextNode("Wartosc mojego naglowka wlasnego tralalelo tralala");

                soapMessage.saveChanges(); // Zapisz zmiany
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {

        System.out.println("Server : handleFault()......");

        return true;
    }

    @Override
    public void close(MessageContext context) {
        log.info("Server : close()......");
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("Server : getHeaders()......");
        return null;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            log.info(soapFault.getFaultString());
            //throw new SOAPFaultException(soapFault);
        }
        catch(SOAPException e) { }
    }
}
