package student.logic.sql;

import student.logic.dao.*;
import student.logic.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlSubjectDao implements SubjectDao {

	private PreparedStatement createStatement;
	private PreparedStatement readStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement allStatement;

	public MySqlSubjectDao(Database pb, Connection connection) throws PersistException {
		try {
			createStatement = connection.prepareStatement("INSERT INTO group1_db.Subject (subject) VALUES (?);");
			readStatement = connection.prepareStatement("SELECT id, subject  FROM group1_db.Subject WHERE id = ?;");
			deleteStatement = connection.prepareStatement("DELETE FROM group1_db.Subject WHERE id= ?;");
			allStatement = connection.prepareStatement("SELECT id, subject  FROM group1_db.Subject;");
			updateStatement = connection.prepareStatement("UPDATE group1_db.Subject SET subject= ? WHERE id= ?;");
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void closeStatement() throws PersistException {
		try {
			if (createStatement != null) {
				createStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (readStatement != null) {
				readStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (updateStatement != null) {
				updateStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (deleteStatement != null) {
				deleteStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (allStatement != null) {
				allStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Subject> showResultSet(ResultSet rs) throws PersistException {
		List<Subject> result = new LinkedList<Subject>();
		try {
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setSubject(rs.getString("subject"));
				result.add(subject);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	public Subject create(Subject subject) throws PersistException {
		try {
			createStatement.setString(1, subject.getSubject());
			int count = createStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On persist modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return subject;
	}

	@Override
	public Subject read(int key) throws PersistException {
		List<Subject> list;
		try {
			readStatement.setInt(1, key);
			ResultSet rs = readStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list.iterator().next();
	}

	@Override
	public void update(Subject subject) throws PersistException {
		try {
			updateStatement.setString(1, subject.getSubject());
			updateStatement.setInt(2, subject.getId());
			int count = updateStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On update modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void delete(Subject subject) throws PersistException {
		try {
			deleteStatement.setInt(1, subject.getId());
			int count = deleteStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On delete modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public List<Subject> getAll() throws PersistException {
	       List<Subject> list;
		try {
			ResultSet rs = allStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

}