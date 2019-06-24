package com.example.qiao.myapplication;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class UserManager {
    public boolean insertUser(String name,String password){
        User user = new User();
        user.setUserName(name);
        user.setUserPwd(password);
        LitePal.getDatabase();
        return user.save();


    }
    public boolean deleteUser(String name){
        List<User> users = DataSupport.findAll(User.class);
        for(User user: users){
            if(name.trim().equals(user.getUserName())){
                user.delete();
                return true;
            }
        }
        return false;
    }
    public void updateUser(String name,String newPwd){
        Log.i("dweffvewr",name);
        User user = new User();
        user.setUserPwd(newPwd);
        user.updateAll("userName = ?",name);
        //return true;
    }
    public boolean findUserByName(String name){

        List<User> users = DataSupport.findAll(User.class);
        for(User person: users){
            if(name.trim().equals(person.getUserName())){
                return false;
            }
        }
        return true;
    }

    public boolean findUserByNameAndPwd(String name,String password) {
        List<User> users = DataSupport.findAll(User.class);
        for(User user: users){
            if(name.trim().equals(user.getUserName()) && password.trim().equals(user.getUserPwd())){
                return true;
            }
        }
        return false;
    }

}




