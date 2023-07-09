DROP TABLE TEAM
CREATE TABLE TEAM (

                        TEAM_ID VARCHAR(255) NOT NULL,
                        NAME VARCHAR(255),
                        PRIMARY KEY (TEAM_ID)
)
DROP TABLE MEMBER
CREATE TABLE MEMBER (

                        MEMBER_ID VARCHAR(255) NOT NULL,
                        TEAM_ID VARCHAR(255),
                        USERNAME VARCHAR(255),
                        PRIMARY KEY (MEMBER_ID)
)


ALTER TABLE MEMBER ADD CONSTRAINT FK_MEMBER_TEAM
    FOREIGN KEY(TEAM_ID)
        REFERENCES TEAM