import models.Game;

import java.nio.file.Paths;

public class Application
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("hello world !");

        Game game = new Game(Paths.get("game").toString());
        game.writeDatabaseProperties(
                "Localhost", 3306, "db_hidden_game", "root", "ISEN");
        game.initDecryptedFileList();
//        game.buildGameFromFileList();
    }


}
