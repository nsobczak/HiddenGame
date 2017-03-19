package models;

import daos.FileDao;
import daos.impl.FileDaoImpl;
import services.CryptoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/03/17.
 */
public class Game
{
    List<File> gameFiles;

    public Game()
    {
        this.gameFiles = new ArrayList<File>();
    }

    public List<File> getGameFiles()
    {
        return gameFiles;
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


}
