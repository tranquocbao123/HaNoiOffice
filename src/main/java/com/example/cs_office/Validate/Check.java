package com.example.cs_office.Validate;

public class Check {

    public boolean checkNull ( String string ){
        if(string == null)
            return false;
        else return true;
    }

    public boolean checkEmail (String email ) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
