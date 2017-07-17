package mypks.bean;

import java.sql.Timestamp;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import mypks.dao.DAO;
import mypks.dto.SurgeryRecordDTO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SurgeryUpdateActionForm extends org.apache.struts.action.ActionForm {

    private String id, surgeryName, operatedDoctor, anesthesiologist, timeStart, timeEnd, process;

    public boolean updateSurgery() {
        StringTokenizer stk = new StringTokenizer(timeStart, "T");
        Timestamp start = Timestamp.valueOf(stk.nextToken() + " " + stk.nextToken() + ":00");
        stk = new StringTokenizer(timeEnd, "T");
        Timestamp end = Timestamp.valueOf(stk.nextToken() + " " + stk.nextToken() + ":00");
        DAO dao = new DAO();
        SurgeryRecordDTO dto = new SurgeryRecordDTO();
        dto.setId(id);
        dto.setOperatedDoctor(operatedDoctor);
        dto.setSurgeryName(surgeryName);
        dto.setAnesthesiologist(anesthesiologist);
        dto.setProcess(process);
        dto.setTimeStart(start);
        dto.setTimeEnd(end);
        return dao.updateSurgery(dto);
    }

    public SurgeryUpdateActionForm() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getSurgeryName() == null || getSurgeryName().length() < 1) {
            errors.add("surgeryname", new ActionMessage("error.surgeryname.required"));
        }
        if (getOperatedDoctor() == null || getOperatedDoctor().length() < 1) {
            errors.add("operateddoctor", new ActionMessage("error.operateddoctor.required"));
        }
        if (getAnesthesiologist() == null || getAnesthesiologist().length() < 1) {
            errors.add("anesthesiologist", new ActionMessage("error.anesthesiologist.required"));
        }
        if (getTimeStart() == null || getTimeStart().length() < 1) {
            errors.add("time", new ActionMessage("error.time.required"));
        }
        if (getTimeEnd() == null || getTimeEnd().length() < 1) {
            errors.add("time", new ActionMessage("error.time.required"));
        }
        if (getProcess() == null || getProcess().length() < 1) {
            errors.add("process", new ActionMessage("error.process.required"));
        }
        return errors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

}
