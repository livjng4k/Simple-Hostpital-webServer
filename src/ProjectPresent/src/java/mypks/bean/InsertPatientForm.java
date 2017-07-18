package mypks.bean;

import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import mypks.dao.WDAO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class InsertPatientForm extends org.apache.struts.action.ActionForm {

    private String name, address, email, phone;
    private boolean gender;
    private String dob;

    public boolean insertPatient() {
        WDAO dao = new WDAO();
        Timestamp ts = Timestamp.valueOf(dob + " 00:00:00");
        return dao.insertPatient(name, gender, ts, address, email, phone);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public InsertPatientForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
        }
        if (getDob() == null || getDob().length() < 1) {
            errors.add("dob", new ActionMessage("error.dob.required"));
        } else {
            Timestamp ts = Timestamp.valueOf(dob + " 00:00:00");
            Date date = new Date();
            if (ts.after(date)) {
                errors.add("time", new ActionMessage("error.time.invalid"));
            }
        }
        if (getAddress() == null || getAddress().length() < 1) {
            errors.add("address", new ActionMessage("error.address.required"));
        }
        if (getEmail() == null || getEmail().length() < 1) {
            errors.add("email", new ActionMessage("error.email.required"));
        } else {
            String emailRegex = "[^%$#@&*]{3,}[@]{1}(\\w+[.]\\w+|\\w+[.]\\w+[.]\\w+)";
            if (!getEmail().matches(emailRegex)) {
                errors.add("email", new ActionMessage("error.email.wrong"));
            }
        }
        if (getPhone() == null || getPhone().length() < 1) {
            errors.add("phone", new ActionMessage("error.phone.required"));
        } else {
            String phoneRegex = "\\d{10,11}";
            if (!getPhone().matches(phoneRegex)) {
                errors.add("phone", new ActionMessage("error.phone.wrong"));
            }
        }

        return errors;
    }
}
