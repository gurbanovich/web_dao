package student.logic.dto;

import java.util.List;

public class Student {

	private Integer id;
	private String firstName;
	private String secondName;
	private int enterYear;
	private List<Object> studentList;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getEnterYear() {
		return enterYear;
	}

	public void setEnterYear(int enterYear) {
		this.enterYear = enterYear;
	}

	public List<Object> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Object> studentList) {
		this.studentList = studentList;
	}
}