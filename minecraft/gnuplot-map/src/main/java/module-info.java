module org.onebeartoe.games.gnuplot.map {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.onebeartoe.games.gnuplot.map to javafx.fxml;
    exports org.onebeartoe.games.gnuplot.map;
}
