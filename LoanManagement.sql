CREATE DATABASE loanmanagement;

CREATE TABLE User(
    
   Username Varchar(233) not Null,
   Password Varchar(233) not Null

);

CREATE TABLE Admin(

    Username Varchar(233) not null,
    Password Varchar(233) not  null
);

INSERT INTO  Admin (Username, Password)
VALUES ('admin','admin');


CREATE TABLE LoanApp(
     purpose VARCHAR(233),
     applno INT NOT NULL AUTO_INCREMENT,
     amtrequest DOUBLE(15,2) NOT NULL,
     doa DATE NOT NULL,
     bstructure VARCHAR(5) NOT NULL,
     bindicator VARCHAR(5) NOT NULL,
     address VARCHAR(233) NOT NULL,
     status VARCHAR(10) NOT NULL,
     email VARCHAR(30) NOT NULL,
     mobile INT NOT NULL,
     Primary Key(applno)
);

CREATE TABLE ApprovedLoan(
    applnum INT NOT NULL AUTO_INCREMENT,
    amotsanctioned DOUBLE(15,2) NOT NULL,
    loanterm INT NOT NULL,
    psd DATE NOT NULL,
    lcd DATE NOT NULL,
    emi INT NOT NULL,
    FOREIGN KEY (`applnum`) REFERENCES `LoanApp`(`applno`) ON DELETE CASCADE
);




   