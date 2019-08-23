/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.CourseService;
import service.imp.CourseServiceImp;

/**
 *
 * @author Van Hai
 */
public class CourseController {
    private CourseService courseService;

    public CourseController() {
        courseService = new CourseServiceImp();
    }
    
    public int countCourse(){
        return courseService.countCourse();
    }
}
