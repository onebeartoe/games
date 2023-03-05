
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
import org.json.simple.parser.ParseException;
import org.onebeartoe.html.Div;
import org.onebeartoe.html.PlainText;
import org.onebeartoe.io.TextFileReader;
import org.onebeartoe.io.buffered.BufferedTextFileReader;

/**
 * 
 */
public class StatisticsReportService
{
//    private StatisticsService statisticsService;
    private UserStatisticsService statisticsService;
    
    private TextFileReader textFileReader;
    
    public StatisticsReportService()
    {
//        statisticsService = new StatisticsService();
        statisticsService = new UserStatisticsService();
        
        textFileReader = new BufferedTextFileReader();
    }
    
    public void generate(Path inpath) throws FileNotFoundException, IOException, URISyntaxException, ParseException
    {
        String outpath = "target/reports/minecraft-statistics.html";
        
        generate(inpath, outpath);
    }
    
    public void generate(Path inpath, String outFile) throws FileNotFoundException, IOException, URISyntaxException, ParseException
    {
        File infile = inpath.toFile();

        
        if( !infile.exists() )
        {
            throw new FileNotFoundException();
        }
        else
        {
//            StatisticsReport report = statisticsService.parse(infile.toPath());
            StatisticsReport report = statisticsService.parse(infile);
            
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
        String statisticsContent = textFileReader.readTextFromClasspath("/reports/statistics.html");
        
        StringBuilder killedBuilder = new StringBuilder();
        for(String killed : report.missingHostileMobKills)
        {
            killedBuilder.append(killed);
            killedBuilder.append("<br>");
        }
        statisticsContent = statisticsContent.replace("#$%MOST_KILLED_MOB_BY_USER_CONTENT%$#", killedBuilder.toString() );
        
        PlainText statistics = new PlainText(statisticsContent);
                
        StringBuilder builder = new StringBuilder()
                                    .append(statistics);
        
        return html.replace("#$%CONTENT%$#", builder.toString() );
    }
    
    private void copyOneResource(File outputDirectory, String resourcePath) throws IOException, URISyntaxException
    {
        String path = "/reports/" + resourcePath;
        URL resource = getClass().getResource(path);

        if(resource == null)
        {
            System.out.println("classpath resource not found: " + path);
        }
        
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
        
        String fontResourcePath = "fonts/minecraft.ttf";                
        copyOneResource(outputDirectory, fontResourcePath);

        String dirtPath = "images/NehemiahK/Minecraft-Game/dirt.png";
        copyOneResource(outputDirectory, dirtPath);
        
        String darkerDirtPath = "images/NehemiahK/Minecraft-Game/darker-dirt.png";
        copyOneResource(outputDirectory, darkerDirtPath);
        
        
        String javascriptResource = "minecraft.js";
        copyOneResource(outputDirectory, javascriptResource);
        
//TODO: lets do this in JavaFX.        
//        String rickyResource = "y2meta.com-Rick-Rolled-Short-Version.mp4";
//        copyOneResource(outputDirectory, rickyResource);
    }
}
