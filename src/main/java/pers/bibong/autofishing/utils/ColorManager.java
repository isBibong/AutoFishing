package pers.bibong.autofishing.utils;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorManager {

    public static @NotNull String color (String mainString) {
        mainString = forMessageToRGB(mainString);

        if (mainString.contains("&")) {
            return ChatColor.translateAlternateColorCodes('&', mainString);
        }

        return mainString;
    }

    public static @NotNull String replace (@NotNull String mainString, String checkString, String replaceString) {
        if (mainString.contains(checkString)) {
            return mainString.replace(checkString, replaceString);
        }
        return mainString;
    }

    /**
     * 將字串RGB解析成RGB物件
     *
     * @param msg 十六進位RGB字串 (<#FF0000> Hello world !)
     *
     * @return RGB字串
     */
    public static String forMessageToRGB (final @NotNull String msg) {
        String message = msg;

        final Pattern hexPattern = Pattern.compile("<#([A-Fa-f0-9]){6}>");
        Matcher       matcher    = hexPattern.matcher(message);

        while (matcher.find()) {
            final ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            final String       before   = message.substring(0, matcher.start());
            final String after  = message.substring(matcher.end());
            message = before + hexColor + after;
            matcher = hexPattern.matcher(message);
        }

        return message;
    }

}
