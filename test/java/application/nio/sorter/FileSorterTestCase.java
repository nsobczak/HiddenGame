package application.nio.sorter;

import org.junit.Before;
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
        Path path = sorter.prepareDirectory("game", Paths.get("test", "java", "application", "nio", "sorter", "testDirectory"));
        assertThat(Files.isDirectory(path)).isTrue();
    }

    @Test
    public void testGetExtension() throws IOException
    {
        Path path = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "test.txt");
        assertThat(sorter.getExtension(path)).isEqualTo("txt");
    }

    @Test
    public void testCopyFile() throws IOException
    {
        //WHEN
        Path pathToOldFile = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "testCreateFile.txt");
        if (Files.notExists(pathToOldFile))
        {
            Files.createFile(pathToOldFile);
        }
        //THEN
        sorter.copyFile(pathToOldFile, Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game"));
        Path pathToNewFile = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game", "testCreateFile.txt");
        assertThat(Files.isRegularFile(pathToNewFile)).isTrue();
        assertThat(Files.exists(pathToNewFile)).isTrue();

    }

    @Test
    public void testMoveFile() throws IOException
    {
        //WHEN
        sorter.prepareDirectory("game", Paths.get("test", "java", "application", "nio", "sorter", "testDirectory"));
        Path targetPath = Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "testMoveFile.txt");
        if (Files.notExists(targetPath))
        {
            Files.createFile(targetPath);
        }
        //THEN
        sorter.moveFileToFolder(targetPath, Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game"));
        assertThat(Files.exists(Paths.get("test", "java", "application", "nio", "sorter", "testDirectory", "game", "testMoveFile.txt")))
                .isTrue();
    }


}
