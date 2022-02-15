/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happ.entity;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class AssistantChef {
   private int IdAC;
   private String FirstNameAC;
   private String LastNameAC;
   private String SexeAC;
   private String EmailAC;
   private String PhoneNumberAC;
   private String DepartmentAC;

    public AssistantChef() {
    }

    public AssistantChef(String FirstNameAC, String LastNameAC, String SexeAC, String EmailAC, String PhoneNumberAC, String DepartmentAC) {
        this.FirstNameAC = FirstNameAC;
        this.LastNameAC = LastNameAC;
        this.SexeAC = SexeAC;
        this.EmailAC = EmailAC;
        this.PhoneNumberAC = PhoneNumberAC;
        this.DepartmentAC = DepartmentAC;
    }

    public AssistantChef(int IdAC, String FirstNameAC, String LastNameAC, String SexeAC, String EmailAC, String PhoneNumberAC, String DepartmentAC) {
        this.IdAC = IdAC;
        this.FirstNameAC = FirstNameAC;
        this.LastNameAC = LastNameAC;
        this.SexeAC = SexeAC;
        this.EmailAC = EmailAC;
        this.PhoneNumberAC = PhoneNumberAC;
        this.DepartmentAC = DepartmentAC;
    }

    public int getIdAC() {
        return IdAC;
    }

    public String getFirstNameAC() {
        return FirstNameAC;
    }

    public String getLastNameAC() {
        return LastNameAC;
    }

    public String getSexeAC() {
        return SexeAC;
    }

    public String getEmailAC() {
        return EmailAC;
    }

    public String getPhoneNumberAC() {
        return PhoneNumberAC;
    }

    public String getDepartmentAC() {
        return DepartmentAC;
    }

    public void setIdAC(int IdAC) {
        this.IdAC = IdAC;
    }

    public void setFirstNameAC(String FirstNameAC) {
        this.FirstNameAC = FirstNameAC;
    }

    public void setLastNameAC(String LastNameAC) {
        this.LastNameAC = LastNameAC;
    }

    public void setSexeAC(String SexeAC) {
        this.SexeAC = SexeAC;
    }

    public void setEmailAC(String EmailAC) {
        this.EmailAC = EmailAC;
    }

    public void setPhoneNumberAC(String PhoneNumberAC) {
        this.PhoneNumberAC = PhoneNumberAC;
    }

    public void setDepartmentAC(String DepartmentAC) {
        this.DepartmentAC = DepartmentAC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.IdAC);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssistantChef other = (AssistantChef) obj;
        if (this.IdAC != other.IdAC) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return "AssistantChef{" + "IdAC=" + IdAC + ", FirstNameAC=" + FirstNameAC + ", LastNameAC=" + LastNameAC + ", SexeAC=" + SexeAC + ", EmailAC=" + EmailAC + ", PhoneNumberAC=" + PhoneNumberAC + ", DepartmentAC=" + DepartmentAC + '}';
    }

   
   
}
