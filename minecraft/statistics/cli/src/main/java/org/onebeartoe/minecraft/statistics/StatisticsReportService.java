
package org.onebeartoe.minecraft.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 * 
 */
public class StatisticsReportService
{
//    private StatisticsService statisticsService;
    private UserStatisticsService statisticsService;
    
//    private TextFileReader textFileReader;
    
    public StatisticsReportService()
    {
//        statisticsService = new StatisticsService();
        statisticsService = new UserStatisticsService();
        
//        textFileReader = new BufferedTextFileReader();
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
        String statisticsContent = readTextFromClasspath("/reports/statistics.html");
        
        StringBuilder killedBuilder = new StringBuilder();
        for(String killed : report.missingHostileMobKills)
        {
            killedBuilder.append(killed);
            killedBuilder.append("<br>");
        }
        statisticsContent = statisticsContent.replace("#$%MOST_KILLED_MOB_BY_USER_CONTENT%$#", killedBuilder.toString() );
        
//        PlainText statistics = new PlainText(statisticsContent);
                
        StringBuilder builder = new StringBuilder()
//                                    .append(statistics);
                                    .append(statisticsContent);
        
        return html.replace("#$%CONTENT%$#", builder.toString() );
    }
    
    
//TODO: reuse this method   
    public String readTextFromClasspath(String infileClaspath) throws IOException
    {
        List<String> lines = readTextLinesFromClasspath(infileClaspath);
        StringBuilder sb = new StringBuilder();
        for(String line : lines)
        {
//TODO: Remove the new line hack below and replace with a call to java.nio.file.Files.readAllBytes(path);
            sb.append(line);
            String s = System.lineSeparator();
            sb.append(s);
        }
        
        return sb.toString();
    }     
    
    public List<String> readTextLinesFromClasspath(String infileClaspath) throws IOException
    {
	InputStream instream = getClass().getResourceAsStream(infileClaspath);

        List<String> lines = readLines(instream);
        
        return lines;
    }    
    

    public List<String> readLines(InputStream instream) throws IOException
    {
        List<String> names = new ArrayList();	
        
        BufferedReader br = new BufferedReader(new InputStreamReader(instream));
	String line = br.readLine();  	
	while (line != null)   
	{
	    names.add(line);
	    line = br.readLine();
	}	
	instream.close();
        
        return names;
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
