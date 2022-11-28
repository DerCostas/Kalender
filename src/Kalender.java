import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalender extends JFrame {


    private JButton[][] kalender = new JButton[61][7]; //15*4+1
    private JButton select, change, hours, quadHours, clear;
    private final JLabel[] labels = new JLabel[68];// 7+ (4*15+1)
    private final JLabel[] blanks = new JLabel[20];
    private JPanel center;
    private final Kalender thisObjekt = this;

    private boolean hoursMode = true;
    private boolean selectMode = false;

    private final Border blackline = BorderFactory.createLineBorder(Color.gray);
    private final String saveFile ="Resources\\KalenderWerte";
    private final String csvFile ="Resources\\Kalender.csv";
    public Kalender(){
        for (int i = 0; i< blanks.length; i++){
            blanks[i] = new JLabel();
        }


        buildPanel();
        buildKalender();
        buildButtons();
        manageLayouts();
    }

    public void buildPanel(){
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setTitle("Kalender");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(false);
        this.setFocusable(true);
        this.requestFocus();
        setLayout(new BorderLayout());
    }

    public void manageLayouts(){
        JPanel kalenderPanel = new JPanel();
        kalenderPanel.setLayout(new BorderLayout());
            this.setLayout(new BorderLayout());
            center = new JPanel();

            changeMode();
        JPanel top = new JPanel();
            top.setLayout(new GridLayout(1,5));
            top.add(select);
            top.add(change);
            top.add(clear);
            top.add(hours);
            top.add(quadHours);
            kalenderPanel.add(center, BorderLayout.CENTER);
        kalenderPanel.add(top, BorderLayout.PAGE_START);
        add(kalenderPanel,BorderLayout.CENTER);

    }

    public void buildKalender(){
        for(int i = 0; i<kalender.length;i++) {
            for (int j = 0; j< kalender[0].length;j++){
                kalender[i][j] = new JButton();
                kalender[i][j].setVisible(true);
                kalender[i][j].setBackground(Color.green);
                kalender[i][j].setText("Frei");
                kalender[i][j].setFont(new Font("Verdana", Font.BOLD, 14));
                kalender[i][j].addActionListener(new ButtonListener());
            }
        }



        for(int i = 0; i< labels.length; i++){
            labels[i] = new JLabel();
           labels[i].setFont(new Font("Verdana", Font.BOLD, 14));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            labels[i].setVerticalAlignment(SwingConstants.CENTER);
            labels[i].setBorder(blackline);
        }
        labels[0].setText("Montag");
        labels[1].setText("Dienstag");
        labels[2].setText("Mittwoch");
        labels[3].setText("Donnerstag");
        labels[4].setText("Freitag");
        labels[5].setText("Samstag");
        labels[6].setText("Sonntag");
        int timeCounter = 7*60;
        for(int i = 7; i< labels.length; i++) {
            if (timeCounter%60==0) {
                labels[i].setText(timeCounter / 60 + ":00");
            }
            else{
                labels[i].setText(timeCounter/60+":"+timeCounter%60);
            }
            timeCounter += 15;
        }
    }

    public void buildButtons(){
        select = new JButton("Select");
        select.addActionListener(new SelectListener());
        select.setBackground(Colors.lightGray);
        change = new JButton("Change Selected");
        change.addActionListener(new ChangeListener());
        change.setBackground(Colors.lightGray);
        clear = new JButton("Clear");
        clear.addActionListener(new ClearListener());
        clear.setBackground(Colors.red);
        hours = new JButton("Hours Only");
        hours.addActionListener(new HoursListener());
        quadHours = new JButton("Quatter Hours");
        quadHours.addActionListener(new QuatterHoursListener());

        if(hoursMode) {
            hours.setBackground(Colors.lightGreen);
            quadHours.setBackground(Colors.lightGray);
        }else{
            quadHours.setBackground(Colors.lightGreen);
            hours.setBackground(Colors.lightGray);
        }
    }
    public void changeMode(){
        center.removeAll();
        if(hoursMode) {

            kalender=read();
            center.setLayout(new GridLayout(17, 8));
            for (int i = 0; i < kalender.length+5; i++) {
                for (int j = 0; j < kalender[0].length+1; j++) {
                    if (i == 0 && j == 0) {
                        blanks[0].setBorder(blackline);
                        center.add(blanks[0]);

                    }else if (i == 0) {
                        center.add(labels[j-1]);
                    } else if (j == 0) {
                        center.add(labels[i + 3]);
                    } else {
                        center.add(kalender[i-4][j-1]);
                    }

                }
                i += 3;
            }

            Printer.write(saveFile, kalender);
            Printer.writeCSV(csvFile, kalender);
        }else{
            kalender=read();
            center.setLayout(new GridLayout(62, 8));
            for (int i = 0; i < kalender.length+1; i++) {
                for (int j = 0; j < kalender[0].length+1; j++) {
                    if (i == 0 && j == 0) {
                        blanks[0].setBorder(blackline);
                        center.add(blanks[0]);

                    }else if (i == 0) {
                        center.add(labels[j-1]);
                    } else if (j == 0) {
                        center.add(labels[i + 6]);
                    } else {
                        center.add(kalender[i-1][j-1]);
                    }

                }
            }
        }
        refresh();
    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < kalender.length; i++){
                for(int j = 0; j < kalender[0].length; j++){
                    if(e.getSource() == kalender[i][j]){
                        ButtonSelection asking = new ButtonSelection();
                        JButton returnValue = asking.showDialog();
                        if(returnValue == null) return;
                        kalender[i][j].setText(returnValue.getText());
                        kalender[i][j].setBackground(returnValue.getBackground());
                        if(hoursMode){
                            for(int k = 1; k<4; k++){
                                kalender[i+k][j].setText(returnValue.getText());
                                kalender[i+k][j].setBackground(returnValue.getBackground());
                            }
                        }


                    }
                }
            }
            print();
        }
    }

