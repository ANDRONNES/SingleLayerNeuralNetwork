import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Layer {
    private int numberOfPerceptrons;
    Perceptron[] layer;

    public Layer() {
        this.numberOfPerceptrons = dirCount();
        createPerceptronLayer();

    }

    private void createPerceptronLayer() {
        this.layer = new Perceptron[numberOfPerceptrons];
        for (int i = 0; i < layer.length; i++) {
            layer[i] = new Perceptron();
        }
    }

    public void train(){
        for (int i = 0; i < layer.length; i++) {
            Teacher t = new Teacher(layer[i]);
            t.teach();
        }
    }

    public void trainigOrTestingResult(){
        Perceptron wonPerceptron = layer[0];
        float maxDecision = layer[0].getDecision();
        for (int i = 1; i < layer.length; i++) {
            if(layer[i].getDecision() > maxDecision) {
                maxDecision = layer[i].getDecision();
                wonPerceptron = layer[i];
            }
        }
    }

    private int dirCount() {
        int dirCount = 0;
        try {
            dirCount = (int) Files.list(Path.of("..\\mpp3/src/Data/TestingData")).filter(Files::isDirectory).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dirCount;
    }
}
