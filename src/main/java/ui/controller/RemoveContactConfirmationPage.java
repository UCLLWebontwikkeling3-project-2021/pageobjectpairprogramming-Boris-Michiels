package ui.controller;

import domain.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveContactConfirmationPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int contactid = Integer.parseInt(request.getParameter("contactid"));
        Contact contact = getService().getOneContact(contactid);
        request.setAttribute("contact", contact);
        return "removeContactConfirmation.jsp";
    }
}