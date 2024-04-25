

unset key

set xrange [-1800: 550]
set yrange [-1200: 600]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "0,0" at 0, 0

plot   'nether.data'           using 1:3:4 with labels

pause -1 "Hit return to resume"

reset
