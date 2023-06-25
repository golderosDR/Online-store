package de.ait_tr.dtos;

public record NewUserDTO(String name, String email, String password) {
    @Override
    public String toString() {
        return "NewUserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

