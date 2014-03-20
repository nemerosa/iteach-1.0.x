package net.nemerosa.iteach.dao.jdbc;

public interface SQL {

    String ACCOUNT_ID_BY_IDENTIFIER = "SELECT ID FROM ACCOUNT WHERE IDENTIFIER = :identifier";
    String ACCOUNT_ID_BY_EMAIL = "SELECT ID FROM ACCOUNT WHERE EMAIL = :email";
    String ACCOUNT_SUMMARY_BY_ID = "SELECT * FROM ACCOUNT WHERE ID = :id";
    String ACCOUNT_ALL = "SELECT * FROM ACCOUNT ORDER BY NAME";
    String ACCOUNT_SUMMARY_BY_EMAIL = "SELECT * FROM ACCOUNT WHERE EMAIL = :email";
    String ACCOUNT_CREATE = "INSERT INTO ACCOUNT (ADMINISTRATOR, VERIFIED, MODE, IDENTIFIER, PASSWORD, EMAIL, NAME, DISABLED) VALUES (FALSE, FALSE, :mode, :identifier, :password, :email, :name, FALSE)";
    String ACCOUNT_SET_VERIFIED = "UPDATE ACCOUNT SET VERIFIED = TRUE WHERE ID = :id";
    String ACCOUNT_BY_PASSWORD = "SELECT * FROM ACCOUNT WHERE MODE = 'PASSWORD' AND IDENTIFIER = :identifier AND VERIFIED IS TRUE AND DISABLED IS FALSE";
    String ACCOUNT_PASSWORD = "SELECT PASSWORD FROM ACCOUNT WHERE MODE = 'PASSWORD' AND ID = :id AND VERIFIED IS TRUE AND DISABLED IS FALSE";

    String TOKEN_SAVE = "INSERT INTO TOKEN (TOKEN, TOKENTYPE, TOKENKEY, CREATION) VALUES (:token, :tokentype, :tokenkey, :creation)";
    String TOKEN_BY_TOKEN_AND_TYPE = "SELECT * FROM TOKEN WHERE TOKEN = :token AND TOKENTYPE = :tokentype ORDER BY CREATION DESC LIMIT 1";
    String TOKEN_DELETE = "DELETE FROM TOKEN WHERE TOKENTYPE = :tokentype AND TOKENKEY = :tokenkey";

    String SCHOOL_ALL = "SELECT * FROM SCHOOL WHERE TEACHERID = :teacherId ORDER BY NAME";
    String SCHOOL_BY_ID = "SELECT * FROM SCHOOL WHERE TEACHERID = :teacherId AND ID = :schoolId";
    String SCHOOL_CREATE = "INSERT INTO SCHOOL (TEACHERID, NAME, CONTACT, COLOUR, EMAIL, HOURLYRATE, POSTALADDRESS, PHONE, MOBILEPHONE, WEBSITE) VALUES (:teacherId, :name, :contact, :colour, :email, :hourlyRate, :postalAddress, :phone, :mobilePhone, :webSite)";
    String SCHOOL_UPDATE = "UPDATE SCHOOL SET NAME = :name, CONTACT = :contact, COLOUR = :colour, EMAIL = :email, HOURLYRATE = :hourlyRate, POSTALADDRESS = :postalAddress, PHONE = :phone, MOBILEPHONE = :mobilePhone, WEBSITE = :webSite WHERE TEACHERID = :teacherId AND ID = :schoolId";

    String STUDENT_CREATE = "INSERT INTO STUDENT (TEACHERID, SCHOOLID, NAME, SUBJECT, EMAIL, POSTALADDRESS, PHONE, MOBILEPHONE) VALUES (:teacherId, :schoolId, :name, :subject, :email, :postalAddress, :phone, :mobilePhone)";
    String STUDENT_BY_ID = "SELECT * FROM STUDENT WHERE TEACHERID = :teacherId AND ID = :studentId";
    String STUDENT_ALL = "SELECT * FROM STUDENT WHERE TEACHERID = :teacherId ORDER BY NAME ASC";

    String LESSON_CREATE = "INSERT INTO LESSON (TEACHERID, STUDENTID, LOCATION, PLANNINGFROM, PLANNINGTO) VALUES (:teacherId, :studentId, :location, :planningForm, :planningTo)";
}
