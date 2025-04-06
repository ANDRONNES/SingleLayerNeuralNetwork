import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataWorker {
    float[] inputs = new float[26];
    List<Path> languageFolders;
    private String currentDirName;

    public String getCurrentDirName() {
        return currentDirName;
    }

    public DataWorker(String path) {
        try {
            languageFolders = Files.list(Path.of(path))
                    .filter(Files::isDirectory)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public DataWorker(){}

    public float[] walkFilesInDirectories(String p) {
        for (int i = 0; i < languageFolders.size(); i++) {
            currentDirName = languageFolders.get(i).getFileName().toString();
            Path languageFolder = languageFolders.get(i);
            Path path = Path.of(p);

            try {
                Files.walkFileTree(languageFolder, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        if (file.toString().endsWith(".txt")) {
                            inputs = readAndModifyFileText(file);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return inputs;
        }
        return inputs;
    }

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
            System.out.println(map);
            return inputs;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
