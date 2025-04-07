import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Teacher {
    private final Perceptron perceptron;
    DataWorker dataWorker = new DataWorker("..\\mpp3/src/Data/TrainingData");

    public Teacher(Perceptron perceptron) {
        this.perceptron = perceptron;
    }

    public void teach(int epoch) {
        for (int i = 0; i < epoch; i++) {
            for (Path folder : dataWorker.getPathesToLanguageFolders()) {

                String currentLang = folder.getFileName().toString();
                int expectedDecision = currentLang.equals(perceptron.getLaguageName()) ? 1 : -1;

                try {
                    Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            if (file.toString().endsWith(".txt")) {

                                float[] inputs = dataWorker.readAndModifyFileText(file);

                                while (Math.abs(expectedDecision - perceptron.compute(inputs)) > perceptron.getThreshould()) {
                                    perceptron.learn(expectedDecision);
                                }
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
