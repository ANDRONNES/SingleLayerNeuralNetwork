import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Layer {
    private int numberOfPerceptrons;
    Perceptron[] layer;
    List<String> dirNames = new ArrayList<>();
    DataWorker dw = new DataWorker();

    public Layer() {
        this.numberOfPerceptrons = dirCount();
        dirNames = dw.getDirNames("..\\mpp3\\src\\Data\\TrainingData");
        createPerceptronLayer();
    }

    private int dirCount() {
        int dirCount = 0;
        try {
            dirCount = (int) Files.list(Path.of("..\\mpp3\\src\\Data\\TrainingData")).filter(Files::isDirectory).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dirCount;
    } //Take num of dirs,then it helps to create needed count of perceptrons

    private void createPerceptronLayer() {
        this.layer = new Perceptron[numberOfPerceptrons];
        for (int i = 0; i < layer.length; i++) {
            layer[i] = new Perceptron();
            layer[i].setLaguageName(dirNames.get(i));
        }
    } //Creating perceptron layer

    public void train() { //training on train data
        for (int i = 0; i < layer.length; i++) {
            Teacher t = new Teacher(layer[i]);
            t.teach(20);
        }
    }

    public Perceptron trainigOrTestingResult() {
        Perceptron wonPerceptron = layer[0];
        float maxDecision = layer[0].getDecision();

        for (int i = 1; i < layer.length; i++) {
            if (layer[i].getDecision() > maxDecision) {
                maxDecision = layer[i].getDecision();
                wonPerceptron = layer[i];
            }
        }
        return wonPerceptron;
    }

    public String testTextFromConsole(String text) {
        float[] inputs = dw.getInputsFromConsoleText(text);
        for (int i = 0; i < layer.length; i++) {
            layer[i].compute(inputs);
        }
        Perceptron wonPerceptron = trainigOrTestingResult();
//        System.out.println();
        return "Your language is " + wonPerceptron.getLaguageName();
    }


    public void test(String path, boolean isOnTrainingData) {
        DataWorker currentDir = new DataWorker(path);
        AtomicInteger correctAnswers = new AtomicInteger(0);
        AtomicInteger totalFiles = new AtomicInteger(0);

        for (Path folder : currentDir.getPathesToLanguageFolders()) {
            String folderLanguage = folder.getFileName().toString();

            try {
                Files.walkFileTree(folder, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        if (file.toString().endsWith(".txt")) {

                            float[] inputs = currentDir.readAndModifyFileText(file);

                            for (Perceptron p : layer) {
                                p.compute(inputs);
                            }

                            Perceptron wonPerceptron = trainigOrTestingResult();
                            String predictedLang = wonPerceptron.getLaguageName();

                            if (isOnTrainingData) {
                                if (predictedLang.equals(folderLanguage)) {
                                    correctAnswers.incrementAndGet();
                                }
                                totalFiles.incrementAndGet();
                            }
                            String whiteSign = file.getFileName().toString().length() == 5 ? "  ": " ";
                            System.out.println("File:" +whiteSign + file.getFileName() + ", FolderLanguage: " + folderLanguage +
                                    ",  PredictedLanguage: " + predictedLang);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (isOnTrainingData) {
            float accuracy = (float) (correctAnswers.get() / totalFiles.get()) * 100;
            System.out.println("Accuracy of Network: " + accuracy + "%");
        }
    }

}
