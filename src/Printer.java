import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Printer {


    Printer(){

    }
    public static boolean write(String fileName, JButton[][] array){
        String text ="";
        for(int i = 0; i<array.length;i++) {
            for (int j = 0; j< array[0].length;j++) {
                text += array[i][j].getText() + "$";
                text += array[i][j].getBackground().getRed() + "@";
                text += array[i][j].getBackground().getGreen() + "@";
                text += array[i][j].getBackground().getBlue() + "@";
                text += "€";
            }
        }
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static JButton[][] read(String filename, JButton[][] array){
        String text;
        try {
            text = Files.readString(Path.of(filename));
        }catch (Exception e ){

            return null;
        }
        String[] buttons = text.split("€");
        for(int i = 0; i< buttons.length; i++){
            array[i/7][i%7].setText(buttons[i].split("\\$")[0]);
            array[i/7][i%7].setBackground(new Color(Integer.parseInt(buttons[i].split("\\$")[1].split("@")[0]),Integer.parseInt(buttons[i].split("\\$")[1].split("@")[1]), Integer.parseInt(buttons[i].split("\\$")[1].split("@")[2] )));

        }
        return array;
    }

    public static void writeCSV(String fileName, JButton[][] array){
        String text ="";
        for(int i = 0; i< array.length;i++) {
            for (int j = 0; j< array[0].length;j++) {
                text += array[i][j].getText() + ";";
            }
            text += "\n";
        }
        try {
            FileWriter writer = new FileWriter(fileName, false);
            writer.write(text);
            writer.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

}
