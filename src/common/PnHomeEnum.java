/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Van Hai
 */
public enum PnHomeEnum {
    PNCOURSENAME("PNCOURSENAME"),
    PNSTUDENTNAME("PNSTUDENTNAME"),
    PNLECTURER("PNLECTURER"),
    PNREPORT("PNREPORT");
    
    
    String value;
    
    PnHomeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static PnHomeEnum covertToEnum(String value){
        switch(value){
            case "PNCOURSENAME":
                return PNCOURSENAME;
            case "PNSTUDENTNAME":
                return PNSTUDENTNAME;
            case  "PNLECTURER":
                return  PNLECTURER;
            case  "PNREPORT":
                return  PNREPORT;
        }
        return null;
    }
}
