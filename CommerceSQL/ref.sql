drop database Commerce;
create database Commerce;
use Commerce;

create table tbAddress(
Address INT(8) AUTO_INCREMENT,
adCountry varchar(50),
adCity varchar(50),
adStreet varchar(50),
adLocationNum varchar(12),
sdFamousAdgacent varchar(50),
PRIMARY KEY (Address)
);

desc tbAddress;

create table tbContact(
Contacts int(8) AUTO_INCREMENT,
coContactNum int(8),
coContactType int(8),
coContactName varchar(50),
PRIMARY KEY (Contacts)
);

desc tbContact;

create table tbDistriputionPoints(
PranchNum int(8) AUTO_INCREMENT,
diPranchName varchar(50),
Contacts int(10),
diPranchType varchar(15),
diPrranchManager varchar(30),
Address Int(8),
PRIMARY KEY (PranchNum,Address),
FOREIGN KEY (Address)  REFERENCES tbAddress (Address),
FOREIGN KEY (Contacts) REFERENCES tbContact (Contacts)
);

desc tbDistriputionPoints;

create table tbSuppliers(
SupplierNum int(8) AUTO_INCREMENT,
suSupplierName varchar(50),
Contacts int(8),
Address int(8),
PRIMARY KEY (SupplierNum),
FOREIGN KEY (Contacts) references tbContact(Contacts),
FOREIGN KEY (Address) references tbAddress (Address)
);

desc tbSuppliers;

create table tbProducts(
ProductNum int(8) AUTO_INCREMENT,
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

desc tbProducts;

create table tbSupplierProduct(
ProductNum int(8),
SupplierNum int(8),
FOREIGN KEY (ProductNum) references tbProducts(ProductNum),
FOREIGN KEY (SupplierNum) references tbSuppliers(SupplierNum)
);

desc tbSupplierProduct;

create table tbCustomers(
CustomerNum int(8) AUTO_INCREMENT,
cdCustomerName varchar(50),
Contacts int(8),
Address int(8),
PRIMARY KEY (CustomerNum),
FOREIGN KEY (Contacts) references tbContact(Contacts),
FOREIGN KEY (Address) references tbAddress (Address)
);

desc tbCustomers;

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
PRIMARY KEY (ProductNum,CustomerNum),
FOREIGN KEY (PranchNum) references tbDistriputionPoints(PranchNum),
FOREIGN KEY (CustomerNum) references tbCustomers(CustomerNum)
);

desc tbSelling;

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
PRIMARY KEY (ProductNum,CustomerNum),
FOREIGN KEY (PranchNum) references tbDistriputionPoints(PranchNum),
FOREIGN KEY (CustomerNum) references tbCustomers(CustomerNum)
);

desc tbSellinPascket;

create table tbCustomerProduct(
ProductNum int(8),
CustomerNum int(8),
FOREIGN KEY (ProductNum) references tbSelling(ProductNum),
FOREIGN KEY (CustomerNum) references tbCustomers(CustomerNum)	
);

desc tbCustomerProduct;
