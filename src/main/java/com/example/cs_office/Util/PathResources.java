package com.example.cs_office.Util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PathResources {

    public static final String SAVE = "/insert";
    public static final String DELETEBYID = "/delete/{id}";
    public static final String UPDATEBYID = "/update/{id}";
    public static final String UPDATESTATUSFALSE = "/updatestatusfalse";
    public static final String UPDATESTATUSTRUE = "/updatestatustrue";
    public static final String FIND_ALL = "/find_all";
    public static final String FIND_BY_STATUSFALSE = "/find_by_status/false";
    public static final String FIND_BY_STATUSTRUE = "/find_by_status/true";
    public static final String FIND_BY_ID = "/find_by_id/{id}";
    public static final String FIND_BY_NAME = "/find_by_name/{name}";
    public static final String FIND_BY_FLNAME = "/find_by_name/{firstname}/{lastname}";
    public static final String FIND_STAFF = "/find_staff";
    public static final String LOGIN = "/hanoioffice/login";
    public static final String LOGOUT = "/hanoioffice/logout";
    public static final String REGISTER = "/register";
    public static final String INSERTSTAFF = "/staff/insert";
    public static final String FORGOTPASSWORD = "/forgotpassword";
    public static final String CUSTOMERCHANGEPASSWORD = "/customer/changepassword/{customerId}";
    public static final String STAFFCHANGEPASSWORD = "/staff/changepassword/{staffId}";
    public static final String RESETPASSWORD = "/resetpassword";
    public static final String BRANCH = "/branch";
    public static final String CUSTOMER = "/customer";
    public static final String DATEMASTER = "/datemaster";
    public static final String EVALUATE = "/evaluate";
    public static final String ORDERDETAIL = "/orderdetail";
    public static final String ORDERHISTORY = "/orderhistory";
    public static final String ORDER = "/order";
    public static final String PRICESERVICE = "/priceservice";
    public static final String PRICETYPEROOM = "/pricetyperoom";
    public static final String ROLE = "/role";
    public static final String ROOM = "/room";
    public static final String SCHEDULEDETAIL = "/scheduledetail";
    public static final String SCHEDULE = "/schedule";
    public static final String SERVICE = "/service";
    public static final String SERVICEDETAIL = "/servicedetail";
    public static final String SHIFT = "/shift";
    public static final String STAFF = "/staff";
    public static final String TYPEROOM = "/typeroom";
    public static final String COUNTORDERDETAIL = "/countorderdetail";
    public static final String BOOK = "/book";
    public static final String LISTROOM = "/listRoom/{idTypeRoom}/{idBranch}";
    public static final String BOOKROOMLT = "/bookroomlt";
    public static final String CHECKROOMBYID = "/checkroombyid/{idRoom}";
    public static final String LISTBOOKROOM = "/listbookroom";
    public static final String ACCEPT = "/accept/{idOrderDetail}";
    public static final String CANCEL = "/cancel/{idOrderDetail}";
    public static final String LISTROOMSALE = "/listRoomSale/{idTypeRoom}/{idBranch}";
    public static final String ROOKBOOK = "/roomBook/{idCustomer}";
    public static final String BOOKROOMKTL = "bookroomktl";
    public static final String LISTIFBYENDSTART = "listifbyendstart";
}
