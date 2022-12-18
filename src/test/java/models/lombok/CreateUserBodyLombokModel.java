package models.lombok;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserBodyLombokModel {

    private String name,
            job,
            email,
            id,
            token,
            error,
            password;

}
