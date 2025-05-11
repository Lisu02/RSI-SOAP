package org.example.wssoapprojekt.controller;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.example.wssoapprojekt.model.Actor;

import java.util.List;

@WebService(name = "ActorService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface ActorController {

    @WebMethod
    Actor addActor(Actor actor);

    @WebMethod
    Actor getActor(Long id);

    @WebMethod
    List<Actor> getActorList();
}
