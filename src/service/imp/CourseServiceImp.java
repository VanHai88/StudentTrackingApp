/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.CourseDao;
import dao.imp.CoursesDaoImp;
import service.CourseService;

/**
 *
 * @author Van Hai
 */
public class CourseServiceImp implements CourseService{
    private CourseDao courseDao;

    public CourseServiceImp() {
        courseDao = new CoursesDaoImp();
    }
    
    @Override
    public int countCourse() {
        return courseDao.countCourse();
    }
    
}
