package mapper.impl;

import mapper.ResultMapper;
import models.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolas on 13/03/17.
 */
public class FileResultMapper implements ResultMapper<File>
{
    private List<File> filesList;

    public FileResultMapper()
    {
        this.filesList = new ArrayList<>();
    }

    public FileResultMapper(List<File> filesList)
    {
        this.filesList = filesList;
    }


    @Override
    public List<File> getParsedList() throws SQLException
    {
        return this.filesList;
    }

    @Override
    public void parseResultSet(ResultSet resultSet) throws SQLException
    {

        while (resultSet.next())
        {
            //Integer id, String filename,
            // String parent, String iv, String content
            File file = new File(resultSet.getInt("id"),
                    resultSet.getString("filename"),
                    resultSet.getString("parent"),
                    resultSet.getString("iv"),
                    resultSet.getString("content")
            );
            this.filesList.add(file);
        }
    }


}



