import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ScreenMoverAndArchiver {
    static Scanner scanner;
    static File currentDirectory = new File(".");


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        System.out.println("Input target directory");
        File targetDirectory = new File(scanner.nextLine() + "/Screenshots_" + date);
        moveAndArchive(targetDirectory);
        deleteDirectory(targetDirectory);
    }

    private static void moveAndArchive(File targetDirectory) {
        File[] filesInCurrentDirectory = currentDirectory.listFiles();
        if (!targetDirectory.exists()) targetDirectory.mkdir();
        for (File file : filesInCurrentDirectory) {
            if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".jpeg")) {
                file.renameTo(new File(targetDirectory.getAbsolutePath() + "/" + file.getName()));
                logName(file.getName());
                try {
                    archiveDirectory(targetDirectory);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void archiveDirectory(File targetDirectory) throws FileNotFoundException, IOException {
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(targetDirectory.getAbsolutePath() + ".zip"));
        File[] inputFiles = targetDirectory.listFiles();
        for (File inputFile : inputFiles) {
            try (FileInputStream fis = new FileInputStream(targetDirectory.getAbsolutePath() + "/" + inputFile.getName())) {
                ZipEntry entry = new ZipEntry(inputFile.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        }
        zout.close();
    }

    private static void deleteDirectory(File targetDirectory) {
        File[] filesToDelete = targetDirectory.listFiles();
        for (File fileToDelete: filesToDelete) fileToDelete.delete();
        targetDirectory.delete();
    }

    private static void logName(String name) {
        File logFile = new File(currentDirectory.getAbsolutePath() + "/screenshots.found");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.write(name + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
