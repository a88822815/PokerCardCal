package com.example.pokercardcal.ui.home;
import android.view.View;

public class Temp {
    private String total3;
    private String total4;
    private String temp3;
    private String temp4;

    private String editText11;
    private String editText12;
    private String editText13;
    private String editText14;




    public Temp(){
        total3 = "";
        total4 = "";
        editText11 = "";
        editText12 = "";
        editText13 = "";
        editText14 = "";
    }
    public void setTotal3(String s){
        this.total3 = s;
    }
    public String getTotal3(){
        return total3;
    }

    public void setTotal4(String s){
        this.total4 = s;
    }
    public String getTotal4(){
        return total4;
    }

    public void setTemp3(String s){
        this.temp3 = s;
    }
    public String getTemp3(){return temp3;}
    public void setTemp4(String s){
        this.temp4 = s;
    }
    public  String getTemp4(){return temp4;}


    public String getEditText11() {
        return editText11;
    }

    public void setEditText11(String editText11) {
        this.editText11 = editText11;
    }

    public String getEditText12() {
        return editText12;
    }

    public void setEditText12(String editText12) {
        this.editText12 = editText12;
    }

    public String getEditText13() {
        return editText13;
    }

    public void setEditText13(String editText13) {
        this.editText13 = editText13;
    }

    public String getEditText14() {
        return editText14;
    }

    public void setEditText14(String editText14) {
        this.editText14 = editText14;
    }


}
