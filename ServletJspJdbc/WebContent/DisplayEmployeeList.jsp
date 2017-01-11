
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.bean.EmployeeBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DISPLAY</title>

<script type="text/javascript">
	 function validateForm(){
		var empId = document.getElementById("id").value;
		if(empId == ""){
			alert("Please enter the Id");
			return false;
		}
		var firstName = document.getElementById("firstName").value;
		if(firstName == ""){
			alert("Please enter the firstName");
			return false;
		}
	} 

</script>

</head>
<body bgcolor="violet">
<%
	ArrayList<EmployeeBean> empList=(ArrayList<EmployeeBean>)request.getAttribute("empList");
%>	
<form action="/ServletJspJdbc/UpdateEmpServlet" method="post">
<table align="center" border="2" cellspacing="2" cellpadding="2" width="80%">
<caption>
	<h2>Employee List </h2>(Total Employees: <%=empList.size()%>) : <%=session.getId() %>
</caption>
<tr >
<th>Emp ID</th>
<th>Emp LastName</th>
<th>Emp FirstName</th>
<th>Emp MiddleName</th>
<th>Emp PhoneNumber</th>
<th>Emp Email</th>
<th>Emp Address</th>
</tr>

<!-- JSP  Scriptlet Code -->
<%
for(EmployeeBean emp:empList){
%>
<tr>
<td class="id"> <input type="radio" class="radio" name="radio" value=<%=emp.getId()%>><%=emp.getId()%></td>
<td><%=emp.getLastname()%></td>
<td><%=emp.getFirstname() %></td>
<td><%=emp.getMiddlename() %></td>
<td><%=emp.getPhonenumber() %></td>
<td><%=emp.getEmail() %></td>
<td><%=emp.getAddress() %></td>
</tr>
<%} %>

</table>

<div align="center">
	<button type="submit" name="update" value="Update">Update</button>
	<button type="submit" name="delete" value="Delete">Delete</button>
</div>
</form>
</body>
</html>