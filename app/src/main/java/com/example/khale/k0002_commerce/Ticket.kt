package com.example.khale.k0002_commerce
class  Ticket{
    var tProductNum:String?=null
    var tProductParCode:String?=null
    var tprProductImage:String?=null
    var tprProductName:String?=null
    var tprPackageType:String?=null
    var tprQuantity:String?=null
    var tprPiecesPackage:String?=null
    var tprPiecePrice:String?=null
    var tprReorderQuantity:String?=null
    var tprProductClass:String?=null
    var tprProductDepartment:String?=null
    var tprPerchaceDate:String?=null
    var tprProductDate:String?=null
    var tprExpireDate:String?=null
    var tPranchNum:String?=null
    var tprBillNum:String?=null
    var tprPayCase:String?=null
    var tprDiscount:String?=null
    var tprLocation:String?=null
    var tprDescription:String?=null

    constructor(tProductNum:String,tProductParCode:String,tprProductImage:String,tprProductName:String,
                tprPackageType:String,tprQuantity:String,tprPiecesPackage:String,tprPiecePrice:String,
                tprReorderQuantity:String,tprProductClass:String,tprProductDepartment:String,tprPerchaceDate:String,
                tprProductDate:String,tprExpireDate:String,tPranchNum:String,
                tprBillNum:String,tprPayCase:String,tprDiscount:String,
                tprLocation:String,tprDescription:String){
        this.tProductNum=tProductNum
        this.tProductParCode=tProductParCode
        this.tprProductImage=tprProductImage
        this.tprProductName=tprProductName
        this.tprPackageType=tprPackageType
        this.tprQuantity=tprQuantity
        this.tprPiecesPackage=tprPiecesPackage
        this.tprPiecePrice=tprPiecePrice
        this.tprReorderQuantity=tprReorderQuantity
        this.tprProductClass=tprProductClass
        this.tprProductDepartment=tprProductDepartment
        this.tprPerchaceDate=tprPerchaceDate
        this.tprProductDate=tprProductDate
        this.tprExpireDate=tprExpireDate
        this.tPranchNum=tPranchNum
        this.tprBillNum=tprBillNum
        this.tprPayCase=tprPayCase
        this.tprDiscount=tprDiscount
        this.tprLocation=tprLocation
        this.tprDescription=tprDescription


    }
}
