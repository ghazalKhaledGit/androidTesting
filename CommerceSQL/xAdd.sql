drop database Commerce;
create database Commerce;
use Commerce;


create table tbUsers(
userNum int(8) AUTO_INCREMENT,
userName varchar(50),
userPassword varchar(50),
userEmail varchar(60),
userImage varchar(350),
userType varchar(50) DEFAULT 'Customer',
PRIMARY KEY (userNum)
);

create table tbUserAddress(
userNum int(8),
userCountry varchar(50),
userCity varchar(50),
userStreet varchar(50),
userLocationNum varchar(12),
userFamousAdgacent varchar(50),
FOREIGN KEY (userNum) REFERENCES tbUsers (userNum)
);

create table tbUserContact(
userNum int(8),
userContactNum varchar(60),
userContactType int(8),
userContactName varchar(50),
FOREIGN KEY (userNum) REFERENCES tbUsers (userNum)
);

create table tbProducts(
ProductNum int(8) AUTO_INCREMENT,
ProductParCode varchar(20),
prProductImage varchar(600),
prProductName varchar(50),
prPackageType varchar(20),
prQuantity int(8),
prPiecesPackage  int(5),
prPiecePrice int(10),
prReorderQuantity int(5),
prProductClass varchar(50),
prProductDepartment varchar(50),
prPerchaceDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
prProductDate TIMESTAMP,
prExpireDate TIMESTAMP,
PranchNum int(8),
prBillNum int(8),
prPayCase varchar(50),
prDiscount int (5),
prLocation varchar(6),
prDescription varchar(300),
CategoryNum1 INT (4),
CategoryNum2 INT (4),
CategoryNum3 INT (4),
CategoryNum4 INT (4),
CategoryNum5 INT (4),
PRIMARY KEY (ProductNum)
FOREIGN key (CategoryNum1) REFERENCES tbCatigoies1 (CategoryNum1);

); 

CREATE TABLE tbCatigoies1(
CategoryNum1 INT (4),
CatigoryName1_A varchar (50),
CatigoryName1_E varchar (50),
PRIMARY KEY CategoryNum1
); 

CREATE TABLE tbCatigoies2(
CategoryNum2 INT (4),
CatigoryName2_A varchar (50),
CatigoryName2_E varchar (50),
PRIMARY KEY CategoryNum1
);

CREATE TABLE tbCatigoies1(
CategoryNum1 INT (4),
CatigoryName1_A varchar (50),
CatigoryName1_E varchar (50),
PRIMARY KEY CategoryNum1
);

