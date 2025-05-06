package org.example.wssoapprojekt.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDate;
/*
Ogólnie te adnotacje @XmlElement nie są konieczne bo aplikacja sama je zmapuje do XML
ale ma to znaczenie w przypadku kolejności ich wyświetlania albo jak byśmy chciali w przyszłości
coś konkretniejszego z nimi robić lub zmieniać nazwy itp
 */
@XmlRootElement(name = "Actor")
@XmlType(propOrder = {"firstName","lastName","birthDay","countryOfOrigin"})
public class Actor {

    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    @XmlSchemaType(name = "dateTime")
    private LocalDate birthDay;
    @XmlElement
    private Country countryOfOrigin;

    public Actor(){}

    public Actor(String firstName, String lastName, LocalDate birthDay ,Country country){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.countryOfOrigin = country;
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public LocalDate getBirthDay() {return birthDay;}
    public void setBirthDay(LocalDate birthDay) {this.birthDay = birthDay;}

    public Country getCountryOfOrigin() {return countryOfOrigin;}
    public void setCountryOfOrigin(Country countryOfOrigin) {this.countryOfOrigin = countryOfOrigin;}
}
