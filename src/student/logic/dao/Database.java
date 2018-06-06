package student.logic.dao;

import java.sql.Connection;

public interface Database {

	public Connection getConnection() throws PersistException;

	public StudentDao getStudentDao(Database pb) throws PersistException;

	public SubjectDao getSubjectDao(Database pb) throws PersistException;

	public MarkDao getMarkDao(Database pb) throws PersistException;

}
