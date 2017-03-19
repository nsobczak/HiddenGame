package services;

import daos.FileDao;
import daos.impl.DataSourceFactory;
import daos.impl.FileDaoImpl;
import models.File;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Created by nicolas on 19/03/17.
 */
public class CryptoServiceTestCase
{
    private CryptoService cryptoService;

    @Before
    public void initCryptoService() throws Exception
    {
        cryptoService = new CryptoService();
    }


    @Test
    public void shouldDecryptFiles() throws Exception
    {
        // WHEN
        byte[] byteFilename = cryptoService.decrypt("UekhwWJBu9xB10o8FGnMv0GIivJYohG9IedjKOr7i2U=", "BFhXOm4J30FkoWwRV1jNmw==");
        byte[] byteParent = cryptoService.decrypt("+J0PcijVI40KARebZxe4Dw==", "BFhXOm4J30FkoWwRV1jNmw==");
        byte[] byteContent = cryptoService.decrypt("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", "BFhXOm4J30FkoWwRV1jNmw==");
        // THEN
        String filename = new String(byteFilename);
        assertThat(filename).isEqualTo("backgroundGenerator.js");

        String parent = new String(byteParent);
        assertThat(parent).isEqualTo("code/");

        String content = new String(byteContent);
        assertThat(content).isEqualTo("/**\n" +
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
                "};");
    }



    @Test
    public void shouldEncryptFiles() throws Exception
    {
        //TODO: check if it is a relevant test
        // WHEN
        byte[] clearByteFilename = "backgroundGenerator.js".getBytes();
        String encryptedFilename = cryptoService.encrypt(clearByteFilename);
        byte[] clearByteParent = "code/".getBytes();
        String encryptedParent = cryptoService.encrypt(clearByteParent);

        // THEN
        System.out.println(encryptedFilename);
        assertThat(encryptedFilename).isNotEqualTo("backgroundGenerator.js");
        System.out.println(encryptedParent);
        assertThat(encryptedParent).isNotEqualTo("code/");
    }

}
