package per.itachi.java.features.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEntry {

    private static final String REGEX_IDCARD_18 =
            "(?<=[^0-9])[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx](?=[^0-9])";
//            "[^0-9][1-9]\\d{5}(19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]";

    public static void main(String[] args) {
        String originalText = args[0];
        int matchedCount = 0;

        Pattern pattern = Pattern.compile(REGEX_IDCARD_18);
        Matcher matcher = pattern.matcher(originalText);
        while (matcher.find()) {
            System.out.printf("The matched part is %s, start=%d, end=%d. %n",
                    matcher.group(), matcher.start(), matcher.end());
//            System.out.printf("The matched part is %s. %n", matcher.group(1));
            ++ matchedCount;
        }

        if (matchedCount <= 0) {
            System.out.println("There is no pattern matched. ");
        }
    }
}
