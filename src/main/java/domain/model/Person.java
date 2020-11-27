package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String userid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public Person(String userid, String firstName, String lastName, String email, String hashedPassword, String role) {
        setUserid(userid);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setHashedPassword(hashedPassword);
        setRoleString(role);
    }

    public Person() {
    }

    public void setUserid(String userid) {
        if (userid == null || userid.trim().isEmpty()) throw new DomainException("No userid given");
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) throw new DomainException("No firstname given");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) throw new DomainException("No last name given");
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new DomainException("No email given");
        String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) throw new DomainException("Email not valid");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) throw new DomainException("No password given");
        try {
            this.password = hashPassword(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setHashedPassword(String hashedPassword) {
        if (hashedPassword == null || hashedPassword.trim().isEmpty()) throw new DomainException("No password given");
        this.password = hashedPassword;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        if (password == null || password.trim().isEmpty()) throw new DomainException("No password given");
        try {
            return getPassword().equals(hashPassword(password));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRoleString(String role) {
        if (role == null || role.trim().isEmpty()) throw new DomainException("Role is empty");
        this.role = Role.valueOf(role.toUpperCase());
    }

    public Role getRole() {
        return role;
    }

    public String getRoleString() {
        return role.getStringValue();
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();
        byte[] passwordBytes = password.getBytes("UTF-8");
        crypt.update(passwordBytes);
        byte[] digest = crypt.digest();
        BigInteger digestAsBigInteger = new BigInteger(1, digest);
        return digestAsBigInteger.toString(16);
    }
}