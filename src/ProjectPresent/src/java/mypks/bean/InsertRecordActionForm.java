package mypks.bean;

import javax.servlet.http.HttpServletRequest;
import mypks.dao.DAO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class InsertRecordActionForm extends org.apache.struts.action.ActionForm {

    private String patientID, status;

    public boolean InsertRecord(String id) {
        DAO dao = new DAO();
        System.out.println("patientID: " + patientID);
        System.out.println("Creator ID: " + id);
        System.out.println("status: " + status);
        return dao.insertRecord(id, patientID, status);
    }

    public InsertRecordActionForm() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getStatus() == null || getStatus().length() < 1) {
            errors.add("status", new ActionMessage("error.status.required"));
        }
        return errors;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
