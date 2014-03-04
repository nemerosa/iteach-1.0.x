package net.nemerosa.iteach.dao.jdbc;

public interface SQL {

    String ACCOUNT_ID_BY_IDENTIFIER = "SELECT ID FROM ACCOUNT WHERE IDENTIFIER = :identifier";
    String ACCOUNT_ID_BY_EMAIL = "SELECT ID FROM ACCOUNT WHERE EMAIL = :email";
    String ACCOUNT_SUMMARY_BY_EMAIL = "SELECT * FROM ACCOUNT WHERE EMAIL = :email";
    String ACCOUNT_CREATE = "INSERT INTO ACCOUNT (ADMINISTRATOR, VERIFIED, MODE, IDENTIFIER, PASSWORD, EMAIL, NAME, DISABLED) VALUES (FALSE, FALSE, :mode, :identifier, :password, :email, :name, FALSE)";
    String ACCOUNT_SET_VERIFIED = "UPDATE ACCOUNT SET VERIFIED = TRUE WHERE ID = :id";

    String TOKEN_SAVE = "INSERT INTO TOKEN (TOKEN, TOKENTYPE, TOKENKEY, CREATION) VALUES (:token, :tokentype, :tokenkey, :creation)";
    String TOKEN_BY_TOKEN_AND_TYPE = "SELECT * FROM TOKEN WHERE TOKEN = :token AND TOKENTYPE = :tokentype ORDER BY CREATION DESC LIMIT 1";
    String TOKEN_DELETE = "DELETE FROM TOKEN WHERE TOKENTYPE = :tokentype AND TOKENKEY = :tokenkey";

}
