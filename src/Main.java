import java.io.File;

public class Main {

    public static void main(String[] args) {

        String folderPart = "C:\\Users\\60034452\\Desktop";
        File file = new File(folderPart);
        long start = System.currentTimeMillis();
        System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }

    public static long getFolderSize(File folder){
        if (folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files){
            sum += getFolderSize(file);
        }
        return sum;
    }
}
