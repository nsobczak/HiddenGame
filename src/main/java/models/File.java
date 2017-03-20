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


    //________________________________________________________________________________________
    public File(Integer id, String filename, String parent, String iv, String content)
    {
        this.id = id;
        this.filename = filename;
        this.parent = parent;
        this.iv = iv;
        this.content = content;
    }


    //________________________________________________________________________________________
    public Integer getId()
    {
        return id;
    }

    public String getFilename()
    {
        return filename;
    }

    public String getParent()
    {
        return parent;
    }

    public String getIv()
    {
        return iv;
    }

    public String getContent()
    {
        return content;
    }


    //________________________________________________________________________________________
    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public void setIv(String iv)
    {
        this.iv = iv;
    }

    public void setContent(String content)
    {
        this.content = content;
    }


    //________________________________________________________________________________________
    @Override
    public String toString()
    {
        String result = "Id : " + this.getId().toString() +
                "\nFilename : " + this.getFilename() +
                "\nParent : " + this.getParent() +
                "\nIv : " + this.getIv() +
                "\nContent : " + this.getContent();
        return result;
    }


    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof File)) return false;
        else
        {
            Boolean result = true;
            File otherFile = (File) other;

            if (!this.getId().equals(otherFile.getId()))
            {
                result = false;
                System.err.println("this.getId() = " + this.getId() + " != " + otherFile.getId() + " = otherFile.getId()");
            } else if (!this.getFilename().equals(otherFile.getFilename()))
            {
                result = false;
                System.err.println("getFilename() = " + this.getFilename() + " != " + otherFile.getFilename() + " = otherFile.getFilename()");
            } else if (!this.getParent().equals(otherFile.getParent()))
            {
                result = false;
                System.err.println("getParent() = " + this.getParent() + " != " + otherFile.getParent() + " = otherFile.getParent()");
            } else if (!this.getIv().equals(otherFile.getIv()))
            {
                result = false;
                System.err.println("getIv() = " + this.getIv() + " != " + otherFile.getIv() + " = otherFile.getIv()");
            } else if (!this.getContent().equals(otherFile.getContent()))
            {
                result = false;
                System.err.println("getContent() = " + this.getContent() + " != " + otherFile.getContent() + " = otherFile.getContent()");
            }
            return result;
        }
    }


}
