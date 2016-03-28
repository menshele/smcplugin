package com.smcplugin.formatter;

import com.intellij.lang.Language;
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
    private static final String SPACES_AFTER_KEYWORD_LABEL = "Space After Keyword";
    public static final String SPACE_WITHIN_PARENTHESES_LABEL = "Space within parentheses";
    public static final String SPACE_BEFORE_PARENTHESES_LABEL = "Space before parentheses";
    public static final String SPACE_AROUND_MAP_STATE_SEPARATOR_LABEL = "Space around map-state (\"::\") separator";
    public static final String SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR_LABEL = "Space around push-proxy-state (\"/\") separator";
    public static final String INSERT_SPACES_BEFORE_TRANSITIONS_LABEL = "Space before \"Transitions block\"";
    private static final String SPACE_BEFORE_TRANSITION_ARGS_LABEL = "Space before \"Transition arguments\"";
    private static final String SPACE_BEFORE_ACTIONS_BLOCK_LABEL = "Space before \"Actions\" block";
    private static final String SPACE_AFTER_ENTRY_EXIT_KEYWORDS_LABEL = "Space after \"Entry/Exit\" keywords";

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
                    String.valueOf(SPACE_BEFORE_SEMICOLON), String.valueOf(SPACE_AFTER_SEMICOLON), String.valueOf(SPACE_WITHIN_BRACES));


            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AFTER_KEYWORD), SPACES_AFTER_KEYWORD_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_WITHIN_PARENTHESES), SPACE_WITHIN_PARENTHESES_LABEL, CodeStyleSettingsCustomizable.SPACES_WITHIN);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_PARENTHESES_OPEN), SPACE_BEFORE_PARENTHESES_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_TRANSITION_ARGS), SPACE_BEFORE_TRANSITION_ARGS_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_ACTIONS_BLOCK), SPACE_BEFORE_ACTIONS_BLOCK_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_BEFORE_TRANSITIONS_BLOCK), INSERT_SPACES_BEFORE_TRANSITIONS_LABEL, CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AROUND_MAP_STATE_SEPARATOR), SPACE_AROUND_MAP_STATE_SEPARATOR_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR), SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(SmcCodeStyleSettings.class, String.valueOf(SmcOptions.SPACE_AFTER_ENTRY_EXIT_KEYWORDS), SPACE_AFTER_ENTRY_EXIT_KEYWORDS_LABEL, CodeStyleSettingsCustomizable.SPACES_OTHER);

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
                "%class com.company.smc.ContextClass\n" +
                "%package com.company.smc\n" +
                "%import sdajasdl.*\n" +
                "%import com.company.test.ImportedClass\n" +
                "%fsmfile turnstile\n" +
                "%fsmclass StateMachine\n" +
                "%access private\n" +
                "%header asd\n" +
                "%start Unlocked::Unlocked\n" +
                "%declare asdsd\n" +
                "%include asdas\n" +
                "\n" +
                "%map Unlocked\n" +
                "%%\n" +
                "Locked\n" +
                "\n" +
                "    Entry {\n" +
                "        pickUp(\";sadlfsdk \\t\\f sdf@\\\"' \\' ;\");\n" +
                "    }\n" +
                "\n" +
                "    Exit {\n" +
                "        hangOn(asd);\n" +
                "    }\n" +
                "{\n" +
                "    coins (arggs : asd, arg : sfsd)\n" +
                "    [ asdasd[ ]askldjaskl[][] ]\n" +
                "    Unlocked {}\n" +
                "\n" +
                "    coin1 (arggs : asd, arg : sfsd)\n" +
                "    [ dasdsad []asdasd ]\n" +
                "    Unlocked {\n" +
                "        alarm();\n" +
                "        unlock(sdf);\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\", sdf);\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    coin2X\n" +
                "    [    asdasd    ]\n" +
                "    Unlocked {}\n" +
                "\n" +
                "    coin4\n" +
                "    [    test etead klsd; jas if new Array [new []] asd [] ]\n" +
                "    Unlocked {\n" +
                "        unlock(sdf, \"sdf\\\" asldas \\t\\'\");\n" +
                "    }\n" +
                "\n" +
                "    pass\n" +
                "    nil\n" +
                "    {\n" +
                "        alarm();\n" +
                "    }\n" +
                "\n" +
                "    test1 pop (pass, wqe){\n" +
                "        testAction(\"test \");\n" +
                "        testAction(asdas, asdas);\n" +
                "    }\n" +
                "\n" +
                "    test2 pop {}\n" +
                "\n" +
                "    test3 pop (lkj){\n" +
                "        testAction();\n" +
                "    }\n" +
                "\n" +
                "    test4\n" +
                "    Unlocked {}\n" +
                "\n" +
                "    test5 Unlocked/push(Unlocked::Locked){\n" +
                "        pickUp(test);\n" +
                "    }\n" +
                "\n" +
                "    test6 Unlocked/push(Unlocked){\n" +
                "        hangOn(asd);\n" +
                "    }\n" +
                "\n" +
                "    test7 Unlocked/push(Locked::Unlocked){\n" +
                "        hangOn(asd);\n" +
                "    }\n" +
                "\n" +
                "    test8 push(Locked)\n" +
                "    {\n" +
                "        hangOn(asd);\n" +
                "    }\n" +
                "    test10 push(Locked::Unlocked){\n" +
                "        hangOn(te);\n" +
                "    }\n" +
                "\n" +
                "    test push(nil){\n" +
                "        pickUp();\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "Unlocked\n" +
                "{\n" +
                "    test push(Locked::Unlocked){}\n" +
                "\n" +
                "    lock\n" +
                "    Locked {\n" +
                "        lock();\n" +
                "    }\n" +
                "\n" +
                "    coin\n" +
                "    nil{\n" +
                "        thankyou();\n" +
                "    }\n" +
                "}\n" +
                "%%\n" +
                "%map Locked %%\n" +
                "\n" +
                "Unlocked\n" +
                "{\n" +
                "    test push(Unlocked::Locked){}\n" +
                "\n" +
                "    lock\n" +
                "    Unlocked {\n" +
                "        lock();\n" +
                "    }\n" +
                "\n" +
                "    coin\n" +
                "    nil{\n" +
                "        thankyou();\n" +
                "    }\n" +
                "}";
    }
}
