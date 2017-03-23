package application.nio.sorter;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Vincent Reynaert & Nicolas Sobczak on 18/03/17.
 */
public class FileSorterTestCase
{
    private FileSorter sorter;

    @Before
    public void initDirectory() throws Exception
    {
        sorter = new FileSorter();
        sorter.prepareDirectory("testDirectory", Paths.get("test", "java", "application", "nio", "sorter"));
    }

    @Test
    public void testPrepareDirectory() throws IOException
    {
        //WHEN
        Path path = sorter.prepareDirectory("game", Paths.get("test", "java", "application", "nio", "sorter", "testDirectory"));
        //THEN
        assertThat(Files.isDirectory(path)).isTrue();
    }

    @Test
    public void testGetExtension() throws IOException
    {
        //GIVEN
        Path path = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "test.txt");
        //THEN
        assertThat(sorter.getExtension(path)).isEqualTo("txt");
    }

    @Test
    public void testCopyFile() throws IOException
    {
        //GIVEN
        Path pathToOldFile = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "testCreateFile.txt");
        if (Files.notExists(pathToOldFile))
        {
            Files.createFile(pathToOldFile);
        }
        Path pathToNewFile = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game", "testCreateFile.txt");

        //WHEN
        sorter.copyFile(pathToOldFile, Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game"));
        //THEN
        assertThat(Files.isRegularFile(pathToNewFile)).isTrue();
        assertThat(Files.exists(pathToNewFile)).isTrue();

    }

    @Test
    public void testMoveFile() throws IOException
    {
        //GIVEN
        Path targetPath = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "testMoveFile.txt");
        if (Files.notExists(targetPath))
        {
            Files.createFile(targetPath);
        }
        //WHEN
        sorter.prepareDirectory("game", Paths.get("test", "java", "application", "nio", "sorter", "testDirectory"));
        //THEN
        sorter.moveFileToFolder(targetPath, Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game"));
        assertThat(Files.exists(Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game", "testMoveFile.txt")))
                .isTrue();
    }


}
