/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import javafx.fxml.Initializable;
import java.sql.Timestamp;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Control;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Amer
 */
public abstract class XchController implements Initializable
{

    protected static MainScreenController mainScreen;
    protected static XchDatabase database;
    protected static XchSettings settings;

    protected Timestamp getTimeStamp(String datetime){ //in case of error this function will return null
        try{

            Timestamp st = Timestamp.valueOf(datetime);
            return st;
        }catch(Exception ex){
            return null;
        }
    }
    
    protected void shakeControl(Control cont) throws InterruptedException
    {

        // create a rotation transform starting at 0 degrees, rotating about pivot point 50, 50.
        final Rotate rotationTransform = new Rotate(5);
        cont.getTransforms().add(rotationTransform);
        rotationTransform.setAxis(Rotate.Z_AXIS);
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
            .add(
                new KeyFrame(
                    Duration.seconds(0.1),
                    new KeyValue(rotationTransform.angleProperty(), 0)
                )
            );
        rotationAnimation.setCycleCount(6);
        rotationAnimation.play();   
        cont.requestFocus();
    }
}
