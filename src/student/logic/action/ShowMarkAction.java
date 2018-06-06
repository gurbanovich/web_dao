package student.logic.action;

import student.logic.dao.*;
import student.logic.dto.*;

import java.util.LinkedList;
import java.util.List;

public class ShowMarkAction implements Action {

	private Database database;
	private MarkDao dao;
	private List<Object> list = new LinkedList<Object>();
	private Mark mark = new Mark();

	public ShowMarkAction(Database database, Mark mark) {
		this.database = database;
		this.mark = mark;
	}
	
	@Override
	public List<Object> showStudAction() throws PersistException {
		try {
			dao = database.getMarkDao(database);
			for (Mark m : dao.getStud(mark))
				list.add(m);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> showSubAction() throws PersistException {
		try {
			dao = database.getMarkDao(database);
			for (Mark m : dao.getSubj(mark))
				list.add(m);
		} catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

	@Override
	public List<Object> showStudSubAction() throws PersistException {
		try {
			dao = database.getMarkDao(database);
			for (Mark m : dao.getStudSubj(mark))
				list.add(m);
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
			dao = database.getMarkDao(database);
			for (Mark m : dao.update(mark)) {
				list.add(m);
			}
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
			dao = database.getMarkDao(database);
			for (Mark m : dao.getAll()) {
				list.add(m);
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
			dao = database.getMarkDao(database);
			dao.delete(mark);
			list.add(mark);
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
			dao = database.getMarkDao(database);
			for (Mark m : dao.create(mark)) {
				list.add(m);
			}
		}  catch (PersistException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
		}
		return list;
	}

}