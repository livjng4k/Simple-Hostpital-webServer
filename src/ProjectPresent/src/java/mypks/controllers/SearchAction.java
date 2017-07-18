package mypks.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mypks.bean.SearchActionForm;
import mypks.dto.SurgeryRecordDTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(false);
        SearchActionForm bean = (SearchActionForm) form;
        String creator = (String) session.getAttribute("ID");
        List<SurgeryRecordDTO> list = bean.search(creator);
        request.setAttribute("resultList", list);
        return mapping.findForward(SUCCESS);
    }
}
