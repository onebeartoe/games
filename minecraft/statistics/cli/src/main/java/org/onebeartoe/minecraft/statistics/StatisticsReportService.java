
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
    
    public void generate(Path inpath) throws FileNotFoundException, IOException, URISyntaxException
    {
        String outpath = "target/reports/minecraft-statistics.html";
        
        generate(inpath, outpath);
    }
    
    public void generate(Path inpath, String outpath) throws FileNotFoundException, IOException, URISyntaxException
    {
        File infile = inpath.toFile();

        
        if( !infile.exists() )
        {
            throw new FileNotFoundException();
        }
        else
        {
            
            
            StatisticsReport report = statisticsService.prepare(infile.toPath());
            
            generateHtml(report, outpath);
            

        }
        
//        return outfile;
    }

    private void generateHtml(StatisticsReport report, String outpath) throws IOException, URISyntaxException 
    {
        
        
        String inpath = "/reports/template.html";
        
        URL resource = getClass().getResource(inpath);
        
        URI uri = resource.toURI();
        
        Path templatePath = Paths.get(uri);
        
        String html = Files.readString(templatePath);
        
        String interpolatedHtml = interpolate(html);
        
        File outfile = null;
        
        outfile = new File(outpath);

        outfile.getParentFile().mkdirs();

        Path path = outfile.toPath();        
        
        byte [] bytes = interpolatedHtml.getBytes();
        
        Files.write(path, bytes);
    }

    private String interpolate(String html) 
    {
        return html.replace("#$%CONTENT%$#", "Hello Minecraft World!");
    }
}
