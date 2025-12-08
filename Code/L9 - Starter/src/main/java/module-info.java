module edu.westga.cs1302.practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;

    opens edu.westga.cs1302.practice.view to javafx.fxml;
    exports edu.westga.cs1302.practice;
}
