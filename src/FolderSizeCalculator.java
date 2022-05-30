import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long>
{
    private Node node;

    public FolderSizeCalculator(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {

        File folder = node.getFolder();
        if (folder.isFile()){
            return folder.length();
        }

        long sum = 0; // сразу задаем 0
        List<FolderSizeCalculator> subTasks = new LinkedList<>(); // создаем лист подзадач, связаным списком
        File[] files = folder.listFiles();

        for(File file : files) {

            Node child = new Node(file); // создаем ноду чайлд и ппередаем в нее ссылку на файл
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            task.fork(); // запустим асинхронно
            subTasks.add(task);
            node.addChild(child);
        }

        for(FolderSizeCalculator task : subTasks) {
            sum += task.join(); // дождёмся выполнения задачи и прибавим результат
        }
        node.setSize(sum);// доавляем сумму в ноду
        return sum;
    }
}
