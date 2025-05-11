package org.example.wssoapprojekt.controller;

import jakarta.jws.WebMethod;
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
    void deleteShowing(Long showingId);

    @WebMethod
    Showing getShowing(Long id);

    @WebMethod
    List<Showing> getShowingList();
}
