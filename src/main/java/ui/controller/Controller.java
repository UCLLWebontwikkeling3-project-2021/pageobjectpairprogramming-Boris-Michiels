package ui.controller;

import domain.service.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private ContactTracingService contactTracingService = new ContactTracingService();
    private HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command"), destination = "index.jsp";

        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, contactTracingService);
                destination = handler.handleRequest(request, response);
            } catch (Exception exc) {
                System.out.println(exc);
            }
        }
        if (destination.startsWith("Redirect")) {
            destination = destination.replaceFirst("Redirect", "");
            response.sendRedirect(destination);
        } else request.getRequestDispatcher(destination).forward(request, response);
    }
}