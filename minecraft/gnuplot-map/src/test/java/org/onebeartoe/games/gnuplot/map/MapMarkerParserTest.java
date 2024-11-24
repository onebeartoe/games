
package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import java.io.IOException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

/**
 *
 */
public class MapMarkerParserTest
{
    MapMarkerParser implementation = new MapMarkerParser();
    
    @Test
    public void parse() throws IOException
    {
        File infile = new File("src/test/resources/three-map-markers.data");
        
        assertThat(infile).exists();
        
        List<MapMarker> markers = implementation.parse(infile);
        
        assertThat(markers.size()).isEqualTo(3);
        
        MapMarker first = markers.get(0);
        
        assertThat(first.location().getX()).isEqualTo(2);
        
        assertThat(first.location().getY()).isEqualTo(0);
        
        assertThat(first.location().getZ()).isEqualTo(12); 
        
        MapMarker second = markers.get(1);
        
        assertThat(second.id()).isEqualTo("\"Love-2\"");
    }
}
