
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MyOval extends MyBoundedShape {

   // constructor with input values
   public MyOval(
      double x1, double y1, double x2, double y2, Color strokeColor, Color filledColor, boolean filled) {
         
      super(x1, y1, x2, y2, strokeColor, filledColor, filled);
   } 
   
   public MyOval() {
      super();
   } 
   
   public void draw(GraphicsContext gc) {
      Objects.requireNonNull(gc, "GraphicsContext must not be null");
      
      if (true == isFilled()) {
         drawFilledOval(gc);
      }
      else {
         drawStrokeOval(gc);
      }
   } 
   
   private void drawStrokeOval(GraphicsContext gc) {
      gc.setStroke(getStrokeColor());
      gc.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
   
   private void drawFilledOval(GraphicsContext gc) {
      gc.setFill(getFilledColor());
      gc.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
} 
