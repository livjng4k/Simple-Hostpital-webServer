<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <logic:messagesPresent>
            <font color="red">
            <html:messages id="error">
            <li>${error}</li>
            </html:messages>
        </font>
    </logic:messagesPresent>
    <form action="Login.do" method="POST">
        Username <input type="text" name="username" value=""/><br/><br/>
        Password <input type="password" name="password" value=""/><br/><br/>
        <input type="submit" value="Login"/>
        <input type="reset" value="Reset"/><br/>
    </form>
</body>
</html>
