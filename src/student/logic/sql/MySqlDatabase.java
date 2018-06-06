package student.logic.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import student.logic.dao.*;

public class MySqlDatabase implements Database {

	private String user = "root";// ����� ������������
	private String password = "616368";// ������ ������������
	private String url = "jdbc:mysql://localhost:3306/group1_db";// URL �����
	private String driver = "com.mysql.jdbc.Driver";// ��� ��������

	public MySqlDatabase() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws PersistException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new PersistException(e);
		}
		return connection;
	}

	public SubjectDao getSubjectDao(Database pb) throws PersistException {
		return new MySqlSubjectDao(pb, pb.getConnection());
	}

	public StudentDao getStudentDao(Database pb) throws PersistException {
		return new MySqlStudentDao(pb, pb.getConnection());
	}

	public MarkDao getMarkDao(Database pb) throws PersistException {
		return new MySqlMarkDao(pb, pb.getConnection());
	}

}
