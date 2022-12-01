import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalender extends JFrame {


    private JButton[][] kalender = new JButton[61][7]; //15*4+1
    private JButton select, mark, hours, quadHours;
    private final JLabel[] labels = new JLabel[68];// 7+ (4*15+1)
    private final JLabel[] blanks = new JLabel[20];
    private JPanel center;
    private final Kalender thisObjekt = this;

    private boolean hoursMode = true;
    private boolean menuMode = false;
    private boolean markMode = false;

    private final Border blackline = BorderFactory.createLineBorder(Color.gray);
    private final String weekOneSaveFile ="Resources\\WeekOneSaveFile";
    private final String weekTwoSaveFile ="Resources\\WeekTwoSaveFile";
    private final String weekThreeSaveFile ="Resources\\WeekThreeSaveFile";
    private final String weekFourSaveFile ="Resources\\WeekFourSaveFile";
    private final String currentlyDisplayedFile = weekOneSaveFile;




    private final String csvFile ="Resources\\Kalender.csv";

    MultiSelectWindow changeSelect;
    public Kalender(){
        for (int i = 0; i< blanks.length; i++){
            blanks[i] = new JLabel();
        }
        IsKeyPressed.prepare();
        changeSelect = new MultiSelectWindow(thisObjekt);
        MarkHelper.setParent(this);

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
            top.add(mark);
            top.add(blanks[1]);
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
        select = new JButton("Menu");
        select.addActionListener(new MenuListener());
        select.setBackground(Colors.lightGray);
        mark = new JButton("Mark");
        mark.addActionListener(new MarkListener());
        mark.setBackground(Colors.lightGray);
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

    public JButton hourModePriorityButton(JButton[] buttons){
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Arbeit")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Uni")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Lernen")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Sport")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Gitarre")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Essen")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText().equals("Other")){
                return buttons[i];
            }
        }
        for(int i = 0; i< buttons.length; i++){
            if(buttons[i].getText() == "Frei"){
                return buttons[i];
            }
        }
        return buttons[0];
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
                        if (i < 61) {
                            center.add(hourModePriorityButton(new JButton[]{kalender[i - 4][j - 1], kalender[i - 3][j - 1], kalender[i - 2][j - 1], kalender[i - 1][j - 1]}));

                        }else{
                            center.add(kalender[i - 4][j - 1]);

                        }
                    }

                }
                i += 3;
            }


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
        mark.setBackground(Colors.lightGray);
        resetMarkMode(true);
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
                        int newIndex[] = getfirstButtonInHourMode(kalender[i][j]);
                        if(returnValue == null) return;
                        if(hoursMode){
                            for(int k = 0; k<4; k++){
                                kalender[newIndex[0]+k][newIndex[1]].setText(returnValue.getText());
                                kalender[newIndex[0]+k][newIndex[1]].setBackground(returnValue.getBackground());
                            }
                        }else{
                            kalender[i][j].setText(returnValue.getText());
                            kalender[i][j].setBackground(returnValue.getBackground());
                        }


                    }
                }
            }

        }
    }

    public int[] getfirstButtonInHourMode(JButton button){
        for(int i = 0; i < kalender.length; i++){
            for(int j = 0; j < kalender[0].length; j++) {
                if(button.equals(kalender[i][j])){
                    switch (i%4) {
                        case 0:
                            return new int[]{i, j};
                        case 1:
                            return new int[]{i - 1, j};
                        case 2:
                            return new int[]{i - 2, j};
                        case 3:
                            return new int[]{i - 3, j};
                    }

                }
            }
        }
        return new int[]{-1,-1};
    }

    public void resetMarkMode(boolean fullReset){
        for (int i = 0; i< kalender.length; i++){
            for (int j = 0; j< kalender[0].length; j++){
                kalender[i][j] =MarkHelper.makeNotSelected(kalender[i][j]);

                if(fullReset) {
                    for( ActionListener al : kalender[i][j].getActionListeners() ) {
                        kalender[i][j].removeActionListener( al );
                    }
                    kalender[i][j].addActionListener(new ButtonListener());
                }
            }
        }
        markMode = false;
    }
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

    public void addSelectPane(MultiSelectWindow panel){
        getContentPane().add(panel, BorderLayout.LINE_START);
        panel.open();
    }
    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!menuMode) {
                menuMode = true;

                addSelectPane(changeSelect);
                ((JButton)e.getSource()).setBackground(Colors.lightGreen);

            }else{
                ((JButton)e.getSource()).setBackground(Colors.lightGray);
                changeSelect.close();
            }
        }
    }
    public class MarkedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < kalender.length; i++) {
                for (int j = 0; j < kalender[0].length; j++) {
                    if (((JButton) e.getSource()).equals(kalender[(i%4)*4][j])){
                        i= (i%4)*4;
                        if(IsKeyPressed.isShiftPressed()){
                            kalender = MarkHelper.makeShiftSelected(kalender[i][j], kalender);
                            if(MarkHelper.isStartSettet()) {
                                for (int k = 0; k < kalender.length; k++) {
                                    for (int h = 0; h < kalender[0].length; h++) {
                                        if (MarkHelper.isSelected(kalender[k][h])) {
                                            changeSelect.addToList(kalender[k][h]);
                                        }
                                    }
                                }
                            }
                        }else {
                            if (hoursMode) {
                                int[] newIndex = getfirstButtonInHourMode(kalender[i][j]);
                                for (int k = 0; k < 4; k++) {
                                    if (MarkHelper.isSelected(kalender[i + k][j])) {
                                        kalender[newIndex[0]+k][newIndex[1]] = MarkHelper.makeNotSelected(kalender[newIndex[0]+k][newIndex[1]]);
                                        changeSelect.removeFromList(kalender[newIndex[0]+k][newIndex[1]]);
                                    } else {
                                        kalender[newIndex[0]+k][newIndex[1]] = MarkHelper.makeSelected(kalender[newIndex[0]+k][newIndex[1]]);
                                        changeSelect.addToList(kalender[newIndex[0]+k][newIndex[1]]);
                                    }
                                }
                            } else {
                                JButton button = (JButton) e.getSource();
                                if (MarkHelper.isSelected(button)) {
                                    button = MarkHelper.makeNotSelected(button);
                                    changeSelect.removeFromList(button);
                                } else {
                                    button = MarkHelper.makeSelected(button);
                                    changeSelect.addToList(button);
                                }
                            }
                        }
                        return;
                    }
                }
            }
        }
    }

    private class MarkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!markMode) {
                markMode = true;
                MarkHelper.setStartSettet(false);
                for (int i = 0; i< kalender.length; i++) {
                    for (int j = 0; j < kalender[0].length; j++) {
                        for( ActionListener al : kalender[i][j].getActionListeners() ) {
                            kalender[i][j].removeActionListener( al );
                        }
                        kalender[i][j].addActionListener(new MarkedButtonListener());
                    }
                }
                ((JButton)e.getSource()).setBackground(Colors.lightGreen);
            }else{
                MarkHelper.setStartSettet(false);
                markMode = false;
                ((JButton)e.getSource()).setBackground(Colors.lightGray);
                resetMarkMode(true);
            }
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
            print();
        }
    }

    public void refresh(){
        this.setVisible(false);
        this.setVisible(true);
    }

    public void print(){
        resetMarkMode(true);
        Printer.writeCSV(csvFile, kalender);
        Printer.write(currentlyDisplayedFile, kalender);
    }

    public JButton[][] read(){
        return Printer.read(currentlyDisplayedFile, kalender);
    }

    public static void main(String[] args){
        JFrame frame = new Kalender();
        frame.setVisible(true);
    }

    public void setSelectMode(boolean selectMode) {
        this.menuMode = selectMode;
    }

    public JButton[][] getKalender() {
        return kalender;
    }

    public void setKalender(JButton[][] kalender) {
        this.kalender = kalender;
    }

    public JButton getSelect() {
        return select;
    }

    public void setSelect(JButton select) {
        this.select = select;
    }


    public JButton getHours() {
        return hours;
    }

    public void setHours(JButton hours) {
        this.hours = hours;
    }

    public JButton getQuadHours() {
        return quadHours;
    }

    public void setQuadHours(JButton quadHours) {
        this.quadHours = quadHours;
    }



    public JLabel[] getLabels() {
        return labels;
    }

    public JLabel[] getBlanks() {
        return blanks;
    }

    public JPanel getCenter() {
        return center;
    }

    public void setCenter(JPanel center) {
        this.center = center;
    }

    public Kalender getThisObjekt() {
        return thisObjekt;
    }

    public boolean isHoursMode() {
        return hoursMode;
    }

    public void setHoursMode(boolean hoursMode) {
        this.hoursMode = hoursMode;
    }

    public boolean isSelectMode() {
        return menuMode;
    }

    public Border getBlackline() {
        return blackline;
    }

    public String getCsvFile() {
        return csvFile;
    }
}
