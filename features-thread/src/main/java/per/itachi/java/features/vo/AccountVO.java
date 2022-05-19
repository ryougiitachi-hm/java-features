package per.itachi.java.features.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountVO {

    private String accountNbr;

    private int accountType;

    private LocalDateTime cdate;
}
