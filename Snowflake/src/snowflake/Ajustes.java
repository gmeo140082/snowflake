/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowflake;
import com.sun.javafx.geom.Vec3f;
import java.util.Vector;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.CV_HOUGH_GRADIENT;
import org.opencv.objdetect.CascadeClassifier;
/**
 *
 * @author gito_
 */
public class Ajustes {
        public Mat TransHist(Mat frame){
        double t[];
        for(int i=0;i<frame.rows();i++)
        {
            for(int j=0;j<frame.cols();j++)
            {
                t=frame.get(i, j);
                t[0]=(t[0]-40)*(130/(130-40));
                t[1]=(t[1]-40)*(130/(130-40));
                t[2]=(t[2]-40)*(130/(130-40));
                frame.put(i, j, t);
            }
        }
        return frame;
    }
    public Mat ojos(Mat frame,Mat oj[])
    {
        CascadeClassifier clas;
        clas= new CascadeClassifier();
        clas.load("C:\\opencv\\sources\\data\\haarcascades\\haarcascade_eye.xml");
        Mat mRgba=new Mat();
        Mat mGrey=new Mat();
        frame.copyTo(mGrey);
        frame.copyTo(mRgba);
        MatOfRect faces = new MatOfRect();
        Imgproc.cvtColor( mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist( mGrey, mGrey );
        clas.detectMultiScale(mGrey, faces);
        int cont=0;
        for (Rect rect : faces.toArray()) {
            if(cont>1)
                break;
            Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x
                    + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            oj[cont]=frame.submat(rect);
            cont++;
        }
        return frame;
    }
    public Mat Circulos(Mat frame)
    {
        Mat gray = new Mat();
        frame.copyTo(gray);
        Mat circles = new Mat();
	int minRadius = 10;
	int maxRadius = 18;
	Imgproc.HoughCircles(gray, circles, 1, minRadius, maxRadius);
        return circles;
    }
}
