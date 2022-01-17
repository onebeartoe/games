
package org.onebeartoe.minecraft.statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 
 */
public class StatisticsReportService
{
    public File generate(Path inpath) throws FileNotFoundException, IOException
    {
        String outpath = "target/reports/minecraft-statistics.html";
        
        return generate(inpath, outpath);
    }
    
    public File generate(Path inpath, String outpath) throws FileNotFoundException, IOException
    {
        File infile = inpath.toFile();

        File outfile = null;
        
        if( !infile.exists() )
        {
            throw new FileNotFoundException();
        }
        else
        {
            outfile = new File(outpath);
            
            outfile.getParentFile().mkdirs();
            
            Path path = outfile.toPath();
            
            Files.write(path, "<html>".getBytes() );
        }
        
        return outfile;
    }   
}
