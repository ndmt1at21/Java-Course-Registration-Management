package com.constants;

import java.awt.Color;
import java.awt.Font;

public class ConfigUI {
    public static final int FONT_BIG_SIZE = 40;
    public static final int FONT_NORMAL_SIZE = 20;
    public static final int FONT_SMALL_SIZE = 14;
    public static final int FONT_TINY_SIZE = 12;
    public static final int BUTTON_ARC = 10;
    public static final int TEXTFIELD_ARC = 20;

    static public class DefaultFont {
        public static final Font H1 = new Font("Roboto", Font.BOLD, FONT_BIG_SIZE);
        public static final Font H2 = new Font("Roboto", Font.BOLD, FONT_NORMAL_SIZE);
        public static final Font H3 = new Font("Roboto", Font.BOLD, FONT_SMALL_SIZE);
        public static final Font BODY = new Font("Roboto", Font.PLAIN, FONT_SMALL_SIZE);
        public static final Font TINY = new Font("Roboto", Font.PLAIN, FONT_TINY_SIZE);
    }

    static public class Padding {
        public static final int LARGE = 25;
        public static final int NORMAL = 10;
        public static final int SMALL = 8;
        public static final int TINY = 5;
    }

    static public class SysColor {
        public static final Color PRIMARY = new Color(24, 90, 219);
        public static final Color PRIMARY_DARK = new Color(30, 97, 176);
        public static final Color GRAY = new Color(196, 196, 196);
        public static final Color GRAY_LIGHT = new Color(238, 238, 238);
        public static final Color YELLOW = new Color(255, 201, 71);
        public static final Color YELLOW_LIGHT = new Color(254, 221, 190);
        public static final Color WHITE = new Color(255, 255, 255);
        public static final Color DARK = new Color(10, 25, 49);
        public static final Color GREEN = new Color(34, 140, 123);
        public static final Color RED = new Color(243, 14, 92);
    }

    static public class Table {
        public static final int ROW_HEIGHT = 50;
    }

    static public final int SCROLL_SPEED = 5;
}
