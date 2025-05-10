package org.example.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import org.example.model.Movie;
import org.example.model.Reservation;

import java.util.List;

//@WebService(
//    name = "CinemaService",
//    serviceName = "CinemaService",
//    portName = "CinemaServicePort",
//    targetNamespace = "http://service.example.org/"
//)
//@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
//@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP11HTTP_BINDING)
//@MTOM(enabled = true, threshold = 1024)
@WebService(name = "CinemaService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface CinemaService {
    @WebMethod(operationName = "getAllMovies")
    List<Movie> getAllMovies();

    @WebMethod(operationName = "getAvailableSeats")
    List<String> getAvailableSeats(
        @WebParam(name = "title") String title,
        @WebParam(name = "date") String date,
        @WebParam(name = "time") String time
    );

    @WebMethod(operationName = "makeReservation")
    Long makeReservation(
        @WebParam(name = "reservation") Reservation reservation
    );

    @WebMethod(operationName = "cancelReservation")
    boolean cancelReservation(
        @WebParam(name = "reservationId") String reservationId
    );

    @WebMethod(operationName = "modifyReservation")
    boolean modifyReservation(
        @WebParam(name = "reservationId") String reservationId,
        @WebParam(name = "newSeats") List<String> newSeats
    );

    @WebMethod(operationName = "getAllReservations")
    List<Reservation> getAllReservations();

    @WebMethod(operationName = "getReservationPDF")
    @XmlSchemaType(name = "base64Binary")
    byte[] getReservationPDF(
        @WebParam(name = "reservationId") Long reservationId
    );
} 