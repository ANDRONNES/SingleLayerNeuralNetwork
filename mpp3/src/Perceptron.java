public class Perceptron {
    private float[] weights;
    private float threshould = 0.01f;
    private float alpha = 0.01f;
    private float[] inputs;
    private float decision;
    private String laguageName;

    public Perceptron() {
        this.weights = randomArray();
    }

    public String getLaguageName() {
        return laguageName;
    }

    public void setLaguageName(String laguageName) {
        this.laguageName = laguageName;
    }


    public float getDecision() {
        return decision;
    }

    public float getThreshould() {
        return threshould;
    }

    public float compute(float[] inputs) {
        float result = 0;
        this.inputs = inputs;
        for (int i = 0; i < weights.length; i++) {
            result += inputs[i] * weights[i];
        }
        this.decision = result;
        return result;
    }

    public void learn(int rightDecision){
        for (int i = 0; i < weights.length; i++) {
            weights[i] += alpha * (rightDecision - decision) * inputs[i];
        }
    }


    public float[] randomArray() {
        float[] randomValuesArray = new float[26];
        for (int i = 0; i < randomValuesArray.length; i++) {
            randomValuesArray[i] = (float) (-1 + (Math.random() * 2));
        }
        return randomValuesArray;
    }
}