package domain.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utility {
    public static void checkRole(HttpServletRequest request, Role[] roles) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null) throw new NotAuthorizedException("You have insufficient rights to request this page");
        Role role = person.getRole();
        for (Role authRole : roles) {
            if (role == authRole) return;
        }
        throw new NotAuthorizedException("You have insufficient rights to request this page");
    }
}