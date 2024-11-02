
package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import java.io.IOException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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
    }
}
