

package org.onebeartoe.games.gnuplot.map;

import java.awt.Point;
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
                .filter(line -> !line.trim().isEmpty() )
                .map(l ->
                {
                    Point p = null;
                   return new MapMarker(p, l); 
                })
                .toList();
        
        return markers;
    }   
}
