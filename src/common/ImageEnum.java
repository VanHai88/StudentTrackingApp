
package common;

/**
 *
 * @author qphan
 */
public enum ImageEnum {
    DESKTOP("/images/desktop.png"),
    EDIT("/images/edit.png"),
    DELETE("/images/delete.png");

    private String path;

    ImageEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
