import javax.swing.*;
import java.awt.*;

public class SquareFractalWindow extends JFrame {
    public static void main(String[] args) { // create a new instance of SquareFractalWindow
        SquareFractalWindow window = new SquareFractalWindow();
    }

    public SquareFractalWindow() {
        setLayout(new BorderLayout()); // layout manager of the window
        setSize(500, 500); // initial window size
        setTitle("Square Fractal"); // title of window
        DrawingArea drawingArea = new DrawingArea(); // create an instance of DrawingArea class
        add("Center", drawingArea); // add DrawingArea instance to the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // program exits when the window is closed
        setLocationRelativeTo(null); // center window on the screen
        setVisible(true); // make the window visible
    }

    // DrawingArea class represents the area where fractals are drawn by extending Canvas
    private class DrawingArea extends Canvas {

        // paint method is called to paint the canvas
        @Override
        public void paint(Graphics g) {
            // calculate the initial size of the square and its position on the canvas
            int size = Math.min(getHeight(), getWidth()) / 3;
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;
            drawSquareFractal(g, x, y, size, 5); // draw the initial fractal
        }

        // method that draws a square fractal (it is recursive)
        private void drawSquareFractal(Graphics g, int x, int y, int size, int depth) {
            if (depth == 0) return; // base case of the recursion, if 0 recursion stops
            g.drawRect(x, y, size, size); // draw a square at the current level of recursion
            int newSize = size / 3; // calculate the size and position of the next squares
            // draw the next level of the fractal (four new squares around the current one)
            // the following lines are recursive as they call themselves with updated arguments
            drawSquareFractal(g, x - newSize, y + newSize, newSize, depth - 1);  // left middle
            drawSquareFractal(g, x + newSize, y - newSize, newSize, depth - 1);  // top middle
            drawSquareFractal(g, x + newSize, y + size, newSize, depth - 1);  // bottom middle
            drawSquareFractal(g, x + size, y + newSize, newSize, depth - 1);  // right middle
        }
    }
}
