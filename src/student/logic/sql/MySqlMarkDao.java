package student.logic.sql;

import student.logic.dao.*;
import student.logic.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlMarkDao implements MarkDao {

	private Database pb;
	private PreparedStatement createStatement;
	private PreparedStatement readStatement;
	private PreparedStatement readSubjStatement;
	private PreparedStatement readSSStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement allStatement;
	private PreparedStatement readStudStatement;
	private PreparedStatement readLastStatement;

	public MySqlMarkDao(Database pb, Connection connection) throws PersistException {
		this.pb = pb;
		try {
			createStatement = connection
					.prepareStatement("INSERT INTO group1_db.Mark (studentId, subjectId, mark) VALUES (?, ?, ?);");
			readStudStatement = connection.prepareStatement(
					"SELECT mark.id, mark.mark, mark.studentId, mark.subjectId FROM group1_db.Mark inner join group1_db.student on student.id=mark.studentId where student.id= ?;");
			readSubjStatement = connection.prepareStatement(
					"SELECT mark.id, mark.mark, mark.studentId, mark.subjectId FROM group1_db.Mark inner join group1_db.subject on subject.id=mark.subjectId where subject.id= ?;");
			readSSStatement = connection.prepareStatement(
					"SELECT mark.id, mark.mark, mark.studentId, mark.subjectId FROM group1_db.Mark inner join group1_db.student on student.id=mark.studentId inner join group1_db.subject on subject.id=mark.subjectId where student.id=? and subject.id=?;");
			deleteStatement = connection.prepareStatement("DELETE FROM group1_db.Mark WHERE id= ?;");
			allStatement = connection
					.prepareStatement("SELECT mark.id, mark.mark, mark.studentId, mark.subjectId FROM group1_db.Mark;");
			updateStatement = connection
					.prepareStatement("UPDATE  group1_db.Mark SET studentId=?, subjectId= ?, mark=? WHERE id= ?;");
			readLastStatement = connection.prepareStatement(
					"SELECT mark.id, mark.mark, mark.studentId, mark.subjectId FROM group1_db.Mark WHERE id = last_insert_id();");
			readStatement = connection.prepareStatement(
					"SELECT mark.id, mark.mark, mark.studentId, mark.subjectId FROM group1_db.Mark WHERE id = ?;");

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
			if (readSubjStatement != null) {
				readSubjStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (readSSStatement != null) {
				readSSStatement.close();
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
		try {
			if (readStudStatement != null) {
				readStudStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (readLastStatement != null) {
				readLastStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Mark> showResultSet(ResultSet rs) throws PersistException {
		List<Mark> result = new LinkedList<Mark>();
		try {
			while (rs.next()) {
				Mark mark = new Mark();
				mark.setId(rs.getInt("id"));
				mark.setMark(rs.getInt("mark"));
				Integer m = rs.getInt("studentId");
				mark.setStudent(pb.getStudentDao(pb).read(m));
				Integer n = rs.getInt("subjectId");
				mark.setSubject(pb.getSubjectDao(pb).read(n));
				result.add(mark);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	public List<Mark> create(Mark mark) throws PersistException {
		List<Mark> list;
		try {
			createStatement.setInt(1, mark.getStudentId());
			createStatement.setInt(2, mark.getSubjectId());
			createStatement.setInt(3, mark.getMark());
			int count = createStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On persist modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}

		try {
			ResultSet rs = readLastStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	@Override
	public List<Mark> update(Mark mark) throws PersistException {
		List<Mark> list;
		try {
			updateStatement.setInt(1, mark.getStudentId());
			updateStatement.setInt(2, mark.getSubjectId());
			updateStatement.setInt(3, mark.getMark());
			updateStatement.setInt(4, mark.getId());
			int count = updateStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On update modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		try {
			readStatement.setInt(1, mark.getId());
			ResultSet rs = readStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	@Override
	public List<Mark> delete(Mark mark) throws PersistException {
		List<Mark> list;
		try {
			deleteStatement.setInt(1, mark.getId());
			int count = deleteStatement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On delete modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		try {
			readStatement.setInt(1, mark.getId());
			ResultSet rs = readStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	@Override
	public List<Mark> getAll() throws PersistException {
		List<Mark> list;
		try {
			ResultSet rs = allStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	@Override
	public List<Mark> getStud(Mark mark) throws PersistException {
		List<Mark> list;
		try {
			readStudStatement.setInt(1, mark.getStudentId());
			ResultSet rs = readStudStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	@Override
	public List<Mark> getSubj(Mark mark) throws PersistException {
	    List<Mark> list;
		try {
			readSubjStatement.setInt(1, mark.getSubjectId());
			ResultSet rs = readSubjStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	@Override
	public List<Mark> getStudSubj(Mark mark) throws PersistException {
		List<Mark> list;
		try {
			readSSStatement.setInt(1, mark.getStudentId());
			readSSStatement.setInt(2, mark.getSubjectId());
			ResultSet rs = readSSStatement.executeQuery();
			list = showResultSet(rs);
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

}