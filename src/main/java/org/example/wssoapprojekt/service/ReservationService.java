package org.example.wssoapprojekt.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.istack.ByteArrayDataSource;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import org.example.wssoapprojekt.DAO.*;
import org.example.wssoapprojekt.controller.ReservationController;
import org.example.wssoapprojekt.model.*;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@WebService(endpointInterface = "org.example.wssoapprojekt.controller.ReservationController")
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@MTOM(enabled = true, threshold = 0)
public class ReservationService implements ReservationController {

    private final MovieDao movieDao = MovieDaoImpl.getMovieDaoInstance();
    private final ReservationDao reservationDao = ReservationDaoImpl.getReservationDaoInstance();
    private final ShowingDao showingDao = ShowingDaoImpl.getShowingDaoInstance();


    @Override
    @WebMethod(operationName = "createReservation")
    @WebResult(name = "createReservationResponse")
    public Reservation createReservation(Long showingId, Reservation reservation) {
        if(showingDao.findById(showingId).isPresent() && !reservation.getSeatLocation().isEmpty()){
            Showing showing = showingDao.findById(showingId).get();
            reservation.setShowing(showing);
            showing.makeSeatReservation(reservation.getSeatLocation(),reservation.getReservationId());
            reservationDao.save(reservation);
        }
        return null;
    }

    @Override
    @WebMethod(operationName = "deleteReservation")
    @WebResult(name = "deleteReservationResponse")
    public void deleteReservation(Long reservationId) {
        if(reservationDao.findById(reservationId).isPresent()){
            Reservation reservation = reservationDao.findById(reservationId).get();
            reservation.getShowing().removeAllSeatReservation(reservation);
            reservationDao.delete(reservationId);
        }
    }

    @Override
    @WebMethod(operationName = "findAllReservations")
    @WebResult(name = "findAllReservationsResponse")
    public List<Reservation> findAllReservations() {
        return reservationDao.findAll();
    }

    @Override
    @WebMethod(operationName = "findAllReservationsForMovie")
    @WebResult(name = "findAllReservationsForMovieResponse")
    public List<Reservation> findAllReservationsForMovie(Long movieId) {
        return reservationDao.findByMovieId(movieId);
    }

    @WebMethod(operationName = "getReservationPDF")
    @XmlSchemaType(name = "base64Binary")
    @XmlMimeType("application/pdf")
    @Override
    public DataHandler getReservationPDF(Long reservationId) {
        try {
            Optional<Reservation> optionalReservation = reservationDao.findById(reservationId);
            Reservation reservation;
            if (optionalReservation.isEmpty()) {
                reservation = new Reservation();
                Showing showing = new Showing();
                showing.setShowingId(-1L);
                showing.setShowingDateAndTime("Jutro");
                Movie movie = new Movie(
                        -1L,
                        "Tytul",
                        "Rezyser",
                        "Wczoraj",
                        "Opis",
                        MovieType.ACTION,
                        null
                );
                showing.setMovie(movie);
                reservation.setShowing(showing);
                LinkedList<SeatLocation> seatLocations = new LinkedList<SeatLocation>();
                seatLocations.add(new SeatLocation(0,0));
                seatLocations.add(new SeatLocation(1,1));
                reservation.setSeatLocation(seatLocations);
            }else{
                reservation = optionalReservation.get();
            }

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();

            // Dodanie tytulu
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reservation Confirmation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Szczegoly rezerwacji
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph("Reservation ID: " + reservationId, normalFont));
            document.add(new Paragraph("Movie title: " + reservation.getShowing().getMovie().getTitle(), normalFont));
            document.add(new Paragraph("Showing date: " + reservation.getShowing().getShowingDateAndTime(), normalFont));
            document.add(new Paragraph("Reserved seats (in progress): ", normalFont));

            document.close();
            byte[] pdfBytes = baos.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
            return new DataHandler(dataSource);


            //return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
