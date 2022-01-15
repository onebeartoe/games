
package org.onebeartoe.minecraft.statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.onebeartoe.minecraft.advancements.UserAdvancementsService.statsPath;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 */
public class StatisticsReportServiceTest 
{
    private StatisticsReportService implementation;
    
    @BeforeTest
    public void initialize()
    {
        implementation = new StatisticsReportService();
    }
    
    /** 
     * This test ensures the implementation throws an exception when the 
     * input file does not exist.
     */
    @Test(expectedExceptions = FileNotFoundException.class)
    public void generate_fail_inputFileDoesNotExist() throws FileNotFoundException, IOException
    {
        String inpath = "some/fake/path.xml";
        
        Path fakePath = Paths.get(inpath);
        
        implementation.generate(fakePath);
    }
    
    /**
     * This test ensures the implementation uses the default output path when 
     * none is specified by the caller.
     */
    @Test
    public void generate_pass_defaultPathIsUsedWhenCallerDoesNotSpecifiy() throws FileNotFoundException, IOException
    {
        String inpath = statsPath;
        
        Path inPath = Paths.get(inpath);
        
        File outfile = implementation.generate(inPath);
        
        assertTrue( outfile.exists() );
        
        assertEquals( outfile.getName(), "minecraft-statistics.html");
    }
    
    /**
     * This test ensures the implementation uses the output path specified by the 
     * caller. 
     */
    @Test
    public void generate_pass_callersOutputPathIsUsed() throws IOException
    {
        String inpath = statsPath;
        
        Path inPath = Paths.get(inpath);
        
        String outpath = "target/another-minecraft-statistics.html";
        
        File outfile = implementation.generate(inPath, outpath);
        
        assertTrue( outfile.exists() );
        
        String expected = new File(outpath)
                            .getCanonicalPath();
        
        String actual = outfile.getCanonicalPath();
        
        assertEquals(expected, actual);
    }
}
