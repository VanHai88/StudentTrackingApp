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
public enum CrudEnum {
    ADD("ADD"), EDIT("EDIT"), DELETE("DELETE");
    
    String value;
    
    CrudEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static CrudEnum covertToEnum(String value){
        switch(value){
            case "ADD":
                return ADD;
            case "EDIT":
                return EDIT;
            case  "DELETE":
                return  DELETE;
        }
        return null;
    }
}
