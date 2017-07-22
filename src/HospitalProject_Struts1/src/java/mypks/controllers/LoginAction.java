package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mypks.bean.LoginActionForm;
import mypks.dto.PatientDTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Check user login action
 * @role
 *     Employee - redirect to page /employee.jsp
 *     Patient - redirect to page /patient.jsp to view and update patient information
 * Create session with requesting client 
 * Store 
 *     fullname - Name of user saved in db
 *     ID - User's ID 
 *     info - if user is patient to store patient information to display
 * @ActionForm LoginActionForm
 */
public class LoginAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginActionForm bean = (LoginActionForm) form;
        String url = bean.checkLogin();
        HttpSession session = request.getSession();
        session.setAttribute("fullname", bean.getFullname());
        session.setAttribute("ID", bean.getUsername());
        if (url.equals("Patient")) {
            PatientDTO dto = bean.getPatientInfo();
            session.setAttribute("info", dto);
        }
        return mapping.findForward(url);
    }
}
