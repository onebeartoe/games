module org.onebeartoe.desktop {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens org.onebeartoe.desktop to javafx.fxml;
//    opens org.onebeartoe.desktop to javafx.media;
    exports org.onebeartoe.desktop;
}
