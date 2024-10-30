
package org.onebeartoe.games.gnuplot.map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.List;

import java.awt.Point;

import org.onebeartoe.games.gnuplot.map.MapMarker;
import org.onebeartoe.games.gnuplot.map.MapMarkers;

/**
 *
 */
public class MapMarkersTest 
{
    private List<MapMarker> points;
    
    private MapMarker a = new MapMarker( new Point(2,0), "A");
    private MapMarker b = new MapMarker( new Point(4,0), "B");
    private MapMarker c = new MapMarker( new Point(0, 6), "C");
    private MapMarker d = new MapMarker( new Point(0, 8), "D");
    private MapMarker e = new MapMarker( new Point(10, 0), "E");
    private MapMarker f = new MapMarker( new Point(0, 14), "F");
    
    public MapMarkersTest() 
    {
        points = List.of(a, b, c, d, e, f);
    }
    
    @Test
    public void closestPoints_origin()
    {
        var origin = new Point(0, 0);
        
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
