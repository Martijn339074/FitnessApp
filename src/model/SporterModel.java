package model;

import model.UserModel;

public class SporterModel extends UserModel {
    private String name;
    private int age;
    private String gender;
    public SporterModel(int id, String username, String password,
                        String email, String phone, String address,
                        String name, int age, String gender) {
        super(id, username, password, email, phone, address);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    
    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
}
