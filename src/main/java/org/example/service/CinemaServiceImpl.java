package org.example.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import org.example.dao.MovieDAO;
import org.example.dao.ReservationDAO;
import org.example.model.Movie;
import org.example.model.Reservation;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

//@WebService(
//    name = "CinemaService",
//    serviceName = "CinemaService",
//    portName = "CinemaServicePort",
//    targetNamespace = "http://service.example.org/"
//)
//@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
//@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP11HTTP_BINDING)
@MTOM(enabled = true, threshold = 1024)
@WebService(endpointInterface = "org.example.service.CinemaService")
//@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class CinemaServiceImpl implements CinemaService {
    private final MovieDAO movieDAO = new MovieDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();

    @WebMethod(operationName = "getAllMovies")
    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    @WebMethod(operationName = "getAvailableSeats")
    @Override
    public List<String> getAvailableSeats(String title, String date, String time) {
        return new ArrayList<>(movieDAO.getAvailableSeats(title, date, time));
    }

    @WebMethod(operationName = "makeReservation")
    @Override
    public Long makeReservation(Reservation reservation) {
        return reservationDAO.createReservation(reservation);
    }

    @WebMethod(operationName = "cancelReservation")
    @Override
    public boolean cancelReservation(String reservationId) {
        return reservationDAO.cancelReservation(reservationId);
    }

    @WebMethod(operationName = "modifyReservation")
    @Override
    public boolean modifyReservation(String reservationId, List<String> newSeats) {
        return reservationDAO.modifyReservation(reservationId, newSeats);
    }

    @WebMethod(operationName = "getAllReservations")
    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    @WebMethod(operationName = "getReservationPDF")
    @XmlSchemaType(name = "base64Binary")
    @Override
    public byte[] getReservationPDF(Long reservationId) {
        try {
            Reservation reservation = reservationDAO.getReservation(reservationId);
            if (reservation == null) {
                //return null;
            }

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();
            
            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reservation Confirmation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Add reservation details
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph("Reservation ID: " + reservationId, normalFont));
            document.add(new Paragraph("Movie: " + "reservation.getMovieTitle()", normalFont));
//            document.add(new Paragraph("Reservation ID: " + reservation.getReservationId(), normalFont));
//            document.add(new Paragraph("Movie: " + reservation.getMovieTitle(), normalFont));
//            document.add(new Paragraph("Date: " + reservation.getScreeningDate(), normalFont));
//            document.add(new Paragraph("Time: " + reservation.getScreeningTime(), normalFont));
//            document.add(new Paragraph("Seats: " + String.join(", ", reservation.getSeats()), normalFont));
//            document.add(new Paragraph("Customer Name: " + reservation.getCustomerName(), normalFont));
//            document.add(new Paragraph("Customer Email: " + reservation.getCustomerEmail(), normalFont));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 