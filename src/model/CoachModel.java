package model;

public class CoachModel extends UserModel {
    private String name;
    private String specialty;

    public CoachModel(int id, String username, String password,
                      String email, String phone, String address,
                      String name, String specialty) {
        super(id, username, password, email, phone, address);
        this.name = name;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getRole() {
        return "Coach";
    }

    @Override
    public String getProfileDetails() {
        return "Name: " + name + "\nSpecialty: " + specialty;
    }

    public void setName(String name) { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
