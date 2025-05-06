package org.example.wssoapprojekt.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "Country")
@XmlEnum
public enum Country {
    USA,POLAND,GERMANY,FRANCE,CANADA,MEXICO,CHINA,RUSSIA,JAPAN
}
