
unset key

set xrange [-11500: 4000]
set yrange [-4500: 4000]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "0,0" at 0, 0

plot   'overworld-map.data'           using 1:2:3 with labels
replot 'overworld-village-bases.data' using 1:2:3 with labels
replot 'overworld-minimal-bases.data' using 1:2:3 with labels 

# show the entries as a dot with tool tip labels
replot 'points-of-interest.data' using 1:2:3 \
     with labels hypertext point pt 7 ps var lc rgb "#ffee99", \
     'points-of-interest.data' using 1:2 \
     with points pt 6 ps var lc rgb "black" lw 0.1


replot 'ancient-cities.data' using 1:2:3 \
     with labels hypertext point pt 7 ps var lc rgb "#0000ff", \
     'ancient-cities.data' using 1:2 \
     with points pt 6 ps var lc rgb "black" lw 0.1



pause -1 "Hit return to resume"

reset
