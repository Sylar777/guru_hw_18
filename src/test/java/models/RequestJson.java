package models;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class RequestJson {
    private String userName;
    private String password;

    public RequestJson() {
        Faker faker = new Faker();
        this.userName = faker.name().username();
        this.password = "Test0000!";
    }
}
