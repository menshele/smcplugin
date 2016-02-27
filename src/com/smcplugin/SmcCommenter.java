package com.smcplugin;

import com.intellij.lang.Commenter;
import com.intellij.lang.CustomUncommenter;
import com.intellij.openapi.util.Couple;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcCommenter implements Commenter, CustomUncommenter {
    private static final Pattern COMMENTS_PATTERN = Pattern.compile("(^\\s*/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
    public static final Pattern BLOCK_COMMENT_START = Pattern.compile("(^\\s*/\\*\\s^|/\\*)", Pattern.MULTILINE);
    public static final Pattern BLOCK_COMMENT_END = Pattern.compile("($\\s*\\*/\\s*$|\\*/)", Pattern.MULTILINE);

    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return "//";
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return "*/";
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return "/*";
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return "*/";
    }

    @Nullable
    @Override
    public TextRange findMaximumCommentedRange(@NotNull CharSequence charSequence) {
        Matcher matcher = COMMENTS_PATTERN.matcher(charSequence);

        // Check all occurrences
        int startOfRange = -1;
        int endOfRange = -1;
        while (matcher.find()) {
            startOfRange = startOfRange < 0 ? matcher.start() : startOfRange;
            endOfRange = matcher.end();
        }
        return startOfRange < 0 ? null : new TextRange(startOfRange, endOfRange);
    }

    @NotNull
    @Override
    public Collection<? extends Couple<TextRange>> getCommentRangesToDelete(@NotNull CharSequence charSequence) {
        Matcher start = BLOCK_COMMENT_START.matcher(charSequence);
        Matcher end = BLOCK_COMMENT_END.matcher(charSequence);

        TextRange startRange = start.find() ? new TextRange(start.start(), start.end()) : null;
        TextRange endRange = end.find() ? new TextRange(end.start(), end.end()) : null;
        return Collections.singleton(new Couple<>(startRange, endRange));
    }

}