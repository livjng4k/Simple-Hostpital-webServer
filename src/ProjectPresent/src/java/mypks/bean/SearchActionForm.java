/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypks.bean;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mypks.dao.DAO;
import mypks.dto.SurgeryRecordDTO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author VS9 X64Bit
 */
public class SearchActionForm extends org.apache.struts.action.ActionForm {

    private String search;

    public List<SurgeryRecordDTO> search() {
        DAO dao = new DAO();
        return dao.searchLikeName(search);
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
