package org.example.wssoapprojekt.controller;

import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.ws.soap.MTOM;
import org.example.wssoapprojekt.model.Reservation;

import java.util.List;

@WebService(name = "ReservationService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
@MTOM(enabled = true, threshold = 0)
public interface ReservationController {
    @WebMethod
    Reservation createReservation(
            @WebParam(name = "showingId") Long showingId,
            @WebParam(name = "reservation") Reservation reservation
    );

    @WebMethod
    void deleteReservation(@WebParam(name = "reservationId")Long reservationId);
    @WebMethod
    void updateReservation(@WebParam(name = "reservation") Reservation reservation);

    @WebMethod
    List<Reservation> findAllReservations();
    @WebMethod
    Reservation findReservation(@WebParam(name = "reservationId")Long reservationId);

    @WebMethod
    List<Reservation> findAllReservationsForMovie(@WebParam(name = "movieId")Long movieId);

    @WebMethod(operationName = "getReservationPDF")
    @XmlSchemaType(name = "base64Binary")
    @XmlMimeType("application/pdf")
    DataHandler getReservationPDF(
        @WebParam(name = "reservationId") Long reservationId
    );

}
