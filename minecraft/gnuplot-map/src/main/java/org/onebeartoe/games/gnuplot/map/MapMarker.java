
package org.onebeartoe.games.gnuplot.map;

import javafx.geometry.Point3D;

record MapMarker(String id, Point3D location, String description, Boolean valid, String line)
{
}
