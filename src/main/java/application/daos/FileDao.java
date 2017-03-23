package application.daos;

import application.models.File;

import java.util.List;

/**
 * Created by Vincent Reynaert & Nicolas Sobczak on 13/03/17.
 */
public interface FileDao
{
    List<File> listFiles() throws Exception;

}


