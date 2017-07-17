<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
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

            <h1>Update Profile</h1>
            <h5>Welcome, ${sessionScope.fullname} <a href="Logout.do">Log out</a></h5>

            <logic:messagesPresent>
                <font color="red">
                <html:messages id="error">
                <li>${error}</li>
                <br/>
            </html:messages>
            </font>
        </logic:messagesPresent>

        <font color="red"> ${sessionScope.message} </font>

        <form action="UpdatePatient.do" method="POST">
            Patient ID <input type="text" name="id" value="${sessionScope.info.id}" readonly/> <br/><br/>
            Patient Name <input type="text" name="name" value="${sessionScope.info.name}"/> <br/><br/>
            Gender  
            <input type="radio" name="gender" value="Male" ${sessionScope.info.gender ? "checked" : ""}/> Male
            <input type="radio" name="gender" value="Female" ${sessionScope.info.gender ? "" : "checked"}/> Female
            <br/><br/>
            DOB <input type="date" name="dob" value="<fmt:formatDate value="${sessionScope.info.DOB}" pattern="yyyy-MM-dd"/>"/> <br/><br/>
            Patient Address <input type="text" name="address" value="${sessionScope.info.address}"/> <br/><br/>
            Email <input type="text" name="email" value="${sessionScope.info.email}"/> <br/><br/>
            Phone <input type="text" name="phone" value="${sessionScope.info.phone}"/> <br/><br/>
            <input type="submit" value="Update"/>
        </form>

    </c:if>
</body>
</html>
