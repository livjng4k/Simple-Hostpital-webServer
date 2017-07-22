<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Surgery Record</h1>
        <logic:messagesPresent>
                <font color="red">
                <html:messages id="error">
                <li>${error}</li>
                <br/>
            </html:messages>
            </font>
        </logic:messagesPresent>
        <form action="UpdateRecord.do" method="POST">
            <input type="hidden" value="${requestScope.surgery.id}" name="id"/>
            Patient ID <input type="text" value="${requestScope.patient.id}" readonly/><br/><br/>
            Patient Name <input type="text" value="${requestScope.patient.name}" readonly/><br/><br/>
            DOB <input readonly type="date" name="dob" value="<fmt:formatDate value="${requestScope.patient.DOB}" pattern="yyyy-MM-dd"/>"/> <br/><br/>
            Gender 
            <input type="radio" name="gender" value="Male" ${requestScope.patient.gender ? "checked" : ""} disabled/> Male
            <input type="radio" name="gender" value="Female" ${requestScope.patient.gender ? "" : "checked"} disabled/> Female
            <br/><br/>
            Surgery Name <input type="text" name="surgeryName" value="${requestScope.surgery.surgeryName}"/><br/><br/>
            Operated Doctor <input type="text" name="operatedDoctor" value="${requestScope.surgery.operatedDoctor}"/><br/><br/>
            Anesthesiologist <input type="text" name="anesthesiologist" value="${requestScope.surgery.anesthesiologist}"/><br/><br/>
            Time Start <input type="datetime-local" name="timeStart" 
                              value="<fmt:formatDate value="${requestScope.surgery.timeStart}" pattern="yyyy-MM-dd"/>T
                              <fmt:formatDate value="${requestScope.surgery.timeStart}" pattern="hh:mm:ss"/>"/><br/><br/>
            Time End <input type="datetime-local" name="timeEnd" 
                              value="<fmt:formatDate value="${requestScope.surgery.timeEnd}" pattern="yyyy-MM-dd"/>T
                              <fmt:formatDate value="${requestScope.surgery.timeEnd}" pattern="hh:mm:ss"/>"/><br/><br/>
            Process <input type="text" name="process" value="${requestScope.surgery.process}"/><br/><br/>
            <input type="submit" value="Update"/>
        </form>
    </body>
</html>
