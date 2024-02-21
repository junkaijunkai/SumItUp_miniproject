package ExtraCredit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the ExtraCredit section
 * @author KevZ.803
 */
public class SumItUpExtra extends Exception{

    private int number1=0;
    private int number2=0;
    private int Max;

    /**
     * The main method is the entry point of the program. It accepts a command argument and creates an object
     * of the SumItUpExtra class if the argument is either 20 or 25. Otherwise, it throws an IndexOutOfBoundsException.
     * @param args command argument
     */
    public static void main(String[] args) {
        try {
            int con = Integer.parseInt(args[0]);
            if(con==20||con==25)
            {
                SumItUpExtra window = new SumItUpExtra();
                window.createWindow(con);
            }else {
                throw new IndexOutOfBoundsException();

            }

        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("error");
        }
    }

    /**
     * The createWindow method creates the graphical user interface using Swing. The frame of the window is titled "Welcome to Sum It Up!"
     * and its dimensions are set to 800 x 650. The frame contains a panel that uses a BorderLayout manager.
     * createWindow also calls helper methods to design and set up the Northern, Central, and Side layout panels of the main panel,
     * as well as set up the button functionality.
     * @param con The argument passed to main method
     */
    public void createWindow(int con){
        Max=con;
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

        // design the central layout
        setPlus(panel);
        // design the side layouts

        number1=setRabbitLeft(panel);
        number2=setRabbitRight(panel);
        System.out.println(number1+" "+number2);



        // design the function of the button
        recurrence(myFrame,panel,northLabel);

        myFrame.add(panel);
        myFrame.setVisible(true);
    }

    /**
     * This method generates UI components for a simple math addition game and adds them to a JFrame.

     * It creates two JComboBoxes with numbers 1 to ‘Max’, sets them empty at the beginning, adds a JLabel ‘+’,

     * creates another JComboBox with the same range, and a JTextField to show the result.

     * Finally, it adds all these components to the southern panel of the JFrame and adds the panel to the

     * JFrame’s southern region using BorderLayout.

     * @param myFrame The JFrame where the UI components will be added

     * @param panel The JPanel where the UI components will be added

     * @param northLabel The JLabel with a welcome message at the top of the panel

     * @return void
     */
    public void recurrence(JFrame myFrame,JPanel panel,JLabel northLabel)
    {
        System.out.println("go!"+" "+number1+" "+number2);
        // set two combo boxes for user inputs
        Scanner sc = new Scanner(System.in);
        //j= sc.nextInt();
        String[] options = new String[Max];
        for(Max=1;Max<=options.length;Max++){
            options[Max-1]= Integer.toString(Max);
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
        panelRabbit.setPreferredSize(new Dimension(72*4,Integer.MAX_VALUE));
        panelRabbit.setLayout(null);
        // generate random number of rabbits
        Random ran = new Random();
        int value = ran.nextInt(Max)+1;
        ImageIcon rabbit = new ImageIcon("img/rabbit.jpg");
        // create an array for the rabbits
        JLabel[] array = new JLabel[value];
        for(int i=0;i<value;i++)
        {
            array[i]= new JLabel(rabbit);
            x = 72*(i%4);
            y = 112*(i/4);
            array[i].setBounds(x,y,72,112);
            panelRabbit.add(array[i]);
        }

        panel.add(BorderLayout.WEST,panelRabbit);
        return value;
    }
    /**
     * Sets an array of rabbits on a panel on the right side of the component.
     * @param panel the JPanel on which the rabbits will be set.
     */
    public int setRabbitRight(JPanel panel) {
        int x=0,y=0;
        // create a panel for rabbits on the right
        JPanel panelRabbit = new JPanel();
        panelRabbit.setPreferredSize(new Dimension(72*4,Integer.MAX_VALUE));
        panelRabbit.setLayout(null);
        Random ran = new Random();
        int value = ran.nextInt(Max)+1;
        ImageIcon rabbit = new ImageIcon("img/rabbit.jpg");
        // create an array for the rabbits
        JLabel[] array = new JLabel[value];

        for(int i=0;i<value;i++)
        {
            array[i]= new JLabel(rabbit);
            x = 72*(i%4);
            y = 112*(i/4);
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
