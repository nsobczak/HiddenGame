package application.models;

import application.daos.FileDao;
import application.daos.impl.FileDaoImpl;
import application.nio.sorter.FileSorter;
import application.services.CryptoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent Reynaert & Nicolas Sobczak on 19/03/17.
 */
public class Game
{
    List<File> gameFiles;
    FileSorter fileSorter;
    private static String host;
    private static int portNumber;
    private static String schema;
    private static String user;
    private static String password;


    public Game()
    {
        this.gameFiles = new ArrayList<File>();
        this.fileSorter = new FileSorter();
    }

    public Game(String rootDirectory) throws IOException
    {
        this.gameFiles = new ArrayList<File>();
        this.fileSorter = new FileSorter(rootDirectory);
    }

    public List<File> getGameFiles()
    {
        return gameFiles;
    }

    public FileSorter getFileSorter()
    {
        return fileSorter;
    }

    public void setGameFiles(List<File> gameFiles)
    {
        this.gameFiles = gameFiles;
    }

    public void setFileSorter(FileSorter fileSorter)
    {
        this.fileSorter = fileSorter;
    }

    public static String getHost()
    {
        return host;
    }

    public static int getPortNumber()
    {
        return portNumber;
    }

    public static String getSchema()
    {
        return schema;
    }

    public static String getUser()
    {
        return user;
    }

    public static String getPassword()
    {
        return password;
    }

    public static void setHost(String host)
    {
        Game.host = host;
    }

    public static void setPortNumber(int portNumber)
    {
        Game.portNumber = portNumber;
    }

    public static void setSchema(String schema)
    {
        Game.schema = schema;
    }

    public static void setUser(String user)
    {
        Game.user = user;
    }

    public static void setPassword(String password)
    {
        Game.password = password;
    }

    //________________________________________________________________________________________
    public static void initGameDatabaseProperties(String host, int portNumber, String schema, String user, String password)
    {
        Game.host = host;
        Game.portNumber = portNumber;
        Game.schema = schema;
        Game.user = user;
        Game.password = password;
    }


    public boolean initDecryptedFileList() throws Exception
    {
        FileDao fileDao = new FileDaoImpl();
        List<File> files = fileDao.listFiles();
        CryptoService cryptoService = new CryptoService();
        int fileNumber = 0;

        for (File file :
                files)
        {
            byte[] byteFilename = cryptoService.decrypt(file.getFilename(), file.getIv());
            String filename = new String(byteFilename);
            byte[] byteParent = cryptoService.decrypt(file.getParent(), file.getIv());
            String parent = new String(byteParent);
            file.setContent(cryptoService.decrypt(file.getCryptedStringContent(), file.getIv()));
            String Iv = new String(file.getIv());

            gameFiles.add(new File(file.getId(), filename, parent, file.getCryptedStringContent(), file.getContent()));

            fileNumber++;
        }
        System.out.println(fileNumber + " decrypted files");
        if (fileNumber > 0) return true;
        else return false;
    }


    public void createGameFile(FileSorter fileSorter, File file) throws IOException
    {
        if (fileSorter != null)
        {
            if (file != null)
            {
                Path pathToFile = null;
                if (!file.getParent().equals(""))
                {
                    fileSorter.prepareDirectory(file.getParent(), fileSorter.getRoot());
                    pathToFile = this.fileSorter.getRoot().resolve(Paths.get(file.getParent(), file.getFilename()));
                } else
                {
                    pathToFile = this.fileSorter.getRoot().resolve(Paths.get(file.getFilename()));
                }
                if (Files.notExists(pathToFile))
                {
                    Files.createFile(pathToFile);
                    Files.write(pathToFile, file.getContent());
                } else
                {
                    System.err.println("File already exists : " + pathToFile.toString());
                }
            } else System.err.println("file is null");
        } else System.err.println("fileSorter is null");
    }


    public void buildGameFromFileList() throws IOException
    {
        if (Files.notExists(this.fileSorter.getRoot()))
        {
            Files.createDirectories(this.fileSorter.getRoot());
        }
        for (File file :
                this.getGameFiles())
        {
            createGameFile(this.fileSorter, file);
        }
        System.out.println("Game has been built !");
    }


}