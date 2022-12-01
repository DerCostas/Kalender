import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Colors {
    public static Color lightGreen = Color.green;
    public static Color lightGray = new Color(192,192,192);
    public static Color blue = new Color(50,130,246);
    public static Color orange = new Color(247,159,20);
    public static Color purple = new Color(205,123,245);
    public static Color red = new Color(234,121,121);
    public static Color cyan = new Color(28,245,210);
    public static Color yellow = new Color(238,245,30);
    public static Color pink = new Color(234,111,194);
    public static Color skintone = new Color(255,195,200);
    public static Color sandy = new Color(209,188,138);
    public static Color darkerLightGreen = Color.green.darker();
    public static Color darkerLightGray = lightGray.darker();
    public static Color darkerBlue = blue.darker();
    public static Color darkerOrange = orange.darker();
    public static Color darkerPurple = purple.darker();
    public static Color darkerRed = red.darker();
    public static Color darkerCyan = cyan.darker();
    public static Color darkerYellow = yellow.darker();
    public static Color darkerPink = pink.darker();
    public static Color darkerSkintone = skintone.darker();
    public static Color darkerSandy = sandy.darker();

    Colors(){

    }

    public static Color stringToColor(String color){
        color = color.toLowerCase();
        switch (color){
            case "light green": return lightGreen;
            case "light gray": return lightGray;
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

    public static JRadioButton colourRadioButtons(JRadioButton button){
            switch (button.getText()) {
                case "Frei":
                    button.setBackground(lightGreen);
                    break;
                case "Uni":
                    button.setBackground(blue);
                    break;
                case "Lernen":
                    button.setBackground(orange);
                    break;
                case "Sport":
                    button.setBackground(skintone);
                    break;
                case "Gitarre":
                    button.setBackground(sandy);
                    break;
                case "Essen":
                    button.setBackground(cyan);
                    break;
                case "Arbeit":
                    button.setBackground(red);
                    break;
                case "Unsure":
                    button.setBackground(yellow);
                    break;
            }
        return button;
    }

}
