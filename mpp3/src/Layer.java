import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Layer {
    private int numberOfPerceptrons;
    Perceptron[] layer;
    List<String> dirNames = new ArrayList<>();
    DataWorker dw = new DataWorker();

    public Layer() {
        this.numberOfPerceptrons = dirCount();
        dirNames = dw.getDirNames("..\\mpp3/src/Data/TrainingData");
        createPerceptronLayer();
    }

    private int dirCount() {
        int dirCount = 0;
        try {
            dirCount = (int) Files.list(Path.of("..\\mpp3/src/Data/TestingData"))
                    .filter(Files::isDirectory)
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dirCount;
    }

    private void createPerceptronLayer() {
        this.layer = new Perceptron[numberOfPerceptrons];
        for (int i = 0; i < layer.length; i++) {
            layer[i] = new Perceptron();
            layer[i].setLaguageName(dirNames.get(i));
        }
    }

    public void train() { //training on train data
        for (int i = 0; i < layer.length; i++) {
            Teacher t = new Teacher(layer[i]);
            t.teach();
        }
        for (int i = 0; i < layer.length; i++) {
            System.out.println(layer[i].getLaguageName());
            System.out.println(layer[i].getWeights());
        }
    }

    public void test(){ //testing on testing data

    }

    public void trainigOrTestingResult() {
        Perceptron wonPerceptron = layer[0];
        float maxDecision = layer[0].getDecision();
        for (int i = 1; i < layer.length; i++) {
            if (layer[i].getDecision() > maxDecision) {
                maxDecision = layer[i].getDecision();
                wonPerceptron = layer[i];
            }
        }
    }
}
