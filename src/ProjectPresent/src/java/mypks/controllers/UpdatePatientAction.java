package mypks.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mypks.bean.PatientUpdateForm;
import mypks.dao.DAO;
import mypks.dto.PatientDTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UpdatePatientAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PatientUpdateForm bean = (PatientUpdateForm) form;
        HttpSession session = request.getSession(false);
        if (bean.update()) {
            session.setAttribute("message", "Update success.");
            DAO dao = new DAO();
            PatientDTO dto = dao.findPatientByPK(bean.getId());
            session.removeAttribute("info");
            Date date = dto.getDOB();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dob = sdf.format(date);
            session.setAttribute("info", dto);
            session.setAttribute("dob", dob);
        } else {
            session.setAttribute("message", "Update failed.");
        }
        return mapping.findForward(SUCCESS);
    }
}
