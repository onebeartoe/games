#!/usr/bin/bash
jpackage --input target/ --type app-image --main-class Wrapplet --main-jar edu-rubiks-cube-jar-with-dependencies-jar-with-dependencies.jar          --dest target
# once it is done (on windows), execute the application with 
#
#   target/Wrapplet/Wrapplet.exe
#
#
#jpackage --input target/ \
#         --type app-image \
#         --main-class Wrapplet \
#         --main-jar edu-rubiks-cube-1.0.jar \
#         --dest target/
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