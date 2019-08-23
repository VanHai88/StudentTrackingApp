
package entities;

/**
 *
 * @author Van Hai
 */
public class LecturerHasSkill {
    private String tech;
    private int exp;

    public LecturerHasSkill() {
    }

    public LecturerHasSkill(String tech, int exp) {
        this.tech = tech;
        this.exp = exp;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "LecturerHasSkill{" + "tech=" + tech + ", exp=" + exp + '}';
    }

}
