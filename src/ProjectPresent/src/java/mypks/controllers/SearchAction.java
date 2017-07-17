package mypks.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        SearchActionForm bean = (SearchActionForm) form;
        List<SurgeryRecordDTO> list = bean.search();
        request.setAttribute("resultList", list);
        return mapping.findForward(SUCCESS);
    }
}
