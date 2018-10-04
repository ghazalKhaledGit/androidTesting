

insert into tbDistriputionPoints (diPranchName) values ("remal");
update tbDistriputionPoints set Address  = PranchNum+11000000 ;	
update tbDistriputionPoints set Contacts = PranchNum+55000000 ;


insert into tbDiAddress (adCity,adStreet,Address) values("khanyonis","ffffff", (select MAX(Address) from tbDistriputionPoints));
insert into tbDiAddress (adCity,adStreet,Address) values("gaza","Java",(select MAX(Address) from tbDistriputionPoints));


insert into tbDiContact (coContactNum,coContactName,Contacts) values("2445254","mobile",(select MAX(Contacts) from tbDistriputionPoints));
insert into tbDiContact (coContactNum,coContactName,Contacts) values("241654848","watanya",(select MAX(Contacts) from tbDistriputionPoints));
insert into tbDiContact (coContactNum,coContactName,Contacts) values("v4igiygyfgt","skype",(select MAX(Contacts) from tbDistriputionPoints));
insert into tbDiContact (coContactNum,coContactName,Contacts) values("g4gfdfxs@gmail.com","watanya",(select MAX(Contacts) from tbDistriputionPoints));
insert into tbDiContact (coContactNum,coContactName,Contacts) values("g4g48gig9gtygf","fb",(select MAX(Contacts) from tbDistriputionPoints));
insert into tbDiContact (coContactNum,coContactName,Contacts) values("74772845254","mobile",(select MAX(Contacts) from tbDistriputionPoints));
insert into tbDiContact (coContactNum,coContactName,Contacts) values("74171231654848","watanya",(select MAX(Contacts) from tbDistriputionPoints));
	

select * from tbDistriputionPoints;
select * from tbDiAddress;	
select * from tbDiContact;


insert into tbSuppliers (suSupplierName) values ("SAlem ghazal");
update tbSuppliers set Address  = SupplierNum+22000000 ;	
update tbSuppliers set Contacts = SupplierNum+66000000 ;

insert into tbSuAddress (adCity,adStreet,Address) values("rafffffah","mostafffffa", (select MAX(Address) from tbSuppliers));

insert into tbSuContact (coContactNum,coContactName,Contacts) values("2811111","mobile",(select MAX(Contacts) from tbSuppliers));
insert into tbSuContact (coContactNum,coContactName,Contacts) values("23111111","watanya",(select MAX(Contacts) from tbSuppliers));
insert into tbSuContact (coContactNum,coContactName,Contacts) values("vi111111giygyfgt","skype",(select MAX(Contacts) from tbSuppliers));
insert into tbSuContact (coContactNum,coContactName,Contacts) values("gg111111fdfxs@gmail.com","watanya",(select MAX(Contacts) from tbSuppliers));
insert into tbSuContact (coContactNum,coContactName,Contacts) values("gg1111118gig9gtygf","fb",(select MAX(Contacts) from tbSuppliers));
insert into tbSuContact (coContactNum,coContactName,Contacts) values("7711111172845254","mobile",(select MAX(Contacts) from tbSuppliers));

select * from tbSuppliers;
select * from tbSuAddress;
select * from tbSuContact;	



insert into tbCustomers (cdCustomerName) values ("Ahmed Ghazal");
update tbCustomers set Address  = CustomerNum+33000000 ;	
update tbCustomers set Contacts = CustomerNum+77000000 ;

insert into tbCuAddress (adCity,adStreet,Address) values("cairo","abbass", (select MAX(Address) from tbCustomers));

insert into tbCuContact (coContactNum,coContactName,Contacts) values("285555555","mobile",(select MAX(Contacts) from tbCustomers));
insert into tbCuContact (coContactNum,coContactName,Contacts) values("23555555","watanya",(select MAX(Contacts) from tbCustomers));
insert into tbCuContact (coContactNum,coContactName,Contacts) values("vi55555giygyfgt","skype",(select MAX(Contacts) from tbCustomers));
insert into tbCuContact (coContactNum,coContactName,Contacts) values("gg55555fdfxs@gmail.com","watanya",(select MAX(Contacts) from tbCustomers));
insert into tbCuContact (coContactNum,coContactName,Contacts) values("gg55555gig9gtygf","fb",(select MAX(Contacts) from tbCustomers));
insert into tbCuContact (coContactNum,coContactName,Contacts) values("77555555572845254","mobile",(select MAX(Contacts) from tbCustomers));

select * from tbCustomers;
select * from tbCuAddress;
select * from tbCuContact;

