package application.mapper.impl;

import application.mapper.ResultMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GeneratedKeysMapper implements ResultMapper<Integer> {
    private List<Integer> integerList;


    public GeneratedKeysMapper() {
        this.integerList = new ArrayList<>();
    }

    @Override
    public List getParsedList() throws SQLException {
        return this.integerList;
    }

    @Override
    public void parseResultSet(ResultSet ids) throws SQLException {
        if (ids.next()) {
            int generatedId = (int) ids.getLong(1);
            this.integerList.add(generatedId);
        } else {
            System.out.println("can't find generated id");
        }
    }


}
