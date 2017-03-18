package daos;

import models.File;

import java.util.List;

/**
 * Created by nicolas on 13/03/17.
 */
public interface FileDao
{
    List<File> listFiles() throws Exception;

//    File addFile(File file) throws Exception;

}


