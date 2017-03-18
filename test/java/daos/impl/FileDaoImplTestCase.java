package daos.impl;

import daos.FileDao;
import models.File;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Created by vvinc_000 on 13/03/2017.
 */
public class FileDaoImplTestCase
{
    private FileDao fileDao;


    @Before
    public void initDatabase() throws Exception
    {
        fileDao = new FileDaoImpl();
        Connection connection = DataSourceFactory.getDataSource().getConnection();
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("DELETE FROM movie");
//        stmt.executeUpdate("DELETE FROM genre");
//        stmt.executeUpdate("INSERT INTO genre(idgenre,name) VALUES (1,'Drama')");
//        stmt.executeUpdate("INSERT INTO genre(idgenre,name) VALUES (2,'Comedy')");
//        stmt.executeUpdate("INSERT INTO genre(idgenre,name) VALUES (3,'Western')");
//        stmt.executeUpdate("INSERT INTO movie(idmovie,title, release_date, genre_id, duration, director, summary) "
//                + "VALUES (1, 'Title 1', '2015-11-26', 1, 120, 'director 1', 'summary of the first movie')");
//        stmt.executeUpdate("INSERT INTO movie(idmovie,title, release_date, genre_id, duration, director, summary) "
//                + "VALUES (2, 'My Title 2', '2015-11-14', 2, 114, 'director 2', 'summary of the second movie')");
//        stmt.executeUpdate("INSERT INTO movie(idmovie,title, release_date, genre_id, duration, director, summary) "
//                + "VALUES (3, 'Third title', '2015-12-12', 2, 176, 'director 3', 'summary of the third movie')");
//        stmt.close();
        connection.close();
    }


