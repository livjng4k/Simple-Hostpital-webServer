<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
            <h1>New Surgery Record</h1>
            <h5>Welcome, ${sessionScope.fullname} <a href="Logout.do">Log out</a></h5>
            <logic:messagesPresent>
                <font color="red">
                <html:messages id="error">
                    ${error}
                    <br/>
                </html:messages>
                </font>
            </logic:messagesPresent>

            <form action="InsertRecord.do">
                Patient ID 
                <select name="patientID">
                    <c:forEach var="patient" items="${requestScope.patientId}">
                        <option value="${patient}">${patient}</option>
                    </c:forEach>
                </select><br/><br/>
                Status <input type="text" name="status" value=""/><br/><br/>
                <input type="submit" value="Insert Record"/>
            </form><br/><br/>

        </c:if>
    </body>
</html>
