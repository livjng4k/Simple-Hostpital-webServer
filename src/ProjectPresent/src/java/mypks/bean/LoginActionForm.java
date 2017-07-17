package mypks.bean;

import javax.servlet.http.HttpServletRequest;
import mypks.dao.DAO;
import mypks.dto.PatientDTO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginActionForm extends org.apache.struts.action.ActionForm {

    private String username, password, role;

    public PatientDTO getPatientInfo() {
        DAO dao = new DAO();
        return dao.findPatientByPK(username);
    }

    public String getFullname() {
        String fullname = "";
        DAO dao = new DAO();
        if (username.charAt(0) == 'E') {
            fullname = dao.findEFullname(username);
        } else {
            fullname = dao.findPFullname(username);
        }
        return fullname;
    }

    public String checkLogin() {
        DAO dao = new DAO();
        role = dao.checkLogin(username, password);
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getUsername() == null || getUsername().length() < 1) {
            errors.add("username", new ActionMessage("error.username.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        if (getPassword() == null || getPassword().length() < 1) {
            errors.add("password", new ActionMessage("error.password.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
