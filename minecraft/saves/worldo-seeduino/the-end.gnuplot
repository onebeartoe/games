

unset key

#min x: -1552
#max x: 834
#min z: 58
#max z: 3280



set xrange [-1650: 950]
set yrange [600: 3400]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "0,0" at 0, 0

plot   'the-end.data'           using 1:3:4 with labels

pause -1 "Hit return to resume"

reset
