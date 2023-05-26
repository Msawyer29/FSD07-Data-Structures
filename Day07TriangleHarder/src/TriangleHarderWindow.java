import javax.swing.*;
import java.awt.*;

public class TriangleHarderWindow extends JFrame {
    public static void main(String[] args) {

        TriangleHarderWindow dd = new TriangleHarderWindow();
    }

    public TriangleHarderWindow() {
        setLayout(new BorderLayout());
        setSize(500, 375);
        setTitle("Triangle Harder Fractal");
        DrawingArea drawingArea = new DrawingArea();
        add("Center", drawingArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on the screen
        setVisible(true);
    }

    private class DrawingArea extends Canvas {
        @Override
        public void paint(Graphics g) {
            int x1 = 10, y1 = getHeight() - 10;
            int x2 = getWidth() - 5, y2 = getHeight() - 5;
            int x3 = (x1 + x2) / 2;
            int y3 = 5;
            //
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x1, y1, x3, y3);
            g.drawLine(x3, y3, x2, y2);
            drawTriangleFractal(g, x1, y1, x2, y2, x3, y3);
        }

        private void drawTriangleFractal(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
            // base case - recursion must end at some point
            double edgeLen = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
            if (edgeLen < 10) return;
            // compute middle points of each edge
            int xA = (x1+x2)/2;
            int yA = (y1+y2)/2;
            int xB = (x3+x2)/2;
            int yB = (y3+y2)/2;
            int xC = (x1+x3)/2;
            int yC = (y1+y3)/2;
            //
            g.drawLine(xA, yA, xB, yB);
            g.drawLine(xA, yA, xC, yC);
            g.drawLine(xC, yC, xB, yB);
            // go on to the next iteration
            drawTriangleFractal(g, x1, y1, xA, yA, xC, yC);
            drawTriangleFractal(g, xA, yA, x2, y2, xB, yB);
            drawTriangleFractal(g, xC, yC, xB, yB, x3, y3);
        }
    }
}
