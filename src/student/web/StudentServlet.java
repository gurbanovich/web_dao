package student.web;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.logic.action.*;
import student.logic.dao.*;
import student.logic.dto.*;
import student.logic.sql.*;

@SuppressWarnings("serial")
public class StudentServlet extends HttpServlet {

	public HttpServletRequest req;
	public HttpServletResponse resp;
	public Student stud = new Student();

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, PersistException, SQLException {
		List<Object> studentList = getAction(req);
		req.setCharacterEncoding("UTF-8");
		stud.setStudentList(studentList);
		req.setAttribute("stud", stud);
		getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
	}

	private List<Object> getAction(HttpServletRequest req) throws PersistException, SQLException {
		Action result = null;
		List<Object> col = null;
		Database database = new MySqlDatabase();
		if (req.getParameter("Add Student") != null) {
			Student st = new Student();
			st.setFirstName(req.getParameter("firstName").trim());
			st.setSecondName(req.getParameter("secondName").trim());
			st.setEnterYear(Integer.parseInt(req.getParameter("enterYear").trim()));
			result = new ShowStudentAction(database, st, 0);
			col = result.createAction();
		}
		if (req.getParameter("Delete student") != null) {
			Student st = new Student();
			st.setId(Integer.parseInt(req.getParameter("studentId").trim()));
			result = new ShowStudentAction(database, st, 0);
			col = result.deleteAction();
		}
		if (req.getParameter("Update student") != null) {
			Student st = new Student();
			st.setFirstName(req.getParameter("firstName").trim());
			st.setSecondName(req.getParameter("secondName").trim());
			st.setEnterYear(Integer.parseInt(req.getParameter("enterYear").trim()));
			st.setId(Integer.parseInt(req.getParameter("studentId").trim()));
			result = new ShowStudentAction(database, st, 0);
			col = result.updateAction();
		}
		if (req.getParameter("Show all students") != null) {
			result = new ShowStudentAction(database, null, 0);
			col = result.showAction();
		}
		if (req.getParameter("Show Student") != null) {
			Integer key = Integer.parseInt(req.getParameter("studentId"));
			result = new ShowStudentAction(database, null, key);
			col = result.showStudAction();
		}
		return col;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processRequest(req, resp);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processRequest(req, resp);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
