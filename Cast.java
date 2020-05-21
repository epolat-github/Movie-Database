public class Cast {
    private String name, surname;
    private String roleTitle;

    public Cast(String name, String surname, String roleTitle) {
        this.name = name;
        this.surname = surname;
        this.roleTitle = roleTitle;
    }

    public String getFullname(){
        String fullName = String.format("%s %s", this.name, this.surname);
        return fullName;
    }

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