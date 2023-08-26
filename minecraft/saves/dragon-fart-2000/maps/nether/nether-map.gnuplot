
unset key

set xrange [-1100: 1100]
set yrange [-1100: 1100]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "Nether Map..."

set label "0,0" at 0, 0

plot 'nether-map.data' using 1:2:3 with labels 

pause -1 "Hit return to continue"

reset
