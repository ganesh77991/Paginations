<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>NAME</td>
			<td>GENDER</td>
			<td>ADDRESS</td>
			<td>CONTRY</td>
			<td>LANGUAGES</td>
		</tr>
		<c:forEach items="${emps}" var="emp">
			<tr>
				<td><c:out value="${emp.empId } " /></td>
				<td><c:out value="${emp.empName}" /></td>
				<td><c:out value="${emp.empGender}" /></td>
				<td><c:out value="${emp.empAddr}" /></td>
				<td><c:out value="${emp.empCntry}" /></td>
				<td><c:out value="${emp.empLangs}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<c:forEach begin="1" end="${pages}" step="1" var="a">
	
	<a href="<%= request.getContextPath() %>/employee/page/${a}" style="text-decoration:none"><c:out value="${a}"/></a>
	
	</c:forEach>

</body>
</html>