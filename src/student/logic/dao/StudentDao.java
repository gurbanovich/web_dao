package student.logic.dao;

import java.util.List;

import student.logic.dto.Student;

public interface StudentDao {

	public Student create(Student student) throws PersistException;

	public Student read(int key) throws PersistException;

	public void update(Student student) throws PersistException;

	public void delete(Student student) throws PersistException;

	public List<Student> getAll() throws PersistException;

	public void closeStatement() throws PersistException;

}