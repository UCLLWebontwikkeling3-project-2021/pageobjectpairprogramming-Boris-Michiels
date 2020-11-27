package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordSucces extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null) throw new RuntimeException("You need to be logged in to view this page");
        request.setAttribute("newPwMessage", "Your password has been updated");
        return "Controller?command=ProfilePage";
    }
}