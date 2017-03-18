package daos.impl;

import daos.FileDao;
import mapper.impl.FileResultMapper;
import models.File;
import utils.QueryExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolas on 13/03/17.
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

