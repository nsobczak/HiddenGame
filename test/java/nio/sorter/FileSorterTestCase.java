package nio.sorter;

import nio.sorter.FileSorter;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nicolas on 18/03/17.
 */
public class FileSorterTestCase
{

    //_______________________________________________________
    /* FONCTIONS DE TEST */
//    testPrepareDirectory();
//    testGetExtension();
//    testCopyFile();
//    testMoveFile();
//    testConstructor("");
//    testSortFiles("");


    @Test
    public void testPrepareDirectory() throws IOException
    {
        FileSorter sorter = new FileSorter();
        Path path = sorter.prepareDirectory("archive", Paths.get("test", "java", "nio", "sorter", "testDirectory"));
        assertThat(Files.isDirectory(path)).isTrue();
    }

    @Test
    public void testGetExtension() throws IOException
    {
        FileSorter sorter = new FileSorter();
        Path path = Paths.get("test", "java", "nio", "sorter", "testDirectory", "test.txt");
        assertThat(sorter.getExtension(path)).isEqualTo("txt");
    }

//    @Test
//    public void testCopyFile() throws IOException
//    {
//        //TODO
//        FileSorter sorter = new FileSorter();
//        sorter.copyFile(
//                Paths.get("test", "java", "nio", "sorter", "testDirectory", "test.txt"),
//                Paths.get("test", "java", "nio", "sorter", "test.txt")
//        );
//        assertThat(Files.isRegularFile(Paths.get("test", "java", "nio", "sorter", "test.txt"))).isTrue();
//    }

    @Test
    public void testMoveFile() throws IOException
    {
        FileSorter sorter = new FileSorter();
        Path archive = Paths.get("test", "java", "nio", "sorter", "testDirectory", "archive");
        sorter.setArchive(archive);

        Path targetPath = Paths.get("test", "java", "nio", "sorter", "testDirectory", "testMoveFile.txt");
        if (Files.notExists(targetPath)){
            Files.createFile(targetPath);
        }

        sorter.moveFileToArchive(Paths.get("test", "java", "nio", "sorter", "testDirectory", "testMoveFile.txt"));
    }

    @Test
    public void testConstructor() throws IOException
    {
        String pathToRoot = "test";
        FileSorter sorter = new FileSorter(pathToRoot);
        assertThat(sorter.getRoot()).isEqualTo(Paths.get(pathToRoot));
        assertThat(sorter.getArchive()).isEqualTo(Paths.get(pathToRoot, "archive"));
        assertThat(sorter.getByExtension()).isEqualTo(Paths.get(pathToRoot, "byext"));

    }
//
//    @Test
//    public void testSortFiles() throws IOException
//    {
//        //TODO: pathToRoot
//        String pathToRoot = "";
//        System.out.println("===test 1===");
//        FileSorter sorter1 = new FileSorter();
//        System.out.println(sorter1.sortFiles());
//        System.out.println("\n===test 2===");
//        FileSorter sorter2 = new FileSorter(pathToRoot);
//        System.out.println("\n\nnumber of sorted files : " + sorter2.sortFiles());
//    }


}
