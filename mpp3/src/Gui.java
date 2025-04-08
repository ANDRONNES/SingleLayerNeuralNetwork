import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Gui {
    public void run(Layer layer){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Language classifier");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JTextArea inputArea = new JTextArea(5, 30);
            inputArea.setLineWrap(true);
            inputArea.setWrapStyleWord(true);
            JButton classifyButton = new JButton("Classify language");
            JLabel resultLabel = new JLabel("Result: ");

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(inputArea, BorderLayout.CENTER);
            panel.add(classifyButton, BorderLayout.SOUTH);
            panel.add(resultLabel, BorderLayout.NORTH);

            frame.add(panel);
            frame.setVisible(true);

            classifyButton.addActionListener((ActionEvent e) -> {

                String text = inputArea.getText().toLowerCase();
                if(text.isEmpty()) return;
                String result = layer.testTextFromConsole(text);
                resultLabel.setText(result);

            });
        });
    }
}
