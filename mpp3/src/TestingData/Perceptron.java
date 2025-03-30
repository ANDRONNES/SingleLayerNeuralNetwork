package TestingData;

public class Perceptron {
    private float[] weights;
    private float[] inputs;
    private float alpha = 0.01f;
    private float bias;

    public Perceptron() {
        
    }

    public int compute(int[] inputs) {
        float result = 0;
        for (int i = 0; i < inputs.length; i++) {
            result += inputs[i] * weights[i];
        }
        result += bias;
        if (result >= 0) return 1;
        else return 0;
    }

    public void learn(int decision, int rightDecision) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += (rightDecision - decision) * alpha * inputs[i];
        }
        bias += (rightDecision - decision) * alpha;
    }



}
