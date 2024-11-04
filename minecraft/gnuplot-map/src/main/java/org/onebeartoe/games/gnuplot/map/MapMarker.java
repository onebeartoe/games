
package org.onebeartoe.games.gnuplot.map;

import java.awt.Point;

import java.io.File;
import java.util.List;
import javafx.geometry.Point3D;

record MapMarker(String id, Point3D location, String description, Boolean valid)
{
}
