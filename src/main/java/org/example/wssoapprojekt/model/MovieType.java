package org.example.wssoapprojekt.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "MovieType")
@XmlEnum
public enum MovieType {
    HORROR,THRILLER,ACTION,COMEDY,FANTASY,SCIENCE_FICTION,WESTERN,R0MANCE,CRIME,HISTORICAL
}
