package tn.happ.services;


import tn.happ.DB_Connection.ConnexionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PermissionServices implements PermissionsAbilityGroup {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public PermissionServices() {
        this.conn = ConnexionSingleton.getConnection();
    }

    @Override
    public boolean isAdmin(int id){

        try {

            ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("USERROLE").equals("ADMIN")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isCollaborator(int id) {
        try {
            ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("USERROLE").equals("COLLABORATOR")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean isCustomer(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("USERROLE").equals("CUSTOMER")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean isEmployee(int id) {
        if (isCollaborator(id)) {
            try {

                ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("COLLABORATORTYPE").equals("EMPLOYEE")) {
                        return true;
                    } else
                        return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;
    }

    @Override
    public boolean isHRmanager(int id) {
        if (isCollaborator(id)) {
            try {

                ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("COLLABORATORTYPE").equals("HRMANAGER")) {
                        return true;
                    } else
                        return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;

    }

    @Override
    public boolean isReciponist(int id) {
        if (isCollaborator(id)) {
            try {

                ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("COLLABORATORTYPE").equals("RECIPONIST")) {
                        return true;
                    } else
                        return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;
    }

    @Override
    public boolean isEventManager(int id) {
        if (isCollaborator(id)) {
            try {

                ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("COLLABORATORTYPE").equals("EVENTMANAGER")) {
                        return true;
                    } else
                        return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;
    }
    @Override
    public boolean isCateringManager(int id) {
        if (isCollaborator(id)) {
            try {

                ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("COLLABORATORTYPE").equals("CATERINGMANAGER")) {
                        return true;
                    } else
                        return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;
    }

    public boolean isAssistantChef(int id)
    {
        if (isCollaborator(id)) {
            try {

                ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("COLLABORATORTYPE").equals("ASSISTANTCHEF")) {
                        return true;
                    } else
                        return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;
    }
}
