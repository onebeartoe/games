
unset key

set xrange [-4500: 4000]
set yrange [-4500: 4000]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "0,0" at 0, 0

plot 'overworld-map.data' using 1:2:3 with labels
# un-comment the next line if you want to include several files with plot data
#replot 'overworld-village-bases.data' using 1:2:3 with labels
#replot 'overworld-minimal-bases.data' using 1:2:3 with labels 

pause -1 "Hit return to resume"

reset
