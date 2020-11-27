package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "RedirectController?command=ProfilePage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null) throw new RuntimeException("You need to be logged in to delete your account");
        String confirmation = request.getParameter("confirmation");

        if (!confirmation.isEmpty() && confirmation.equals("Delete")) {
            getService().deletePerson(person);
            getService().removeContacts(person.getUserid());
            session.invalidate();
            //request.setAttribute("deleteMessage", "Your account has been removed");
            destination =  "RedirectController?command=DeleteSucces";
        }
        return destination;
    }
}