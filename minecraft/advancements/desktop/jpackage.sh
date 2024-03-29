
jpackage --input target/ \
         --type app-image \
         --main-class org.onebeartoe.desktop.App \
         --main-jar desktop-1.0-jar-with-dependencies.jar \
         --dest target/



# With the above configuration the executable is created at this location:
#
#   target/App/bin/App
#
# To specify the application name use the '--name' parameter:
#
#         --name MinecraftCompanionApp \
#
#
# This was inspired by 'Trick #3' in the following blog.
#
#   https://blogs.oracle.com/javamagazine/post/java-11-tricks-generics-inheritance-jshell
#
#
#
# If errors occur about copy object, then make sure binutils in installed
#
#   sudo apt install binutils
#