<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.fullname}">
            <font color="red">Please <a href="login.jsp">Login</a> first </font>
        </c:if>
        <c:if test="${not empty sessionScope.fullname}">
            <h1>Insert Patient</h1>
            <h5>Welcome, ${sessionScope.fullname} <a href="Logout.do">Log out</a></h5>
            <logic:messagesPresent>
                <font color="red">
                <html:messages id="error">
                <li>${error}</li>
                <br/>
            </html:messages>
            </font>
        </logic:messagesPresent>
        <font color="red"> ${requestScope.message} </font>
        <form action="InsertPatient.do" method="POST">
            Patient Name <input type="text" name="name" value=""/> <br/><br/>
            Gender  
            <input type="radio" name="gender" value="Male" checked="true"/> Male
            <input type="radio" name="gender" value="Female" /> Female
            <br/><br/>
            DOB <input type="date" name="dob" value=""/> <br/><br/>
            Patient Address <input type="text" name="address" value=""/> <br/><br/>
            Email <input type="text" name="email" value=""/> <br/><br/>
            Phone <input type="text" name="phone" value=""/> <br/><br/>
            <input type="submit" value="Update"/>
        </form>
    </c:if>
</body>
</html>
