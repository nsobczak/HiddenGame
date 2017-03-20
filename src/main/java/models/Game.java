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

    //________________________________________________________________________________________
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
            byte[] byteContent = cryptoService.decrypt(file.getContent(), file.getIv());
            String content = new String(byteContent);
            String Iv = new String(file.getIv());

            gameFiles.add(new File(file.getId(), filename, parent, Iv, content));

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
                    Files.write(pathToFile, file.getContent().getBytes());
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