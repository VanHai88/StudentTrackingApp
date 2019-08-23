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
public enum PnReportButtomEnum {
    REPORTMONTH("REPORTMONTH"),
    REPORTYEAR("REPORTYEAR"),
    REPORTBETWEENTIME("REPORTBETWEENTIME"),
    LISTCOURS("LISTCOURS")
    ;

    private String value;

    private PnReportButtomEnum(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    public static PnReportButtomEnum convertToEnum(String value){
        switch(value){
            case "REPORTMONTH":
                return REPORTMONTH;
            case "REPORTYEAR":
                return REPORTYEAR;
            case "REPORTBETWEENTIME":
                return REPORTBETWEENTIME;
            case "LISTCOURS":
                return LISTCOURS;
        }
        return null;
    }
}
