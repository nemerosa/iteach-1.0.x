CREATE TABLE VERSION (
  VALUE   INTEGER   NOT NULL,
  UPDATED TIMESTAMP NOT NULL
);

CREATE TABLE ACCOUNT (
  ID            INTEGER      NOT NULL AUTO_INCREMENT,
  ADMINISTRATOR BOOLEAN      NOT NULL,
  MODE          VARCHAR(10)  NOT NULL,
  IDENTIFIER    VARCHAR(200) NOT NULL,
  PASSWORD      VARCHAR(300) NOT NULL,
  EMAIL         VARCHAR(100) NOT NULL,
  NAME          VARCHAR(80)  NOT NULL,
  VERIFIED      BOOLEAN      NOT NULL,
  DISABLED      BOOLEAN      NOT NULL,
  CONSTRAINT ACCOUNT_PK PRIMARY KEY (ID),
  CONSTRAINT ACCOUNT_UQ_IDENTIFIER UNIQUE (IDENTIFIER),
  CONSTRAINT ACCOUNT_UQ_EMAIL UNIQUE (EMAIL)
);

CREATE TABLE TOKEN (
  TOKEN     VARCHAR(200) NOT NULL,
  TOKENTYPE VARCHAR(20)  NOT NULL,
  TOKENKEY  VARCHAR(80)  NOT NULL,
  CREATION  TIMESTAMP    NOT NULL,
  CONSTRAINT TOKEN_PK PRIMARY KEY (TOKEN)
);

CREATE TABLE SCHOOL (
  ID            INTEGER      NOT NULL AUTO_INCREMENT,
  TEACHERID     INTEGER      NOT NULL,
  NAME          VARCHAR(80)  NOT NULL,
  CONTACT       VARCHAR(80)  NULL,
  COLOUR        CHAR(7)      NOT NULL,
  EMAIL         VARCHAR(120) NULL,
  HOURLYRATE    VARCHAR(20)  NULL,
  POSTALADDRESS VARCHAR(200) NULL,
  PHONE         VARCHAR(40)  NULL,
  MOBILEPHONE   VARCHAR(40)  NULL,
  WEBSITE       VARCHAR(200) NULL,
  CONSTRAINT SCHOOL_PK PRIMARY KEY (ID),
  CONSTRAINT SCHOOL_UQ UNIQUE (TEACHERID, NAME),
  CONSTRAINT SCHOOL_FK_TEACHER FOREIGN KEY (TEACHERID) REFERENCES ACCOUNT (ID)
    ON DELETE CASCADE
);

CREATE TABLE STUDENT (
  ID            INTEGER      NOT NULL AUTO_INCREMENT,
  TEACHERID     INTEGER      NOT NULL,
  SCHOOLID      INTEGER      NOT NULL,
  NAME          VARCHAR(80)  NOT NULL,
  SUBJECT       VARCHAR(120) NULL,
  EMAIL         VARCHAR(120) NULL,
  POSTALADDRESS VARCHAR(200) NULL,
  PHONE         VARCHAR(40)  NULL,
  MOBILEPHONE   VARCHAR(40)  NULL,
  CONSTRAINT STUDENT_PK PRIMARY KEY (ID),
  CONSTRAINT STUDENT_UQ UNIQUE (TEACHERID, NAME),
  CONSTRAINT STUDENT_FK_TEACHER FOREIGN KEY (TEACHERID) REFERENCES ACCOUNT (ID)
    ON DELETE CASCADE,
  CONSTRAINT STUDENT_FK_SCHOOL FOREIGN KEY (TEACHERID, SCHOOLID) REFERENCES SCHOOL (TEACHERID, ID)
    ON DELETE CASCADE
);

CREATE TABLE LESSON (
  ID           INTEGER     NOT NULL AUTO_INCREMENT,
  TEACHERID    INTEGER     NOT NULL,
  STUDENTID    INTEGER     NOT NULL,
  PLANNINGFROM DATETIME    NOT NULL,
  PLANNINGTO   DATETIME    NOT NULL,
  LOCATION     VARCHAR(80) NULL,
  CONSTRAINT LESSON_PK PRIMARY KEY (ID),
  CONSTRAINT LESSON_FK_TEACHER FOREIGN KEY (TEACHERID) REFERENCES ACCOUNT (ID)
    ON DELETE CASCADE,
  CONSTRAINT LESSON_FK_STUDENT FOREIGN KEY (TEACHERID, STUDENTID) REFERENCES STUDENT (TEACHERID, ID)
    ON DELETE CASCADE
);

-- Initial admin account
INSERT INTO ACCOUNT (ADMINISTRATOR, MODE, IDENTIFIER, PASSWORD, EMAIL, NAME, VERIFIED, DISABLED)
  VALUES (TRUE, 'PASSWORD', 'admin', '$2a$10$UGNrQwIPG0a/UAGH4FV6IuPLMe5I8.NcucdSEv115jjXslu9OGEqi',
          '', 'Administrator', TRUE, FALSE);
