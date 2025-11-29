package org.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\d+|[+\\-*/]");
    private final Validator validator;

    public Parser(Validator validator) {
        this.validator = validator;
    }

    public List<String> parse(String input) {
        validator.validate(input);

        return getTokens(input);
    }

    private static List<String> getTokens(String input) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(input);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }
}
