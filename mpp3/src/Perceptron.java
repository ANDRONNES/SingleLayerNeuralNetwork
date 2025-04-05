public class Perceptron {
    private float[] weights;
    private float threshould = 0.01f;
    private float alpha = 0.01f;
    private float[] inputs;
    private float decision;

    public float[] getWeights() {
        return weights;
    }

    public float getDecision() {
        return decision;
    }

    public float getThreshould() {
        return threshould;
    }

    public Perceptron() {
        this.weights = randomArray();
    }

    public float compute(float[] inputs) {
        float result = 0;
        this.inputs = inputs;
        for (int i = 0; i < weights.length; i++) {
            result += inputs[i] * weights[i];
        }
        decision = result;
        return result;
        /*result += threshould;
        if (result >= 0) {
            return 1;
        } else
            return 0;*/
    }


    //wins perceptron with the biggest y(decision)
    public void learn(int rightDecision){
        for (int i = 0; i < weights.length; i++) {
            weights[i] += (rightDecision - decision) * inputs[i]  * alpha;
        }
    }

    /*while(Math.abs(rightDecision-decision)<threshould) do learning like this

    {
        public void learn ( int decision, int rightDecision){
        for (int i = 0; i < weights.length; i++) {
            weights[i] += (rightDecision - decision) * inputs[i] *//* * alpha*//*;
        }
//        threshould += (rightDecision - decision) * alpha;
        if (Math.abs(rightDecision - decision) < threshould) {  //In learnPerceptron method
            return;
        }
    }
    }*/

    public float[] randomArray() {
        float[] randomValuesArray = new float[26];
        for (int i = 0; i < randomValuesArray.length; i++) {
            randomValuesArray[i] = (float) (-1 + (Math.random() * 2));
        }
        return randomValuesArray;
    }
}