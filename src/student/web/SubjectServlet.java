package student.web;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import student.logic.action.ShowSubjectAction;
import student.logic.dao.*;
import student.logic.dto.*;
import student.logic.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.logic.action.*;


@SuppressWarnings("serial")
public class SubjectServlet extends HttpServlet {

	public HttpServletRequest req;
	public HttpServletResponse resp;
	public Subject subj = new Subject();

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, PersistException, SQLException {
		List<Object> subjectList = getAction(req);
		req.setCharacterEncoding("UTF-8");
		subj.setSubjectList(subjectList);
		req.setAttribute("subj", subj);
		getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
	}

	private List<Object> getAction(HttpServletRequest req) throws PersistException, SQLException {
		Action result = null;
		List<Object> col = null;
		Database database = new MySqlDatabase();

		if (req.getParameter("Add subject") != null) {
			Subject subj = new Subject();
			subj.setSubject(req.getParameter("subject"));
			result = new ShowSubjectAction(database, subj, 0);
			col = result.createAction();
		}
		if (req.getParameter("Delete subject") != null) {
			Subject subj = new Subject();
			subj.setId(Integer.parseInt(req.getParameter("subjectId")));
			result = new ShowSubjectAction(database, subj, 0);
			col = result.deleteAction();
		}
		if (req.getParameter("Update subject") != null) {
			Subject subj = new Subject();
			subj.setSubject(req.getParameter("subject"));
			subj.setId(Integer.parseInt(req.getParameter("subjectId")));
			result = new ShowSubjectAction(database, subj, 0);
			col = result.updateAction();
		}
		if (req.getParameter("Show all subjects") != null) {
			result = new ShowSubjectAction(database, null, 0);
			col = result.showAction();
		}
		if (req.getParameter("Show subject") != null) {
			Integer key = Integer.parseInt(req.getParameter("subjectId"));
			result = new ShowSubjectAction(database, null, key);
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
