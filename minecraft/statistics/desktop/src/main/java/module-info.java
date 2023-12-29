


module org.onebeartoe.desktop 
{
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    // This is a onebeartoe dependency.
    requires cli;
    

    // you can find the 'requires' for a non-Java-module JAR by issusing this command:
    //         $ jar \
    //                  --file=/home/roberto/.m2/repository/com/googlecode/json-simple/json-simple/1.1/json-simple-1.1.jar \
    //                  --describe-module
    // Then you copy and paste whatever is before the '@' symbol.

    requires json.simple;
    
    opens org.onebeartoe.desktop to javafx.fxml;
//    opens org.onebeartoe.desktop to javafx.media;
    exports org.onebeartoe.desktop;
}
