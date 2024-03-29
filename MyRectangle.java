
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MyRectangle extends MyBoundedShape { 

   // constructor with input values
   public MyRectangle(
      double x1, double y1, double x2, double y2, Color strokeColor, Color filledColor, boolean filled) {
         
      super(x1, y1, x2, y2, strokeColor, filledColor, filled);
   } 
   
   public MyRectangle() {
      super();
   } 
   
   public void draw(GraphicsContext gc) {
      Objects.requireNonNull(gc, "GraphicsContext must not be null");
      
      if (true == isFilled()) {
         drawFilledRectangle(gc);
      }
      else {
         drawStrokeRectangle(gc);
      }
   } 
   
   private void drawStrokeRectangle(GraphicsContext gc) {
      gc.setStroke(getStrokeColor());
      gc.strokeRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
   
   private void drawFilledRectangle(GraphicsContext gc) {
      gc.setFill(getFilledColor());
      gc.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
} 
