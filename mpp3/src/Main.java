import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        float[] inputs = readFile("..\\mpp3/src/Data/TrainingData/Polish/1.txt");
//        Perceptron[] layer = new Perceptron[3];
//        System.out.println(Arrays.toString(inputs));
//        Perceptron p = new Perceptron();
//        System.out.println(p.compute(inputs));
//        Layer l = new Layer();
//        for (int i = 0; i < layer.length; i++) {
//            for (int j = 0; j < 10; j++) {
//
//            }
//        }

    }

    public static void learn(int rightDecision,Perceptron p){
        while(Math.abs(rightDecision - p.getDecision())> p.getThreshould()){
            p.learn(rightDecision);
        }
    }


}
