<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список Студентов</title>
</head>
<body>
<form action="/web_dao/home" method="POST">
<table>
<tr>
<th>номер сткдента </th>
<th>фамилия</th>
<th>имя</th>
<th>год поступления</th>
</tr>
<c:forEach var="st" items="${stud.studentList}">
<tr>
<td><c:out value="${st.id}"/></td>
<td><c:out value="${st.firstName}"/></td>
<td><c:out value="${st.secondName}"/></td> 
<td><c:out value="${st.enterYear}"/></td>

</tr>
</c:forEach>
</table>
<table>
<tr>
<th>номер предмета</th>
<th>предмет</th>
</tr>
<c:forEach var="subj" items="${subj.subjectList}">
<tr>
<td><c:out value="${subj.id}"/></td>
<td><c:out value="${subj.subject}"/></td>

</tr>
</c:forEach>
</table>

<table>
<tr>
<th>номер оценки</th>
<th>имя</th>
<th>фамилия</th>
<th>предмет</th>
<th>оценка</th>
</tr>
<c:forEach var="m" items="${mark.markList}">
<tr>
<td><c:out value="${m.id}"/></td>
<td><c:out value="${m.student.firstName}"/></td>
<td><c:out value="${m.student.secondName}"/></td>
<td><c:out value="${m.subject.subject}"/></td>
<td><c:out value="${m.mark}"/></td>

</tr>
</c:forEach>
</table>

<table>

<tr>
<td><input type="submit" value="OK" name="ok"/></td>
</tr>
</table>

</form>
</body>
</html>