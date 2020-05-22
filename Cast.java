
//-----------------------------------------------------
// Title:       Movie Database with BST
// Author:      Erinç Polat
//              Çağla Su Keşan
// ID:          13715949830
//              ~~other id~~
// Section:     01
// Assignment:  4
// Description: This class defines the Cast object
//-----------------------------------------------------

public class Cast {
    private String name, surname;
    private String roleTitle;

    public Cast(String name, String surname, String roleTitle) {
        this.name = name;
        this.surname = surname;
        this.roleTitle = roleTitle;
    }

    public String getFullname() {
        // Summary: Combines the name and surname of the particular cast for easiness of
        // comparing.
        String fullName = String.format("%s %s", this.name, this.surname);
        return fullName;
    }

    // GETTERS && SETTERS //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

}