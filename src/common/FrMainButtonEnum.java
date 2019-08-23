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
public enum FrMainButtonEnum {
    HOMEPAGE("HOMEPAGE"),
    CLASSPAGE("CLASSPAGE"),
    STUDENTPAGE("CLASSPAGE"),
    LECTURERPAGE("LECTURERPAGE"),
    REPORT("REPORT");
    
    private String value;

    private FrMainButtonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static FrMainButtonEnum covertToEnum(String value){
        switch(value){
            case "HOMEPAGE":
                return HOMEPAGE;
            case "CLASSPAGE":
                return CLASSPAGE;
            case "STUDENTPAGE":
                return STUDENTPAGE;
            case "LECTURERPAGE":
                return LECTURERPAGE;
            case "REPORT":
                return REPORT;
        }
        return null;
    }
}
