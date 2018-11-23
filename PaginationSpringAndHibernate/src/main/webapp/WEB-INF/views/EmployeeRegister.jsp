<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="from" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Employee Register</h1>

<from:form action="insert" modelAttribute="employee">
<pre>
Name     : <from:input path="empName"/>
Gender   : <from:radiobutton path="empGender" value="male"/>Male
           <from:radiobutton path="empGender" value="female"/>Female
Address  : <from:textarea path="empAddr"/>
Country  :<from:select path="empCntry">
			<from:option value="">--SELECT--</from:option>
			<from:option value="ING">India</from:option>
			<from:option value="ENG">England</from:option>
          </from:select>
Languages:<from:checkbox path="empLangs" value="ENG"/>English
          <from:checkbox path="empLangs" value="HIN"/>Hindi
          <from:checkbox path="empLangs" value="TEL"/>Telugu
		  <input type="submit" value="Register">
</pre>
</from:form>
${message} 
</body>
</html>