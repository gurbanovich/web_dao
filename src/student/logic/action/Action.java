package student.logic.action;

import java.util.LinkedList;
import java.util.List;

import student.logic.dao.PersistException;

public interface Action {

	public List<Object> showStudAction() throws PersistException;

	default public List<Object> showSubAction() throws PersistException {
		return new LinkedList<Object>();
	}

	default public List<Object> showStudSubAction() throws PersistException {
		return new LinkedList<Object>();
	}

	public List<Object> showAction() throws PersistException;

	public List<Object> updateAction() throws PersistException;

	public List<Object> deleteAction() throws PersistException;

	public List<Object> createAction() throws PersistException;

}