/*
 Drawing - specified by command line parameters - number of random:
  lines, rectangles and ovals 
*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.Collection;

public class DrawShapesController { 
   @FXML private Canvas canvas;
   
   private static final short DEFAULT_SHAPES_NUMBER = 10;
   
   @FXML
   void drawShapesButtonPressed(ActionEvent event) {
      /*
      String[] argsCopy = DrawShapes.getArgs();
      System.out.println("args = " + argsCopy);
      for (int index = 0; index < argsCopy.length; index++) {
         System.out.printf("ARGS[%d] = %s%n", index , argsCopy[index]);
      }*/
      
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      final int WIDTH  = (int) canvas.getWidth();
      final int HEIGHT = (int) canvas.getHeight();
      
      int shapesNumber = getShapesNumberProxy("4", DrawShapes.CommandLineParameter.UNNAMED);
      
      ArrayList<MyShape> myShapesArrayList = getMyShapesArrayList(shapesNumber, WIDTH, HEIGHT); 

      // clear the Canvas then draw the shapes
      gc.clearRect(0, 0, WIDTH, HEIGHT); 

      for (MyShape shape : myShapesArrayList) {
         shape.draw(gc);
      } 
   }
   
   private MyShapesFactory createMyShapesFactory(final int WIDTH_RANGE, final int HEIGTH_RANGE) {
      final short COLOR_COMPONENT_RANGE = 256;
      final short LINE_WIDTH_RANGE      = 5;
      
      MyShapesFactory myShapesFactory = new MyShapesFactory(COLOR_COMPONENT_RANGE, (short)WIDTH_RANGE, (short)HEIGTH_RANGE, 
                                             LINE_WIDTH_RANGE);
      return myShapesFactory;
   }
   
   private ArrayList<MyShape> getMyShapesArrayList(final int NUMBER_OF_SHAPES,
                                    final int WIDTH_RANGE, final int HEIGTH_RANGE) {
      MyShapesFactory     myShapesFactory   = createMyShapesFactory(WIDTH_RANGE, HEIGTH_RANGE);
      Collection<MyShape> collection        = myShapesFactory.getRandomMyShapesCollection(NUMBER_OF_SHAPES);
      ArrayList<MyShape>  myShapesArrayList = new ArrayList<>(NUMBER_OF_SHAPES);
      
      myShapesArrayList.addAll(collection);
      
      return myShapesArrayList;
   }
   
   public int getShapesNumberProxy(String parameter, DrawShapes.CommandLineParameter commandLineParameter) {
      int shapesNumber = DEFAULT_SHAPES_NUMBER;
      
      try {
         shapesNumber = getShapesNumber(parameter, commandLineParameter);   
      }
      catch( NumberFormatException exception) {
         System.err.println("Exception: " + exception.getMessage());
         Thread.dumpStack();   
      }
      catch( NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
         System.err.println("Exception: " + exception.getMessage());
         Thread.dumpStack();   
      }
      
      return shapesNumber;
   }
   
   public int getShapesNumber(String parameter, DrawShapes.CommandLineParameter commandLineParameter) {
      Objects.requireNonNull(parameter, "parameter must not be null");   
      
      String shapesNumberString = null;
      switch (commandLineParameter) {
         case NAMED:
            shapesNumberString   = DrawShapes.getParameter(parameter);
            
            break;
         default:
            int parameterNumber  = Integer.parseUnsignedInt​(parameter);
            shapesNumberString   = DrawShapes.getParameter(parameterNumber, commandLineParameter);
      }
      
      int shapesNumber = Integer.parseUnsignedInt​(shapesNumberString);
      
      return shapesNumber;
   } 
}

