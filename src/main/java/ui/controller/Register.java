package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "Controller?command=ProfilePage";
        ArrayList<String> errors = new ArrayList<>();
        Person person = new Person();

        setUserid(person, request, errors);
        setFirstName(person, request, errors);
        setLastName(person, request, errors);
        setEmail(person, request, errors);
        setPassword(person, request, errors);
        setRole(person, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addPerson(person);
                HttpSession session = request.getSession();
                session.setAttribute("person", person);
                destination = "RedirectController?command=ProfilePage";
            } catch (DbException d) {
                errors.add(d.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return destination;
    }

    private void setUserid(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String userid = request.getParameter("userid");
        try {
            person.setUserid(userid);
            request.setAttribute("useridPreviousValue", userid);
            request.setAttribute("useridClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("useridClass", "has-error");
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
            request.setAttribute("firstNameClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("firstNameClass", "has-error");
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            person.setLastName(request.getParameter("lastName"));
            request.setAttribute("lastNamePreviousValue", lastName);
            request.setAttribute("lastNameClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("lastNameClass", "has-error");
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
            request.setAttribute("emailClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("emailClass", "has-error");
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            person.setPassword(password);
            request.setAttribute("passwordPreviousValue", password);
            request.setAttribute("passwordClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("passwordClass", "has-error");
        }
    }

    private void setRole(Person person, HttpServletRequest request, ArrayList<String> errors) {
        try {
            person.setRoleString("user");
        } catch (DomainException d) {
            errors.add(d.getMessage());
        }
    }
}