    @Test
    public void shouldListFiles() throws Exception
    {
        // WHEN
        List<File> files = fileDao.listFiles();
        // THEN
        assertThat(files).hasSize(108);
        assertThat(files).extracting("id", "filename", "parent", "iv", "content")
                .contains(tuple(235, "UekhwWJBu9xB10o8FGnMv0GIivJYohG9IedjKOr7i2U=", "+J0PcijVI40KARebZxe4Dw==", "BFhXOm4J30FkoWwRV1jNmw==", "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"),
                        tuple(236, "oOs11Fg32R9pFUIYPUECh15kqxxLtF/N/8y6LiD571A=", "+J0PcijVI40KARebZxe4Dw==", "BFhXOm4J30FkoWwRV1jNmw==", "NZCflmiHX3XZTGr0/tJe6fJC8PbmjzwBVrUk+dKC4rojyLi4c3lglR3GI1LZ0J0uTGHQUXoes72k3GeoVImZdEV0Y5GqapwIwEdMoOe/AwdbG6aSaGe232JOGkjepqlsHbwL06eJZExgk6Ljz7jHrvwS5KIP57XKfiuKwF6XFOh36u78aK98cJF1qnRtg1FdYts193LtH8uJE0Eg332aYOeJFrOEJza4m2X74RfeEvS14fBv7XtQpb8n52ZJN8JUi1VL/lpOiDesU+farmVF+IpduOS9yqNMTq1/lRILveLPp/poAQMLSWh4kk5n0bjN0iPq+loENlEkC5F/747si5TQUdYaf48LaJduN4x2kx1IS1tjcWLi/9OhNRNq7EPzzHKZWX0JIt4Uf3HOv1K4f2PFqiM8TqbUmCqfc57rGhN/MlsU9zuXz4KPJTMu4mZxhlHipZ5W2Z7fJHqmBuy5WIiwtMmBq3a4i/V6ZA/XRapEJLrn9YBjw0Yctbv26X1nrU+PAih1RB/6ReyMAwNZJd/zYrtfqPpoJidjqQt0ozDvRl6XXjw2BKrEXr/qFcMDV6/OwTwaisnCyk9Nw6m0uZm0ARKndAKgBPaqlTS5hNuE0YuCxcsrmO4FMdLW+MI3mzT7G/im74Vkto2ISSIfc4p8yggUcNpX13DYRMKusMi/98YRk2RdSpa9COGUluUnQCZVrZDKf1+xH89GyfMqIRZ28TCEarpOUsk6XgtuS6yLYcWXR7zflmDD2CGWgpoXZZiTRM04I0bRTffhHrr6ZG2fKudofXB3r8GPr1p68tQ6RFl4SfMNslTiQzoX4USOndOM+pDz6T0+5VAEHUHlRhjSjFj1g6HPK+F0JH2oszKNhgY49mqdCR4uJvZhQX9dKe4cnK66sjTH8WMja/+nqDDHvpv+s4tHHlfBu4Y72ozSeGMfCKxx62P+RLKNIR6hYAV9MZMlQVxbUER+/g64vMU44qoCwjcb9L0t62EMA2K3vnlksvgi7IkImau4i5rRN/oSRyIrTPZv+bpHr62plpkOw8l5u/MTBKPO+FktunYkhT7+3QMLxALlW0i4DkvdXKJCZQ2wdQ7JfNfb73EdOrCkU+Dg4kX+OZlG7nnCA9ExurUyejCEyKUeQpNEQzGL521Nu8r47rnqqph2IHnW6ponG4xBkpUF2dz1oE5G6D2QQomppFD/tz+YPv/fsTbBzyyRM27jVIlPJEYxlZnkOSY/DuW3eiKm1GaGFMhO+Va4HyuyTa1wM28CtzOaF3crsujGCPL4sBnmPWfTSw+jLi9VdK9Rc9TZSv+umrXjlqeSgQz+YSsf8OpfdyD5ZYaF/p/8QqM/EGLjdQVekLSMVWqZVpt/CsUCqlHZW36MqOfGaIkXLHouaNBgTGEEJNFwk3FZHN8mNT6EgA9DhZcTpAVBCpRb+uBpGXmbNlF568QZH+SEbaZTDs44zMAerkBX28pQaMGDLJ8UqlvQsu1GElQHScEQAibTDysqo+hBXxZOh37rhDKLOectBAPGvzKAUjd5tg2l+s91Ai0x6Lws1IlQ89K2f7LCYQ89bdj9XnhXiNzlem5GML7PHHzJ15rXZDsXVx/QbhbPhq4aN/UlB2W9dWFdG0mZ5MX1VaSSyfNZhoTXlPA0HgbbtqZt75IxwOaj6nlamXPMHSmVrJlf1Q=="),
                        tuple(342, "LKiOowZVytucHUpKaj8MQw==", "crdUnSW60IkzJzHgxGIQ2w==", "BFhXOm4J30FkoWwRV1jNmw==", "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")
                        );
    }

//
//    @Test
//    public void shouldListMoviesByGenre() throws Exception {
//        // WHEN
//        List<File> files = fileDao.listMoviesByGenre("Comedy");
//        // THEN
//        assertThat(files).hasSize(2);
//        assertThat(files).extracting("director", "title").containsOnly(tuple("director 2", "My Title 2"),
//                tuple("director 3", "Third title"));
//    }
//
//
//    @Test
//    public void shouldAddMovie() throws Exception {
//        // GIVEN
//        Movie movieToAdd = new Movie();
//        movieToAdd.setTitle("TitleTest");
//        movieToAdd.setDirector("DirectorTest");
//        movieToAdd.setDuration(42);
//        movieToAdd.setGenre(genreDao.getGenre("Western"));
//        movieToAdd.setReleaseDate(LocalDate.of(2017,2,13));
//        movieToAdd.setSummary("SummaryTest");
//        // WHEN
//        movieDao.addMovie(movieToAdd);
//        // THEN
//        List<Movie> movies = movieDao.listMoviesByGenre("Western");
//        assertThat(movies).hasSize(1);
//        assertThat(movies.get(0).getId()).isNotNull();
//        assertThat(movies.get(0).getTitle()).isEqualTo(movieToAdd.getTitle());
//        assertThat(movies.get(0).getDirector()).isEqualTo(movieToAdd.getDirector());
//        assertThat(movies.get(0).getSummary()).isEqualTo(movieToAdd.getSummary());
//        assertThat(movies.get(0).getDuration()).isEqualTo(movieToAdd.getDuration());
//    }
}
