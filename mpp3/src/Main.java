import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Layer perceptronLayer = new Layer();
        perceptronLayer.train();
        perceptronLayer.test("..\\mpp3/src/Data/TrainingData",true);
        perceptronLayer.test("..\\mpp3/src/Data/TestingData",false);
        enterTextFromConsole(perceptronLayer);

    }

    private static void enterTextFromConsole(Layer layer) {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        if(text.isEmpty()) return;
        layer.testTextFromConsole(text);
    }
}
