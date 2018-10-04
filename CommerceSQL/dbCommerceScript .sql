drop database Commerce;
create database Commerce;
use Commerce;

create table tbDistriputionPoints(
PranchNum int(8) AUTO_INCREMENT,
diPranchName varchar(50),
diPranchType varchar(15),
diPrranchManager varchar(30),
Address int(10) UNIQUE,
Contacts int(10) UNIQUE,
PRIMARY KEY (PranchNum)
);

create table tbDiAddress(
Address INT(10),
adCountry varchar(50),
adCity varchar(50),
adStreet varchar(50),
adLocationNum varchar(12),
sdFamousAdgacent varchar(50),
FOREIGN KEY (Address) REFERENCES tbDistriputionPoints (Address)
);

create table tbDiContact(
Contacts int(10),
coContactNum varchar(60),
coContactType int(8),
coContactName varchar(50),
FOREIGN KEY (Contacts) REFERENCES tbDistriputionPoints (Contacts)
);



create table tbSuppliers(
SupplierNum int(8) AUTO_INCREMENT,
suSupplierName varchar(50),
Address int(10) UNIQUE,
Contacts int(10) UNIQUE,
PRIMARY KEY (SupplierNum)
);

create table tbSuAddress(
Address INT(10),
adCountry varchar(50),
adCity varchar(50),
adStreet varchar(50),
adLocationNum varchar(12),
sdFamousAdgacent varchar(50),
FOREIGN KEY (Address) REFERENCES tbSuppliers (Address)
);

create table tbSuContact(
Contacts int(10),
coContactNum varchar(60),
coContactType int(8),
coContactName varchar(50),
FOREIGN KEY (Contacts) REFERENCES tbSuppliers(Contacts)
);



create table tbCustomers(
CustomerNum int(8) AUTO_INCREMENT,
cdCustomerName varchar(50),
cdPassword varchar(50),
Address int(10) UNIQUE,
Contacts int(10) UNIQUE,
PRIMARY KEY (CustomerNum)
);

create table tbCuAddress(
Address INT(10),
adCountry varchar(50),
adCity varchar(50),
adStreet varchar(50),
adLocationNum varchar(12),
sdFamousAdgacent varchar(50),
FOREIGN KEY (Address) REFERENCES tbCustomers (Address)
);

create table tbCuContact(
Contacts int(10),
coContactNum varchar(60),
coContactType int(8),
coContactName varchar(50),
FOREIGN KEY (Contacts) REFERENCES tbCustomers (Contacts)
);


create table tbProducts(
ProductNum int(8) AUTO_INCREMENT,
ProductParCode varchar(20),
prProductName varchar(50),
prProductImage varchar(100),
prPackageType varchar(20),
prPerchaceQuantity int(8),
prUnitNum int(5),
prUnitPrice int(10),
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
PRIMARY KEY (ProductNum),
FOREIGN KEY (PranchNum) references tbDistriputionPoints(PranchNum)
);


create table tbSupplierProduct(
ProductNum int(8),
SupplierNum int(8),
FOREIGN KEY (ProductNum) references tbProducts(ProductNum),
FOREIGN KEY (SupplierNum) references tbSuppliers(SupplierNum)
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
FOREIGN KEY (PranchNum) references tbDistriputionPoints(PranchNum),
FOREIGN KEY (CustomerNum) references tbCustomers(CustomerNum)
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
FOREIGN KEY (PranchNum) references tbDistriputionPoints(PranchNum),
FOREIGN KEY (CustomerNum) references tbCustomers(CustomerNum)
);




create table tbCustomerProduct(
ProductNum int(8),
CustomerNum int(8),
FOREIGN KEY (ProductNum) references tbSelling(ProductNum),
FOREIGN KEY (CustomerNum) references tbCustomers(CustomerNum)	
);


