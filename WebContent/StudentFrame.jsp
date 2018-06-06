<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список студентов</title>
</head>
<body>
<form action="/web_dao/student" method="POST">
<table>
<tr>
<td>номер студента:</td><td><input type="text" name="studentId" value="${stud.id}"/></td> 
</tr>
<tr>
<td>имя:</td><td><input type="text" name="firstName" value="${stud.firstName}"/></td>
</tr>
<tr>
<td>фамилия:</td><td><input type="text" name="secondName" value="${stud.secondName}"/></td>
</tr>
<tr>
<td>год поступления:</td><td><input type="text" name="enterYear" value="${stud.enterYear}"/></td>
</tr>
</table>
<table>
<tr>
<td><input type="submit" name="Add Student" value="Add Student"/></td>
<td><input type="submit" name="Delete student" value="Delete student"/></td>
<td><input type="submit" name="Update student" value="Update student"/></td>
<td><input type="submit" name="Show all students" value="Show all students"/></td>
<td><input type="submit" name="Show Student" value="Show Student"/></td>
</tr>
</table>
</form>
<form action="/web_dao/subject" method="POST">
<table>
<tr>
<td>номер предмета:</td><td><input type="text" name="subjectId" value="${subj.id}"/></td>
<td>предмет:</td><td><input type="text" name="subject" value="${subj.subject}"/></td>
</tr>
</table>
<table>
<tr>
<td><input type="submit" name="Add subject" value="Add subject"/></td>
<td><input type="submit" name="Delete subject" value="Delete subject"/></td>
<td><input type="submit" name="Update subject" value="Update subject"/></td>
<td><input type="submit" name="Show all subjects" value="Show all subjects"/></td>
<td><input type="submit" name="Show subject" value="Show subject"/></td>
</tr>
</table>
</form>
<form action="/web_dao/mark" method="POST">
<table>
<tr>
<td>номер студента:</td><td><input type="text" name="studentId" value="${stud.id}"/></td>
<td>номер предмета:</td><td><input type="text" name="subjectId" value="${subj.id}"/></td>
<td>номер оценки:</td><td><input type="text" name="markId" value="${mark.id}"/></td>
</tr>
</table>
<table>
<tr> 
<td>оценка:</td><td><input type="text" name="mark" value="${mark.mark}"/></td>
</tr>
</table>
<table>
<tr>
<td><input type="submit" name="Add mark" value="Add mark"/></td>
<td><input type="submit" name="Delete mark" value="Delete mark"/></td>
<td><input type="submit" name="Update mark" value="Update mark"/></td>
<td><input type="submit" name="Show all marks" value="Show all mark"/></td>
<td><input type="submit" name="Show student marks" value="Show student marks"/></td>
<td><input type="submit" name="Show subject marks" value="Show subject marks"/></td>
<td><input type="submit" name="Show student marks on subject" value="Show student marks on subject"/></td>
</tr>
</table>
</form>
</body>
</html>