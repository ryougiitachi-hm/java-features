package per.itachi.java.features.beans.joint.controller.vo;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {

    private String username;

    private LocalDate birthday;

    private String type;
}
