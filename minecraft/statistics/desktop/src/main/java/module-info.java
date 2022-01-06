module org.onebeartoe.desktop {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.onebeartoe.desktop to javafx.fxml;
    exports org.onebeartoe.desktop;
}
