package mypks.bean;

import mypks.dao.DAO;

public class InsertRecordActionForm extends org.apache.struts.action.ActionForm {

    private String patientID, status;

    /*
     * Call method inserting new Surgery Record to database
     * @param 
     *      id - Creator ID
     * @return
     *      true/false for success or failed
     */
    public boolean InsertRecord(String id) {
        DAO dao = new DAO();
        return dao.insertRecord(id, patientID, status);
    }

    public InsertRecordActionForm() {
        super();
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
