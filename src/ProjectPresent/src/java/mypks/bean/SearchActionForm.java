package mypks.bean;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mypks.dao.DAO;
import mypks.dto.SurgeryRecordDTO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SearchActionForm extends org.apache.struts.action.ActionForm {

    private String search;

    public List<SurgeryRecordDTO> search(String creator) {
        DAO dao = new DAO();
        return dao.searchSurgery(search, creator);
    }

    public SearchActionForm() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getSearch() == null || getSearch().length() < 1) {
            errors.add("search", new ActionMessage("error.search.required"));
        }
        return errors;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
