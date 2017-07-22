package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mypks.bean.UpdatePatientActionForm;
import mypks.dao.DAO;
import mypks.dto.PatientDTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Patient update their own information 
 * @SessionAttribute
 *     info - patient's information with display purpose
 * @ActionForm UpdatePatientActionForm
 */
public class UpdatePatientAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UpdatePatientActionForm bean = (UpdatePatientActionForm) form;
        HttpSession session = request.getSession(false);
        if (bean.update()) {
            session.setAttribute("message", "Update success.");
            DAO dao = new DAO();
            PatientDTO dto = dao.findPatientByPK(bean.getId());
            session.removeAttribute("info");
            session.setAttribute("info", dto);
        } else {
            session.setAttribute("message", "Update failed.");
        }
        return mapping.findForward(SUCCESS);
    }
}
