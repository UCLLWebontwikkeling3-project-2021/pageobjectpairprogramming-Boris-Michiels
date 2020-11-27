package domain.db;

import domain.model.Contact;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB {
    private Connection connection;
    private String schema;

    public ContactDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Contact contact) {
        if (contact == null) throw new DbException("Contact is null");
        String sql = String.format("INSERT INTO %s.contact (userid, firstname, lastname, email, phone, timestamp) VALUES (?, ?, ?, ?, ?, ?)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getUserid());
            statementSQL.setString(2, contact.getFirstName());
            statementSQL.setString(3, contact.getLastName());
            statementSQL.setString(4, contact.getEmail());
            statementSQL.setString(5, contact.getPhoneNumber());
            statementSQL.setTimestamp(6, Timestamp.valueOf(contact.getTimeStamp()));
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value")) throw new DbException("Contact already exists");
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                contacts.add(createContact(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    @Override
    public List<Contact> get(String userid) {
        if (userid == null || userid.isEmpty()) throw new DbException("No id given");
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                contacts.add(createContact(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    @Override
    public Contact getOne(int contactid) {
        String sql = String.format("SELECT * FROM %s.contact WHERE contactid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, contactid);
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) return createContact(result);
            else throw new DbException("Contact not found");
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void removeAll() {
        String sql = String.format("DELETE FROM %s.contact *", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void remove(String userid) {
        String sql = String.format("DELETE FROM %s.contact WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void removeOne(int contactid) {
        String sql = String.format("DELETE FROM %s.contact WHERE contactid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, contactid);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private Contact createContact(ResultSet result) throws SQLException {
        int contactid = result.getInt("contactid");
        String userid = result.getString("userid");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String email = result.getString("email");
        String phone = result.getString("phone");
        LocalDateTime timeStamp = result.getTimestamp("timestamp").toLocalDateTime();
        return new Contact(contactid, userid, firstname, lastname, email, phone, timeStamp);
    }
}