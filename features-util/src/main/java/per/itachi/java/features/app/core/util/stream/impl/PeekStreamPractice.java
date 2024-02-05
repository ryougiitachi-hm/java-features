package per.itachi.java.features.app.core.util.stream.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.app.core.util.stream.StreamPractice;
import per.itachi.java.features.app.dto.UserDTO;

/**
 * Intermediate operation.
 * The peek function is similar to forEach but intermediate action instead of terminal action,
 * with return value.
 * */
@Slf4j
public class PeekStreamPractice implements StreamPractice {

    @Override
    public String methodName() {
        return METHOD_NAME_STREAM_PEEK;
    }

    @Override
    public void exerciseNormal() {
        log.info("Start {} exerciseNormal", getClass().getSimpleName());

        // String is immutable so there is nothing changed in this case.
        List<String> listStr = Arrays.asList("a", "b", "c", "d", "e");
        List<String> listStrResult = listStr.stream()
                .peek(element -> {
                    element.substring(1, 1);
                })
                .toList(); // listStr == listStrResult actually.
        log.info("The result of listStrResult is {}, listStr={}, listStrResult={}",
                Objects.hashCode(listStrResult), Objects.hashCode(listStr), listStrResult);
        // Java bean class can have better expression for indicating how peek works.
        List<UserDTO> listUser = Arrays.asList(
                createUser("", LocalDate.of(2023, 12, 3)),
                createUser("", LocalDate.of(2023, 12, 4)),
                createUser("", LocalDate.of(2023, 12, 5)),
                createUser("", LocalDate.of(2023, 12, 6)),
                createUser("", LocalDate.of(2023, 12, 7)),
                createUser("", LocalDate.of(2023, 12, 8))
        );
        List<UserDTO> listUserResult = listUser.stream()
                .peek(user -> {
                    user.setUsername(UUID.randomUUID().toString());
                })
                .toList();
        log.info("The result of listUserResult is {}", listUserResult);

        log.info("Finish {} exerciseNormal", getClass().getSimpleName());
    }

    @Override
    public void exerciseException() {
        log.info("There is nothing in {} currently. ", getClass().getSimpleName());
    }

    private UserDTO createUser(String username, LocalDate birthday) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        return userDTO;
    }
}
