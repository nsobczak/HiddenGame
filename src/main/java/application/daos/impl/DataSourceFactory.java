package application.daos.impl;

import application.models.Game;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;

public class DataSourceFactory
{
    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() throws IOException
    {
        if (dataSource == null)
        {
            createDataSource();
        }
        return dataSource;
    }


    private static void createDataSource()
    {
        System.out.println("create data source");
        dataSource = new MysqlDataSource();
        dataSource.setServerName(Game.getHost());
        dataSource.setPort(Game.getPortNumber());
        dataSource.setDatabaseName(Game.getSchema());
        dataSource.setUser(Game.getUser());
        dataSource.setPassword(Game.getPassword());

    }


}