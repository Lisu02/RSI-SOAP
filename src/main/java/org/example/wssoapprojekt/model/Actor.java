package org.example.wssoapprojekt.model;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;
import java.util.Random;

import static org.example.wssoapprojekt.util.GlobalUtilities.random;

/*
Ogólnie te adnotacje @XmlElement nie są konieczne bo aplikacja sama je zmapuje do XML
ale ma to znaczenie w przypadku kolejności ich wyświetlania albo jak byśmy chciali w przyszłości
coś konkretniejszego z nimi robić lub zmieniać nazwy itp
 */
@XmlRootElement(name = "Actor")
//@XmlType(propOrder = {"firstName","lastName","birthDay","countryOfOrigin"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Actor {

    private Long id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
  //  @XmlSchemaType(name = "dateTime")
    private String birthDay;
    @XmlElement
    private Country countryOfOrigin;

    public Actor(){
        this("testName","testLastname","LocalDate.now()",Country.values()[random.nextInt(0,7)]);
    }

    public Actor(String firstName, String lastName, String birthDay ,Country country){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.countryOfOrigin = country;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getBirthDay() {return birthDay;}
    public void setBirthDay(String birthDay) {this.birthDay = birthDay;}

    public Country getCountryOfOrigin() {return countryOfOrigin;}
    public void setCountryOfOrigin(Country countryOfOrigin) {this.countryOfOrigin = countryOfOrigin;}
}
