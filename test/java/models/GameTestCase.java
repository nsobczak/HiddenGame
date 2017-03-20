package models;

import nio.sorter.FileSorter;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

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

    @Test
    public void shouldInitialiazeGameFiles() throws Exception
    {
        // WHEN
        game.initDecryptedFileList();
        File otherFile = new File(236, "backgroundRenderer.js", "code/", "BFhXOm4J30FkoWwRV1jNmw==",
                "/**\n" +
                        "\tRenders a background portion of the level.\n" +
                        "\tCode by Rob Kleffner, 2011\n" +
                        "*/\n" +
                        "\n" +
                        "Mario.BackgroundRenderer = function(level, width, height, distance) {\n" +
                        "    this.Level = level;\n" +
                        "    this.Width = width;\n" +
                        "    this.Distance = distance;\n" +
                        "    this.TilesY = ((height / 32) | 0) + 1;\n" +
                        "    \n" +
                        "    this.Background = Mario.SpriteCuts.GetBackgroundSheet();\n" +
                        "};\n" +
                        "\n" +
                        "Mario.BackgroundRenderer.prototype = new Enjine.Drawable();\n" +
                        "\n" +
                        "Mario.BackgroundRenderer.prototype.Draw = function(context, camera) {\n" +
                        "    var xCam = camera.X / this.Distance;\n" +
                        "    var x = 0, y = 0, b = null, frame = null;\n" +
                        "    \n" +
                        "    //the OR truncates the decimal, quicker than Math.floor\n" +
                        "    var xTileStart = (xCam / 32) | 0;\n" +
                        "    //the +1 makes sure the right edge tiles get drawn\n" +
                        "    var xTileEnd = (((xCam + this.Width) / 32) | 0);\n" +
                        "    \n" +
                        "    for (x = xTileStart; x <= xTileEnd; x++) {\n" +
                        "        for (y = 0; y < this.TilesY; y++) {\n" +
                        "            b = this.Level.GetBlock(x, y) & 0xff;\n" +
                        "            frame = this.Background[b % 8][(b / 8) | 0];\n" +
                        "            \n" +
                        "            //bitshifting by five is the same as multiplying by 32\n" +
                        "            context.drawImage(Enjine.Resources.Images[\"background\"], frame.X, frame.Y, frame.Width, frame.Height, ((x << 5) - xCam) | 0, (y << 5) | 0, frame.Width, frame.Height);\n" +
                        "        }\n" +
                        "    }\n" +
                        "};"
        );

        // THEN
        assertThat(game.getGameFiles()).hasSize(108);
        assertThat(game.getGameFiles().get(1).equals(otherFile)).isTrue();
        assertThat(game.getGameFiles().get(62).getParent().equals("")).isTrue();
    }

    @Test
    public void shouldCreateGameFile() throws Exception
    {
        //TODO
        // WHEN
        game.setFileSorter(new FileSorter("buildGameTest"));
        if (Files.notExists(game.fileSorter.getRoot())){
            Files.createDirectories(game.fileSorter.getRoot());
        }
        File testFileWithoutParent = new File(42, "testFileWithoutParent.txt", "", "iv", "I am a test file");
        File testFileWithParent = new File(666, "testFileWithParent.txt", "parentTest", "iv", "I am a test file");
        //THEN
        game.createGameFile(game.getFileSorter(), testFileWithoutParent);
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("testFileWithoutParent.txt")))).isTrue();
        game.createGameFile(game.getFileSorter(), testFileWithParent);
        assertThat(Files.exists(game.getFileSorter().getRoot().resolve(Paths.get("parentTest", "testFileWithParent.txt")))).isTrue();
    }


}
