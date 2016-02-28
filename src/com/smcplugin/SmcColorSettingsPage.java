package com.smcplugin;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Action Name", SmcSyntaxHighlighter.SMC_ACTION_NAME),
            new AttributesDescriptor("Line Comment", SmcSyntaxHighlighter.SMC_LINE_COMMENT),
            new AttributesDescriptor("Block Comment Start", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT_OPEN),
            new AttributesDescriptor("Block Comment Content", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT_CONTENT),
            new AttributesDescriptor("Block Comment End", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT_CLOSE),
            new AttributesDescriptor("Braces", SmcSyntaxHighlighter.SMC_BRACES),
            new AttributesDescriptor("Parentheses", SmcSyntaxHighlighter.SMC_PARENTHESES),
            new AttributesDescriptor("Brackets", SmcSyntaxHighlighter.SMC_BRACKETS),
            new AttributesDescriptor("Verbatim Brackets", SmcSyntaxHighlighter.SMC_VERBATIM_BRACKETS),
            new AttributesDescriptor("Comma", SmcSyntaxHighlighter.SMC_COMMA),
            new AttributesDescriptor("Semicolon", SmcSyntaxHighlighter.SMC_SEMICOLON),
            new AttributesDescriptor("Keyword", SmcSyntaxHighlighter.SMS_KEYWORD),
            new AttributesDescriptor("Entry Block Token", SmcSyntaxHighlighter.SMC_ENTRY_KEYWORD),
            new AttributesDescriptor("Exit Block Token", SmcSyntaxHighlighter.SMC_EXIT_KEYWORD),
            new AttributesDescriptor("Verbatim Code", SmcSyntaxHighlighter.SMC_VERBATIM_CODE),
            new AttributesDescriptor("Guard Code", SmcSyntaxHighlighter.SMC_GUARD_RAW_CODE),
            new AttributesDescriptor("Map Name", SmcSyntaxHighlighter.SMC_MAP_NAME),
            new AttributesDescriptor("Start Map Name", SmcSyntaxHighlighter.SMC_START_MAP_NAME),
            new AttributesDescriptor("State Name", SmcSyntaxHighlighter.SMC_STATE_NAME),
            new AttributesDescriptor("Start State Name", SmcSyntaxHighlighter.SMC_START_STATE_NAME),
            new AttributesDescriptor("Transition Name", SmcSyntaxHighlighter.SMC_TRANSITION_NAME),
            new AttributesDescriptor("Transition Parameter Name", SmcSyntaxHighlighter.SMC_PARAMETER_NAME),
            new AttributesDescriptor("Transition Parameter Type", SmcSyntaxHighlighter.SMC_PARAMETER_TYPE),
            new AttributesDescriptor("Pop Keyword", SmcSyntaxHighlighter.SMC_POP_KEYWORD),
            new AttributesDescriptor("Pop Callback Transition Name", SmcSyntaxHighlighter.SMC_CALLBACK_TRANSITION_NAME),
            new AttributesDescriptor("Push Keyword", SmcSyntaxHighlighter.SMC_PUSH_KEYWORD),
            new AttributesDescriptor("Push Proxy State Name", SmcSyntaxHighlighter.SMC_PUSH_PROXY_STATE_NAME),
            new AttributesDescriptor("Push Map Name", SmcSyntaxHighlighter.SMC_PUSH_MAP_NAME),
            new AttributesDescriptor("Push State Name", SmcSyntaxHighlighter.SMC_PUSH_STATE_NAME),
            new AttributesDescriptor("Map State Separator", SmcSyntaxHighlighter.SMC_MAP_NAME_STATE_NAME_SEPARATOR),
            new AttributesDescriptor("Map Section Bound", SmcSyntaxHighlighter.SMC_MAP_SECTION_BOUND),
            new AttributesDescriptor("Other", SmcSyntaxHighlighter.SMC_DEFAULT)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return SmcIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new SmcSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "%{\n" +
                "asda;\n" +
                "as;asdjlaksd\n" +
                "asdasd\n" +
                "%}\n" +
                "\n" +
                "%package sdfd\n" +
                "//asdas\n" +
                "\n" +
                "%class adtest\n" +
                "%import static sdajasdl.*\n" +
                "%import com.db.Class\n" +
                "%package turnstile\n" +
                "%fsmfile turnstile\n" +
                "%fsmclass turnstile\n" +
                "%access private \n" +
                "%header asd\n" +
                "//\n" +
                "/*asldkjasld*/\n" +
                "%declare asdsd\n" +
                "%include asdas\n" +
                "%start  MainMap/* asda*/::/**/State\n" +
                "%map MainMap\n" +
                "%%\n" +
                "//\n" +
                " Locked\n" +
                "Entry {\n" +
                "asd(\n" +
                "//\n" +
                "tst, \";sadlfsdk \\t\\f sdf@\\\"' \\' ;\");\n" +
                "asd(te);\n" +
                "}\n" +
                "Exit {\n" +
                "   asd(te, \"asdasd\\r\");\n" +
                "}/*asdasd*/\n" +
                "{\n" +
                " coin (  arggs  : asd , arg: sfsd )\n" +
                " [  asd() && test [] ]\n" +
                " Unlocked {\n" +
                "    unlock(  sdf , \"sdf\\\" asldas \\t\\'\" );\n" +
                " }\n" +
                "\n" +
                " coin2 (  arggs  : asd , arg: sfsd )\n" +
                "  [  asd() && test []] ]\n" +
                "  Unlocked {\n" +
                "     unlock(  sdf , \"sdf\\\" asldas \\t\\'\" );\n" +
                "  }\n" +
                "\n" +
                "  coin1 (  arggs  : asd , arg: sfsd )\n" +
                "  Unlocked {\n" +
                "       unlock(  sdf , \"sdf\\\" asldas \\t\\'\" );\n" +
                "  }\n" +
                "\n" +
                "  coin3\n" +
                "   [  asd() && test []] ]\n" +
                "   Unlocked {\n" +
                "      unlock(  sdf , \"sdf\\\" asldas \\t\\'\" );\n" +
                "   }\n" +
                "\n" +
                "  coin3\n" +
                "  Unlocked {\n" +
                "       unlock(  sdf , \"sdf\\\" asldas \\t\\'\" );\n" +
                "    }\n" +
                "\n" +
                " pass nil { alarm(); }\n" +
                "\n" +
                " test pop ( pass, wqe) {\n" +
                "    test();\n" +
                "    test();\n" +
                " }\n" +
                "\n" +
                "\n" +
                "  test pop {\n" +
                "     test(asad);\n" +
                "     test();\n" +
                "  }\n" +
                "\n" +
                "    test pop(lkj) {\n" +
                "       test(asad);\n" +
                "       test();\n" +
                "    }\n" +
                "\n" +
                "  test push1\n" +
                "  {\n" +
                "\n" +
                "  }\n" +
                "\n" +
                "  test Unlocked/push(sdas::werwr){\n" +
                "    asdasd ( asd ) ;\n" +
                "  }\n" +
                "  test Unlocked/ push ( klj ) {asdasd(asd);}\n" +
                "  test push (sdas::werwr){asdasd(asd);}\n" +
                "  test  /**/push (werwr){asdasd(asd);}\n" +
                "  test push (werwr){asdasd();}\n" +
                "  test  /**/push  /**/( /**/nil /**/) /**/{ /**/asdasd();}\n" +
                "}\n" +
                "Unlocked\n" +
                "{\n" +
                " lock Locked { lock(); }\n" +
                " coin nil { thankyou();}\n" +
                "}\n" +
                "%%";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Smc";
    }
}