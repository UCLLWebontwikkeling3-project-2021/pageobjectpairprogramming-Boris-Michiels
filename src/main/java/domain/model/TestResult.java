package domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestResult {
    private String userid;
    private LocalDate date;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TestResult(String userid, LocalDate date) {
        setUserid(userid);
        setDate(date);
    }

    public TestResult() {
    }

    public void setUserid(String userid) {
        if (userid == null || userid.trim().isEmpty()) throw new DomainException("No userid given");
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new DomainException("No date given");
        if (date.isAfter(LocalDate.now())) throw new DomainException("The date you gave was in the future");
        this.date = date;
    }

    public void setDateString(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) throw new DomainException("No date given");
        setDate(LocalDate.parse(dateString, dateFormatter));
    }

    public LocalDate getDate() {
        return date;
    }
}