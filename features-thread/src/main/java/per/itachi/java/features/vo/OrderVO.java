package per.itachi.java.features.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {

    private String orderNbr;

    private volatile String username;

    private String paidAccountNbr;
}
