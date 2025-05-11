package org.example.wssoapprojekt.controller;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.example.wssoapprojekt.model.Showing;

import java.util.List;

@WebService(name = "ShowingService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface ShowingController {

    @WebMethod
    Showing addShowing(Showing showing);

    @WebMethod
    void deleteShowing(@WebParam(name = "showingId") Long showingId);

    @WebMethod
    Showing getShowing(@WebParam(name = "showingId") Long showingId);

    @WebMethod
    List<Showing> getShowingList();
}
