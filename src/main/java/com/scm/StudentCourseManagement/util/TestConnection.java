package com.scm.StudentCourseManagement.util;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args){
        try (Connection conn = DBConnection.getConnection()){
            if(conn != null){
                System.out.println("Connection is Successful!!..");
            }else{
                System.out.println("Connection Failed");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}