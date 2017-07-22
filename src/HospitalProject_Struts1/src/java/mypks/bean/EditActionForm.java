package mypks.bean;

import javax.servlet.http.HttpServletRequest;
import mypks.dao.DAO;
import mypks.dto.PatientDTO;
import mypks.dto.SurgeryRecordDTO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class EditActionForm extends org.apache.struts.action.ActionForm {

    private String id, patientID;

    public SurgeryRecordDTO getSurgery() {
        DAO dao = new DAO();
        return dao.findSurgeryByPK(id);
    }

    public PatientDTO getPatient() {
        DAO dao = new DAO();
        return dao.findPatientByPK(patientID);
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EditActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        return errors;
    }
}
