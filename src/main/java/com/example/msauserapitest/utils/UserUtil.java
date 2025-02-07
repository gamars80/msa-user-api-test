package com.example.msauserapitest.utils;

import com.example.msauserapitest.user.enums.AgeType;
import com.example.msauserapitest.user.enums.Sex;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtil {

    private static final int ADULT_AGE_MORE_THAN = 19;
    private static final int CHILD_AGE_UNDER = 14;
    private static final int VOTING_ELIGIBLE_AGE = 18;

    public static Sex convertGender(int gender) {
        if (gender < 0 || gender > 9) {
            throw new IllegalArgumentException("올바르지 않은 성별코드입니다.");
        }

        List<Integer> male = List.of(9, 1, 3, 5, 7);
        if (male.contains(gender)) {
            return Sex.M;
        }

        return Sex.F;
    }

    /**
     * @param socialSecurityNumFront: 주민번호 앞자리(YYMMdd)
     * @param socialSecurityNumBack:  주민번호 뒷자리 첫쩨숫자
     */
    public static LocalDate convertBirth(@DateTimeFormat(pattern = "YYMMdd") String socialSecurityNumFront, int socialSecurityNumBack) {
        if (socialSecurityNumBack < 0 || socialSecurityNumBack > 9) {
            throw new IllegalArgumentException("올바르지 않은 성별코드입니다.");
        }

        String completeBirth = switch (socialSecurityNumBack) {
            case 9, 0 -> "18";
            case 1, 2, 5, 6 -> "19";
            case 3, 4, 7, 8 -> "20";
            default -> "";
        } + socialSecurityNumFront;

        String formatString = "yyyyMMdd";

        SimpleDateFormat format = new SimpleDateFormat(formatString);
        format.setLenient(false);
        try {
            format.parse(completeBirth);
            return LocalDate.parse(completeBirth, DateTimeFormatter.ofPattern(formatString));
        } catch (DateTimeParseException | ParseException e) {
            throw new IllegalArgumentException("올바르지 않은 생년월일입니다.");
        }
    }

    public static AgeType ageVerification(LocalDate birth, LocalDateTime today) {
        return ageVerification(birth, LocalDate.from(today));
    }

    public static AgeType ageVerification(LocalDate birth, LocalDate today) {
        if (birth.isAfter(today)) {
            throw new IllegalArgumentException("올바르지 않은 생년월일입니다.");
        }

        Period age = birth.until(today);
        if (age.getYears() >= ADULT_AGE_MORE_THAN) {
            return AgeType.ADULT;
        }
        if (age.getYears() < CHILD_AGE_UNDER) {
            return AgeType.CHILD;
        }

        return AgeType.MINOR;
    }

    public static AgeType ageVerification(LocalDate birth) {
        return ageVerification(birth, LocalDate.now());
    }

    /**
     * @param socialSecurityNumFront: 주민번호 앞자리(YYMMdd)
     * @param socialSecurityNumBack:  주민번호 뒷자리 첫쩨숫자
     */
    public static AgeType ageVerification(String socialSecurityNumFront, int socialSecurityNumBack, LocalDate today) {
        LocalDate birth = convertBirth(socialSecurityNumFront, socialSecurityNumBack);

        return ageVerification(birth, today);
    }

    public static void  validUserName(String userName) {
        if (Pattern.matches(".{33,}", userName)) {
            throw new IllegalArgumentException("이름은 최대 32자까지 작성할 수 있습니다.");
        }

        if (Pattern.matches("^[가-힣]+$", userName) ||
                Pattern.matches("^[a-zA-Z]+[\\.\\-]?[a-zA-Z]+(\s[a-zA-Z]+[\\.\\-]?[a-zA-Z]+){0,4}$", userName)) {
            return;
        }

        if (Pattern.matches("(.*)[가-힣]+(.*)$", userName)) {
            throw new IllegalArgumentException("이름을 정확히 입력해 주세요. 한글 이름에는 영문자, 숫자, 특수문자 및 공백을 사용할 수 없습니다.");
        }

        if (Pattern.matches("(.*)[a-zA-Z]+(.*)$", userName)) {
            throw new IllegalArgumentException("이름을 정확히 입력해 주세요. 영어 이름은 일부 특수문자(.-)를 포함하여 5단어까지 사용할 수 있습니다.");
        }

        throw new IllegalArgumentException("이름을 정확히 입력해 주세요.");
    }

    public static boolean isEligibleForVoting(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);

        return period.getYears() >= VOTING_ELIGIBLE_AGE;
    }
}
