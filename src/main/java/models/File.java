package models;

/**
 * Created by nicolas on 13/03/17.
 */
public class File
{
    private Integer id;
    private String filename;
    private String parent;
    private String iv;
    private String content;


    public File(Integer id, String filename, String parent, String iv, String content)
    {
        this.id = id;
        this.filename = filename;
        this.parent = parent;
        this.iv = iv;
        this.content = content;
    }


}
