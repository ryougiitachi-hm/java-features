package per.itachi.java.features.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

    private String username;

    private int userType;

    private String permission;
}
