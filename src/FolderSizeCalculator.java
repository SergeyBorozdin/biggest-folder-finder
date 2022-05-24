import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> // интерфейс помогает разветвлять потоки, которые потом можно собирать воедино
{
    private File folder;

    public FolderSizeCalculator(File folder) {
        this.folder = folder;
    }

    @Override
    protected Long compute() { // возвращает какой то тип данных, есть у каждого класса RecursiveTask. В данном случае это лонг

        if (folder.isFile()){ // проверка если файл сразу возвращаем размер
            return folder.length();
        }

        long sum = 0; // сразу задаем 0
        List<FolderSizeCalculator> subTasks = new LinkedList<>(); // создаем лист подзадач, связаным списком
        File[] files = folder.listFiles();

        for(File file : files) {
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); // запустим асинхронно
            subTasks.add(task);
        }

        for(FolderSizeCalculator task : subTasks) {
            sum += task.join(); // дождёмся выполнения задачи и прибавим результат
        }
        return sum;
    }
}
