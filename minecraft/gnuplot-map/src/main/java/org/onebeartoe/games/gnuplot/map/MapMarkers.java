

package org.onebeartoe.games.gnuplot.map;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.geom.Point2D;

/**
 *
 * @author roberto
 */
public class MapMarkers 
{
    public static 
    List<MapMarker> closestPoints(Point source, List<MapMarker> points)
    {
        List<DistancedMapMarker> distancedMarkers = points.stream().map((m) -> 
        {
            var distance = Point2D.distance(source.x, source.y, m.location().x, m.location().y);

            var dm = new DistancedMapMarker(m.location(), m.label(), distance);

            return dm;
        })
        .collect(Collectors.toList());

        distancedMarkers.sort((m1, m2) -> m1.distance().compareTo(m2.distance()));
        
        return distancedMarkers.stream().map(dm -> new MapMarker(dm.location(), dm.label()))
                .collect(Collectors
                .toList());
    }
}

