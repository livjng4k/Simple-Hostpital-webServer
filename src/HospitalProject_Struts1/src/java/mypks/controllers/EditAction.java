package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypks.bean.EditActionForm;
import mypks.dto.PatientDTO;
import mypks.dto.SurgeryRecordDTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Get Surgery Record data with given Surgery_Reocrd_ID and Patient info
 * and put to view Update Surgery Record
 * @ActionForm EditActionForm
 */
public class EditAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EditActionForm bean = (EditActionForm) form;
        SurgeryRecordDTO surgery = bean.getSurgery();
        PatientDTO patient = bean.getPatient();
        request.setAttribute("surgery", surgery);
        request.setAttribute("patient", patient);
        return mapping.findForward(SUCCESS);
    }
}
