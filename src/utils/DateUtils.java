/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Van Hai
 */
public class DateUtils {
    public static java.time.LocalDate getLDateFromsDate(final java.sql.Date sDate){
	if(sDate == null) {
            return null;
	}
	return sDate.toLocalDate();
    }
	
    public static Date getDateateFromsLD(LocalDate lDate){
	if(lDate == null) {
            return null;
	}
	return java.sql.Date.valueOf(lDate);
    }
    
    public static LocalDate getLocalDate(java.util.Date udate){
        if(udate == null){
            return LocalDate.now();
        }
        return udate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    } 
}
