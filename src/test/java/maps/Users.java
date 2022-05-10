package maps;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Users {
    private String email;
    private String name;
    private String gender;
    private String status;

}
