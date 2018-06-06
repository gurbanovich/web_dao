package student.logic.dao;

import java.util.List;

import student.logic.dto.Mark;

public interface MarkDao {

	public List<Mark> create(Mark mark) throws PersistException;

	public List<Mark> update(Mark mark) throws PersistException;

	public List<Mark> delete(Mark mark) throws PersistException;

	public List<Mark> getAll() throws PersistException;

	public List<Mark> getStud(Mark mark) throws PersistException;

	public List<Mark> getSubj(Mark mark) throws PersistException;

	public List<Mark> getStudSubj(Mark mark) throws PersistException;

	public void closeStatement() throws PersistException;

}