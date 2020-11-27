package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AddContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "Controller?command=ContactsPage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null) throw new RuntimeException("You need to be logged in to add a contact");
        ArrayList<String> errors = new ArrayList();
        Contact contact = new Contact();

        setUserid(person, contact, request, errors);
        setFirstName(contact, request, errors);
        setLastname(contact, request, errors);
        setEmail(contact, request, errors);
        setPhoneNumber(contact, request, errors);
        setTimeStamp(contact, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addContact(contact);
                destination = "RedirectController?command=ContactsPage";
            } catch (DbException d) {
                errors.add(d.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return destination;
    }

    private void setUserid(Person person, Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String userid = person.getUserid();
        try {
            contact.setUserid(userid);
        } catch (DomainException d) {
            errors.add(d.getMessage());
        }
    }

    private void setFirstName(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            contact.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
            request.setAttribute("firstNameClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("firstNameClass", "has-error");
        }
    }

    private void setLastname(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            contact.setLastName(request.getParameter("lastName"));
            request.setAttribute("lastNamePreviousValue", lastName);
            request.setAttribute("lastNameClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("lastNameClass", "has-error");
        }
    }

    private void setEmail(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            contact.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
            request.setAttribute("emailClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("emailClass", "has-error");
        }
    }

    private void setPhoneNumber(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String phoneNumber = request.getParameter("phoneNumber");
        try {
            contact.setPhoneNumber(phoneNumber);
            request.setAttribute("phoneNumberPreviousValue", phoneNumber);
            request.setAttribute("phoneNumberClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("phoneNumberClass", "has-error");
        }
    }

    private void setTimeStamp(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String timeStamp = request.getParameter("dateTime");
        try {
            contact.setTimeStampString(timeStamp);
            request.setAttribute("dateTimePreviousValue", timeStamp);
            request.setAttribute("dateTimeClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("dateTimeClass", "has-error");
        }
    }
}