package org.example.wssoapprojekt.ws.dto;

import org.example.wssoapprojekt.model.MovieType;

import java.awt.*;
import java.util.Date;

public record MovieDTO(
        String title,
        String author,
        Date releseDate,
        String description,
        MovieType movieType,
        Image image
) {
}
