

unset key

#min x: -8248
#max x: 4128
#min z: -4674
#max z: 2440



set xrange [-8150: 4100]
set yrange [-4600: 2350]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "0,0" at 0, 0

plot   'overworld.data'           using 1:3:4 with labels

pause -1 "Hit return to resume"

reset
