package per.itachi.java.features.app.core.util.stream.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.app.core.util.stream.StreamPractice;
import per.itachi.java.features.app.dto.UserDTO;

/**
 * Terminal operation.
 * */
@Slf4j
public class CollectStreamPractice implements StreamPractice {

    @Override
    public String methodName() {
        return METHOD_NAME_STREAM_COLLECT;
    }

    @Override
    public void exerciseNormal() {
    }

    @Override
    public void exerciseException() {
        log.info("Start {} exerciseException", getClass().getSimpleName());
        // Collectors.toMap doesn't exist in JDK8.
        // not sure which version this Collectors.toMap was added to JDK since.
        // at least JDK11.
        List<UserDTO> listUser = Arrays.asList(
                createUser("a", LocalDate.of(2023, 12, 3)),
                createUser("a", LocalDate.of(2023, 12, 4)),
                createUser("b", LocalDate.of(2023, 12, 5)),
                createUser("a", LocalDate.of(2023, 12, 6)),
                createUser("d", LocalDate.of(2023, 12, 7)),
                createUser("e", LocalDate.of(2023, 12, 8))
        );
        // An exception IllegalStateException will be thrown due to Duplicate key in this case.
        // Duplicate key a (attempted merging values UserDTO ... ).
//        Map<String, UserDTO> mapUser = listUser.stream()
//                .collect(Collectors.toMap(UserDTO::getUsername, user->user));
        // To avoid above exceptional case, the parameters mergeFunction and mapSupplier can be considerable.
        // Mainly mergeFunction, which can be used to resolve conflict. It is still available when the number of conflict >= 3.
        // mapSupplier is used to specify which Map should be returned, such as TreeMap::new. HashMap by default.
        Map<String, UserDTO> mapUser = listUser.stream()
                .collect(Collectors.toMap(UserDTO::getUsername, user->user, (user1, user2)->user1));
        log.info("The value of mapUser {} is {}. ", mapUser.getClass().getSimpleName(), mapUser);
        log.info("Finish {} exerciseException", getClass().getSimpleName());
    }

    private UserDTO createUser(String username, LocalDate birthday) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        return userDTO;
    }
}
