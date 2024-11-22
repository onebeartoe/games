
package org.onebeartoe.games.gnuplot.map;

import java.util.List;
import java.util.stream.Collectors;
import java.awt.geom.Point2D;
import javafx.geometry.Point3D;

/**
 *
 */
public class MapMarkers 
{
    public static 
    List<MapMarker> closestPoints(Point3D source, List<MapMarker> points)
    {
        List<DistancedMapMarker> distancedMarkers = points.stream().map((m) -> 
        {
            var distance = Point2D.distance(source.getX(), source.getZ(), m.location().getX(), m.location().getZ());

            var dm = new DistancedMapMarker(m.id(), m.location(), m.description(), distance);

            return dm;
        })
        .collect(Collectors.toList());

        distancedMarkers.sort((m1, m2) -> m1.distance().compareTo(m2.distance()));
        
        return distancedMarkers.stream().map(dm -> new MapMarker(dm.id(), dm.location(), dm.description(), true))
                .collect(Collectors
                .toList());
    }
}

