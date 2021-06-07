package com.constants;

import java.awt.Font;

public class ConfigUI {
    public static final int FONT_BIG_SIZE = 25;
    public static final int FONT_NORMAL_SIZE = 20;
    public static final int FONT_SMALL_SIZE = 15;
    public static final int FONT_TINY_SIZE = 12;

    static public class DefaultFont {
        public static final Font h1 = new Font("Tamoha", Font.BOLD, FONT_BIG_SIZE);
        public static final Font h2 = new Font("Tamoha", Font.BOLD, FONT_NORMAL_SIZE);
        public static final Font body = new Font("Tamoha", Font.PLAIN, FONT_SMALL_SIZE);
        public static final Font tiny = new Font("Tamoha", Font.PLAIN, FONT_TINY_SIZE);
    }
}
