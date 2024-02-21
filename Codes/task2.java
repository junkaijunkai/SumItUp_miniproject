package Task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/**
 * This class represents a simple GUI application that generates two random numbers of rabbits, and allows the user
 * to input two operands to compute their sum. The application window is built with Swing components such as JFrame,
 * JPanel, JLabel, and JTextField.
 * @author KevZ.803
 */
public class task2 {

    private int number1=0;
    private int number2=0;
    /**
     * The main function that instantiates a new Task2 object to create the Sum It Up window.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        task2 window=new task2();
        window.createWindow();
    }
    /**
     * Creates the Sum It Up window with the interface designs and sets up the layout.
     */
    public void createWindow(){

        // create the frame
        JFrame myFrame=new JFrame("Welcome to Sum It Up!");
        myFrame.setSize(800,650);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());

        // design the northern layout
        JLabel northLabel=new JLabel();
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northLabel.setText("Enter Two Operands and click 'Check!'");
        // add the lines to the frame
        myFrame.add(northLabel,BorderLayout.NORTH);

        /**
         * Creates the plus sign in the middle of the Sum It Up window to separate the two operands.
         *
         * @param panel the JPanel where the plus sign is displayed
         */
        setPlus(panel);

        /**
         * Sets up the left side of the Sum It Up window to display the first randomly generated operand.
         *
         * @param panel the JPanel where the first operand is displayed
         *
         * @return the randomly generated first operand
         */
        number1=setRabbitLeft(panel);
        /**
         * Sets up the right side of the Sum It Up window to display the second randomly generated operand.
         *
         * @param panel the JPanel where the second operand is displayed
         *
         * @return the randomly generated second operand
         */
        number2=setRabbitRight(panel);
        System.out.println(number1+" "+number2);



        // design the function of the button
        recurrence(myFrame,panel,northLabel);

