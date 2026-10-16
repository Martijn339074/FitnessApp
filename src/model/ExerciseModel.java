package model;

public class ExerciseModel {
    private int id;
    private String name;
    private String muscleGroup;
    private String description;

    public ExerciseModel(int id, String name, String muscleGroup, String description) {
        this.id = id;
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getDescription() {
        return description;
    }
}
