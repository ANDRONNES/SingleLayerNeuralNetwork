import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        float[] inputs = readFile("..\\mpp3/src/Data/TrainingData/Polish/1.txt");
        Perceptron[] layer = new Perceptron[3];
        System.out.println(Arrays.toString(inputs));
        Perceptron p = new Perceptron();
        System.out.println(p.compute(inputs));


    }

    public static float[] readFile(String fileName) {
        Map<Character, Integer> map = new HashMap<>();
        int letterCount = 0;
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
             BufferedReader br = new BufferedReader(isr)) {
            String s;
            while ((s = br.readLine()) != null) {
                if (s.isEmpty()) {
                    continue;
                }
                s = s.toLowerCase().replaceAll("[^a-z]", "");
                char[] chars = s.toCharArray();
                letterCount += chars.length;
                for (Character c : chars) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
            float[] inputs = new float[26];
            for (char c = 'a'; c <= 'z'; c++) {
                inputs[c - 'a'] = (float) map.getOrDefault(c, 0) / letterCount;
            }
            System.out.println(map);
            return inputs;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
