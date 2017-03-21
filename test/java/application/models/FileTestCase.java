package application.models;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by root on 19/03/17.
 */
public class FileTestCase
{
    private File fileWithCryptedContent;
    private File fileWithContent;

    @Before
    public void initFile() throws Exception
    {
        fileWithCryptedContent = new File(123, "testFileName", "testParent", "testIv", "testContent");
        fileWithContent = new File(600, "testFileName", "testParent", "testIv", new byte[]{123, 100});
    }


    @Test
    public void toStringShouldPrintFile() throws Exception
    {
        // THEN
        assertThat(fileWithCryptedContent.toString()).isEqualTo("Id : 123\n" +
                "Filename : testFileName\n" +
                "Parent : testParent\n" +
                "Iv : testIv\n" +
                "CryptedStringContent : testContent\n" +
                "Content : null");
        assertThat(fileWithContent.toString()).isEqualTo("Id : 600\n" +
                "Filename : testFileName\n" +
                "Parent : testParent\n" +
                "Iv : testIv\n" +
                "CryptedStringContent : \n" +
                "Content : {d");
    }


    @Test
    public void equalsShouldBeEqual() throws Exception
    {
        // WHEN
        File otherFile1 = new File(123, "testFileName", "testParent", "testIv", "testContent");
        File otherFile2 = new File(600, "testFileName", "testParent", "testIv", new byte[]{123, 100});
        //THEN
        assertThat(fileWithCryptedContent.equals(otherFile1)).isTrue();
        assertThat(fileWithContent.equals(otherFile2)).isTrue();
    }

    @Test
    public void equalsShouldNotBeEqual() throws Exception
    {
        // WHEN
        File otherFileNull = null;
        String otherFileString = "Test";
        File otherFileId_1 = new File(124, "testFileName", "testParent", "testIv", "testContent");
        File otherFileFilename_1 = new File(123, "test", "testParent", "testIv", "testContent");
        File otherFileParent_1 = new File(123, "testFileName", "test", "testIv", "testContent");
        File otherFileIv_1 = new File(123, "testFileName", "testParent", "test", "testContent");
        File otherFileContent_1 = new File(123, "testFileName", "testParent", "testIv", "test");
        File otherFileCryptedContent_1 = new File(123, "testFileName", "testParent", "testIv", new byte[]{123, 127, 100});
        File otherFileContent_2 = new File(600, "testFileName", "testParent", "testIv", "test");
        File otherFileCryptedContent_2 = new File(600, "testFileName", "testParent", "testIv", new byte[]{123, 127, 100});

        //THEN
        assertThat(fileWithCryptedContent.equals(otherFileNull)).isFalse();
        assertThat(fileWithCryptedContent.equals(otherFileString)).isFalse();

        assertThat(fileWithCryptedContent.equals(otherFileId_1)).isFalse();
        assertThat(fileWithCryptedContent.equals(otherFileFilename_1)).isFalse();
        assertThat(fileWithCryptedContent.equals(otherFileParent_1)).isFalse();
        assertThat(fileWithCryptedContent.equals(otherFileIv_1)).isFalse();
        assertThat(fileWithCryptedContent.equals(otherFileContent_1)).isFalse();
        assertThat(fileWithCryptedContent.equals(otherFileCryptedContent_1)).isFalse();

        assertThat(fileWithContent.equals(otherFileContent_2)).isFalse();
        assertThat(fileWithContent.equals(otherFileCryptedContent_2)).isFalse();
    }


}
