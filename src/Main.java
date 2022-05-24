import java.io.File;

public class Main {

    public static void main(String[] args) {

        String folderPart = "C:\\Users\\60034452\\Desktop";
        File file = new File(folderPart);
        System.out.println(getFolderSize(file));
    }

    public static long getFolderSize(File folder){
        // если файл сразу возвращаем длину
        if (folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files){
            // вызывает размер у корневой папки, потом у всех дочерних
            // и после ссумирует размер всех папок и файлов
            sum += getFolderSize(file);
        }
        return sum;
    }
}
