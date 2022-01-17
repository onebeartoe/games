
package org.onebeartoe.minecraft.statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 */
public class StatisticsReportService
{
    private StatisticsService statisticsService;
    
    public StatisticsReportService()
    {
        statisticsService = new StatisticsService();
    }
    
    public File generate(Path inpath) throws FileNotFoundException, IOException, URISyntaxException
    {
        String outpath = "target/reports/minecraft-statistics.html";
        
        return generate(inpath, outpath);
    }
    
    public File generate(Path inpath, String outpath) throws FileNotFoundException, IOException, URISyntaxException
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
            
            StatisticsReport report = statisticsService.prepare(path);
            
            byte [] html = generateHtml(report);
            
            Files.write(path, html);
        }
        
        return outfile;
    }

    private byte [] generateHtml(StatisticsReport report) throws IOException, URISyntaxException 
    {
        String inpath = "/reports/template.html";
        
        URL resource = getClass().getResource(inpath);
        
        URI uri = resource.toURI();
        
        Path path = Paths.get(uri);
        
        String html = Files.readString(path);
        
        String interpolatedHtml = interpolate(html);
        
        
        return interpolatedHtml.getBytes();
    }

    private String interpolate(String html) 
    {
        return html.replace("#$%CONTENT%$#", "Hello Minecraft World!");
    }
}
