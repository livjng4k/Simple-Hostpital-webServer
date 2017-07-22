package mypks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import mypks.dto.PatientDTO;
import mypks.dto.SurgeryRecordDTO;

public class DAO {

    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;

    /*
     * Search for patient with their name have specific characters
     * @param 
     *      search - characters appear in patient name
     * @return list <String>
     */
    private List<String> searchPatientLikeName(String search) {
        List<String> id = null;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select PatientID from Patient where PatientName like ?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, "%" + search + "%");
            rs = preSt.executeQuery();
            id = new ArrayList<>();
            while (rs.next()) {
                id.add(rs.getString("PatientID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return id;
    }

    /*
     * Search for Surgery Record with Patient name have specified characters and was created by given EmployeeID
     * @param 
     *      search - specified characters
     *      creator - given EmployeeID
     * @return list <SurgeryRecordDTO>
     */
    public List<SurgeryRecordDTO> searchSurgery(String search, String creator) {
        List<SurgeryRecordDTO> list = null;
        List<String> id = searchPatientLikeName(search);
        if (id.size() > 0) {
            SurgeryRecordDTO dto;
            try {
                conn = connections.MyConnection.getConnection();
                String sql = "select Surgery_Record_ID,PatientID,SurgeryName from SurgeryReport "
                        + "where PatientID=? and Creator_Emp=?";
                preSt = conn.prepareStatement(sql);
                for (String s : id) {
                    preSt.setString(1, s);
                    preSt.setString(2, creator);
                    rs = preSt.executeQuery();
                    while (rs.next()) {
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        dto = new SurgeryRecordDTO();
                        dto.setId(rs.getString(1));
                        dto.setPatientID(rs.getString(2));
                        dto.setSurgeryName(rs.getString(3));
                        list.add(dto);
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    String name = findPFullname(list.get(i).getPatientID());
                    list.get(i).setPatientName(name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnections();
            }
        }
        return list;
    }

    /*
     * Insert new SurgeryRecord to database
     * @param 
     *      creator - Creator_emp
     *      patientID - PatientID
     *      status - Status
     * @return 
     *      true/false - insert success/failed
     */
    public boolean insertRecord(String creator, String patientID, String status) {
        boolean kt = true;
        try {
            String id = getNextSurgeryRecordID();
            conn = connections.MyConnection.getConnection();
            String sql = "insert into SurgeryReport(Surgery_Record_ID,Creator_Emp,DateOfCreate,PatientID,Status) values (?,?,?,?,?)";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, id);
            preSt.setString(2, creator);
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            preSt.setTimestamp(3, time);
            preSt.setString(4, patientID);
            preSt.setString(5, status);
            kt = preSt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return kt;
    }

    /*
     * Get all available PatientID from database
     * @return list<String>
     */
    public List<String> getAllPatientID() {
        List<String> list = null;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select PatientID from Patient";
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return list;
    }

    /*
     * Update Surgery Record to database with given SurgeryRecord data
     * @param
     *      dto - SurgeryRecord data
     * @return 
     *      true/false - update success/failed
     */
    public boolean updateSurgery(SurgeryRecordDTO dto) {
        boolean kt = true;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "update SurgeryReport set SurgeryName=?,OperatedDoctor=?,Anesthesiologist=?,TimeOfStart=?,TimeOfEnd=?,"
                    + "ProcessOfSurgery=? where Surgery_Record_ID=?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, dto.getSurgeryName());
            preSt.setString(2, dto.getOperatedDoctor());
            preSt.setString(3, dto.getAnesthesiologist());
            preSt.setTimestamp(4, dto.getTimeStart());
            preSt.setTimestamp(5, dto.getTimeEnd());
            preSt.setString(6, dto.getProcess());
            preSt.setString(7, dto.getId());
            kt = preSt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return kt;
    }

    /*
     * Find SurgeryRecord have given Surgery_Record_ID
     * @param 
     *      pk - given Surgery_Record_ID
     * @return
     *      SurgeryRecord information
     */
    public SurgeryRecordDTO findSurgeryByPK(String pk) {
        SurgeryRecordDTO dto = null;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select SurgeryName,OperatedDoctor,Anesthesiologist,TimeOfStart,TimeOfEnd,ProcessOfSurgery"
                    + " from SurgeryReport where Surgery_Record_ID=?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, pk);
            rs = preSt.executeQuery();
            if (rs.next()) {
                dto = new SurgeryRecordDTO();
                dto.setId(pk);
                dto.setSurgeryName(rs.getString("SurgeryName"));
                dto.setOperatedDoctor(rs.getString("OperatedDoctor"));
                dto.setAnesthesiologist(rs.getString("Anesthesiologist"));
                dto.setTimeStart(rs.getTimestamp("TimeOfStart"));
                dto.setTimeEnd(rs.getTimestamp("TimeOfEnd"));
                dto.setProcess(rs.getString("ProcessOfSurgery"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return dto;
    }

    /*
     * Find Patient have given Patient_ID
     * @param 
     *      pk - given Patient_ID
     * @return
     *      Patient information
     */
    public PatientDTO findPatientByPK(String pk) {
        PatientDTO dto = null;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select PatientName,PatientAddr,DOB,Email,Gender,PatientPass,Phone,Status"
                    + " from Patient where PatientID=?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, pk);
            rs = preSt.executeQuery();
            if (rs.next()) {
                dto = new PatientDTO();
                dto.setId(pk);
                dto.setName(rs.getString("PatientName"));
                dto.setAddress(rs.getString("PatientAddr"));
                dto.setDOB(rs.getTimestamp("DOB"));
                dto.setEmail(rs.getString("Email"));
                dto.setGender(rs.getBoolean("Gender"));
                dto.setPass(rs.getString("PatientPass"));
                dto.setPhone(rs.getString("Phone"));
                dto.setStatus(rs.getString("Status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return dto;
    }

    /*
     * Find PatientName have given PatientID
     * @param 
     *      pk - given PatientID
     * @return
     *      PatientName
     */
    public String findPFullname(String pk) {
        String name = "";
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select PatientName from Patient where PatientID=?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, pk);
            rs = preSt.executeQuery();
            if (rs.next()) {
                name = rs.getString("PatientName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return name;
    }

    /*
     * Find EmployeeName have given EmployeeID
     * @param 
     *      pk - given EmployeeID
     * @return
     *      EmployeeName
     */
    public String findEFullname(String pk) {
        String name = "";
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select EmployeeName from Employee where EmployeeID=?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, pk);
            rs = preSt.executeQuery();
            if (rs.next()) {
                name = rs.getString("EmployeeName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return name;
    }

    /*
     * Check if given username and password is correct in database and execute Login with returned role
     * @param
     *     username - given username
     *     password - given password
     * @return 
     *     Employee - valid Employee login
     *     Patient - valid Patient login
     *     failed - invalid login
     */
    public String checkLogin(String username, String password) {
        String role = "failed";
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "";
            String expectedRole = "falied";
            // if username starts with 'E' it should be Employee
            // if username starts with 'P' it should be Patient
            if (username.charAt(0) == 'E') {
                sql = "select 1 from Employee where EmployeeID=? and EmpPass=?";
                expectedRole = "Employee";
            } else if (username.charAt(0) == 'P') {
                sql = "select 1 from Patient where PatientID=? and PatientPass=?";
                expectedRole = "Patient";
            }
            // if String sql still empty means wrong username.
            if (sql.isEmpty()) {
                return role;
            }
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, username);
            preSt.setString(2, password);
            rs = preSt.executeQuery();
            if (rs.next()) {
                role = expectedRole;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return role;
    }

    /*
     * Close all opened SQL-involving connections
     */
    private void closeConnections() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preSt != null) {
                preSt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Auto generate the next posible Surgery_Record_ID from database
     * @return
     *      next Surgery_Record_ID
     */
    private String getNextSurgeryRecordID() {
        String Surgery_Record_ID = "";
        Statement stt = null;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select Surgery_Record_ID from SurgeryReport"
                    + " where Surgery_Record_ID=(select max(Surgery_Record_ID) from SurgeryReport)";
            stt = conn.createStatement();
            rs = stt.executeQuery(sql);
            if (rs.next()) {
                String s = rs.getString(1);
                StringTokenizer stk = new StringTokenizer(s, "_");
                stk.nextToken();
                int id = Integer.parseInt(stk.nextToken());
                s = Integer.toString(++id);
                while (s.length() < 5) {
                    s = "0" + s;
                }
                Surgery_Record_ID = "SurR_" + s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stt != null) {
                try {
                    stt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            closeConnections();
        }
        return Surgery_Record_ID;
    }
}
