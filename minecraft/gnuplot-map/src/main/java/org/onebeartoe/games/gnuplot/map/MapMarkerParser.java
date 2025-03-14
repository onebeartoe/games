

package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javafx.geometry.Point3D;

/**
 * 
 */
public class MapMarkerParser 
{
    public List<MapMarker> parse(File infile) throws IOException 
    {
        String text = Files.readString(infile.toPath());
        
        String[] split = text.split("(\\r?\\n)(\\r?\\n)");
        
        return List.of(split)
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
                        description.append("\n");
                    }
                    
                    var verification = new GnuplotDataVerification();
                    
                    var lastLine = lines[count-1];
                    
                    
                    MapMarker mapMarker = null;
                    try {
                        MapMarker descriptionLessMarker = verification.isValid(lastLine);

                        mapMarker = new MapMarker(descriptionLessMarker.id(), 
                                                descriptionLessMarker.location(), 
                                                description.toString(), 
                                                descriptionLessMarker.valid(),
                                                lastLine); 
                    }
                    catch(IllegalArgumentException iae)
                    {
                        mapMarker = new MapMarker(infile.getAbsolutePath(), 
                                                new Point3D(0,0,0) , 
                                                iae.getMessage(), 
                                                false,
                                                lastLine);                        
                    }
                    
                    return mapMarker;
                })
                .toList();
    }   
}
