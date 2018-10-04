select d.PranchNum, d.diPranchName, a.adCity,a.adStreet,a.Address, c.coContactNum,c.coContactName,c.Contacts 
from tbDistriputionPoints d, tbDiAddress a, tbDiContact c
where d.PranchNum=2 and a.Address=(11000000+d.PranchNum) and c.Contacts =(55000000+d.PranchNum)
and a.adCity="gaza";


select s.SupplierNum, s.suSupplierName, a.adCity,a.adStreet,a.Address, c.coContactNum,c.coContactName,c.Contacts 
from tbSuppliers s, tbSuAddress a, tbSuContact c
where s.SupplierNum=2 and a.Address=(22000000+s.SupplierNum) and c.Contacts =(66000000+s.SupplierNum);
