package mypks.dto;

import java.sql.Timestamp;

public class SurgeryRecordDTO {

    private String patientName;
    private String id, process, creator_emp, status, patientID, surgeryName, operatedDoctor, anesthesiologist;
    private Timestamp timeStart, timeEnd, timeCreate;

    public SurgeryRecordDTO() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getCreator_emp() {
        return creator_emp;
    }

    public void setCreator_emp(String creator_emp) {
        this.creator_emp = creator_emp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getOperatedDoctor() {
        return operatedDoctor;
    }

    public void setOperatedDoctor(String operatedDoctor) {
        this.operatedDoctor = operatedDoctor;
    }

    public String getAnesthesiologist() {
        return anesthesiologist;
    }

    public void setAnesthesiologist(String anesthesiologist) {
        this.anesthesiologist = anesthesiologist;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

}
