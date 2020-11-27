package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "Controller?command=ProfilePage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null) throw new RuntimeException("You need to be logged in to change your password");
        String newPassword = request.getParameter("newPassword");

        try {
            person.setPassword(newPassword);
            getService().updatePerson(person);
            //request.setAttribute("newPwMessage", "Your password has been updated");
            destination = "RedirectController?command=ChangePasswordSucces";
        } catch (DomainException e) {
            request.setAttribute("newPwMessage", e.getMessage());
        }
        return destination;
    }
}