import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectWindow extends JPanel {

    private javax.swing.JButton  cancelButton, moveDown, moveUp, clearCurrent, clearAll,cloneOne ,cloneTwo, cloneThree, cloneFour, applyButton;
    private JToggleButton weekOne,weekTwo,weekThree,  weekFour;
    private javax.swing.JRadioButton arbeitButton, colorButton, essenButton, freiButton, gitarreButton, lernenButton, otherButton, otherNameButton, sportButton, uniButton;
    private javax.swing.JComboBox<String> colourSelecter;
    private JRadioButton currentlySelected;
    private javax.swing.JTextField otherTextField;
    private Color[] colors = new Color[10];
    ButtonGroup kalenderInbutButtons, weekButtons;
    Kalender parent;
    private JPanel weeksPanel, cellEditPanel;
    private final int parentSizer = 260;

    MultiSelectWindow thisObjekt = this;

    LinkedList<JButton> selected;

    MultiSelectWindow( Kalender kalender){
        selected = new LinkedList<JButton>();
        parent = kalender;
        buildButtons();
        buildLayout();
    }


    public void addToList(JButton button){
        selected.add(button);
    }
    public void removeFromList(JButton button){
        selected.remove(button);
    }


    public void buildButtons(){
        freiButton = new javax.swing.JRadioButton();
        freiButton.addActionListener(new AuswahlListener() );
        uniButton = new javax.swing.JRadioButton();
        uniButton.addActionListener(new AuswahlListener() );
        lernenButton = new javax.swing.JRadioButton();
        lernenButton.addActionListener(new AuswahlListener() );
        sportButton = new javax.swing.JRadioButton();
        sportButton.addActionListener(new AuswahlListener() );
        gitarreButton = new javax.swing.JRadioButton();
        gitarreButton.addActionListener(new AuswahlListener() );
        essenButton = new javax.swing.JRadioButton();
        essenButton.addActionListener(new AuswahlListener() );
        arbeitButton = new javax.swing.JRadioButton();
        arbeitButton.addActionListener(new AuswahlListener() );
        otherButton = new javax.swing.JRadioButton();
        otherButton.addActionListener(new AuswahlListener() );
        otherNameButton = new javax.swing.JRadioButton();
        otherNameButton.addActionListener(new AuswahlListener() );
        colorButton = new javax.swing.JRadioButton();
        colorButton.addActionListener(new AuswahlListener() );
        freiButton.setBackground(colors[0]);
        uniButton.setBackground(colors[1]);
        lernenButton.setBackground(colors[2]);
        sportButton.setBackground(colors[8]);
        gitarreButton.setBackground(colors[9]);
        essenButton.setBackground(colors[5]);
        arbeitButton.setBackground(colors[6]);
        otherButton.setBackground(colors[7]);
        otherNameButton.setBackground(colors[3]);

        kalenderInbutButtons = new ButtonGroup();
        kalenderInbutButtons.add(freiButton);
        kalenderInbutButtons.add(uniButton);
        kalenderInbutButtons.add(lernenButton);
        kalenderInbutButtons.add(sportButton);
        kalenderInbutButtons.add(gitarreButton);
        kalenderInbutButtons.add(essenButton);
        kalenderInbutButtons.add(arbeitButton);
        kalenderInbutButtons.add(otherButton);
        kalenderInbutButtons.add(otherNameButton);

        weekButtons = new ButtonGroup();
        weekButtons.add(weekOne);
        weekButtons.add(weekTwo);
        weekButtons.add(weekThree);
        weekButtons.add(weekFour);

        weeksPanel = new javax.swing.JPanel();
        weeksPanel.setBorder(parent.getBlackline());
        cellEditPanel = new javax.swing.JPanel();
        cellEditPanel.setBorder(parent.getBlackline());

        moveUp = new javax.swing.JButton();
        moveUp.addActionListener(new MoveUpListener());
        moveDown = new javax.swing.JButton();
        moveDown.addActionListener(new MoveDownListener());
        weekOne = new javax.swing.JToggleButton();
        weekOne.addActionListener(new WeeksOneListener());
        weekOne.setSelected(true);
        weekTwo = new javax.swing.JToggleButton();
        weekTwo.addActionListener(new WeeksTwoListener());
        weekThree = new javax.swing.JToggleButton();
        weekThree.addActionListener(new WeeksThreeListener());
        weekFour = new javax.swing.JToggleButton();
        weekFour.addActionListener(new WeeksFourListener());
        cloneOne = new javax.swing.JButton();
        cloneOne.addActionListener(new CloneOneListener());
        cloneTwo = new javax.swing.JButton();
        cloneTwo.addActionListener(new CloneTwoListener());
        cloneThree = new javax.swing.JButton();
        cloneThree.addActionListener(new CloneThreeListener());
        cloneFour = new javax.swing.JButton();
        cloneFour.addActionListener(new CloneFourListener());
        clearAll = new javax.swing.JButton();
        clearAll.addActionListener(new ClearAllListener());
        clearCurrent = new javax.swing.JButton();
        clearCurrent.addActionListener(new ClearCurrentListener());


       applyButton =new JButton();
        applyButton.addActionListener(new ApplyListener());

        cancelButton =new JButton();
        cancelButton.addActionListener(new CancelListener());


        otherTextField = new javax.swing.JTextField();
        otherTextField.setEnabled(false);
        colourSelecter = new javax.swing.JComboBox<>();
        colourSelecter.setEnabled(false);


        freiButton.setText("Frei");
        uniButton.setText("Uni");
        lernenButton.setText("Lernen");
        sportButton.setText("Sport");
        gitarreButton.setText("Gitarre");
        essenButton.setText("Essen");
        arbeitButton.setText("Arbeit");
        otherButton.setText("Unsure");
        otherNameButton.setText("Other");
        colorButton.setText("Color");
        otherTextField.setText("");
        cancelButton.setText("Cancel");
        applyButton.setText("Apply");

        freiButton = Colors.colourRadioButtons(freiButton);
        uniButton = Colors.colourRadioButtons(uniButton);
        lernenButton = Colors.colourRadioButtons(lernenButton);
        sportButton = Colors.colourRadioButtons(sportButton);
        gitarreButton = Colors.colourRadioButtons(gitarreButton);
        essenButton = Colors.colourRadioButtons(essenButton);
        arbeitButton = Colors.colourRadioButtons(arbeitButton);
        otherButton = Colors.colourRadioButtons(otherButton);




        clearCurrent.setText("Clear Current");
        moveUp.setText("Move Up");
        moveDown.setText("Move Down");
        weekOne.setText("Week One");
        weekTwo.setText("Week Two");
        weekThree.setText("Week Three");
        weekFour.setText("Week Four");
        cloneOne.setText("Clone One");
        cloneTwo.setText("Clone Two");
        cloneThree.setText("Clone Three");
        cloneFour.setText("Clone Four");
        clearAll.setText("Clear All");




        colourSelecter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light Green",
                "Blue",
                "Orange",
                "Purple",
                "Red",
                "Cyan",
                "Yellow",
                "pink",
                "Skintone",
                "Beige"
        }));


    }


    public void makeColors(){
        colors[0] = Colors.lightGreen;
        colors[1] = Colors.blue;
        colors[2] = Colors.orange;
        colors[3] = Colors.purple;
        colors[4] = Colors.red;
        colors[5] = Colors.cyan;
        colors[6] = Colors.yellow;
        colors[7] = Colors.pink;
        colors[8] = Colors.skintone;
        colors[9] = Colors.sandy;
    }

    private class ApplyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton temp = new JButton();
            if(otherNameButton.isSelected()){
                temp.setText(otherTextField.getText());
            }else{
                if(currentlySelected != null){
                    temp.setText(currentlySelected.getText());
                }

            }
            if(colorButton.isSelected()){
                temp.setBackground(Colors.stringToColor(colourSelecter.getItemAt(colourSelecter.getSelectedIndex())));
            }else{
                if(currentlySelected != null) {
                    temp.setBackground(currentlySelected.getBackground());
                }
            }
            JButton[][] array = parent.getKalender();
            for(int i = 0; i< array.length; i++){
                for(int j = 0; j<array[0].length; j++){
                    if(MarkHelper.isSelected(array[i][j])){
                        array[i][j].setText(temp.getText());
                        array[i][j].setBackground(temp.getBackground());
                    }
                }

            }
            parent.setKalender(array);
            MarkHelper.setStartSettet(false);
            parent.print();
        }
    }

    private class AuswahlListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(otherNameButton.isSelected()){
                otherTextField.setEnabled(true);
            }else{
                otherTextField.setEnabled(true);
            }if(colorButton.isSelected()){
                colourSelecter.setEnabled(true);
            }else {
                colourSelecter.setEnabled(false);
            }
            JRadioButton button = (JRadioButton) e.getSource();
            if(!button.equals(colorButton) && !button.equals(otherNameButton)&& button.isSelected()){
                currentlySelected = button;
            }
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.resetMarkMode(false);
        }
    }

    public void close(){
        parent.resetMarkMode(true);
        parent.setSelectMode(false);
        setVisible(false);
        parent.setSize(parent.getWidth()-parentSizer, parent.getHeight());
    }

    public void open(){
        this.setVisible(true);
        parent.setSize(parent.getWidth()+ parentSizer, parent.getHeight());

    }



    private class WeeksOneListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            weekOne.setSelected(true);
            weekTwo.setSelected(false);
            weekThree.setSelected(false);
            weekFour.setSelected(false);

            if(!parent.getCurrentlyDisplayedFile().equals(parent.getWeekOneSaveFile())) {
                parent.print();
                parent.setCurrentlyDisplayedFile(parent.getWeekOneSaveFile());
                parent.changeMode();
            }
        }
    }

    private class WeeksTwoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            weekOne.setSelected(false);
            weekTwo.setSelected(true);
            weekThree.setSelected(false);
            weekFour.setSelected(false);

            if(!parent.getCurrentlyDisplayedFile().equals(parent.getWeekTwoSaveFile())) {
                parent.print();
                parent.setCurrentlyDisplayedFile(parent.getWeekTwoSaveFile());
                parent.changeMode();
            }
        }
    }

    private class WeeksThreeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            weekTwo.setSelected(false);
            weekOne.setSelected(false);
            weekThree.setSelected(true);
            weekFour.setSelected(false);

            if(!parent.getCurrentlyDisplayedFile().equals(parent.getWeekThreeSaveFile())) {
                parent.print();
                parent.setCurrentlyDisplayedFile(parent.getWeekThreeSaveFile());
                parent.changeMode();
            }
        }
    }

    private class WeeksFourListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            weekTwo.setSelected(false);
            weekThree.setSelected(false);
            weekOne.setSelected(false);
            weekFour.setSelected(true);

            if(!parent.getCurrentlyDisplayedFile().equals(parent.getWeekFourSaveFile())) {
                parent.print();
                parent.setCurrentlyDisplayedFile(parent.getWeekFourSaveFile());
                parent.changeMode();
            }
        }
    }

    private class CloneOneListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "Week One will be copied into current Week", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            parent.setKalender(Printer.read(parent.getWeekOneSaveFile(), parent.getKalender()));
            parent.print();
        }
    }

    private class CloneTwoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "Week Two will be copied into current Week", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            parent.setKalender(Printer.read(parent.getWeekTwoSaveFile(), parent.getKalender()));
            parent.print();

        }
    }

    private class CloneThreeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "Week Three will be copied into current Week", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            parent.setKalender(Printer.read(parent.getWeekThreeSaveFile(), parent.getKalender()));
            parent.print();
        }
    }
    private class CloneFourListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "Week Four will be copied into current Week", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            parent.setKalender(Printer.read(parent.getWeekFourSaveFile(), parent.getKalender()));
            parent.print();
        }
    }

    private class ClearCurrentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "Current Week is gona be cleared", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;
            parent.clearKalender();
        }
    }

    private class ClearAllListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "All Weeks is gona be cleared", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;

            parent.setKalender(Printer.read(parent.getEmptySaveFile(), parent.getKalender()));
            parent.setCurrentlyDisplayedFile(parent.getWeekOneSaveFile());
            parent.print();
            parent.setKalender(Printer.read(parent.getEmptySaveFile(), parent.getKalender()));
            parent.setCurrentlyDisplayedFile(parent.getWeekTwoSaveFile());
            parent.print();
            parent.setKalender(Printer.read(parent.getEmptySaveFile(), parent.getKalender()));
            parent.setCurrentlyDisplayedFile(parent.getWeekThreeSaveFile());
            parent.print();
            parent.setKalender(Printer.read(parent.getEmptySaveFile(), parent.getKalender()));
            parent.setCurrentlyDisplayedFile(parent.getWeekFourSaveFile());
            parent.print();

        }
    }



    private class MoveUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "All Weeks is gona be cleared", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;

            parent.setKalender(Printer.read(parent.getWeekTwoSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekOneSaveFile());
            parent.setKalender(Printer.read(parent.getWeekThreeSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekTwoSaveFile());
            parent.setKalender(Printer.read(parent.getWeekFourSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekThreeSaveFile());
            parent.setKalender(Printer.read(parent.getDefaultSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekFourSaveFile());

        }
    }

    private class MoveDownListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(parent,
                    "All Weeks are gona be moved one down", "Are you Sure", JOptionPane.OK_CANCEL_OPTION);
            // 0=ok
            if(input != 0) return;




            parent.setKalender(Printer.read(parent.getWeekThreeSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekFourSaveFile());
            parent.setKalender(Printer.read(parent.getWeekTwoSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekThreeSaveFile());
            parent.setKalender(Printer.read(parent.getWeekOneSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekTwoSaveFile());
            parent.setKalender(Printer.read(parent.getDefaultSaveFile(), parent.getKalender()));
            parent.printInto(parent.getWeekOneSaveFile());

        }
    }


    public List showDialog(){
        this.setVisible(true);
        return selected;
    }

    public void buildLayout() {
        this.setSize(400, 1000);
        javax.swing.GroupLayout weeksPanelLayout = new javax.swing.GroupLayout(weeksPanel);
        weeksPanel.setLayout(weeksPanelLayout);
        weeksPanelLayout.setHorizontalGroup(
                weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                                .addComponent(weekThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cloneThree))
                                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(weekOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(weekTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 24, Short.MAX_VALUE)
                                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cloneOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cloneTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, weeksPanelLayout.createSequentialGroup()
                                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(moveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                                                .addComponent(weekFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(10, 10, 10)))
                                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                                                .addComponent(moveDown, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(cloneFour, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                                .addComponent(clearCurrent)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(clearAll)))
                                .addContainerGap())
        );
        weeksPanelLayout.setVerticalGroup(
                weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(weeksPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cloneOne)
                                        .addComponent(weekOne))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekTwo)
                                        .addComponent(cloneTwo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekThree)
                                        .addComponent(cloneThree))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekFour)
                                        .addComponent(cloneFour))
                                .addGap(18, 18, 18)

                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(moveUp)
                                        .addComponent(moveDown))
                                .addGap(18, 18, 18)
                                .addGroup(weeksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(clearCurrent)
                                        .addComponent(clearAll))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout cellEditPanelLayout = new javax.swing.GroupLayout(cellEditPanel);
        cellEditPanel.setLayout(cellEditPanelLayout);
        cellEditPanelLayout.setHorizontalGroup(
                cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cellEditPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(cellEditPanelLayout.createSequentialGroup()
                                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lernenButton)
                                                        .addComponent(freiButton)
                                                        .addComponent(gitarreButton)
                                                        .addComponent(arbeitButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(otherButton)
                                                        .addComponent(uniButton)
                                                        .addComponent(essenButton)
                                                        .addComponent(sportButton))
                                                .addGap(44, 44, 44))
                                        .addGroup(cellEditPanelLayout.createSequentialGroup()
                                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(otherNameButton)
                                                        .addComponent(colorButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(otherTextField)
                                                        .addComponent(colourSelecter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(cellEditPanelLayout.createSequentialGroup()
                                                .addComponent(cancelButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(applyButton)))
                                .addContainerGap())
        );
        cellEditPanelLayout.setVerticalGroup(
                cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cellEditPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(uniButton)
                                        .addComponent(freiButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lernenButton)
                                        .addComponent(sportButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(gitarreButton)
                                        .addComponent(essenButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(arbeitButton)
                                        .addComponent(otherButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(otherNameButton)
                                        .addComponent(otherTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(colorButton)
                                        .addComponent(colourSelecter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(cellEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(applyButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BigPaneLayout = new javax.swing.GroupLayout(this);
        this.setLayout(BigPaneLayout);
        BigPaneLayout.setHorizontalGroup(
                BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BigPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cellEditPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(weeksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 8, Short.MAX_VALUE))
        );
        BigPaneLayout.setVerticalGroup(
                BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BigPaneLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(cellEditPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(weeksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(52, Short.MAX_VALUE))
        );
    }
}
