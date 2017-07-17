<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h1>Result</h1>
            <h5>Welcome, ${sessionScope.fullname} <a href="Logout.do">Log out</a></h5>
            <c:if test="${requestScope.resultList eq null}">
                <font color="red">No record found</font>
            </c:if>
            <c:if test="${not empty requestScope.resultList}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Patient ID</th>
                            <th>Patient Name</th>
                            <th>Surgery Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.resultList}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.patientID}</td>
                                <td>${dto.patientName}</td>
                                <td>${dto.surgeryName}</td>
                                <td>
                                    <a href="Edit.do?id=${dto.id}&patientID=${dto.patientID}">Update</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
    </body>
</html>
