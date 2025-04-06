import java.nio.file.Files;

public class Teacher {
    private Perceptron perceptron;
    private int rightDecision = 1;
    private int rightDecisionForAnotherLang = -1;
    private float[] inputs;
    DataWorker dataWorker = new DataWorker("..\\mpp3/src/Data/TrainingData");
    public Teacher(Perceptron perceptron) {
        this.perceptron = perceptron;
    }
    public void teach(){
        inputs = getInputs();
        if(dataWorker.getCurrentDirName().equals(perceptron.getLaguageName())) {
            while (Math.abs(rightDecision - perceptron.compute(getInputs())) > perceptron.getThreshould()) {
                perceptron.learn(rightDecision,inputs);
            }
        } else {
            while (Math.abs(rightDecisionForAnotherLang - perceptron.compute(getInputs())) > perceptron.getThreshould()) {
                perceptron.learn(rightDecisionForAnotherLang,inputs);
            }
        }
    }
    public float[] getInputs(){
        inputs = dataWorker.walkFilesInDirectories("..\\mpp3/src/Data/TrainingData");
        return inputs;
    }
}
