package echsupport.rattrap;

/**
 * Created by sjoplin on 10/2/17.
 */

public enum RegistrationStatus {
    USER(false, "User"), ADMIN(true, "Admin");

    private boolean priviledge;
    private String value;

    RegistrationStatus(boolean priviledge, String value) {
        this.value = value;
        this.priviledge = priviledge;
    }

    public String toString() {
        return value;
    }

}
