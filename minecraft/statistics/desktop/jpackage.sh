
jpackage --input target/ \
         --type app-image \
         --main-class org.onebeartoe.desktop.App \
         --main-jar desktop-1.0.jar \
         --dest target/



# With the above configuration the executable is created at this location:
#
#   target/App/bin/App
#
# To specify the application name use the '--name' parameter:
#
#         --name MinecraftCompanionApp \
