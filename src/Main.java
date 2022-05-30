import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    public static void main(String[] args) {

//        System.out.println(getHumanReadableSize(240640));
//        System.exit(0);
//        System.out.println(getSizeFromHumanReadable("235K"));
//        System.exit(0);
//
//


        String folderPart = "C:\\Users\\60034452\\Desktop";
        File file = new File(folderPart);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator =
                new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();// позволяет запускать множество разветвляющихся потоков
        long size = pool.invoke(calculator);// invoke метод возвращает размер
        System.out.println(size);

        //System.out.println(getFolderSize(file));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

    }

    // TODO 24B, 234Kb, 36Mb, 34Gb, 42Tb

    public static String getHumanReadableSize(long size){

        for (int i = 0; i < sizeMultipliers.length; i++) {

            double value = size / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value) + "" + sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Недопустимое значение";
    }

    // TODO 24B, 234Kb, 36Mb, 34Gb, 42Tb
    public static long getSizeFromHumanReadable(String size){

        HashMap<Character, Integer> char2multiplier = getMutipliers();
        char sizeFactor = size.replaceAll("[0-9\\s+]", "").charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));
        return length;
    }

    private static HashMap<Character, Integer> getMutipliers(){

        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++ ){
            char2multiplier.put(sizeMultipliers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
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
