package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaController implements Initializable {
    private File file;
    private Media md;
    private MediaPlayer mp;

    @FXML
    private MediaView mv;
    private Stage stage;
    public void setStage(Stage st){
        stage=st;
    }

    @FXML
    private Slider volSlider;
    @FXML
    private Slider vslider;

    @FXML
    private Label lblTime;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        file = new File("//home//chhaily//Videos//RitmoDeLaNoche.mp4");
        md=new Media(file.toURI().toString());
        mp=new MediaPlayer(md);
        mv.setMediaPlayer(mp);
    }

    public void Play(){
        mp.play();
    }

    public void Pause(){
        mp.pause();
    }

    public void Reset(){
        mp.stop();
    }

    public void open(){
        FileChooser fc = new FileChooser();
        ExtensionFilter ftl = new ExtensionFilter("Media mp4", "*.mp4");
        ExtensionFilter ftl2 = new ExtensionFilter("Media wav", "*.wav");
        fc.getExtensionFilters().add(ftl);
        fc.getExtensionFilters().add(ftl2);
        fc.setTitle("Open Media File");
        file = fc.showOpenDialog(stage);
        if(file != null){
            md = new Media(file.toURI().toString());
            mp=new MediaPlayer(md);
            mv.setMediaPlayer(mp);
            mp.play();

            volSlider.setValue(50);
            volSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                    double v = volSlider.getValue() / 100;
                    mp.setVolume(v);
                }
            });

            mp.currentTimeProperty().addListener((observable, oldValue, newValue)-> {
               if(!vslider.isValueChanging()) {
                   double total = mp.getTotalDuration().toMillis();
                   double current = mp.getCurrentTime().toMillis();
                   vslider.setMax(total);
                   vslider.setValue(current);
                   lblTime.setText(getTimeString(current) + "/" + getTimeString(total));
               }
            });

            vslider.valueProperty().addListener(ov->{
                if(vslider.isValueChanging()){
                    mp.seek(new Duration(vslider.getValue()));
                    lblTime.setText(getTimeString(vslider.getValue()) + "/" + getTimeString(vslider.getMax()));
                }
            });
        }
    }

    public void close(){
        stage.close();
    }

    public static String getTimeString(double millis){
        millis /= 1000;
        String s = formatTime(millis % 60);
        millis /= 60;
        String m = formatTime(millis % 60);
        millis /= 60;
        String h = formatTime(millis % 24);
        return h + ":" + m + ":" + s;
    }

    public static String formatTime(double time){
        int t = (int)time;
        if(t>9) {
            return String.valueOf(t);
        }
        return "0" + t;
    }

}
