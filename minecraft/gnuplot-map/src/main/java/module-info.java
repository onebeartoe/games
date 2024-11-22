module org.onebeartoe.games.gnuplot.map {
    requires java.desktop;
    requires java.prefs;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.onebeartoe.games.gnuplot.map to javafx.fxml;
    exports org.onebeartoe.games.gnuplot.map;
}
