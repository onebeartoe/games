module org.onebeartoe.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens org.onebeartoe.desktop to javafx.fxml;
    exports org.onebeartoe.desktop;
}
