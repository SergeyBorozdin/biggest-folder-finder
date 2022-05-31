import java.util.HashMap;

public class SizeCalculator {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> char2multiplier = new HashMap<>();

    public static String getHumanReadableSize(long size)
    {
        for (int i = 0; i < sizeMultipliers.length; i++)
        {
            double value = size / Math.pow(1024.0, i);
            if (value < 1024) {
                return Math.round(value * 100)  / 100. + "" + sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Недопустимо большое значение";
    }

    // TODO 24B, 234Kb, 36Mb, 34Gb, 42Tb
    public static long getSizeFromHumanReadable(String size)
    {
        HashMap<Character, Integer> char2multiplier = getMutipliers();
        char sizeFactor = size.replaceAll("[0-9\\s+]", "").charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));
        return length;
    }

    private static HashMap<Character, Integer> getMutipliers()
    {
        for (int i = 0; i < sizeMultipliers.length; i++ ){
            char2multiplier.put(sizeMultipliers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }
}
//    public static long getFolderSize(File folder){
//        if (folder.isFile()){
//            return folder.length();
//        }
//        long sum = 0;
//        File[] files = folder.listFiles();
//        for (File file : files){
//            sum += getFolderSize(file);
//        }
//        return sum;
//    }

