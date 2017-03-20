import daos.FileDao;
import daos.impl.FileDaoImpl;
import models.File;
import models.Game;
import services.CryptoService;

import java.util.ArrayList;
import java.util.List;

public class Application
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("hello world !");

        Game game = new Game();
        game.initDecryptedFileList();
        System.out.println(game.getGameFiles().get(62).toString());


    }


}
