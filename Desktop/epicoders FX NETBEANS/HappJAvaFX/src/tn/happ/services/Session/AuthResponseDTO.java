package tn.happ.services.Session;


public class AuthResponseDTO {
    private int idUser;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String userRole;
    private String collaboratorType;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCollaboratorType() {
        return collaboratorType;
    }

    public void setCollaboratorType(String collaboratorType) {
        this.collaboratorType = collaboratorType;
    }

    @Override
    public String toString() {
        return "AuthResponseDTO{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userRole='" + userRole + '\'' +
                ", collaboratorType='" + collaboratorType + '\'' +
                '}';
    }
}

