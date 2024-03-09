import javax.swing.*;
import java.awt.*;
import java.util.function.Function;
import java.lang.Math;
import java.util.Scanner;

public class LimitCalculator extends JFrame {

    public LimitCalculator() {
        setTitle("Limit Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel axesPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawAxes(g);
                graphFunction(g);  // Pass Graphics object to the graphFunction
            }
        };
        axesPanel.setBackground(Color.BLACK);
        axesPanel.setFocusable(true);

        mainPanel.add(axesPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void drawAxes(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.GREEN);

        int scaledOriginX = width / 2;
        int scaledOriginY = height / 2;

        g.drawLine(0, scaledOriginY, width, scaledOriginY);
        g.drawLine(scaledOriginX, 0, scaledOriginX, height);
    }

    private void graphFunction(Graphics g) {
        try {
            //Function type comes from import java.util.function.Function;
            //function will equal the evaluateExpression method which returns that actual function
            //so function will equal the actual function from evaluate expression method
            Function<Double, Double> function = x -> evaluateExpression(x);

            //plot function method just draws out the function above in red
            plotFunction(g, function);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error in function input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double evaluateExpression(double x) {
        //with the java.lang.Math you can use trig, log, radical, rational, exponential, etc
        // to have pie you just use Math.PI

        //trying to take function a user input
        /* 
        Scanner input = new Scanner(System.in);
        System.out.println("Input function here: ");

        double expression = input.nextDouble();

        input.close();

        return expression;
        */

        //for now just return 0.125 * x^2 + 5
        return 0.125 * (Math.pow(x, 2)) + 50;
    }

    private void plotFunction(Graphics g, Function<Double, Double> function) {
        int width = getWidth();
        int height = getHeight();
        double xMin = -width / 2.0;
        double xMax = width / 2.0;
        double xRange = xMax - xMin;
        double scale = width / xRange;

        g.setColor(Color.RED);

        double prevX = xMin;
        double prevY = function.apply(prevX);
        for (double x = xMin + 1; x <= xMax; x++) {
            double y = function.apply(x);

            int scaledX1 = (int) (prevX * scale + width / 2);
            int scaledY1 = (int) (-prevY * scale + height / 2);
            int scaledX2 = (int) (x * scale + width / 2);
            int scaledY2 = (int) (-y * scale + height / 2);

            g.drawLine(scaledX1, scaledY1, scaledX2, scaledY2);

            prevX = x;
            prevY = y;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LimitCalculator::new);
    }
}
