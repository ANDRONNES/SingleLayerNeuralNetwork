import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        float[] inputs = readFile("..\\mpp3/src/TrainingData/Polish/1.txt");
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
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) < 'A' || s.charAt(i) > 'Z' &&
                            s.charAt(i) < 'a' || s.charAt(i) > 'z') {
                        s = s.substring(0, i) + s.substring(i + 1);
                        i--;
                    }
                }
                s = s.replaceAll(" ", "");
                s = s.toLowerCase();
                char[] chars = s.toCharArray();
                for (Character c : chars) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                letterCount += entry.getValue();
            }

            float[] inputs = new float[26];
            for (int i = 0; i < inputs.length; i++) {
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    inputs[i] = (float) entry.getValue() / letterCount;
                    i++;
                }

                break;
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
