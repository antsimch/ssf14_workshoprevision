package sg.edu.nus.iss.ssf14_workshoprevision.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Contact implements Serializable {
    
    @NotBlank(message = "Field cannot be blank")
    @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
    private String name;

    @NotEmpty(message = "Field cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Field cannot be blank")
    @Size(min = 7, message = "Invalid phone number")
    private String phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Min(value = 10, message = "Age of contact cannot be younger than 10 years old")
    @Max(value = 100, message = "Age of contact cannot be older than 100 years old")
    private int age;

    private String id;

    public Contact() {
        generateId();
    }

    public Contact(String name, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public void generateId() {

        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < Constants.HEX_STRING_SIZE)
            sb.append(Integer.toHexString(r.nextInt(0, Constants.HEX_VALUE_MAX)));

        this.setId(sb.toString());
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
                + dateOfBirth + ", age=" + age + ", id=" + id + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {

        int calculatedAge = 0;

        if (dateOfBirth != null) 
            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();

        this.dateOfBirth = dateOfBirth;
        setAge(calculatedAge);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
