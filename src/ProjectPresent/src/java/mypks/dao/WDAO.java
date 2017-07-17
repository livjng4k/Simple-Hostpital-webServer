package mypks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.StringTokenizer;

public class WDAO {

    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;

    public boolean insertPatient(String name, boolean gender, Timestamp dob, String address, String email, String phone) {
        boolean kt = true;
        try {
            String id = getNextPatientID();
            conn = connections.MyConnection.getConnection();
            String sql = "insert into Patient(PatientID,PatientName,Gender,DOB,PatientAddr,Email,Phone) "
                    + "values(?,?,?,?,?,?,?)";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, id);
            preSt.setString(2, name);
            preSt.setBoolean(3, gender);
            preSt.setTimestamp(4, dob);
            preSt.setString(5, address);
            preSt.setString(6, email);
            preSt.setString(7, phone);
            kt = preSt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            kt = false;
        } finally {
            closeConnections();
        }
        return kt;
    }

    public boolean updatePatientInfo(String id, String name, boolean gender, Timestamp dob, String address, String email, String phone) {
        boolean kt = true;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "update Patient set PatientName=?,Gender=?,DOB=?,PatientAddr=?,Email=?,Phone=? "
                    + "where PatientID=?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(7, id);
            preSt.setString(1, name);
            preSt.setBoolean(2, gender);
            preSt.setTimestamp(3, dob);
            preSt.setString(4, address);
            preSt.setString(5, email);
            preSt.setString(6, phone);
            kt = preSt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            kt = false;
        } finally {
            closeConnections();
        }
        return kt;
    }

    private String getNextPatientID() {
        String ID = "";
        Statement stt = null;
        try {
            conn = connections.MyConnection.getConnection();
            String sql = "select PatientID from Patient where PatientID=(select MAX(PatientID) from Patient)";
            stt = conn.createStatement();
            rs = stt.executeQuery(sql);
            if (rs.next()) {
                String s = rs.getString(1);
                StringTokenizer stk = new StringTokenizer(s, "-");
                stk.nextToken();
                String alphabet = stk.nextToken();
                int number = Integer.parseInt(stk.nextToken());
                s = Integer.toString(++number);
                while (s.length() < 6) {
                    s = "0" + s;
                }
                ID = "P-" + alphabet + "-" + s;
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
        return ID;
    }

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
}
