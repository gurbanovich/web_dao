package student.logic.sql;

import student.logic.dao.*;
import student.logic.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlStudentDao implements StudentDao {

	private PreparedStatement createStatement;
	private PreparedStatement readStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement allStatement;

	public MySqlStudentDao(Database pb, Connection connection) throws PersistException {
		try {
			createStatement = connection.prepareStatement(
					"INSERT INTO group1_db.Student (firstName, secondName, enterYear) VALUES (?, ?, ?);");
			readStatement = connection.prepareStatement(
					"SELECT id, firstName, secondName, enterYear FROM group1_db.Student WHERE id = ?;");
			deleteStatement = connection.prepareStatement("DELETE FROM group1_db.Student WHERE id= ?;");
			allStatement = connection
					.prepareStatement("SELECT id, firstName, secondName, enterYear FROM group1_db.Student;");
			updateStatement = connection.prepareStatement(
					"UPDATE group1_db.Student SET firstName=?, secondName = ?, enterYear = ? WHERE id = ?;");
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

	private List<Student> showResultSet(ResultSet rs) throws PersistException {
		List<Student> result = new LinkedList<Student>();
		try {
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setFirstName(rs.getString("firstName"));
				student.setSecondName(rs.getString("secondName"));
				student.setEnterYear(rs.getInt("enterYear"));
				result.add(student);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	public Student create(Student student) throws PersistException {
		try {
			createStatement.setString(1, student.getFirstName());
			createStatement.setString(2, student.getSecondName());
			createStatement.setInt(3, student.getEnterYear());
			int count = createStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On persist modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return student;
	}

	@Override
	public Student read(int key) throws PersistException {
		List<Student> list;
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
	public void update(Student student) throws PersistException {
		try {
			updateStatement.setString(1, student.getFirstName());
			updateStatement.setString(2, student.getSecondName());
			updateStatement.setInt(3, student.getEnterYear());
			updateStatement.setInt(4, student.getId());
			int count = updateStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On update modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void delete(Student student) throws PersistException {
		try {
			deleteStatement.setInt(1, student.getId());
			int count = deleteStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On delete modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public List<Student> getAll() throws PersistException {
		List<Student> list;
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