//    public void changeButtonLaybleInHoursMode(JButton button){
//
//    }
    private class HoursListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            print();
            hoursMode = true;
            hours.setBackground(Colors.lightGreen);
            quadHours.setBackground(Colors.lightGray);
            changeMode();
        }
    }

    private class QuatterHoursListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            print();
            hoursMode = false;
            quadHours.setBackground(Colors.lightGreen);
            hours.setBackground(Colors.lightGray);
            changeMode();
        }
    }

    public void addSelectPane(JPanel panel){
        getContentPane().add(panel, BorderLayout.LINE_START);
        refresh();
    }
    private class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!selectMode) {
                selectMode = true;
                MultiSelectWindow changeSelect = new MultiSelectWindow(thisObjekt);
                addSelectPane(changeSelect);
            }
        }
    }

    private class ChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = select.getText();
            System.out.println(s);
        }
    }



    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(thisObjekt,
                    "Everything is gona be Reset", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            for (JButton[] jButtons : kalender) {
                for (int j = 0; j < kalender[0].length; j++) {
                    jButtons[j].setBackground(Colors.lightGreen);
                    jButtons[j].setText("Frei");
                }
            }
            Printer.write(saveFile, kalender);
        }
    }

    public void refresh(){
        this.setVisible(false);
        this.setVisible(true);
    }

    public void print(){
            Printer.writeCSV(csvFile, kalender);
            Printer.write(saveFile, kalender);
    }

    public JButton[][] read(){
        return Printer.read(saveFile, kalender);
    }

//    public JButton getPriority(JButton[] buttons){
//        for(int i = 0; i< buttons.length; i++) {
//            if (buttons[i].getBackground().equals(third)
//                &&!buttons[(i+1)%4].getBackground().equals(third)
//                &&!buttons[(i+2)%4].getBackground().equals(third)
//                &&!buttons[(i+3)%4].getBackground().equals(third)) {
//                return buttons[i];
//            }
//        }
//        for(int i = 0; i< buttons.length; i++) {
//            if (buttons[i].getBackground().equals(second)
//                    &&!buttons[(i+1)%4].getBackground().equals(second)
//                    &&!buttons[(i+2)%4].getBackground().equals(second)
//                    &&!buttons[(i+3)%4].getBackground().equals(second)) {
//                return buttons[i];
//            }
//        }
//        return buttons[0];
//    }


    public static void main(String[] args){
        JFrame frame = new Kalender();
        frame.setVisible(true);
    }

    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
    }



}
