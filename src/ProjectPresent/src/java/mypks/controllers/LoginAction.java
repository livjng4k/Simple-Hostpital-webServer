package mypks.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mypks.bean.LoginActionForm;
import mypks.dto.PatientDTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
