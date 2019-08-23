
package entities;

/**
 *
 * @author Van Hai
 */
public class Skill {
    private int id;
    private String tech;

    public Skill() {
    }

    public Skill(int id, String tech) {
        this.id = id;
        this.tech = tech;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        return tech;
    }
}
