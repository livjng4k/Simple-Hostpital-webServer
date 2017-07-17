package mypks.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypks.dao.DAO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * This Struts Action gets all available Patient ID to put to
 * InsertNewSurgeryRecord view.
 */
public class DisplayPatientID extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DAO dao = new DAO();
        List<String> list = dao.getAllPatientID();
        request.setAttribute("patientId", list);
        return mapping.findForward(SUCCESS);
    }
}
