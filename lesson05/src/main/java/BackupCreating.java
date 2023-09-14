import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class BackupCreating {

    public static void main(String[] args) throws IOException {
        String sourceDirectory = "./";
        String backupDirectory = "./backup";

        createBackupDirectory(sourceDirectory, backupDirectory);
    }

    public static void createBackupDirectory(String sourceDirectory, String backupDirectory) throws IOException {
        // Создание папки
        File backup = new File(backupDirectory);
        if (!backup.exists()) {
            backup.mkdir();
        }

        // Получение списка файлов в директории
        File source = new File(sourceDirectory);
        File[] filesToBackup = source.listFiles();

        // Копирование
        for (File file : filesToBackup) {
            if (file.isFile()) {
                Files.copy(file.toPath(), new File(backup.getPath() + "/" + file.getName()).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}