/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.GClass;
import java.util.List;
import service.ClassService;
import service.imp.ClassServiceImp;

/**
 *
 * @author Van Hai
 */
public class ClassController {
    private ClassService classService;

    public ClassController() {
        classService = new ClassServiceImp();
    }
    
    public List<GClass> getAllClass() {
        return classService.getAllClass();
    }
}
