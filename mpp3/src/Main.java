import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Layer perceptronLayer = new Layer();
        perceptronLayer.train();
        perceptronLayer.test("..\\mpp3\\src\\Data\\TrainingData",true);
        perceptronLayer.test("..\\mpp3\\src\\Data\\TestingData",false);
        enterTextFromWindow(perceptronLayer);

    }

    private static void enterTextFromWindow(Layer layer) {
        Gui gui = new Gui();
        gui.run(layer);
    }
}
