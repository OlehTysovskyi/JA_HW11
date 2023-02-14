package source.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.User;
import source.domain.UserRole;
import source.service.UserService;
import source.service.impl.UserServiceImpl;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = UserServiceImpl.getUserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		
		System.out.println(email);
		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(password);


		if (!email.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !password.isEmpty()) {
			userService.create(new User(email, firstname, lastname, UserRole.USER.toString(), password));
		}
		
		response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
        
	}

}