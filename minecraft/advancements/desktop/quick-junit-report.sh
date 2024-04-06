

# This is an answer on Stackoverflow
#
#       https://stackoverflow.com/a/23958874/803890


# Run tests and generate .xml reports
mvn test

# Convert .xml reports into .html report, but without the CSS or images
mvn surefire-report:report-only

# Put the CSS and images where they need to be without the rest of the
# time-consuming stuff
mvn site -DgenerateReports=false

