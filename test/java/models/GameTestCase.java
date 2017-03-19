package models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        //TODO
        // WHEN
        game.initDecryptedFileList();

        // THEN
        assertThat(game.getGameFiles()).hasSize(108);

        System.out.println(game.getGameFiles().get(0).toString());
        assertThat(game.getGameFiles().get(0).equals(
                new File(235, "backgroundGenerator.js", "code/", "BFhXOm4J30FkoWwRV1jNmw==",
                        "/**\n" +
                                "\tGenerates the backgrounds for a level.\n" +
                                "\tCode by Rob Kleffner, 2011\n" +
                                "*/\n" +
                                "\n" +
                                "Mario.BackgroundGenerator = function(width, height, distant, type) {\n" +
                                "    this.Width = width;\n" +
                                "    this.Height = height;\n" +
                                "    this.Distant = distant;\n" +
                                "    this.Type = type;\n" +
                                "};\n" +
                                "\n" +
                                "Mario.BackgroundGenerator.prototype = {\n" +
                                "    SetValues: function(width, height, distant, type) {\n" +
                                "        this.Width = width;\n" +
                                "        this.Height = height;\n" +
                                "        this.Distant = distant;\n" +
                                "        this.Type = type;\n" +
                                "    },\n" +
                                "\n" +
                                "    CreateLevel: function() {\n" +
                                "        var level = new Mario.Level(this.Width, this.Height);\n" +
                                "        switch (this.Type) {\n" +
                                "            case Mario.LevelType.Overground:\n" +
                                "                this.GenerateOverground(level);\n" +
                                "                break;\n" +
                                "            case Mario.LevelType.Underground:\n" +
                                "                this.GenerateUnderground(level);\n" +
                                "                break;\n" +
                                "            case Mario.LevelType.Castle:\n" +
                                "                this.GenerateCastle(level);\n" +
                                "                break;\n" +
                                "        }\n" +
                                "        return level;\n" +
                                "    },\n" +
                                "    \n" +
                                "    GenerateOverground: function(level) {\n" +
                                "        var range = this.Distant ? 4 : 6;\n" +
                                "        var offs = this.Distant ? 2 : 1;\n" +
                                "        var oh = Math.floor(Math.random() * range) + offs;\n" +
                                "        var h = Math.floor(Math.random() * range) + offs;\n" +
                                "        \n" +
                                "        var x = 0, y = 0, h0 = 0, h1 = 0, s = 2;\n" +
                                "        for (x = 0; x < this.Width; x++) {\n" +
                                "            oh = h;\n" +
                                "            while (oh === h) {\n" +
                                "                h = Math.floor(Math.random() * range) + offs;\n" +
                                "            }\n" +
                                "            \n" +
                                "            for (y = 0; y < this.Height; y++) {\n" +
                                "                h0 = (oh < h) ? oh : h;\n" +
                                "                h1 = (oh < h) ? h : oh;\n" +
                                "                s = 2;\n" +
                                "                if (y < h0) {\n" +
                                "                    if (this.Distant){\n" +
                                "                        s = 2;\n" +
                                "                        if (y < 2) { s = y; }\n" +
                                "                        level.SetBlock(x, y, 4 + s * 8);\n" +
                                "                    } else {\n" +
                                "                        level.SetBlock(x, y, 5);\n" +
                                "                    }\n" +
                                "                } else if (y === h0) {\n" +
                                "                    s = h0 === h ? 0 : 1;\n" +
                                "                    s += this.Distant ? 2 : 0;\n" +
                                "                    level.SetBlock(x, y, s);\n" +
                                "                } else if (y === h1) {\n" +
                                "                    s = h0 === h ? 0 : 1;\n" +
                                "                    s += this.Distant ? 2 : 0;\n" +
                                "                    level.SetBlock(x, y, s + 16);\n" +
                                "                } else {\n" +
                                "                    s = (y > h1) ? 1 : 0;\n" +
                                "                    if (h0 === oh) { s = 1 - s; }\n" +
                                "                    s += this.Distant ? 2 : 0;\n" +
                                "                    level.SetBlock(x, y, s + 8);\n" +
                                "                }\n" +
                                "            }\n" +
                                "        }\n" +
                                "    },\n" +
                                "    \n" +
                                "    GenerateUnderground: function(level) {\n" +
                                "        var x = 0, y = 0, t = 0, yy = 0;\n" +
                                "        if (this.Distant) {\n" +
                                "            var tt = 0;\n" +
                                "            for (x = 0; x < this.Width; x++) {\n" +
                                "                if (Math.random() < 0.75) { tt = 1 - tt; }\n" +
                                "            \n" +
                                "                for (y = 0; y < this.Height; y++) {\n" +
                                "                    t = tt;\n" +
                                "                    yy = y - 2;\n" +
                                "                    \n" +
                                "                    if (yy < 0 || yy > 4) {\n" +
                                "                        yy = 2;\n" +
                                "                        t = 0;\n" +
                                "                    }\n" +
                                "                    level.SetBlock(x, y, (4 + t + (3 + yy) * 8));\n" +
                                "                }\n" +
                                "            }\n" +
                                "        } else {\n" +
                                "            for (x = 0; x < this.Width; x++) {\n" +
                                "                for (y = 0; y < this.Height; y++) {\n" +
                                "                    t = x % 2;\n" +
                                "                    yy = y - 1;\n" +
                                "                    if (yy < 0 || yy > 7) {\n" +
                                "                        yy = 7;\n" +
                                "                        t = 0;\n" +
                                "                    }\n" +
                                "                    if (t === 0 && yy > 1 && yy < 5) {\n" +
                                "                        t = -1;\n" +
                                "                        yy = 0;\n" +
                                "                    }\n" +
                                "                    \n" +
                                "                    level.SetBlock(x, y, (6 + t + yy * 8));\n" +
                                "                }\n" +
                                "            }\n" +
                                "        }\n" +
                                "    },\n" +
                                "    \n" +
                                "    GenerateCastle: function(level) {\n" +
                                "        var x = 0, y = 0, t = 0, yy = 0;\n" +
                                "        if (this.Distant) {\n" +
                                "            for (x = 0; x < this.Width; x++) {\n" +
                                "                for (y = 0; y < this.Height; y++) {\n" +
                                "                    t = x % 2;\n" +
                                "                    yy = y - 1;\n" +
                                "                    \n" +
                                "                    if (yy > 2 && yy < 5) {\n" +
                                "                        yy = 2;\n" +
                                "                    } else if (yy >= 5) {\n" +
                                "                        yy -= 2;\n" +
                                "                    }\n" +
                                "                    \n" +
                                "                    if (yy < 0) {\n" +
                                "                        t = 0;\n" +
                                "                        yy = 5;\n" +
                                "                    } else if (yy > 4) {\n" +
                                "                        t = 1;\n" +
                                "                        yy = 5;\n" +
                                "                    } else if (t < 1 && yy === 3) {\n" +
                                "                        t = 0;\n" +
                                "                        yy = 3;\n" +
                                "                    } else if (t < 1 && yy > 0 && yy < 3) {\n" +
                                "                        t = 0;\n" +
                                "                        yy = 2;\n" +
                                "                    }\n" +
                                "                    \n" +
                                "                    level.SetBlock(x, y, (1 + t + (yy + 4) * 8));\n" +
                                "                }\n" +
                                "            }\n" +
                                "        } else {\n" +
                                "            for (x = 0; x < this.Width; x++) {\n" +
                                "                for (y = 0; y < this.Height; y++) {\n" +
                                "                    t = x % 3;\n" +
                                "                    yy = y - 1;\n" +
                                "                    \n" +
                                "                    if (yy > 2 && yy < 5) {\n" +
                                "                        yy = 2;\n" +
                                "                    } else if (yy >= 5) {\n" +
                                "                        yy -= 2;\n" +
                                "                    }\n" +
                                "                    \n" +
                                "                    if (yy < 0) {\n" +
                                "                        t = 1;\n" +
                                "                        yy = 5;\n" +
                                "                    } else if (yy > 4) {\n" +
                                "                        t = 2;\n" +
                                "                        yy = 5;\n" +
                                "                    } else if (t < 2 && yy === 4) {\n" +
                                "                        t = 2;\n" +
                                "                        yy = 4;\n" +
                                "                    } else if (t < 2 && yy > 0 && yy < 4) {\n" +
                                "                        t = 4;\n" +
                                "                        yy = -3;\n" +
                                "                    }\n" +
                                "                    \n" +
                                "                    level.SetBlock(x, y, (1 + t + (yy + 3) * 8));\n" +
                                "                }\n" +
                                "            }\n" +
                                "        }\n" +
                                "    }\n" +
                                "    \n" +
                                "};")
                )
        ).isTrue();

    }


}
