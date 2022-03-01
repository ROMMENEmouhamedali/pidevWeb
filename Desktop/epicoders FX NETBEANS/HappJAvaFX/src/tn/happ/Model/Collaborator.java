package tn.happ.Model;


import tn.happ.Model.Ennumerations.CollaboratorType;
import tn.happ.Model.Ennumerations.UserRole;

public class Collaborator extends User {
    private CollaboratorType CollaboratorType;

    public  CollaboratorType  getCollaboratorType() {
        return this.CollaboratorType;
    }

    public void setCollaboratorType( CollaboratorType collaboratorType) {
      this.CollaboratorType = collaboratorType;
    }

    public Collaborator(String firstName, String lastName, String userName, String email, int phoneNumber, UserRole userRole, String password,CollaboratorType collaboratorType) {
        super(firstName, lastName, userName, email, phoneNumber, userRole, password);
        CollaboratorType = collaboratorType;
    }
}
