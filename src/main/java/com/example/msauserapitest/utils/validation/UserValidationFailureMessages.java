package com.example.msauserapitest.utils.validation;

public abstract class UserValidationFailureMessages extends ValidationFailureMessages {
    public static abstract class User {
        public static final String NICKNAME_CANNOT_BLANK = "닉네임" + CANNOT_BE_BLANK;
        public static final String NICKNAME_HAS_TO_BE_BETWEEN_LENGTH = "닉네임" + IS_OUT_OF_LENGTH_OF_STRING;
    }

    public static abstract class Account {
        public static final String LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING = "로그인 아이디" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String LOGIN_ID_HAS_TO_BE_IN_EMAIL_FORMAT = "로그인 아이디" + HAS_TO_BE_IN_EMAIL_FORMAT;
        public static final String PASSWORD_CANNOT_BE_BLANK = "비밀번호" + CANNOT_BE_BLANK;
        public static final String PASSWORD_HAS_TO_BE_BETWEEN_LENGTH = "비밀번호" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String USER_CANNOT_BE_NULL = "유저" + CANNOT_BE_NULL;
    }

    public static abstract class SocialAccount {
        public static final String LOGIN_ID_CANNOT_BE_BLANK = "로그인 아이디" + CANNOT_BE_BLANK;
        public static final String LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING = "로그인 아이디" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String USER_CANNOT_BE_NULL = "유저" + CANNOT_BE_NULL;
    }
}
