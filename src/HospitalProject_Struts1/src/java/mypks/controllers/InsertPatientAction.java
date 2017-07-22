package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypks.bean.InsertPatientActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * 
 * @ActionForm InsertPatientActionForm
 */
public class InsertPatientAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InsertPatientActionForm bean = (InsertPatientActionForm) form;
        String url = bean.insertPatient() ? "success" : "failed";
        if (url.equals("failed")) {
            request.setAttribute("message", "Insert Failed");
        }
        return mapping.findForward(url);
    }
}
