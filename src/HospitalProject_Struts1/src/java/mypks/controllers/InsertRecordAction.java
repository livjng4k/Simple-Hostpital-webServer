package mypks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mypks.bean.InsertRecordActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Insert Surgery Record data to database
 * Parsing current session attribute "ID" (more detail in LoginAction.java)
 * @ActionForm InsertRecordActionForm
 */
public class InsertRecordAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InsertRecordActionForm bean = (InsertRecordActionForm) form;
        HttpSession session = request.getSession(false);
        String creatorID = (String) session.getAttribute("ID");
        bean.InsertRecord(creatorID);
        return mapping.findForward(SUCCESS);
    }
}
