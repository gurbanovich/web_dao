package student.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.logic.dto.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Student stud = new Student();
		Subject subj = new Subject();
		Mark mark = new Mark();
		req.setAttribute("stud", stud);
		req.setAttribute("subj", subj);
		req.setAttribute("mark", mark);
		getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

}
