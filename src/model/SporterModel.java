package model;

public class SporterModel extends UserModel {
    private String name;
    private int age;
    private String gender;
    private double weightKg;
    private double heightCm;

    public SporterModel(int id, String username, String password,
                        String email, String phone, String address,
                        String name, int age, String gender,
                        double weightKg, double heightCm) {
        super(id, username, password, email, phone, address);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
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

    public double getWeightKg() {
        return weightKg;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public double calculateBmi() {
        if (heightCm <= 0) {
            return 0;
        }
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    public boolean isHealthy() {
        double bmi = calculateBmi();
        return bmi >= 18.5 && bmi < 25.0;
    }

    @Override
    public String getRole() {
        return "Sporter";
    }

    @Override
    public String getProfileDetails() {
        return "Name: " + name + "\nAge: " + age + "\nGender: " + gender
            + "\nWeight (kg): " + weightKg + "\nHeight (cm): " + heightCm
            + "\nBMI: " + String.format("%.1f", calculateBmi());
    }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
    public void setHeightCm(double heightCm) { this.heightCm = heightCm; }
}
