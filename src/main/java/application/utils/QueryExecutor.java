package application.utils;

import application.daos.impl.DataSourceFactory;
import application.mapper.ResultMapper;

import java.sql.*;
import java.time.LocalDate;

public class QueryExecutor
{

    public static void executeSelectQuery(String query, ResultMapper mapper, Object... parameters) throws Exception
    {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection())
        {
            try (PreparedStatement statement = connection.prepareStatement(query))
            {
                QueryExecutor.mapParameters(statement, parameters);
                ResultSet resultSet = statement.executeQuery();
                mapper.parseResultSet(resultSet);
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        } catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void mapParameters(final PreparedStatement statement, final Object[] parameters) throws SQLException
    {
        for (int i = 0; i < parameters.length; i++)
        {
            Object currentParameter = parameters[i];
            if (currentParameter instanceof String)
            {
                statement.setString(i + 1, String.valueOf(currentParameter));
            }
            if (currentParameter instanceof Integer)
            {
                statement.setInt(i + 1, (Integer) currentParameter);
            }
            if (currentParameter instanceof LocalDate)
            {
                statement.setObject(i + 1, currentParameter, Types.DATE);
            }
        }
    }


}
