package student.logic.dto;

import java.util.List;

public class Subject {

	private Integer id;
	private String subject;
	private List<Object> subjectList;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Object> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Object> subjectList) {
		this.subjectList = subjectList;
	}

}