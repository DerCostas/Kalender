import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Kalender extends JFrame {


    private JButton[][] kalender = new JButton[61][7]; //15*4+1
    private JButton select, mark, hours, quadHours, defaultFile;
    private final JLabel[] labels = new JLabel[68];// 7+ (4*15+1)
    private final JLabel[] blanks = new JLabel[20];
    private JPanel center, top;
    private final Kalender thisObjekt = this;

    private boolean hoursMode = true;
    private boolean menuMode = false;
    private boolean markMode = true;

    private final Border blackline = BorderFactory.createLineBorder(Color.gray);

    private final Border smallRed = BorderFactory.createLineBorder(Color.red, 2);
    private final Border bigRed = BorderFactory.createLineBorder(Color.red, 5);
    private final String weekOneSaveFile ="D:\\programming\\timer\\Kalender\\Resources\\WeekOneSaveFile";
    private final String weekTwoSaveFile ="D:\\programming\\timer\\Kalender\\Resources\\WeekTwoSaveFile";
    private final String weekThreeSaveFile ="D:\\programming\\timer\\Kalender\\Resources\\WeekThreeSaveFile";
    private final String weekFourSaveFile ="D:\\programming\\timer\\Kalender\\Resources\\WeekFourSaveFile";
    private final String defaultSaveFile = "D:\\programming\\timer\\Kalender\\Resources\\Default";
    private final String emptySaveFile = "D:\\programming\\timer\\Kalender\\Resources\\EmptyKalender";

    private final ImageIcon icon = new ImageIcon("D:\\programming\\timer\\Kalender\\Resources\\KalenderIcon.png");
    private String currentlyDisplayedFile = weekOneSaveFile;

    Timer selectCurrentButton , tempTimer;


    private final String csvFile ="D:\\programming\\timer\\Kalender\\Resources\\Kalender.csv";

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
        startTimer();

        selectCurrentTime();
        selectCurrentButton.start();
    }

    public void startTimer(){
        selectCurrentButton = new Timer((int) (900000-((new Date(System.currentTimeMillis()).getTime()%86400000)%3600000)%900000), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCurrentButton.setDelay(300000);
                selectCurrentTime();
                System.out.println(selectCurrentButton.getDelay());
            }
        });

    }

    public void selectCurrentTime(){
        int minutes = Integer.parseInt ( new SimpleDateFormat("mm").format(new Date(System.currentTimeMillis())));
        int hours = Integer.parseInt ( new SimpleDateFormat("kk").format(new Date(System.currentTimeMillis())));
        int weekday = Integer.parseInt ( new SimpleDateFormat("u").format(new Date(System.currentTimeMillis())))-1;
        int currentI;
        if(hoursMode) {
            if(hours < 7){
                currentI = 0;
            }else if(hours >= 22) {
                currentI = kalender.length-1;
            }
            else{
                currentI = ((hours - 7) * 4);
            }
            if(kalender[currentI][weekday].getBorder().equals(smallRed) || kalender[currentI][weekday].getBorder().equals(smallRed)){
                return;
            }
            for(int i = 0; i<kalender.length;i++) {
                for (int j = 0; j< kalender[0].length;j++) {
                    kalender[i][j].setBorder(blackline);
                }
            }
            kalender[currentI][weekday].setBorder(bigRed);
        }else{
            currentI =((hours-7)*4)+(minutes/15);
            if(kalender[currentI][weekday].getBorder().equals(smallRed) || kalender[currentI][weekday].getBorder().equals(smallRed)){
                return;
            }
            for(int i = 0; i<kalender.length;i++) {
                for (int j = 0; j< kalender[0].length;j++) {
                    kalender[i][j].setBorder(blackline);
                }
            }
            kalender[currentI][weekday].setBorder(smallRed);
        }
        kalender[currentI][weekday].setVisible(false);
        kalender[currentI][weekday].setVisible(true);
    }

    public void buildPanel(){
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setTitle("Kalender");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
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


            top = new JPanel();
            top.setLayout(new GridLayout(1,5));
            top.add(select);
            top.add(mark);
            top.add(defaultFile);
            top.add(hours);
            top.add(quadHours);
        changeMode();
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
                kalender[i][j].setBorder(blackline);
                kalender[i][j].setFont(new Font("Verdana", Font.BOLD, 14));
                if(markMode){
                    kalender[i][j].addActionListener(new MarkedButtonListener());
                }else{
                    kalender[i][j].addActionListener(new ButtonListener());
                }

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
        defaultFile = new JButton("Default");
        defaultFile.addActionListener(new DefaultListener());
        defaultFile.setBackground(Colors.lightGreen);

        select = new JButton("Menu");
        select.addActionListener(new MenuListener());
        select.setBackground(Colors.lightGray);
        mark = new JButton("Mark");
        mark.addActionListener(new MarkListener());
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
        if(markMode){
            mark.setBackground(Colors.lightGreen);
        }else{
            mark.setBackground(Colors.lightGray);
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
        if(markMode){
            mark.setBackground(Colors.lightGreen);
        }else{
            mark.setBackground(Colors.lightGray);
        }
        resetMarkMode(false);
        selectCurrentTime();
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
                        markMode = false;
                    }
                    kalender[i][j].addActionListener(new ButtonListener());
                }
            }
        }

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
                    if (((JButton) e.getSource()).equals(kalender[i][j])){
                        if(!IsKeyPressed.isCtrlPressed()) {
                            resetMarkMode(false);
                        }
                        if(IsKeyPressed.isShiftPressed()){

                            if(hoursMode) {
                                kalender = MarkHelper.makeShiftSelected(kalender[i][j], kalender, true);
                            }else{
                                kalender = MarkHelper.makeShiftSelected(kalender[i][j], kalender, false);
                            }
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
                            MarkHelper.setStartSettet(false);
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
                mark.setBackground(Colors.lightGreen);
                MarkHelper.setStartSettet(false);
                for (int i = 0; i< kalender.length; i++) {
                    for (int j = 0; j < kalender[0].length; j++) {
                        for( ActionListener al : kalender[i][j].getActionListeners() ) {
                            kalender[i][j].removeActionListener( al );
                        }
                        System.out.println("i: "+ i+ " j:"+j);
                        kalender[i][j].addActionListener(new MarkedButtonListener());
                    }
                }
                ((JButton)e.getSource()).setBackground(Colors.lightGreen);
            }else{
                mark.setBackground(Colors.lightGray);
                MarkHelper.setStartSettet(false);
                markMode = false;
                ((JButton)e.getSource()).setBackground(Colors.lightGray);
                resetMarkMode(true);
            }
        }
    }




        public void clearKalender() {
            for (JButton[] jButtons : kalender) {
                for (int j = 0; j < kalender[0].length; j++) {
                    jButtons[j].setBackground(Colors.lightGreen);
                    jButtons[j].setText("Frei");
                }
            }
            print();

    }

    private class DefaultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(thisObjekt,
                    "Current Week is gona be set to Default", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            print();
            resetMarkMode(false);
            kalender = Printer.read(defaultSaveFile, kalender);
            print();
            changeMode();

        }
    }

    public void refresh(){
        center .setVisible(false);
        center .setVisible(true);
        top .setVisible(false);
        top .setVisible(true);
        changeSelect.setVisible(false);
        changeSelect.setVisible(true);
    }

    public void print(){
        resetMarkMode(false);
        Printer.writeCSV(csvFile, kalender);
        Printer.write(currentlyDisplayedFile, kalender);
    }

    public void printInto(String file){
        resetMarkMode(false);
        Printer.write(file, kalender);
    }

    public JButton[][] read(){
        return Printer.read(currentlyDisplayedFile, kalender);
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

    public boolean isHoursMode() {
        return hoursMode;
    }

    public Border getBlackline() {
        return blackline;
    }


    public String getCurrentlyDisplayedFile() {
        return currentlyDisplayedFile;
    }

    public void setCurrentlyDisplayedFile(String currentlyDisplayedFile){
        this.currentlyDisplayedFile = currentlyDisplayedFile;
    }

    public String getWeekOneSaveFile() {
        return weekOneSaveFile;
    }

    public String getWeekTwoSaveFile() {
        return weekTwoSaveFile;
    }

    public String getWeekThreeSaveFile() {
        return weekThreeSaveFile;
    }

    public String getWeekFourSaveFile() {
        return weekFourSaveFile;
    }

    public String getDefaultSaveFile() {
        return defaultSaveFile;
    }

    public String getEmptySaveFile() {
        return emptySaveFile;
    }

    public static void main(String[] args){
        JFrame frame = new Kalender();
        frame.setVisible(true);
    }
}


