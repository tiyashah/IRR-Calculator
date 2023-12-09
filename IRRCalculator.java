import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * TIYA SHAH, Calculating IRR using computer science
 *
 * @author Tiya Shah
 * @version December 9, 2023
 */

public class IRRCalculator extends JFrame {
    private static final ArrayList<Integer> cashFlows = new ArrayList<>();
    private static int cost = 0;
    private final JTextField costInput;
    private final JTextField nInput;
    public JButton calculate = new JButton("Calculate IRR");

    public IRRCalculator() {
        setTitle("IRR Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(35, 10, 40, 10));


        JPanel homescreen = new JPanel(new GridLayout(1, 2));

        //DESCRIPTION
        JTextArea IRRdescription = new JTextArea(
                "Internal Rate of Return (IRR) is a measure used in " +
                        "\nfinance to estimate how profitable an investment is. It's" +
                        "\nthe rate at which the net value of cash flows from an " +
                        "\ninvestment becomes zero. If the IRR is higher than a " +
                        "\ncompany's cost of capital, the investment is usually" +
                        "\nconsidered good; if lower, it might not be as attractive." +
                        "\nIt helps in comparing different investment options" +
                        "\nto make better financial decisions.");
        IRRdescription.setPreferredSize(new Dimension(300, 130));
        Font font = new Font("Arial", Font.PLAIN, 13);
        IRRdescription.setFont(font);
        IRRdescription.setForeground(Color.white);
        IRRdescription.setBackground(Color.black);
        IRRdescription.setEditable(false);
        IRRdescription.setCaretPosition(0);
        homescreen.add(IRRdescription);

        //ENTER NUMBER OF CASH FLOWS AND DISCOUNT
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel("IRR Calculator - Tiya Shah");
        title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
        title.setForeground(Color.white);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 10;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.5;
        rightPanel.add(title, c);

        JPanel infoEntry = new JPanel(new GridLayout(2, 2, 0, 5));
        JLabel initialCost = new JLabel("Initial cost:");
        initialCost.setFont(new Font("Arial", Font.BOLD, 12));
        initialCost.setForeground(Color.white);
        costInput = new JTextField();
        JLabel cashFlowsn = new JLabel("Number of cash flows:");
        cashFlowsn.setFont(new Font("Arial", Font.BOLD, 12));
        cashFlowsn.setForeground(Color.white);
        nInput = new JTextField();

        infoEntry.add(initialCost);
        infoEntry.add(costInput);
        infoEntry.add(cashFlowsn);
        infoEntry.add(nInput);
        infoEntry.setOpaque(false);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 10;
        c.ipady = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.25;
        rightPanel.add(infoEntry, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 10;
        c.ipady = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.weighty = 0.25;
        rightPanel.add(calculate, c);
        rightPanel.setBackground(Color.black);

        homescreen.add(rightPanel);

        calculateIRR();

        panel.add(homescreen);
        panel.setBackground(Color.black);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        IRRCalculator testing = new IRRCalculator();
    }

    public void calculateIRR() {
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numCashFlows = Integer.parseInt(nInput.getText());
                cost = Integer.parseInt(costInput.getText());
                int cf = 0;
                for (int i = 1; i <= numCashFlows; i++) {
                    cf = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter cash flow " + i, "IRR Calculator",
                            JOptionPane.QUESTION_MESSAGE));
                    cashFlows.add(cf);
                }

                double irr = 0;
                double temp = 0;
                for (double i = 0; i < 1.0; i += 0.00000001) {
                    for (int x = 1; x <= cashFlows.size(); x++) {
                        temp += ((double) cashFlows.get(x - 1) / Math.pow(1.0 + i, x));
                    }

                    if (Math.abs(temp - cost) < 0.000001) {
                        irr = i;
                        break;
                    }
                    temp = 0.0;
                }
                String irrDisplayed = String.format("%.4f", irr * 100.0);
                JOptionPane.showMessageDialog(null, "The IRR of this investment is " + irrDisplayed + "%", "IRR Calculator", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Thank you for using Tiya's IRR Calculator", "IRR Calculator", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
    }
}
