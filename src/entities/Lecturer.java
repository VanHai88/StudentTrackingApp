
package entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Van Hai
 */
public class Lecturer {
    private int id;
    private String FullName;
    private String JobDescription;
    private double Wage;
    private String addRess;
    private LocalDate dateOfBirth;
    private String statusLecturers;
    private String email;
    private String pathFile;
    private int evaluate;

    public Lecturer() {
    }

    public Lecturer(int id, String FullName, String JobDescription, double Wage, String addRess, LocalDate dateOfBirth, String statusLecturers, String email, String pathFile, int evaluate) {
        this.id = id;
        this.FullName = FullName;
        this.JobDescription = JobDescription;
        this.Wage = Wage;
        this.addRess = addRess;
        this.dateOfBirth = dateOfBirth;
        this.statusLecturers = statusLecturers;
        this.email = email;
        this.pathFile = pathFile;
        this.evaluate = evaluate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String JobDescription) {
        this.JobDescription = JobDescription;
    }

    public double getWage() {
        return Wage;
    }

    public void setWage(double Wage) {
        this.Wage = Wage;
    }

    public String getAddRess() {
        return addRess;
    }

    public void setAddRess(String addRess) {
        this.addRess = addRess;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStatusLecturers() {
        return statusLecturers;
    }

    public void setStatusLecturers(String statusLecturers) {
        this.statusLecturers = statusLecturers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null ||!(obj instanceof Lecturer)){
            return false;
        }
        
        Lecturer lecturer = (Lecturer) obj;
        return lecturer.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "Lecturer{" + "id=" + id + ", FullName=" + FullName + ", JobDescription=" + JobDescription + ", Wage=" + Wage + ", addRess=" + addRess + ", dateOfBirth=" + dateOfBirth + ", statusLecturers=" + statusLecturers + ", email=" + email + ", pathFile=" + pathFile + ", evaluate=" + evaluate + '}';
    }
    
}