        myFrame.add(panel);
        myFrame.setVisible(true);
    }

    /**

     * This method creates a user interface that allows the user to input two numbers and see their sum. Two JComboBoxes containing numbers 1-10 are set up, along with a JLabel that displays a “+” sign, a JTextField to display the sum, and a JButton to check the user’s answer. These components are added to a JPanel that is then added to a JFrame. The method takes three parameters, a JFrame called “myFrame”, a JPanel called “panel”, and a JLabel called “northLabel”.

     * @param myFrame a JFrame that is used to display the user interface

     * @param panel a JPanel that serves no purpose within the method and is therefore not used

     * @param northLabel a JLabel that serves no purpose within the method and is therefore not used
     */
    public void recurrence(JFrame myFrame,JPanel panel,JLabel northLabel)
    {
        System.out.println("go!"+" "+number1+" "+number2);
        // set two combo boxes for user inputs
        int j=0;
        String[] options = new String[10];
        for(j=1;j<=10;j++){
            options[j-1]= Integer.toString(j);
        }
        JComboBox<String> comboBox1 = new JComboBox<>(options);
        JLabel plusLabel = new JLabel("+");
        JComboBox<String> comboBox2 = new JComboBox<>(options);

        // set the boxes empty at the beginning
        comboBox1.setSelectedItem(null);
        comboBox2.setSelectedItem(null);

        JLabel equalsLabel = new JLabel("=");
        JTextField textField3 = new JTextField(10);
        JButton button = new JButton("Check!");

        // add components to the southern panel
        JPanel southPanel=new JPanel();
        southPanel.add(comboBox1);
        southPanel.add(plusLabel);
        southPanel.add(comboBox2);
        southPanel.add(equalsLabel);
        southPanel.add(textField3);
        southPanel.add(button);

        // add the southern panel to the frame
        myFrame.add(southPanel,BorderLayout.SOUTH);

        /**
         * This method adds an ActionListener to the “button” JButton that is created in the “recurrence” method. When the button is clicked, the ActionListener retrieves the user’s inputs from the JComboBoxes and JTextField, checks whether the sum of the inputs matches the correct answer, and updates the JLabel “northLabel” to indicate whether the user’s answer is correct or incorrect. If the user’s answer is correct, the method resets the user interface and generates a new addition problem for the user to solve.

         * @param myFrame a JFrame that is used to display the user interface

         * @param panel a JPanel that is used to display the rabbits and plus sign in the user interface

         * @param northLabel a JLabel that displays whether the user’s answer is correct or incorrect
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    String input1 = (String) comboBox1.getSelectedItem();
                    String input2 = (String) comboBox2.getSelectedItem();
                    String input3 = textField3.getText();
                    if((input1 != null||input1.length()!=0)&&(input2 != null||input2.length()!=0)&&(input3 != null||input3.length()!=0))
                    {
                        // determine whether the outcome of the summation equation is a number
                        isNumber(input3);

                        int num1=Integer.parseInt(input1);
                        int num2=Integer.parseInt(input2);
                        double num3=Double.parseDouble(input3);
                        if(num1==number1 && num2==number2 && num3==number1+number2){
                            northLabel.setText("Correct! Have another go?");
                            comboBox1.setSelectedItem(null);
                            comboBox2.setSelectedItem(null);
                            textField3.setText("");
                            refreshRabbits(panel);
                            setPlus(panel);
                            number1 = setRabbitLeft(panel);
                            number2 = setRabbitRight(panel);
                            System.out.println(number1+" "+number2);
                            recurrence(myFrame,panel,northLabel);

                        } else{
                            northLabel.setText("Wrong! Try again!");

                        }
                    }
                }

            }
        });

    }

    /**
     * Adds a plus sign image to the center of a specified JPanel using the BorderLayout.
     * @param panel the JPanel to add the plus sign image to
     */
    public void setPlus(JPanel panel){
        // create an icon for the image
        ImageIcon plusIcon =new ImageIcon("img/plus.png");
        JLabel iconLabel=new JLabel(plusIcon);
        // set the icon on the label
        panel.add(BorderLayout.CENTER,iconLabel);
    }
    /**
     * Sets an array of rabbits on a panel on the left side of the component.
     * @param panel the JPanel on which the rabbits will be set.
     * @return int the number of rabbits generated randomly.
     */
    public int setRabbitLeft(JPanel panel) {
        int x=0,y=0;
        // create a panel for rabbits on the left
        JPanel panelRabbit = new JPanel();
        panelRabbit.setPreferredSize(new Dimension(72*3,Integer.MAX_VALUE));
        panelRabbit.setLayout(null);
        // generate random number of rabbits
        Random ran = new Random();
        int value = ran.nextInt(10)+1;
        ImageIcon rabbit = new ImageIcon("img/rabbit.jpg");
        // create an array for the rabbits
        JLabel[] array = new JLabel[value];
        for(int i=0;i<value;i++)
        {
            array[i]= new JLabel(rabbit);
            x = 72*(i%3);
            y = 112*(i/3);
            array[i].setBounds(x,y,72,112);
            panelRabbit.add(array[i]);
        }

        panel.add(BorderLayout.WEST,panelRabbit);
        return value;
    }
    /**
     * Sets an array of rabbits on a panel on the right side of the component.
     * @param panel the JPanel on which the rabbits will be set.
     * @return int the number of rabbits generated randomly.
     */
    public int setRabbitRight(JPanel panel) {
            int x=0,y=0;
            // create a panel for rabbits on the right
            JPanel panelRabbit = new JPanel();
            panelRabbit.setPreferredSize(new Dimension(72*3,Integer.MAX_VALUE));
            panelRabbit.setLayout(null);
            Random ran = new Random();
            int value = ran.nextInt(10)+1;
            ImageIcon rabbit = new ImageIcon("img/rabbit.jpg");
            // create an array for the rabbits
            JLabel[] array = new JLabel[value];

            for(int i=0;i<value;i++)
            {
                array[i]= new JLabel(rabbit);
                x = 72*(i%3);
                y = 112*(i/3);
                array[i].setBounds(x,y,72,112);
                panelRabbit.add(array[i]);
            }

            panel.add(BorderLayout.EAST,panelRabbit);

            return value;
        }

        /**
     * Checks if a given string input is a valid number.
     * @param input the string to be checked for being a number
     * @return true if input is a valid number; false otherwise
     */
        public boolean isNumber(String input) {
            if(input==null||input.length()==0){
                return false;
            }
            try {
                Double.parseDouble(input);
                return true;
            }
            catch (NumberFormatException e){
                System.out.println("error");
                return false;
            }
        }
    /**
     * Removes all components from a JPanel and then refreshes the panel.
     * @param panel the JPanel to be refreshed.
     */
        public void refreshRabbits(JPanel panel){
        // remove all components on "panel"
        panel.removeAll();
        // refresh the page
        panel.revalidate();
        panel.updateUI();
    }
}
