import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class ButtonSelection extends JDialog {
    JButton output;
    ButtonGroup buttonGroup;


    private javax.swing.JComboBox<String> Colour;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JRadioButton jRadioButton1;
    Color[] colors = new Color[10];
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JTextField jTextField1;

    JButton toSend;
    ButtonSelection(){
        output = new JButton();
        makeColors();
        buildPanel();
        buildButtons();
        buildLayout();
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



    public void buildPanel(){
        this.setSize(350, 200);
        this.setLocationRelativeTo(null);
        this.setTitle("Kalender");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setAlwaysOnTop(false);
        this.setFocusable(true);
        this.requestFocus();
        this.setModal(true);
    }

    public void buildButtons(){
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton1.addActionListener(new AuswahlListener() );
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton2.addActionListener(new AuswahlListener() );
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton3.addActionListener(new AuswahlListener() );
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton4.addActionListener(new AuswahlListener() );
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton5.addActionListener(new AuswahlListener() );
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton6.addActionListener(new AuswahlListener() );
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton7.addActionListener(new AuswahlListener() );
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton8.addActionListener(new AuswahlListener() );
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton9.addActionListener(new AuswahlListener() );
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton10.addActionListener(new AuswahlListener() );

        // jRadioButton9= Colors.colourRadioButtons(jRadioButton9);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);
        buttonGroup.add(jRadioButton4);
        buttonGroup.add(jRadioButton5);
        buttonGroup.add(jRadioButton6);
        buttonGroup.add(jRadioButton7);
        buttonGroup.add(jRadioButton8);
        buttonGroup.add(jRadioButton9);




        jButton1 =new JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField1.setEnabled(false);
        jButton2 = new javax.swing.JButton();
        Colour = new javax.swing.JComboBox<>();
        Colour.setEnabled(false);


        jRadioButton1.setText("Frei");
        jRadioButton2.setText("Uni");
        jRadioButton3.setText("Lernen");
        jRadioButton4.setText("Sport");
        jRadioButton5.setText("Gitarre");
        jRadioButton6.setText("Essen");
        jRadioButton7.setText("Arbeit");
        jRadioButton8.setText("Other");
        jRadioButton9.setText("Other");
        jRadioButton10.setText("Color");
        jTextField1.setText("");
        jButton1.setText("Cancel");
        jButton2.setText("Apply");

        jRadioButton1= Colors.colourRadioButtons(jRadioButton1);
        jRadioButton2= Colors.colourRadioButtons(jRadioButton2);
        jRadioButton3= Colors.colourRadioButtons(jRadioButton3);
        jRadioButton4= Colors.colourRadioButtons(jRadioButton4);
        jRadioButton5= Colors.colourRadioButtons(jRadioButton5);
        jRadioButton6= Colors.colourRadioButtons(jRadioButton6);
        jRadioButton7= Colors.colourRadioButtons(jRadioButton7);
        jRadioButton8= Colors.colourRadioButtons(jRadioButton8);

        jButton1.addActionListener(new CancelListener());
        jButton2.addActionListener(new ApplyListener());

        Colour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light Green",
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
    @SuppressWarnings("unchecked")
    public void buildLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jRadioButton10)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(Colour, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jRadioButton5)
                                                                        .addComponent(jRadioButton1)
                                                                        .addComponent(jRadioButton9))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jRadioButton6)
                                                                                        .addComponent(jRadioButton2))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jRadioButton7)
                                                                                        .addComponent(jRadioButton3))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jRadioButton4)
                                                                                        .addComponent(jRadioButton8))))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton2)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton2)
                                        .addComponent(jRadioButton3)
                                        .addComponent(jRadioButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton5)
                                        .addComponent(jRadioButton6)
                                        .addComponent(jRadioButton7)
                                        .addComponent(jRadioButton8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton9)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Colour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jRadioButton10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap())
        );
        pack();
    }

    public JButton showDialog(){
        this.setVisible(true);
        return toSend;
    }


    private class AuswahlListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                if(jRadioButton9.isSelected()){
                    jTextField1.setEnabled(true);
                }else {
                    jTextField1.setEnabled(true);
                }
                if(jRadioButton10.isSelected()){
                    Colour.setEnabled(true);
                }else {
                    Colour.setEnabled(false);
                }
        }
    }



    private class ApplyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            toSend = new JButton();
            JRadioButton button = getSelectedButtonText(buttonGroup);
            if(jRadioButton9.isSelected()){
                toSend.setText(jTextField1.getText());
            }else{
                toSend.setText(button.getText());
            }
            if(jRadioButton10.isSelected()){
                toSend.setBackground(Colors.stringToColor(Colour.getItemAt(Colour.getSelectedIndex())));
            }else{
                toSend.setBackground(button.getBackground());
            }
            setVisible(false);
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }


    public JRadioButton getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return (JRadioButton) button;
            }
        }

        return null;
    }
}
