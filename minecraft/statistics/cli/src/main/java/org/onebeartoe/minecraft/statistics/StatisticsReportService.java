
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
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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
    
    public void generate(Path inpath, String outFile) throws FileNotFoundException, IOException, URISyntaxException
    {
        File infile = inpath.toFile();

        
        if( !infile.exists() )
        {
            throw new FileNotFoundException();
        }
        else
        {
            StatisticsReport report = statisticsService.prepare(infile.toPath());
            
            generateHtml(report, outFile);
            
            File outputDirectory = new File(outFile)
                                    .getParentFile();
            
            copyResources(outputDirectory);
        }
    }

    private void generateHtml(StatisticsReport report, String outFile) throws IOException, URISyntaxException 
    {
        String inpath = "/reports/template.html";
        
        URL resource = getClass().getResource(inpath);
        
        URI uri = resource.toURI();
        
        Path templatePath = Paths.get(uri);
        
        String html = Files.readString(templatePath);
        
        String interpolatedHtml = interpolate(html);
        
        File outfile = null;
        
        outfile = new File(outFile);

        outfile.getParentFile().mkdirs();

        Path path = outfile.toPath();        
        
        byte [] bytes = interpolatedHtml.getBytes();
        
        Files.write(path, bytes);
    }

    private String interpolate(String html) 
    {
        return html.replace("#$%CONTENT%$#", "Hello Minecraft World!");
    }

    private void copyResources(File outputDirectory) throws URISyntaxException, IOException 
    {        
        URL resource = getClass().getResource("/reports/layout.css");
        
        URI uri = resource.toURI();
        
        Path layoutPath = Paths.get(uri);
     
        File layoutOutfile = new File(outputDirectory, "layout.css");
        
        Path outputPath = layoutOutfile.toPath();

        Path copy = Files.copy(layoutPath, outputPath, REPLACE_EXISTING);
        
        System.out.println("" + copy);
    }
}
