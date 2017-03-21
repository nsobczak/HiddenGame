package application.models;

import application.nio.sorter.FileSorter;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by root on 19/03/17.
 */
public class GameTestCase
{
    private Game game;

    @Before
    public void initFile() throws Exception
    {
        game = new Game();
    }

//    @Test
//    public void shouldWriteDatabasePropertiesFile() throws Exception
//    {
//        //WHEN
//        String otherContent = "db.server=host" +
//                "\ndb.port=" + -1 +
//                "\ndb.schema=schema" +
//                "\ndb.user=user" +
//                "\ndb.password=password";
//        //THEN
//        game.writeDatabaseProperties(
//                "host", -1, "schema", "user", "password");
//        String fileContent = new String(Files.readAllBytes(Paths.get("src", "main", "ressources", "db.properties")));
//        assertThat(fileContent).isEqualTo(otherContent);
//    }

    @Test
    public void shouldInitialiazeGameFiles() throws Exception
    {
        // WHEN
        game.writeDatabaseProperties(
                "Localhost", 3306,
                "db_hidden_game", "root",
                "ISEN");
        game.initDecryptedFileList();
        System.out.println(game.getGameFiles().get(1).toString());
        File otherFile = new File(236, "backgroundRenderer.js", "code/", game.getGameFiles().get(1).getIv(), "",
                new byte[]{47, 42, 42, 10, 9, 82, 101, 110, 100, 101, 114, 115, 32, 97, 32, 98, 97, 99, 107, 103, 114, 111, 117, 110, 100, 32, 112, 111, 114, 116, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 108, 101, 118, 101, 108, 46, 10, 9, 67, 111, 100, 101, 32, 98, 121, 32, 82, 111, 98, 32, 75, 108, 101, 102, 102, 110, 101, 114, 44, 32, 50, 48, 49, 49, 10, 42, 47, 10, 10, 77, 97, 114, 105, 111, 46, 66, 97, 99, 107, 103, 114, 111, 117, 110, 100, 82, 101, 110, 100, 101, 114, 101, 114, 32, 61, 32, 102, 117, 110, 99, 116, 105, 111, 110, 40, 108, 101, 118, 101, 108, 44, 32, 119, 105, 100, 116, 104, 44, 32, 104, 101, 105, 103, 104, 116, 44, 32, 100, 105, 115, 116, 97, 110, 99, 101, 41, 32, 123, 10, 32, 32, 32, 32, 116, 104, 105, 115, 46, 76, 101, 118, 101, 108, 32, 61, 32, 108, 101, 118, 101, 108, 59, 10, 32, 32, 32, 32, 116, 104, 105, 115, 46, 87, 105, 100, 116, 104, 32, 61, 32, 119, 105, 100, 116, 104, 59, 10, 32, 32, 32, 32, 116, 104, 105, 115, 46, 68, 105, 115, 116, 97, 110, 99, 101, 32, 61, 32, 100, 105, 115, 116, 97, 110, 99, 101, 59, 10, 32, 32, 32, 32, 116, 104, 105, 115, 46, 84, 105, 108, 101, 115, 89, 32, 61, 32, 40, 40, 104, 101, 105, 103, 104, 116, 32, 47, 32, 51, 50, 41, 32, 124, 32, 48, 41, 32, 43, 32, 49, 59, 10, 32, 32, 32, 32, 10, 32, 32, 32, 32, 116, 104, 105, 115, 46, 66, 97, 99, 107, 103, 114, 111, 117, 110, 100, 32, 61, 32, 77, 97, 114, 105, 111, 46, 83, 112, 114, 105, 116, 101, 67, 117, 116, 115, 46, 71, 101, 116, 66, 97, 99, 107, 103, 114, 111, 117, 110, 100, 83, 104, 101, 101, 116, 40, 41, 59, 10, 125, 59, 10, 10, 77, 97, 114, 105, 111, 46, 66, 97, 99, 107, 103, 114, 111, 117, 110, 100, 82, 101, 110, 100, 101, 114, 101, 114, 46, 112, 114, 111, 116, 111, 116, 121, 112, 101, 32, 61, 32, 110, 101, 119, 32, 69, 110, 106, 105, 110, 101, 46, 68, 114, 97, 119, 97, 98, 108, 101, 40, 41, 59, 10, 10, 77, 97, 114, 105, 111, 46, 66, 97, 99, 107, 103, 114, 111, 117, 110, 100, 82, 101, 110, 100, 101, 114, 101, 114, 46, 112, 114, 111, 116, 111, 116, 121, 112, 101, 46, 68, 114, 97, 119, 32, 61, 32, 102, 117, 110, 99, 116, 105, 111, 110, 40, 99, 111, 110, 116, 101, 120, 116, 44, 32, 99, 97, 109, 101, 114, 97, 41, 32, 123, 10, 32, 32, 32, 32, 118, 97, 114, 32, 120, 67, 97, 109, 32, 61, 32, 99, 97, 109, 101, 114, 97, 46, 88, 32, 47, 32, 116, 104, 105, 115, 46, 68, 105, 115, 116, 97, 110, 99, 101, 59, 10, 32, 32, 32, 32, 118, 97, 114, 32, 120, 32, 61, 32, 48, 44, 32, 121, 32, 61, 32, 48, 44, 32, 98, 32, 61, 32, 110, 117, 108, 108, 44, 32, 102, 114, 97, 109, 101, 32, 61, 32, 110, 117, 108, 108, 59, 10, 32, 32, 32, 32, 10, 32, 32, 32, 32, 47, 47, 116, 104, 101, 32, 79, 82, 32, 116, 114, 117, 110, 99, 97, 116, 101, 115, 32, 116, 104, 101, 32, 100, 101, 99, 105, 109, 97, 108, 44, 32, 113, 117, 105, 99, 107, 101, 114, 32, 116, 104, 97, 110, 32, 77, 97, 116, 104, 46, 102, 108, 111, 111, 114, 10, 32, 32, 32, 32, 118, 97, 114, 32, 120, 84, 105, 108, 101, 83, 116, 97, 114, 116, 32, 61, 32, 40, 120, 67, 97, 109, 32, 47, 32, 51, 50, 41, 32, 124, 32, 48, 59, 10, 32, 32, 32, 32, 47, 47, 116, 104, 101, 32, 43, 49, 32, 109, 97, 107, 101, 115, 32, 115, 117, 114, 101, 32, 116, 104, 101, 32, 114, 105, 103, 104, 116, 32, 101, 100, 103, 101, 32, 116, 105, 108, 101, 115, 32, 103, 101, 116, 32, 100, 114, 97, 119, 110, 10, 32, 32, 32, 32, 118, 97, 114, 32, 120, 84, 105, 108, 101, 69, 110, 100, 32, 61, 32, 40, 40, 40, 120, 67, 97, 109, 32, 43, 32, 116, 104, 105, 115, 46, 87, 105, 100, 116, 104, 41, 32, 47, 32, 51, 50, 41, 32, 124, 32, 48, 41, 59, 10, 32, 32, 32, 32, 10, 32, 32, 32, 32, 102, 111, 114, 32, 40, 120, 32, 61, 32, 120, 84, 105, 108, 101, 83, 116, 97, 114, 116, 59, 32, 120, 32, 60, 61, 32, 120, 84, 105, 108, 101, 69, 110, 100, 59, 32, 120, 43, 43, 41, 32, 123, 10, 32, 32, 32, 32, 32, 32, 32, 32, 102, 111, 114, 32, 40, 121, 32, 61, 32, 48, 59, 32, 121, 32, 60, 32, 116, 104, 105, 115, 46, 84, 105, 108, 101, 115, 89, 59, 32, 121, 43, 43, 41, 32, 123, 10, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 98, 32, 61, 32, 116, 104, 105, 115, 46, 76, 101, 118, 101, 108, 46, 71, 101, 116, 66, 108, 111, 99, 107, 40, 120, 44, 32, 121, 41, 32, 38, 32, 48, 120, 102, 102, 59, 10, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 102, 114, 97, 109, 101, 32, 61, 32, 116, 104, 105, 115, 46, 66, 97, 99, 107, 103, 114, 111, 117, 110, 100, 91, 98, 32, 37, 32, 56, 93, 91, 40, 98, 32, 47, 32, 56, 41, 32, 124, 32, 48, 93, 59, 10, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 10, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 47, 47, 98, 105, 116, 115, 104, 105, 102, 116, 105, 110, 103, 32, 98, 121, 32, 102, 105, 118, 101, 32, 105, 115, 32, 116, 104, 101, 32, 115, 97, 109, 101, 32, 97, 115, 32, 109, 117, 108, 116, 105, 112, 108, 121, 105, 110, 103, 32, 98, 121, 32, 51, 50, 10, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 99, 111, 110, 116, 101, 120, 116, 46, 100, 114, 97, 119, 73, 109, 97, 103, 101, 40, 69, 110, 106, 105, 110, 101, 46, 82, 101, 115, 111, 117, 114, 99, 101, 115, 46, 73, 109, 97, 103, 101, 115, 91, 34, 98, 97, 99, 107, 103, 114, 111, 117, 110, 100, 34, 93, 44, 32, 102, 114, 97, 109, 101, 46, 88, 44, 32, 102, 114, 97, 109, 101, 46, 89, 44, 32, 102, 114, 97, 109, 101, 46, 87, 105, 100, 116, 104, 44, 32, 102, 114, 97, 109, 101, 46, 72, 101, 105, 103, 104, 116, 44, 32, 40, 40, 120, 32, 60, 60, 32, 53, 41, 32, 45, 32, 120, 67, 97, 109, 41, 32, 124, 32, 48, 44, 32, 40, 121, 32, 60, 60, 32, 53, 41, 32, 124, 32, 48, 44, 32, 102, 114, 97, 109, 101, 46, 87, 105, 100, 116, 104, 44, 32, 102, 114, 97, 109, 101, 46, 72, 101, 105, 103, 104, 116, 41, 59, 10, 32, 32, 32, 32, 32, 32, 32, 32, 125, 10, 32, 32, 32, 32, 125, 10, 125, 59}
        );

        // THEN

        assertThat(game.getGameFiles()).hasSize(108);
        assertThat(game.getGameFiles().get(1).equals(otherFile)).isTrue();
        assertThat(game.getGameFiles().get(62).getParent().equals("")).isTrue();
    }

