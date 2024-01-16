import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class CustomCalculator extends JFrame implements ActionListener {
    private static JFrame frame;
    private static JTextField textField;
    private String firstValue, operator, secondValue;

    CustomCalculator() {
        firstValue = operator = secondValue = "";
    }

    public static void main(String args[]) {
        frame = new JFrame("MIS 203 Final 22030451001");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        CustomCalculator calculator = new CustomCalculator();
        textField = new JTextField(16);
        textField.setEditable(false);
        JButton[] numberButtons = new JButton[10];
        JButton addButton = new JButton("+");
        addButton.setBackground(Color.black);
        addButton.setForeground(Color.white);
        JButton subtractButton = new JButton("-");
        subtractButton.setBackground(Color.black);
        subtractButton.setForeground(Color.white);
        JButton multiplyButton = new JButton("*");
        multiplyButton.setBackground(Color.black);
        multiplyButton.setForeground(Color.white);
        JButton divideButton = new JButton("/");
        divideButton.setBackground(Color.black);
        divideButton.setForeground(Color.white);
        JButton dotButton = new JButton(".");
        dotButton.setBackground(Color.black);
        dotButton.setForeground(Color.white);
        JButton clearButton = new JButton("C");
        clearButton.setBackground(Color.black);
        clearButton.setForeground(Color.white);
        JButton equalsButton = new JButton("=");
        equalsButton.setBackground(Color.black);
        equalsButton.setForeground(Color.white);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(calculator);
            numberButtons[i].setBackground(Color.black);
            numberButtons[i].setForeground(Color.white);
        }
        addButton.addActionListener(calculator);
        subtractButton.addActionListener(calculator);
        multiplyButton.addActionListener(calculator);
        divideButton.addActionListener(calculator);
        dotButton.addActionListener(calculator);
        clearButton.addActionListener(calculator);
        equalsButton.addActionListener(calculator);

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(addButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(subtractButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(multiplyButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divideButton);
        panel.add(dotButton);
        panel.add(numberButtons[0]);
        panel.add(clearButton);
        panel.add(equalsButton);
        panel.setBackground(Color.GRAY);
        frame.add(panel);
        frame.setSize(200, 220);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ((action.charAt(0) >= '0' && action.charAt(0) <= '9') || action.charAt(0) == '.') {
            if (!operator.equals("")) {
                secondValue += action;
                textField.setText(firstValue + operator + secondValue);
            } else {
                firstValue += action;
                textField.setText(firstValue + operator + secondValue);
            }
        } else if (action.charAt(0) == 'C') {
            firstValue = operator = secondValue = "";
            textField.setText(firstValue + operator + secondValue);
        } else if (action.charAt(0) == '=') {
            if (!secondValue.equals("")) {
                double result = performOperation();
                textField.setText(firstValue + operator + secondValue + "=" + result);
                firstValue = Double.toString(result);
                operator = secondValue = "";
            }
        } else {
            operator = action;
            textField.setText(firstValue + operator + secondValue);
        }
    }

    private double performOperation() {
        double result = 0;
        if (!secondValue.equals("")) {
            double num1 = Double.parseDouble(firstValue);
            double num2 = Double.parseDouble(secondValue);
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot divide by zero!");
                    }
                    break;
            }
        }
        return result;
    }
}
