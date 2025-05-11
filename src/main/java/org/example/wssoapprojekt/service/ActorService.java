package org.example.wssoapprojekt.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.wssoapprojekt.DAO.ActorDao;
import org.example.wssoapprojekt.DAO.ActorDaoImpl;
import org.example.wssoapprojekt.controller.ActorController;
import org.example.wssoapprojekt.model.Actor;

import java.util.List;

@WebService(endpointInterface = "org.example.wssoapprojekt.controller.ActorController")
public class ActorService implements ActorController {

    private final ActorDao actorDao = ActorDaoImpl.getActorDaoInstance();

    @Override
    @WebMethod(operationName = "addActor")
    @WebResult(name = "addActorResponse")
    public Actor addActor(Actor actor) {
        return actorDao.save(actor);
    }

    @Override
    @WebMethod(operationName = "getActor")
    @WebResult(name = "getActorResponse")
    public Actor getActor(Long id) {
        return actorDao.findById(id).get();
    }

    @Override
    @WebMethod(operationName = "getActorList")
    @WebResult(name = "getActorListResponse")
    public List<Actor> getActorList() {
        return actorDao.findAll();
    }
}
