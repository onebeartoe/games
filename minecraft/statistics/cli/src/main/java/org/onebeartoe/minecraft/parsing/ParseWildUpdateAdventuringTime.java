
package org.onebeartoe.minecraft.parsing;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class parses a text file with the labels of the Wild Update (1.20) biomes
 * for the Adventuring Time advancement.
 */
public class ParseWildUpdateAdventuringTime
{
    public static void main(String[] args) throws IOException, URISyntaxException, Exception 
    {        
        var labelsFile = "advancements/raw-data/1.20/adventuring-time-biomes-labels.text";
        
        URI uri = ClassLoader.getSystemResource(labelsFile).toURI();
        
        Path path = Path.of(uri);
        
        Stream<String> lines = Files.lines(path);
        
        List<String> dataValues = lines.map(l -> l.toLowerCase() )
             .map(l -> l.replaceAll(" ", "_"))
             .map(l -> "\"minecraft:" + l + "\",")
             .collect( Collectors.toList() );
        
        if(dataValues.size() != 53)
        {
            throw new Exception("there should be 53 biomes for the 1.20 Adventuring time advancement");
        }
        
        System.out.println("dataValues = " + dataValues);
        System.out.println();
        
        dataValues.stream()
                .forEach(System.out::println );
        System.out.println("");
        
    }
}
