package student.logic.action;

import student.logic.dao.*;
import student.logic.dto.*;

import java.util.LinkedList;
import java.util.List;

public class ShowSubjectAction implements Action {

	private Database database;
	private SubjectDao dao;
	private int key;
	private List<Object> list = new LinkedList<Object>();
	private Subject subject = new Subject();

	public ShowSubjectAction(Database database, Subject subject, int key) {
		this.database = database;
		this.subject = subject;
		this.key = key;
	}
	
	@Override
	public List<Object> showStudAction() throws PersistException {
		try  {
			dao = database.getSubjectDao(database);
			Subject sub = dao.read(key);
			list.add(sub);

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
			dao = database.getSubjectDao(database);
			dao.update(subject);
			list.add(subject);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> showAction() throws PersistException {
		try {
			dao = database.getSubjectDao(database);
			for (Subject sub : dao.getAll()) {
				list.add(sub);
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
			dao = database.getSubjectDao(database);
			dao.delete(subject);
			list.add(subject);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> createAction() throws PersistException {
		try  {
			dao = database.getSubjectDao(database);
			Subject sub = dao.create(subject);
			list.add(sub);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	

}
