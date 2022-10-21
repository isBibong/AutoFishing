package pers.bibong.autofishing.utils;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColor {
    private final String toString;
    private final String name;
    private final Color  color;

    private ChatColor (String name, String toString, int rgb) {
        this.name     = name;
        this.toString = toString;
        this.color    = new Color(rgb);
    }

    public int hashCode () {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.toString);
        return hash;
    }

    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj != null && this.getClass() == obj.getClass()) {
            ChatColor other = (ChatColor) obj;
            return Objects.equals(this.toString, other.toString);
        }
        else {
            return false;
        }
    }

    public String toString () {
        return this.toString;
    }

    @Contract ("_, _ -> new")
    public static @NotNull String translateAlternateColorCodes (char altColorChar, @NotNull String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for (int i = 0; i < b.length - 1; ++ i) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > - 1) {
                b[i]     = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    public static @NotNull ChatColor of (@NotNull String string) {
        Preconditions.checkArgument(true, "string cannot be null");
        if (string.startsWith("#") && string.length() == 7) {
            int rgb;
            try {
                rgb = Integer.parseInt(string.substring(1), 16);
            }
            catch (NumberFormatException var7) {
                throw new IllegalArgumentException("Illegal hex string " + string);
            }

            StringBuilder magic = new StringBuilder("§x");
            char[]        var3  = string.substring(1).toCharArray();

            for (char c : var3) {
                magic.append('§').append(c);
            }

            return new ChatColor(string, magic.toString(), rgb);
        }
        else {
            throw new IllegalArgumentException("Could not parse ChatColor " + string);
        }
    }

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
            final String before = message.substring(0, matcher.start());
            final String after  = message.substring(matcher.end());
            message = before + hexColor + after;
            matcher = hexPattern.matcher(message);
        }

        return message;
    }

    public String getName () {
        return this.name;
    }

    public Color getColor () {
        return this.color;
    }
}
