package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "RedirectController?command=ContactsPage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null) throw new RuntimeException("You need to be logged in to remove a contact");
        String confirmation = request.getParameter("confirmation");

        if (!confirmation.isEmpty() && confirmation.equals("Remove")) {
            int contactid = Integer.parseInt(request.getParameter("contactid"));
            getService().removeOneContact(contactid);
            //request.setAttribute("contactRemovedMessage", "Contact has been removed");
            destination = "RedirectController?command=RemoveContactSucces";
        }
        return destination;
    }
}