CREATE TABLE tbCatigoies1(
CategoryNum1 INT (4),
CatigoryName1_A varchar (50),
CatigoryName1_E varchar (50),
PRIMARY KEY CategoryNum1
);
CREATE TABLE tbCatigoies1(
CategoryNum1 INT (4),
CatigoryName1_A varchar (50),
CatigoryName1_E varchar (50),
PRIMARY KEY CategoryNum1
);
/*



create table tbDistriputionPoints(
diPranchNum int(8) AUTO_INCREMENT,
diPranchName varchar(50),
diPranchType varchar(15),
diPranchManager varchar(30),
diAddress int(10) UNIQUE,
diContacts int(10) UNIQUE,
PRIMARY KEY (diPranchNum)
);

create table tbDiAddress(
diAddress INT(10),
diCountry varchar(50),
diCity varchar(50),
diStreet varchar(50),
diLocationNum varchar(12),
diFamousAdgacent varchar(50),
FOREIGN KEY (diAddress) REFERENCES tbDistriputionPoints (diAddress)
);

create table tbDiContact(
diContacts int(10),
diContactNum varchar(60),
diContactType int(8),
diContactName varchar(50),
FOREIGN KEY (diContacts) REFERENCES tbDistriputionPoints (diContacts)
);



create table tbSuppliers(
suSupplierNum int(8) AUTO_INCREMENT,
suSupplierName varchar(50),
suAddress int(10) UNIQUE,
suContacts int(10) UNIQUE,
PRIMARY KEY (suSupplierNum)
);

create table tbSuAddress(
suAddress INT(10),
suCountry varchar(50),
suCity varchar(50),
suStreet varchar(50),
suLocationNum varchar(12),
suFamousAdgacent varchar(50),
FOREIGN KEY (suAddress) REFERENCES tbSuppliers (suAddress)
);

create table tbSuContact(
suContacts int(10),
suContactNum varchar(60),
suContactType int(8),
suContactName varchar(50),
FOREIGN KEY (suContacts) REFERENCES tbSuppliers(suContacts)
);



create table tbCustomers(
customerNum int(8) AUTO_INCREMENT,
customerName varchar(50),
customerPassword varchar(50),
customerEmail varchar(60),
customerImage varchar(150),
customerAddress int(10) UNIQUE,
customerContacts int(10) UNIQUE,
PRIMARY KEY (customerNum)
);

create table tbCustomerAddress(
customerAddress INT(10),
customerCountry varchar(50),
customerCity varchar(50),
customerStreet varchar(50),
customerLocationNum varchar(12),
customerFamousAdgacent varchar(50),
FOREIGN KEY (customerAddress) REFERENCES tbCustomers (customerAddress)
);

create table tbCustomerContact(
customerContacts int(10),
customerContactNum varchar(60),
customerContactType int(8),
customerContactName varchar(50),
FOREIGN KEY (customerContacts) REFERENCES tbCustomers (customerContacts)
);


create table tbProducts(
ProductNum int(8) AUTO_INCREMENT,
ProductParCode varchar(20),
prProductName varchar(50),
prProductImage varchar(100),
prPackageType varchar(20),
prQuantity int(8),
prPiecesPackage  int(5),
prPiecePrice int(10),
prReorderQuantity int(5),
prProductClass varchar(50),
prProductDepartment varchar(50),
PranchNum int(8),
prBillNum int(8),
prPerchaceDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
prProductDate TIMESTAMP,
prExpireData TIMESTAMP,
prPayCase varchar(50),
prDiscount int (5),
prLocation varchar(6),
prDescription varchar(300),
PRIMARY KEY (ProductNum),
FOREIGN KEY (PranchNum) references tbDistriputionPoints(diPranchNum)
); 



create table tbSupplierProduct(
ProductNum int(8),
SupplierNum int(8),
FOREIGN KEY (ProductNum) references tbProducts(ProductNum),
FOREIGN KEY (SupplierNum) references tbSuppliers(suSupplierNum)
);




create table tbSelling(
ProductNum int(8) AUTO_INCREMENT,
ProductParCode varchar(20),
seUnitPrice int(9.2),
seSellingDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
sePackageType varchar(15),
seSellingQuantity int(12),
seUnitNum int(12), 
PranchNum int(8),
CustomerNum int(8),
seBillNum int(12),
sePayCase varchar(15),
seDiscount int(5.2),
PRIMARY KEY (ProductNum),
FOREIGN KEY (PranchNum) references tbDistriputionPoints(diPranchNum),
FOREIGN KEY (CustomerNum) references tbCustomers(cuCustomerNum)
);



create table tbSellinPascket(
ProductNum int(8) AUTO_INCREMENT,
ProductParCode varchar(20),
seUnitPrice int(9.2),
seSellingDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
sePackageType varchar(15),
seSellingQuantity int(12),
seUnitNum int(12), 
PranchNum int(8),
CustomerNum int(8),
seBillNum int(12),
sePayCase varchar(15),
seDiscount int(5.2),
PRIMARY KEY (ProductNum),
FOREIGN KEY (PranchNum) references tbDistriputionPoints(diPranchNum),
FOREIGN KEY (CustomerNum) references tbCustomers(cuCustomerNum)
);




create table tbCustomerProduct(
ProductNum int(8),
CustomerNum int(8),
FOREIGN KEY (ProductNum) references tbSelling(ProductNum),
FOREIGN KEY (CustomerNum) references tbCustomers(cuCustomerNum)	
);


*/