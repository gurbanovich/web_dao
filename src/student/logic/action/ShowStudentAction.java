package student.logic.action;

import student.logic.dao.*;
import student.logic.dto.*;

import java.util.LinkedList;
import java.util.List;

public class ShowStudentAction implements Action {

	private Database database;
	private StudentDao dao;
	private int key;
	private List<Object> list = new LinkedList<Object>();
	private Student student = new Student();

	public ShowStudentAction(Database database, Student student, int key) {
		this.database = database;
		this.student = student;
		this.key = key;
	}
	
	@Override
	public List<Object> showStudAction() throws PersistException {
		try {
			dao = database.getStudentDao(database);
			Student stud = dao.read(key);
			list.add(stud);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> updateAction() throws PersistException {
		try {
			dao = database.getStudentDao(database);
			dao.update(student);

			list.add(student);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> showAction() throws PersistException {
		try  {
			dao = database.getStudentDao(database);
			for (Student stud : dao.getAll()) {
				list.add(stud);
			}
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> deleteAction() throws PersistException {
		try {
			dao = database.getStudentDao(database);
			list.add(student);
			dao.delete(student);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> createAction() throws PersistException {
		try {
			dao = database.getStudentDao(database);
			Student stud = dao.create(student);
			list.add(stud);

		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}


	

}