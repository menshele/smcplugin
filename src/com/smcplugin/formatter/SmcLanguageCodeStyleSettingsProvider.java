package com.smcplugin.formatter;

import com.intellij.lang.Language;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.smcplugin.SmcLanguage;
import org.jetbrains.annotations.NotNull;

import static com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable.SpacingOption.*;
import static com.smcplugin.formatter.SmcCodeStyleSettings.SmcOptions;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    public static final String SEPARATORS_GROUP = "Spaces around separators";
    public static final String INSERT_SPACES_BEFORE_LABEL = "Insert space before brace";
    private static final String SPACES_AFTER_KEYWORD_LABEL = "Space After Keyword";
    public static final String SPACE_WITHIN_PARENTHESES_LABEL = "Space within parentheses";
    public static final String SPACE_BEFORE_PARENTHESES_LABEL = "Space before parentheses";
    public static final String SPACE_AROUND_MAP_STATE_SEPARATOR_LABEL = "Space around map-state (\"::\") separator";
    public static final String PUSH_PROXY_STATE_KEYWORD_SEPARATOR_LABEL = "Space around push-proxy-state (\"/\") separator";
    private static final String SPACE_BEFORE_TRANSITION_ARGS_LABEL = "Space before transition arguments";

    @NotNull
    @Override
    public Language getLanguage() {
        return SmcLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {

            consumer.moveStandardOption(String.valueOf(SPACE_BEFORE_COLON), SEPARATORS_GROUP);
            consumer.moveStandardOption(String.valueOf(SPACE_AFTER_COLON), SEPARATORS_GROUP);
            consumer.moveStandardOption(String.valueOf(SPACE_AFTER_COMMA), SEPARATORS_GROUP);
            consumer.moveStandardOption(String.valueOf(SPACE_BEFORE_SEMICOLON), SEPARATORS_GROUP);
            consumer.moveStandardOption(String.valueOf(SPACE_AFTER_SEMICOLON), SEPARATORS_GROUP);

            consumer.showStandardOptions(String.valueOf(SPACE_BEFORE_COLON), String.valueOf(SPACE_AFTER_COLON), String.valueOf(SPACE_AFTER_COMMA),
                    String.valueOf(SPACE_BEFORE_SEMICOLON), String.valueOf(SPACE_AFTER_SEMICOLON), String.valueOf(SPACE_WITHIN_BRACKETS), String.valueOf(SPACE_WITHIN_BRACES));

            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_LEFT_BRACE), INSERT_SPACES_BEFORE_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_LEFT_BRACE);

            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AFTER_KEYWORD), SPACES_AFTER_KEYWORD_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_WITHIN_PARENTHESES), SPACE_WITHIN_PARENTHESES_LABEL, CodeStyleSettingsCustomizable.SPACES_WITHIN);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_PARENTHESES_OPEN), SPACE_BEFORE_PARENTHESES_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_TRANSITION_ARGS), SPACE_BEFORE_TRANSITION_ARGS_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AROUND_MAP_STATE_SEPARATOR), SPACE_AROUND_MAP_STATE_SEPARATOR_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR), PUSH_PROXY_STATE_KEYWORD_SEPARATOR_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);

        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {

            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.BLANK_LINES_AROUND_MAP), "Blank lines around \"Map\" definition", CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.BLANK_LINES_AROUND_STATE), "Blank lines around \"State\" definition", CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.BLANK_LINES_AROUND_TRANSITION), "Blank lines around \"Transition\" definition", CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.BLANK_LINES_AROUND_ENTRY), "Blank lines around \"Entry\" definition", CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.BLANK_LINES_AROUND_EXIT), "Blank lines around \"Exit\" definition", CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);

        } else if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
            newWrapOption(consumer, SmcOptions.WRAP_ACTIONS, "Wrap \"Action\" statement");
            newWrapOption(consumer, SmcOptions.WRAP_ENTRY, "Wrap \"Entry\"block");
            newWrapOption(consumer, SmcOptions.WRAP_EXIT, "Wrap \"Exit\" block");
            newWrapOption(consumer, SmcOptions.WRAP_GUARD, "Wrap \"Guard\" statement");
            newWrapOption(consumer, SmcOptions.WRAP_NEXT_STATE, "Wrap \"Next state\" name");
            newWrapOption(consumer, SmcOptions.WRAP_POP_TRANSITION, "Wrap \"Pop transition\" statement");
            newWrapOption(consumer, SmcOptions.WRAP_PUSH_TRANSITION, "Wrap \"Push transition\" statement");
            newWrapOption(consumer, SmcOptions.WRAP_STATE, "Wrap \"State\" Statement");
            newWrapOption(consumer, SmcOptions.WRAP_TRANSITION, "Wrap \"Transition\" Statement");
            newWrapOption(consumer, SmcOptions.WRAP_TRANSITION_ARGS, "Wrap \"Transition arguments\"");
        }
    }

    private void newWrapOption(@NotNull CodeStyleSettingsCustomizable consumer, SmcOptions wrapPropertyOption, String title) {
        consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(wrapPropertyOption), title,
                null,
                CodeStyleSettingsCustomizable.WRAP_OPTIONS,
                CodeStyleSettingsCustomizable.WRAP_VALUES);
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "%{\n" +
                "asda;\n" +
                "as;asdjlaksd\n" +
                "asdasd\n" +
                "%}\n" +
                "\n" +
                "%package sdfd\n" +
                "\n" +
                "%class adtest\n" +
                "%import static sdajasdl.*\n" +
                "%import com.db.Class\n" +
                "%package turnstile\n" +
                "%fsmfile turnstile\n" +
                "%fsmclass turnstile\n" +
                "%access private\n" +
                "%header asd\n" +
                "%declare asdsd\n" +
                "%include asdas\n" +
                "%start MainMap::State\n" +
                "%map MainMap\n" +
                "%%\n" +
                "Locked\n" +
                "    Entry {\n" +
                "        asd(tst, \";sadlfsdk \\t\\f sdf@\\\"' \\' ;\");\n" +
                "        asd();\n" +
                "        asd(te);\n" +
                "    }\n" +
                "\n" +
                "    Exit {\n" +
                "        asd(te, \"asdasd\\r\");\n" +
                "    }\n" +
                "{\n" +
                "    coin (arggs : asd, arg : sfsd)\n" +
                "    [  asd() && test [ ] status + test - 6 asdlk [] z [ ] [ ] ]\n" +
                "    Unlocked {\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    coin2 (arggs : asd, arg : sfsd)\n" +
                "    [  asd() && test [] ]\n" +
                "    Unlocked {\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    coin1 (arggs : asd, arg : sfsd)\n" +
                "    Unlocked {\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    coin2 Unlocked {}\n" +
                "\n" +
                "    coin3\n" +
                "    [  asd() && test [ ] [ ] ]\n" +
                "    Unlocked {\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    coin4 Unlocked {\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    pass nil {\n" +
                "        alarm();\n" +
                "    }\n" +
                "\n" +
                "    test pop(pass, wqe) {\n" +
                "        test(); test2(); test3();\n" +
                "    }\n" +
                "\n" +
                "    test pop {\n" +
                "        test(asad); test2();\n" +
                "    }\n" +
                "\n" +
                "    test pop(lkj) {\n" +
                "        test(asad);\n" +
                "        test();\n" +
                "    }\n" +
                "\n" +
                "    test push1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    test Unlocked/push(sdas::werwr) {\n" +
                "        asdasd(asd);\n" +
                "    }\n" +
                "\n" +
                "    test Unlocked/push(werwr) {\n" +
                "        asdasd(asd);\n" +
                "    }\n" +
                "\n" +
                "    test\n" +
                "    Unlocked/push(klj) {\n" +
                "        asdasd(asd);\n" +
                "    }\n" +
                "\n" +
                "    test push(sdas::werwr) {\n" +
                "        asdasd(asd);\n" +
                "    }\n" +
                "\n" +
                "    test push(werwr) {\n" +
                "        asdasd(asd);\n" +
                "    }\n" +
                "\n" +
                "    test push(werwr) {\n" +
                "        asdasd();\n" +
                "    }\n" +
                "\n" +
                "    test push(nil) {\n" +
                "        asdasd();\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "Unlocked {\n" +
                "\n" +
                "    test push(sdas::werwr) {\n" +
                "    }\n" +
                "\n" +
                "    lock Locked {\n" +
                "        lock();\n" +
                "    }\n" +
                "\n" +
                "    coin nil {\n" +
                "        thankyou();\n" +
                "    }\n" +
                "}\n" +
                "%%";
    }
}
