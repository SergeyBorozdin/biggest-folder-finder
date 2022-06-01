import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final long SIZE_LIMIT = 50 * 1024 * 1024;

    public static void main(String[] args) {

        String folderPart = "C:\\Users\\60034452\\Desktop\\Data";

        File file = new File(folderPart);
        Node root = new Node(file, SIZE_LIMIT); // созадем обоект ноды

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator =
                new FolderSizeCalculator(root); // добавляем
        ForkJoinPool pool = new ForkJoinPool();// позволяет запускать множество разветвляющихся потоков
        pool.invoke(calculator);// invoke метод возвращает размер
        System.out.println(root);



        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

    }

}
