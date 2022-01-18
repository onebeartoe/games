
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
import org.onebeartoe.html.Div;
import org.onebeartoe.html.PlainText;
import org.onebeartoe.io.TextFileReader;
import org.onebeartoe.io.buffered.BufferedTextFileReader;

/**
 * 
 */
public class StatisticsReportService
{
    private StatisticsService statisticsService;
    
    private TextFileReader textFileReader;
    
    public StatisticsReportService()
    {
        statisticsService = new StatisticsService();
        
        textFileReader = new BufferedTextFileReader();
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
        
        File outfile = new File(outFile);

        outfile.getParentFile().mkdirs();

        Path path = outfile.toPath();        
        
        String interpolatedHtml = interpolate(html, report);
        
        byte [] bytes = interpolatedHtml.getBytes();
        
        Files.write(path, bytes);
    }

    private String interpolate(String html, StatisticsReport report) throws IOException 
    {
        Div top = new Div();        
        top.setClasses("some-class");
        String welcome = "Statistics";
        top.add( new PlainText(welcome) );
        
        String statisticsContent = textFileReader.readTextFromClasspath("/reports/statistics.html");
        PlainText statistics = new PlainText(statisticsContent);
                
        Div bottom = new Div();
        PlainText button = new PlainText("<button onclick=\"startEngraving()\" >Stop</button>");
        bottom.add(button);

        StringBuilder builder = new StringBuilder()
                                    .append(top)
                                    .append(statistics)
                                    .append(bottom);
        
        return html.replace("#$%CONTENT%$#", builder.toString() );
    }
    
    private void copyOneResource(File outputDirectory, String resourcePath) throws IOException, URISyntaxException
    {
         URL resource = getClass().getResource("/reports/" + resourcePath);
        
        URI uri = resource.toURI();
        
        Path layoutPath = Paths.get(uri);
     
        
        File resourcePathFile = new File(resourcePath);
        String filename = resourcePathFile.getName();
        File layoutOutfile = new File(outputDirectory, filename);
        
        Path outputPath = layoutOutfile.toPath();

        Path copy = Files.copy(layoutPath, outputPath, REPLACE_EXISTING);
        
        System.out.println("" + copy);       
        
    }

    private void copyResources(File outputDirectory) throws URISyntaxException, IOException 
    {        
        String layoutResourcePath = "layout.css";                
        copyOneResource(outputDirectory, layoutResourcePath);
        
        String resourcePath = "style.css";                
        copyOneResource(outputDirectory, resourcePath);

        String dirtPath = "images/NehemiahK/Minecraft-Game/dirt.png";
        copyOneResource(outputDirectory, dirtPath);
    }
}
