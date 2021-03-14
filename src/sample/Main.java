package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ListView and Rectangle(FadeTransition)"); // заголовок формы

        // наша панель
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(70);

        // эфект сепия
        SepiaTone sepiaTone = new SepiaTone();

        // картинка
        Image image = new Image(getClass().getResourceAsStream("enot.jpeg")); // откуда
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(450); // размеры
        imageView.setFitWidth(580);
        imageView.setEffect(sepiaTone); // эфект
        // добавление на панель
        GridPane.setConstraints(imageView, 0, 0); // откуда (место)
        GridPane.setColumnSpan(imageView, 3); // сколько ячеек займент
        root.getChildren().addAll(imageView); // добавить на панель

        // слайдеры
        Slider opacitySlider = new Slider(0,1,0.8);
        Slider sepiaSlider = new Slider(0,1,0);
        Slider sizeSlider = new Slider(0,1,1);

        // лайбел (метки)
        Label opacityLabel = new Label("Opacity ");
        Label sepiaLabel = new Label("Sepia ");
        Label sizeLabel = new Label("Size ");

        Label opacityValue = new Label(Double.toString(opacitySlider.getValue()));
        Label sepiaValue = new Label(Double.toString(sepiaSlider.getValue()));
        Label sizeValue = new Label(Double.toString(sizeSlider.getValue()));

        // расположение на панели
        GridPane.setConstraints(opacityLabel, 0, 1);
        GridPane.setConstraints(sepiaLabel, 0, 2);
        GridPane.setConstraints(sizeLabel, 0, 3);

        GridPane.setConstraints(opacitySlider, 1, 1);
        GridPane.setConstraints(sepiaSlider, 1, 2);
        GridPane.setConstraints(sizeSlider, 1, 3);

        GridPane.setConstraints(opacityValue, 2, 1);
        GridPane.setConstraints(sepiaValue, 2, 2);
        GridPane.setConstraints(sizeValue, 2, 3);

        // действие на слайдере opacitySlider
        opacitySlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            imageView.setOpacity(newValue.doubleValue());
            opacityValue.setText(String.format("%.2f", newValue));
        }));

        // действие на слайдере sepiaSlider
        sepiaSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            sepiaTone.setLevel(newValue.doubleValue());
            sepiaValue.setText(String.format("%.2f", newValue));
        }));

        // действие на слайдере sizeSlider
        sizeSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            imageView.setScaleX(newValue.doubleValue());
            imageView.setScaleY(newValue.doubleValue());
            sizeValue.setText(String.format("%.2f", newValue));
        }));

        // добавить на панель
        root.getChildren().addAll(opacityLabel, opacitySlider, opacityValue);
        root.getChildren().addAll(sepiaLabel, sepiaSlider, sepiaValue);
        root.getChildren().addAll(sizeLabel, sizeSlider, sizeValue);

        // добавление на сцены | на форму
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);  // размер формы и сцена
        primaryStage.show(); // отобразить
    }


    public static void main(String[] args) {
        launch(args);
    }
}