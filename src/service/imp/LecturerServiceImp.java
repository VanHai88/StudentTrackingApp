
package service.imp;

import dao.LecturerDao;
import dao.imp.LecturerDaoImp;
import entities.Lecturer;
import java.util.List;
import service.LecturerService;

/**
 *
 * @author Van Hai
 */
public class LecturerServiceImp implements LecturerService{
    private LecturerDao lecturerDao;

    public LecturerServiceImp() {
        lecturerDao = new LecturerDaoImp();
    }
    
    @Override
    public List<Lecturer> getAllLecturers() {
        return lecturerDao.getAllLecturers();
    }

    @Override
    public int save(Lecturer lecturer) {
        return lecturerDao.save(lecturer);
    }

    @Override
    public int Edit(Lecturer lecturer) {
        return lecturerDao.Edit(lecturer);
    }

    @Override
    public Lecturer getLecturer(int id) {
        return lecturerDao.getLecturer(id);
    }
    
}
