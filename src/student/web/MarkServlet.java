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
public class MarkServlet extends HttpServlet {

	public HttpServletRequest req;
	public HttpServletResponse resp;
	public Mark mark = new Mark();

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, PersistException, SQLException {
		List<Object> markList = getAction(req);
		req.setCharacterEncoding("UTF-8");
		mark.setMarkList(markList);
		req.setAttribute("mark", mark);
		getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
	}

	
	private List<Object> getAction(HttpServletRequest req) throws PersistException, SQLException {
		Action result = null;
		List<Object> col = null;
		Database database = new MySqlDatabase();
		if (req.getParameter("Add mark") != null) {
			Mark m = new Mark();
			m.setStudentId(Integer.parseInt(req.getParameter("studentId")));
			m.setSubjectId(Integer.parseInt(req.getParameter("subjectId")));
			m.setMark(Integer.parseInt(req.getParameter("mark")));
			result = new ShowMarkAction(database, m);
			col = result.createAction();
		}
		if (req.getParameter("Delete mark") != null) {
			Mark m = new Mark();
			m.setId(Integer.parseInt(req.getParameter("markId")));
			result = new ShowMarkAction(database, m);
			col = result.deleteAction();
		}
		if (req.getParameter("Update mark") != null) {
			Mark m = new Mark();
			m.setStudentId(Integer.parseInt(req.getParameter("studentId")));
			m.setSubjectId(Integer.parseInt(req.getParameter("subjectId")));
			m.setMark(Integer.parseInt(req.getParameter("mark")));
			m.setId(Integer.parseInt(req.getParameter("markId")));
			result = new ShowMarkAction(database, m);
			col = result.updateAction();
		}
		if (req.getParameter("Show all marks") != null) {
			result = new ShowMarkAction(database, null);
			col = result.showAction();
		}
		if (req.getParameter("Show student marks") != null) {
			Mark m = new Mark();
			m.setStudentId(Integer.parseInt(req.getParameter("studentId")));
			result = new ShowMarkAction(database, m);
			col = result.showStudAction();
		}
		if (req.getParameter("Show subject marks") != null) {
			Mark m = new Mark();
			m.setSubjectId(Integer.parseInt(req.getParameter("subjectId")));
			result = new ShowMarkAction(database, m);
			col = result.showSubAction();
		}
		if (req.getParameter("Show student marks on subject") != null) {
			Mark m = new Mark();
			m.setStudentId(Integer.parseInt(req.getParameter("studentId")));
			m.setSubjectId(Integer.parseInt(req.getParameter("subjectId")));
			result = new ShowMarkAction(database, m);
			col = result.showStudSubAction();
		}
		return col;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processRequest(req, resp);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processRequest(req, resp);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();		}
	}

}
