package models;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by root on 19/03/17.
 */
public class FileTestCase
{
    private File file;

    @Before
    public void initFile() throws Exception
    {
        file = new File(123, "testFileName", "testParent", "testIv", "testContent");
    }


    @Test
    public void toStringShouldPrintFile() throws Exception
    {
        // WHEN
        // THEN
        assertThat(file.toString()).isEqualTo("Id : 123\n" +
                "Filename : testFileName\n" +
                "Parent : testParent\n" +
                "Iv : testIv\n" +
                "Content : testContent");
    }


    @Test
    public void equalsShouldBeEqual() throws Exception
    {
        // WHEN
        File otherFile = new File(123, "testFileName", "testParent", "testIv", "testContent");
        //THEN
        assertThat(file.equals(otherFile)).isTrue();
    }

    @Test
    public void equalsShouldNotBeEqual() throws Exception
    {
        // WHEN
        File otherFileNull = null;
        String otherFileString = "Test";
        File otherFileId = new File(124, "testFileName", "testParent", "testIv", "testContent");
        File otherFileFilename = new File(123, "test", "testParent", "testIv", "testContent");
        File otherFileParent = new File(123, "testFileName", "test", "testIv", "testContent");
        File otherFileIv = new File(123, "testFileName", "testParent", "test", "testContent");
        File otherFileContent = new File(123, "testFileName", "testParent", "testIv", "test");
        //THEN
        assertThat(file.equals(otherFileNull)).isFalse();
        assertThat(file.equals(otherFileString)).isFalse();
        assertThat(file.equals(otherFileId)).isFalse();
        assertThat(file.equals(otherFileFilename)).isFalse();
        assertThat(file.equals(otherFileParent)).isFalse();
        assertThat(file.equals(otherFileIv)).isFalse();
        assertThat(file.equals(otherFileContent)).isFalse();
    }


}
