package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Invalidate current session on server and redirect to page /login.jsp
 */
public class LogoutAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "HomePage";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return mapping.findForward(SUCCESS);
    }
}
