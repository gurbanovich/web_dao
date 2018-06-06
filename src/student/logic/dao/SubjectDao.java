package student.logic.dao;

import java.util.List;

import student.logic.dto.Subject;

public interface SubjectDao {

	public Subject create(Subject subject) throws PersistException;

	public Subject read(int key) throws PersistException;

	public void update(Subject subject) throws PersistException;

	public void delete(Subject subject) throws PersistException;

	public List<Subject> getAll() throws PersistException;

	public void closeStatement() throws PersistException;

}