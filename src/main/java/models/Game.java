package models;

import daos.FileDao;
import daos.impl.FileDaoImpl;
import nio.sorter.FileSorter;
import services.CryptoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/03/17.
 */
public class Game
{
    List<File> gameFiles;
    FileSorter fileSorter;
    private String host;
    private int portNumber;
    private String schema;
    private String user;
    private String password;


    public Game()
    {
        this.gameFiles = new ArrayList<File>();
        this.fileSorter = new FileSorter();
        this.host = null;
        this.portNumber = -1;
        this.schema = null;
        this.user = null;
        this.password = null;
    }

    public Game(String rootDirectory) throws IOException
    {
        this.gameFiles = new ArrayList<File>();
        this.fileSorter = new FileSorter(rootDirectory);
        this.host = null;
        this.portNumber = -1;
        this.schema = null;
        this.user = null;
        this.password = null;
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

    //________________________________________________________________________________________
    public void initGameDatabaseProperties(String host, int portNumber, String schema, String user, String password)
    {
        this.host = host;
        this.portNumber = portNumber;
        this.schema = schema;
        this.user = user;
        this.password = password;
    }

    public void writeDatabaseProperties(String host, int portNumber, String schema, String user, String password) throws IOException
    {
        Path pathToDbProperties = Paths.get("src", "main", "resources", "db.properties");
        if (Files.notExists(pathToDbProperties))
        {
            Files.createFile(pathToDbProperties);
        }
        String content = "db.server=" + host +
                "\ndb.port=" + String.valueOf(portNumber) +
                "\ndb.schema=" + schema +
                "\ndb.user=" + user +
                "\ndb.password=" + password;
        Files.write(pathToDbProperties, content.getBytes());
        initGameDatabaseProperties(host, portNumber, schema, user, password);
    }


    public void initDecryptedFileList() throws Exception
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
    }


}