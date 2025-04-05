import java.nio.file.Files;

public class Teacher {
    private Perceptron perceptron;
    private int rightDecision = 1;
    public Teacher(Perceptron perceptron) {
        this.perceptron = perceptron;
    }
    public void teach(){
        while(Math.abs(rightDecision - perceptron.compute(getInputs()))> perceptron.getThreshould()){
            perceptron.learn(rightDecision);
        }
    }
    public float[] getInputs(){
        DataWorker dataWorker = new DataWorker("..\\mpp3/src/Data/TrainingData");
        
    }
}
