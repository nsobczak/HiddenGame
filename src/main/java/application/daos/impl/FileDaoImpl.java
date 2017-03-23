package application.daos.impl;

import application.daos.FileDao;
import application.mapper.impl.FileResultMapper;
import application.models.File;
import application.utils.QueryExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent Reynaert & Nicolas Sobczak on 13/03/17.
 */
public class FileDaoImpl implements FileDao
{
    @Override
    public List<File> listFiles() throws Exception
    {
        List<File> returnedList = new ArrayList<>();
        FileResultMapper fileResultMapper = new FileResultMapper(returnedList);
        String sqlQuery = "SELECT * FROM files";
        QueryExecutor.executeSelectQuery(sqlQuery, fileResultMapper);
        return fileResultMapper.getParsedList();
    }


}

