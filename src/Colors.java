import java.awt.*;

public class Colors {
    public static Color lightGreen = Color.green;
    public static Color lightGray = new Color(192,192,192);
    public static Color blue = new Color(50,130,246);
    public static Color orange = new Color(247,159,20);
    public static Color purple = new Color(205,123,245);
    public static Color red = new Color(255,88,88);
    public static Color cyan = new Color(28,245,210);
    public static Color yellow = new Color(238,245,30);
    public static Color pink = new Color(242,70,206);
    public static Color skintone = new Color(255,195,200);
    public static Color sandy = new Color(209,188,138);
    Colors(){

    }

    public static Color stringToColor(String color){
        color = color.toLowerCase();
        switch (color){
            case "lightGreen": return lightGreen;
            case "lightGray": return lightGray;
            case "blue": return blue;
            case "orange": return orange;
            case "purple": return purple;
            case "red": return red;
            case "cyan": return cyan;
            case "yellow": return yellow;
            case "pink": return pink;
            case "skintone": return skintone;
            case "sandy": return sandy;
            default: return lightGray;

        }
    }

}
