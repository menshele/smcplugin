package com.smcplugin.formatter;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.smcplugin.SmcLanguage;
import org.jetbrains.annotations.NotNull;

import static com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable.SPACES_BEFORE_KEYWORD;
import static com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable.SpacingOption.*;
import static com.smcplugin.formatter.SmcCodeStyleSettings.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    public static final String SEPARATORS = "Spaces around separators";
    public static final String BRACKETS = "Bracket spacing options";
    public static final String INSERT_SPACES_BEFORE = "Insert space before brace";
    private static final String SPACES_AFTER_KEYWORD = "Space After Keyword";
    public static final String SPACE_WITHIN_PARENTHESES = "Space within parentheses";
    public static final String SPACE_BEFORE_PARENTHESES = "Space before parentheses";
    public static final String SPACE_AROUND_MAP_STATE_SEPARATOR = "Space around map-state (\"::\") separator";
    public static final String PUSH_PROXY_STATE_KEYWORD_SEPARATOR = "Space around push-proxy-state (\"/\") separator";

    @NotNull
    @Override
    public Language getLanguage() {
        return SmcLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.moveStandardOption(String.valueOf(SPACE_BEFORE_COLON), SEPARATORS);
            consumer.moveStandardOption(String.valueOf(SPACE_AFTER_COLON), SEPARATORS);
            consumer.moveStandardOption(String.valueOf(SPACE_AFTER_COMMA), SEPARATORS);
            consumer.moveStandardOption(String.valueOf(SPACE_BEFORE_SEMICOLON), SEPARATORS);
            consumer.moveStandardOption(String.valueOf(SPACE_AFTER_SEMICOLON), SEPARATORS);

            consumer.showStandardOptions(String.valueOf(SPACE_BEFORE_COLON), String.valueOf(SPACE_AFTER_COLON), String.valueOf(SPACE_AFTER_COMMA),
                    String.valueOf(SPACE_BEFORE_SEMICOLON), String.valueOf(SPACE_AFTER_SEMICOLON),String.valueOf(SPACE_WITHIN_BRACKETS),String.valueOf(SPACE_WITHIN_BRACES));

            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_LEFT_BRACE), INSERT_SPACES_BEFORE, CodeStyleSettingsCustomizable.SPACES_BEFORE_LEFT_BRACE);

            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AFTER_KEYWORD), SPACES_AFTER_KEYWORD, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_WITHIN_PARENTHESES), SPACE_WITHIN_PARENTHESES, CodeStyleSettingsCustomizable.SPACES_WITHIN);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_PARENTHESES_OPEN), SPACE_BEFORE_PARENTHESES, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AROUND_MAP_STATE_SEPARATOR), SPACE_AROUND_MAP_STATE_SEPARATOR, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.PUSH_PROXY_STATE_KEYWORD_SEPARATOR), PUSH_PROXY_STATE_KEYWORD_SEPARATOR, CodeStyleSettingsCustomizable.SPACES_OTHER);

        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        }
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
                "        test();\n" +
                "        test();\n" +
                "    }\n" +
                "\n" +
                "    test pop {\n" +
                "        test(asad);\n" +
                "        test();\n" +
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
