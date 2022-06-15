package api;

import com.github.javafaker.Faker;
import maps.Users;

public class ApiBody {

    static Users users;
    public static Faker faker = new Faker();

    public static Users initBodyUsers() {
        users = Users.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().fullName())
                .gender("female")
                .status("active")
                .build();
        return users;
    }

}