    @Test
    public void shouldCreateGameFile() throws Exception
    {
        // WHEN
        game.setFileSorter(new FileSorter(Paths.get("test", "java", "models", "buildGameCreateFileTest").toString()));
        if (Files.notExists(game.fileSorter.getRoot()))
        {
            Files.createDirectories(game.fileSorter.getRoot());
        }
        String content = new String("I am a test file");
        File testFileWithoutParent = new File(42, "testFileWithoutParent.txt", "", "iv", content.getBytes());
        File testFileWithParent = new File(666, "testFileWithParent.txt", "parentTest", "iv", content.getBytes());
        //THEN
        game.createGameFile(game.getFileSorter(), testFileWithoutParent);
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("testFileWithoutParent.txt")))).isTrue();
        game.createGameFile(game.getFileSorter(), testFileWithParent);
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("parentTest", "testFileWithParent.txt")))).isTrue();
    }


    @Test
    public void shouldBuildGameFromFileList() throws Exception
    {
        // WHEN
        game.setFileSorter(new FileSorter(Paths.get("test", "java", "models", "buildGameTest").toString()));
        if (Files.notExists(game.fileSorter.getRoot()))
        {
            Files.createDirectories(game.fileSorter.getRoot());
        }
        String content_1 = new String("I am a test file");
        String content_2 = new String("# testFile3\n" + content_1);
        File testFileWithoutParent = new File(42, "testFileWithoutParent.txt", "", "iv", content_1.getBytes());
        File testFileWithParent = new File(666, "testFileWithParent.txt", "parentTest", "iv", content_1.getBytes());
        File testFile3 = new File(3, "testFile3.md", "", "iv", content_2.getBytes());
        //THEN
        game.setGameFiles(Arrays.asList(testFileWithoutParent, testFileWithParent, testFile3));
        game.buildGameFromFileList();
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("testFileWithoutParent.txt")))).isTrue();
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("parentTest", "testFileWithParent.txt")))).isTrue();
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("testFile3.md")))).isTrue();
    }


}
