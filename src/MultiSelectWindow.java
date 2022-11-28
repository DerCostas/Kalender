import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectWindow extends JPanel {

    private javax.swing.JButton applyButton, cancelButton, moveDown, moveUp, clearCurrent, clearAll,cloneOne ,cloneTwo, cloneThree, cloneFour;
    private JToggleButton weekOne,weekTwo,weekThree,  weekFour;
    private javax.swing.JRadioButton arbeitButton, colorButton, essenButton, freiButton, gitarreButton, lernenButton, otherButton, otherNameButton, sportButton, uniButton;
    private javax.swing.JComboBox<String> colourSelecter;
    private javax.swing.JScrollPane CellsTopEditScrollPane;
    private javax.swing.JTextField otherTextField;
    private Color[] colors = new Color[10];
    ButtonGroup kalenderInbutButtons, weekButtons;
    Kalender parent;

    LinkedList<JButton> selected;
    MultiSelectWindow( Kalender kalender){
        selected = new LinkedList<JButton>();
        parent = kalender;
        buildButtons();
        buildLayout();

        parent.setSize(parent.getWidth()+220, parent.getHeight());

    }


    public void addToList(JButton button){
        selected.add(button);
    }

    public void buildButtons(){
        CellsTopEditScrollPane = new JScrollPane();

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


        moveUp = new javax.swing.JButton();
        moveUp.addActionListener(new MoveListener());
        moveDown = new javax.swing.JButton();
        moveDown.addActionListener(new MoveListener());
        weekOne = new javax.swing.JToggleButton();
        weekOne.addActionListener(new WeeksListener());
        weekTwo = new javax.swing.JToggleButton();
        weekTwo.addActionListener(new WeeksListener());
        weekThree = new javax.swing.JToggleButton();
        weekThree.addActionListener(new WeeksListener());
        weekFour = new javax.swing.JToggleButton();
        weekFour.addActionListener(new WeeksListener());
        cloneOne = new javax.swing.JButton();
        cloneOne.addActionListener(new CloneListener());
        cloneTwo = new javax.swing.JButton();
        cloneTwo.addActionListener(new CloneListener());
        cloneThree = new javax.swing.JButton();
        cloneThree.addActionListener(new CloneListener());
        cloneFour = new javax.swing.JButton();
        cloneFour.addActionListener(new CloneListener());
        clearAll = new javax.swing.JButton();
        clearAll.addActionListener(new ClearListener());
        clearCurrent = new javax.swing.JButton();
        clearCurrent.addActionListener(new ClearListener());




        cancelButton =new JButton();
        cancelButton.addActionListener(new CancelListener());
        applyButton = new javax.swing.JButton();
        applyButton.addActionListener(new ApplyListener());

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
        otherButton.setText("Other");
        otherNameButton.setText("Other");
        colorButton.setText("Color");
        otherTextField.setText("");
        cancelButton.setText("Cancel");
        applyButton.setText("Apply");

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
                "Beige"}));


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
            close();
        }
    }

    private class AuswahlListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(otherNameButton.isSelected()){
                otherTextField.setEnabled(true);
            }else {
                otherTextField.setEnabled(true);
            }
            if(colorButton.isSelected()){
                colourSelecter.setEnabled(true);
            }else {
                colourSelecter.setEnabled(false);
            }
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();

        }
    }

    public void close(){
        parent.setSelectMode(false);
        setVisible(false);
        parent.setSize(parent.getWidth()-getWidth(), parent.getHeight());
    }



    private class WeeksListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    private class CloneListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    private class MoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }




    public List showDialog(){
        this.setVisible(true);
        return selected;
    }

    public void buildLayout(){

        javax.swing.GroupLayout BigPaneLayout = new javax.swing.GroupLayout(this);
        this.setLayout(BigPaneLayout);
        BigPaneLayout.setHorizontalGroup(
                BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BigPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lernenButton)
                                                        .addComponent(freiButton)
                                                        .addComponent(gitarreButton)
                                                        .addComponent(arbeitButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(otherButton)
                                                        .addComponent(uniButton)
                                                        .addComponent(essenButton)
                                                        .addComponent(sportButton))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(otherNameButton)
                                                        .addComponent(colorButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(otherTextField)
                                                        .addComponent(colourSelecter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BigPaneLayout.createSequentialGroup()
                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                                .addComponent(weekThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cloneThree))
                                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                                .addComponent(clearCurrent)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(clearAll))
                                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(weekOne, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                                                        .addComponent(weekTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, Short.MAX_VALUE)
                                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(cloneOne, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                                                        .addComponent(cloneTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BigPaneLayout.createSequentialGroup()
                                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                                                .addComponent(moveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                                                .addComponent(weekFour, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                                                .addGap(10, 10, 10)))
                                                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                                                .addGap(5, 5, 5)
                                                                                .addComponent(moveDown))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BigPaneLayout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(cloneFour, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addContainerGap())
                                        .addGroup(BigPaneLayout.createSequentialGroup()
                                                .addComponent(cancelButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(applyButton)
                                                .addContainerGap())))
        );
        BigPaneLayout.setVerticalGroup(
                BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BigPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(uniButton)
                                        .addComponent(freiButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lernenButton)
                                        .addComponent(sportButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(gitarreButton)
                                        .addComponent(essenButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(arbeitButton)
                                        .addComponent(otherButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(otherNameButton)
                                        .addComponent(otherTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(colorButton)
                                        .addComponent(colourSelecter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekOne)
                                        .addComponent(cloneOne))
                                .addGap(18, 18, 18)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekTwo)
                                        .addComponent(cloneTwo))
                                .addGap(18, 18, 18)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekThree)
                                        .addComponent(cloneThree))
                                .addGap(18, 18, 18)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weekFour)
                                        .addComponent(cloneFour))
                                .addGap(18, 18, 18)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(moveUp)
                                        .addComponent(moveDown))
                                .addGap(53, 53, 53)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(clearCurrent)
                                        .addComponent(clearAll))
                                .addGap(40, 40, 40)
                                .addGroup(BigPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(applyButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public static void main(String[] args){


        MultiSelectWindow window = new MultiSelectWindow(new Kalender());
        window.setVisible(true);
        JFrame test = new JFrame();

        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        test.getContentPane().add(window);
        test.setVisible(true);
    }
}
