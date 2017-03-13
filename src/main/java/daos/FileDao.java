package daos;

import models.File;

import java.util.List;

/**
 * Created by nicolas on 13/03/17.
 */
public interface FileDao
{
    List<File> listMovies() throws Exception;

    List<File> listMoviesByGenre(String genreName) throws Exception;

    File addMovie(File movie) throws Exception;

}


