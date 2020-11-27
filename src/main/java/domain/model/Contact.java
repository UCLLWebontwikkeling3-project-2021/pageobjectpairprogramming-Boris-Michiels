package domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private int contactid;
    private String userid;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime timeStamp;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Contact(int contactid, String userid, String firstName, String lastName, String email, String phoneNumber, LocalDateTime timeStamp) {
        setContacid(contactid);
        setUserid(userid);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setTimeStamp(timeStamp);
    }

    public Contact() {
    }

    private void setContacid(int contacid) {
        this.contactid = contacid;
    }

    public int getContactid() {
        return contactid;
    }

    public void setUserid(String userid) {
        if (userid == null || userid.trim().isEmpty()) throw new DomainException("No userid given");
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) throw new DomainException("No firstname given");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) throw new DomainException("No last name given");
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new DomainException("No email given");
        String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) throw new DomainException("Email not valid");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) throw new DomainException("No phone number given");
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        if (timeStamp == null) throw new DomainException("No date and/or time given");
        if (timeStamp.isAfter(LocalDateTime.now())) throw new DomainException("The date you gave was in the future");
        this.timeStamp = timeStamp;
    }

    public void setTimeStampString(String timeStampString) {
        if (timeStampString == null || timeStampString.trim().isEmpty()) throw new DomainException("No date and/or time given");
        setTimeStamp(LocalDateTime.parse(timeStampString, dateTimeFormatter));
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getDateString() {
        return timeStamp.format(dateFormatter);
    }

    public String getTimeString() {
        return timeStamp.format(timeFormatter);
    }
}