module org.onebeartoe.desktop 
{
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    requires org.onebeartoe.minecraft;

    requires json.simple;
    
    opens org.onebeartoe.desktop to javafx.fxml;
    
//    opens org.onebeartoe.desktop to org.onebeartoe.minecraft;

    exports org.onebeartoe.desktop;
}
