package org.example.wssoapprojekt.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.wssoapprojekt.DAO.ShowingDao;
import org.example.wssoapprojekt.DAO.ShowingDaoImpl;
import org.example.wssoapprojekt.controller.ShowingController;
import org.example.wssoapprojekt.model.Showing;

import java.util.List;

@WebService(endpointInterface = "org.example.wssoapprojekt.controller.ShowingController")
public class ShowingService implements ShowingController {

    private final ShowingDao showingDao = ShowingDaoImpl.getShowingDaoInstance();


    @Override
    @WebMethod(operationName = "addShowing")
    @WebResult(name = "addShowingResponse")
    public Showing addShowing(Showing showing) {
        return showingDao.save(showing);
    }

    @Override
    @WebMethod(operationName = "deleteShowing")
    @WebResult(name = "deleteShowingResponse")
    public void deleteShowing(Long showingId) {
        showingDao.delete(showingId);
    }

    @Override
    @WebMethod(operationName = "getShowing")
    @WebResult(name = "getShowingResponse")
    public Showing getShowing(Long showingId) {
        return showingDao.findById(showingId).get();
    }

    @Override
    @WebMethod(operationName = "getShowingList")
    @WebResult(name = "getShowingListResponse")
    public List<Showing> getShowingList() {
        return showingDao.findAll();
    }
}
