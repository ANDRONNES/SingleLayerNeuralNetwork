import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataWorker {
    private List<Path> PathesTolanguageFolders;

    public DataWorker(String path) {
        try {
            PathesTolanguageFolders = Files.list(Path.of(path))
                    .filter(Files::isDirectory)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataWorker(){}

    public float[] readAndModifyFileText(Path fileName) {
        Map<Character, Integer> map = new HashMap<>();
        int letterCount = 0;
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(String.valueOf(fileName)));
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
            return inputs;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float[] getInputsFromConsoleText(String s){
        float [] result = new float[26];
        Map<Character, Integer> map = new HashMap<>();
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        char[] chars = s.toCharArray();
        int letterCount = chars.length;
        for (Character c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for(char c = 'a'; c <= 'z';c++){
            result[c-'a'] = (float) map.getOrDefault(c,0) / letterCount;
        }
        return result;
    }

    public List<String> getDirNames(String path){
        File mainDir = new File(path);
        List<String>dirNames = new ArrayList<>();
        if (mainDir.exists() && mainDir.isDirectory()) {
            File[] subDirs = mainDir.listFiles(File::isDirectory);
            if (subDirs != null) {
                for (File dir : subDirs) {
                    dirNames.add(dir.getName());
                }
            }
        } else {
            System.out.println("Directories not found");
        }
        return dirNames;
    }

    public List<Path> getPathesToLanguageFolders() {
        return PathesTolanguageFolders;
    }
}
