package student.logic.dto;

import java.util.List;

public class Mark {

	private Integer id = null;
	private int studentId;
	private int subjectId;
	private int mark;
	private Student student;
	private Subject subject;
	private List<Object> markList;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Object> getMarkList() {
		return markList;
	}

	public void setMarkList(List<Object> markList) {
		this.markList = markList;
	}

}