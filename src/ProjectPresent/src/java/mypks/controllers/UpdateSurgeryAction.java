package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypks.bean.SurgeryUpdateActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UpdateSurgeryAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SurgeryUpdateActionForm bean = (SurgeryUpdateActionForm) form;
        bean.updateSurgery();
        return mapping.findForward(SUCCESS);
    }
}
