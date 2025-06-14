package org.example.wssoapprojekt.controller;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.example.wssoapprojekt.model.Actor;

import java.util.List;

@WebService(name = "ActorService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
@HandlerChain(file = "handler-chain.xml")
public interface ActorController {

    @WebMethod
    Actor addActor(Actor actor);

    @WebMethod
    Actor getActor(@WebParam(name = "actorId")Long actorId);

    @WebMethod
    List<Actor> getActorList();
}
