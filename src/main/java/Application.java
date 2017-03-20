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
        //TODO: apparemment y'aurait un pb de lecture du port de la bdd causée par le type de db.port int/String par dataSource.setPort(Integer.valueOf(dbProperties.getProperty("db.port")));
        //quand on le lance 2 fois la 2ème fois ça fonctionne (ce n'est pas un pb de temps d'écriture du fichier)
        game.initDecryptedFileList();
        game.buildGameFromFileList();
    }


}
