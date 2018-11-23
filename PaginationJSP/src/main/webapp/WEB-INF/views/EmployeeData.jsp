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

		<!-- taking start position from Controller -->
		<c:set var="startPosition" value="${startPosition}" />
		
		<!-- taking pageSize from Controller -->
		<c:set var="pageSize" value="${pageSize}" />

		<!-- taking records from Controller -->
		<c:forEach begin="${startPosition}" step="1"
			end="${startPosition+pageSize-1}" items="${emps}" var="emp">
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
     
     <!-- making page number -->
     <!-- getting page numbers from Controller -->
	<c:forEach begin="1" end="${pages}" step="1" var="a">
		
		<!-- page Numbers as Links -->
		<a href="<%= request.getContextPath() %>/employee/page/${a}"
			style="text-decoration: none"><c:out value="${a}" /></a>

	</c:forEach>

</body>
</html>