DROP TABLE IF EXISTS ACCOUNT;  
CREATE TABLE ACCOUNT (  
acno BIGINT ,
pin  INT(4) NOT NULL,  
balance NUMERIC NOT NULL  ,
overdraft NUMERIC 
); 
DROP TABLE IF EXISTS DENOMINATION;  
CREATE TABLE DENOMINATION (  
denomination  INT ,
quantity INT 
);   