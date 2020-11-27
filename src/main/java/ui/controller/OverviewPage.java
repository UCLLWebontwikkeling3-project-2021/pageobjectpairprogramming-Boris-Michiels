package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OverviewPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null || !person.getRoleString().equals("admin")) throw new RuntimeException("You need admin privileges to view this page");
        List<Person> persons = getService().getAllPersons();
        request.setAttribute("persons", persons);
        return "personOverview.jsp";
    }
}