module org.onebeartoe.games.type.o.rama {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.onebeartoe.games.type.o.rama to javafx.fxml;
    exports org.onebeartoe.games.type.o.rama;
}
