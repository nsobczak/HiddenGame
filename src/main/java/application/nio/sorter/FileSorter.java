package application.nio.sorter;

import java.io.IOException;
import java.nio.file.*;


/**
 * Created by Vincent Reynaert and Nicolas Sobczak on 16/01/2017.
 */
public class FileSorter
{
    private Path root;


    //_______________________________________________________
    public FileSorter()
    {
        this.root = null;
    }


    public FileSorter(String rootDirectory) throws IOException
    {
        this.root = Paths.get(rootDirectory);
    }


    public void setRoot(Path root)
    {
        this.root = root;
    }

    public Path getRoot()
    {
        return root;
    }


    //______________________________________________________________

    /**
     * @param newDir: le nouveau repertoire
     * @param base:   le chemin vers le dossier ou l'on veut creer le repertoire
     * @return
     * @throws IOException
     */
    public Path prepareDirectory(String newDir, Path base) throws IOException
    {
        Path newDirectory = null;
        // Si c'est un directory
        if (Files.isDirectory(base))
        {
            // S'il n'existe pas
            if (Files.notExists(base))
            {
                // Creation du directory
                System.out.println("Creation du directory");
                Files.createDirectories(base);
            }
            // On append le newDir a la base
//            System.out.println("append le newDir a la base");
            newDirectory = base.resolve(newDir);
            try
            {
                Files.createDirectory(newDirectory);
            } catch (FileAlreadyExistsException e)
            {
                System.err.println("prepareDirectory : le répertoire existe deja !");
            }
        } else
        {
            // Sinon on souleve l'exception
            throw new IOException();
        }
//        System.out.println(newDirectory);
        return newDirectory;
    }


    /**
     * GETEXTENSION
     *
     * @param entry: le chemin du fichier a analyser
     * @return: l'extension du fichier
     */
    public String getExtension(Path entry)
    {
        String file = null;
        String result = null;

//        System.out.println(entry);
        file = entry.getFileName().toString();
        String[] parts = file.split("\\.");
        result = parts[parts.length - 1];

        return result;
    }


    /**
     * @param entry:     chemin vers le fichier que l'on veut copier
     * @param directory: chemin vers le dossier ou l'on veut copier le fichier
     * @throws IOException, FileAlreadyExistsException
     */
    public void copyFile(Path entry, Path directory) throws IOException
    {
        if (Files.notExists(entry))
        {
            System.err.println("le fichier n'existe pas !");
            throw new IOException();
        } else
        {
            Path filename = entry.getFileName();
//            System.out.println("filename : " + filename);
            Path prepDir = directory.resolve(filename);
//            System.out.println("prepDir : " + prepDir);
            if (Files.exists(prepDir))
            {
                System.err.println("le fichier existe déjà !");
            } else
            {
                Files.copy(entry, prepDir);
            }
        }
    }


    /**
     * @param entry
     * @throws IOException
     */
    public void moveFileToFolder(Path entry, Path targetFolder) throws IOException
    {
        if (Files.notExists(entry))
        {
            System.err.println("le fichier n'existe pas !");
            throw new IOException();
        } else
        {
            //Création du répertoire game s'il n'existe pas
            try
            {
                prepareDirectory("game", Paths.get("src", "isen", "java22017", "practical2"));
            } catch (IOException e)
            {
//                e.printStackTrace();
            }
            //Déplacement du fichier
            Path filename = entry.getFileName();
            Path prepDir = targetFolder.resolve(filename);
            Path file = Files.move(entry, prepDir, StandardCopyOption.REPLACE_EXISTING);
        }
    }


}
