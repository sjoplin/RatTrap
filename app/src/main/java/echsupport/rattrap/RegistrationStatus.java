package echsupport.rattrap;

/**
 * Created by sjoplin on 10/2/17.
 */

enum RegistrationStatus {
    USER(false), ADMIN(true);

    boolean priviledge;

    RegistrationStatus(boolean priviledge) {
        this.priviledge = priviledge;
    }

}
