import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class MarkHelper {

    private static int endI, startI, endJ, startJ;
    private static boolean startSettet;

    private static List<JButton> selectList = new LinkedList<JButton>();

    private static Kalender parent;

    public static boolean isSelected(JButton button) {
        if (button.getBackground().equals(Colors.darkerLightGray)) return true;
        if (button.getBackground().equals(Colors.darkerBlue)) return true;
        if (button.getBackground().equals(Colors.darkerOrange)) return true;
        if (button.getBackground().equals(Colors.darkerLightGreen)) return true;
        if (button.getBackground().equals(Colors.darkerPurple)) return true;
        if (button.getBackground().equals(Colors.darkerRed)) return true;
        if (button.getBackground().equals(Colors.darkerCyan)) return true;
        if (button.getBackground().equals(Colors.darkerYellow)) return true;
        if (button.getBackground().equals(Colors.darkerPink)) return true;
        if (button.getBackground().equals(Colors.darkerSkintone)) return true;
        if (button.getBackground().equals(Colors.darkerSandy)) return true;
        return false;
    }

    public static JRadioButton selectedRadioButton(JRadioButton[] array){
        for(int i = 0; i< array.length; i++){
            if(array[i].isSelected()) return array[i];
        }
        return null;
    }

    public static JButton makeNotSelected(JButton button){
                if (button.getBackground().equals(Colors.darkerLightGray)) button.setBackground(Colors.lightGray);
                if (button.getBackground().equals(Colors.darkerBlue)) button.setBackground(Colors.blue);
                if (button.getBackground().equals(Colors.darkerOrange)) button.setBackground(Colors.orange);
                if (button.getBackground().equals(Colors.darkerLightGreen)) button.setBackground(Colors.lightGreen);
                if (button.getBackground().equals(Colors.darkerPurple)) button.setBackground(Colors.purple);
                if (button.getBackground().equals(Colors.darkerRed)) button.setBackground(Colors.red);
                if (button.getBackground().equals(Colors.darkerCyan)) button.setBackground(Colors.cyan);
                if (button.getBackground().equals(Colors.darkerYellow)) button.setBackground(Colors.yellow);
                if (button.getBackground().equals(Colors.darkerPink)) button.setBackground(Colors.pink);
                if (button.getBackground().equals(Colors.darkerSkintone)) button.setBackground(Colors.skintone);
                if (button.getBackground().equals(Colors.darkerSandy)) button.setBackground(Colors.sandy);
        return button;
    }

    public static JButton makeSelected(JButton button){

        if(button.getBackground().equals(Colors.lightGray)) button.setBackground(Colors.darkerLightGray);
        if(button.getBackground().equals(Colors.blue)) button.setBackground(Colors.darkerBlue);
        if(button.getBackground().equals(Colors.orange)) button.setBackground(Colors.darkerOrange);
        if(button.getBackground().equals(Colors.lightGreen)) button.setBackground(Colors.darkerLightGreen);
        if(button.getBackground().equals(Colors.purple)) button.setBackground(Colors.darkerPurple);
        if(button.getBackground().equals(Colors.red)) button.setBackground(Colors.darkerRed);
        if(button.getBackground().equals(Colors.cyan)) button.setBackground(Colors.darkerCyan);
        if(button.getBackground().equals(Colors.yellow)) button.setBackground(Colors.darkerYellow);
        if(button.getBackground().equals(Colors.pink)) button.setBackground(Colors.darkerPink);
        if(button.getBackground().equals(Colors.skintone)) button.setBackground(Colors.darkerSkintone);
        if(button.getBackground().equals(Colors.sandy)) button.setBackground(Colors.darkerSandy);
        return button;
    }

    public static JButton[][] makeShiftSelected(JButton button, JButton[][] array, boolean hourmode){
        int disposition = 0;
        if(hourmode){
            disposition = 3;
        }
        if(!startSettet) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    if (button.equals(array[i][j])) {
                        startI = i;
                        startJ = j;
                        array[i][j] = makeSelected(button);
                    }
                }
            }
            startSettet =  true;
            return array;
        }else{
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    for(int k = 0; k < selectList.size(); k++){
                        if(array[i][j].equals(selectList.get(k))){
                            array[i][j] = makeNotSelected(array[i][j]);
                        }
                    }
                    if (button.equals(array[i][j])) {
                        endI = i;
                        endJ = j;
                        if(parent.isHoursMode()) {
                            if (startI < endI) {
                                endI = endI + disposition;
                            } else {
                                int temp = startI;
                                startI = endI;
                                endI = temp;
                                endI = endI + disposition;
                            }
                        }

                    }
                }
            }

            for (int i = startI; i<= endI; i++ ){
                for (int j = startJ; j<= endJ; j++){
                    array[i][j] = makeSelected(array[i][j]);
                    selectList.add(array[i][j]);
                }
            }
            return array;
        }
    }

    public static JButton[][] resetShiftMarkedButtons(JButton[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                for (int k = 0; k < selectList.size(); k++) {
                    if (array[i][j].equals(selectList.get(k))) {
                        array[i][j] = makeNotSelected(array[i][j]);
                    }
                }
            }
        }
        selectList = new LinkedList<JButton>();
        return array;
    }
    public void setStart(int i, int j){
        startI = i;
        startJ = j;
    }

    //TODO: nicht einen punkt fixieren und dann immer 2. verändern, sonder immer den näheren verändern
    public JButton[][] getMarkedButons(int i, int j, JButton[][] array){
        JButton[][] temp = new JButton[i-startI][j-startJ];
        int iCounter = 0;
        int jCounter = 0;
        for (int k = startI; k<= i; k++ ){
            for (int h = startJ; h<= j; h++){
                temp[iCounter][jCounter] = array[k][h];
                jCounter++;
            }
            iCounter++;
        }
        return temp;
    }

    public static boolean isStartSettet() {
        return startSettet;
    }

    public static void setStartSettet(boolean startSettet) {
        MarkHelper.startSettet = startSettet;
    }

    public static Kalender getParent() {
        return parent;
    }

    public static void setParent(Kalender parent) {
        MarkHelper.parent = parent;
    }
}
