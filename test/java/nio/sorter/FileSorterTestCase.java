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
        Path path = sorter.prepareDirectory("testDirectory", Paths.get("test", "java", "nio", "sorter"));
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
//                Paths.get("src", "isen", "java22017", "practical2", "animal", "adelie-penguin.html"),
//                Paths.get("src", "isen", "java22017", "practical2")
//        );
//    }

//    @Test
//    public void testMoveFile() throws IOException
//    {
//        //TODO
//        FileSorter sorter = new FileSorter();
//        Path archive = Paths.get("src", "isen", "java22017", "practical2", "archive");
////        System.out.println(archive);
//        sorter.setArchive(archive);
////        System.out.println(sorter.getArchive());
//
//        sorter.moveFileToArchive(
//                Paths.get("src", "isen", "java22017", "practical2", "animal", "adelie-penguin.html")
//        );
//    }

//    @Test
//    public void testConstructor() throws IOException
//    {
//        //TODO: pathToRoot
//        String pathToRoot = "";
//        FileSorter sorter = new FileSorter(pathToRoot);
//        System.out.println("getArchive : " + sorter.getArchive());
//        System.out.println("getRoot : " + sorter.getRoot());
//        System.out.println("getByExtension : " + sorter.getByExtension());
//
//    }
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