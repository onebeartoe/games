
package org.onebeartoe.games.gnuplot.map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.geometry.Point3D;

/**
 *
 */
public class MapMarkersTest 
{
    private List<MapMarker> points;
    
    private MapMarker a = new MapMarker( new Point3D(2, 0, 0), "A", true);
    private MapMarker b = new MapMarker( new Point3D(4, 0, 0), "B", true);
    private MapMarker c = new MapMarker( new Point3D(0, 0, 6), "C", true);
    private MapMarker d = new MapMarker( new Point3D(0, 0, 8), "D", true);
    private MapMarker e = new MapMarker( new Point3D(10, 0, 0), "E", true);
    private MapMarker f = new MapMarker( new Point3D(0, 0, 14), "F", true);
    
    public MapMarkersTest() 
    {
        points = List.of(a, b, c, d, e, f);
    }
    
    @Test
    public void closestPoints_origin()
    {
        var origin = new Point3D(0, 0, 0);
        
        List closestPoints = MapMarkers.closestPoints(origin, points);
        
        assertThat(closestPoints).containsExactly(a, b, c, d, e, f);
    }
    
    @Test
    public void closestPoints_furthestAway()
    {
        var targetMarker = f.location();
        
        List closestPoints = MapMarkers.closestPoints(targetMarker, points);
        
        assertThat(closestPoints).containsExactly(f, d, c, a, b, e);
    }
}
