

package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * 
 */
public class MapMarkerParser 
{
    public List<MapMarker> parse(File infile) throws IOException 
    {
        String text = Files.readString(infile.toPath());
        
        String[] split = text.split("(\\r?\\n)(\\r?\\n)");
        
        List<MapMarker> markers = List.of(split)
                .stream()
                .filter(chunk -> !chunk.trim().isEmpty() )
                .map(chunk ->
                {         
                    var lines = chunk.split("(\\r?\\n)");
                    
                    var count = lines.length;
                    
                    var description = new StringBuffer();
                    
                    for(int i = 0; i < count-1; i++)
                    {
                        description.append(lines[i]);
                    }
                    
                    var verification = new GnuplotDataVerification();
                    
                    var lastLine = lines[count-1];
                    
                    MapMarker descriptionLessMarker = verification.isValid(lastLine);
                    
                    return new MapMarker(descriptionLessMarker.location(), description.toString(), descriptionLessMarker.valid() ); 
                })
                .toList();
        
        return markers;
    }   
}
