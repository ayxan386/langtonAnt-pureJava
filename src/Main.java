import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    public static int width = 1000,height = 1000;
    JFrame frame;
    public static void main(String[] args) {
        Main m = new Main();
        m.init();
        double fps = 120;
        long per = (long)(1e+9/fps);
        long curr = System.nanoTime();
        while(true)
        {
            if(curr + per <= System.nanoTime())
            {
                curr+=per;
                m.draw();
            }
        }
    }
    public Main()
    {
        frame = new JFrame("JFrame");
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        myPanel mp = new myPanel();
        frame.add(mp);
        frame.setVisible(true);
    }
    public static int[][] grid;
    public static int w;
    public static int rows,cols;
    public static int xa,ya,dir;
    public void init()
    {
        w = 8;
        rows = height / w;
        cols = width / w;
        Random r = new Random();
        xa = rows/2;//Math.abs(r.nextInt()) % rows;
        ya = cols/2;//Math.abs(r.nextInt()) % cols;
        dir = Math.abs(r.nextInt()) % 4;
        grid = new int[rows][cols];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                grid[i][j] = 0;
    }
    int frames = 0;
    public void draw()
    {
        System.out.println(frames);
        frames++;
        drawGrid(grid);

        if(grid[xa][ya] == 0)
        {
            grid[xa][ya] = 1;
            dir = (dir + 1) % 4;
        }
        else
        {
            grid[xa][ya] = 0;
            dir = (dir - 1 + 4) % 4;
        }
        switch (dir)
        {
            case 0:
                xa = (xa + 1) % rows;
                break;
            case 1:
                ya = (ya + 1) % cols;
                break;
            case 2:
                xa = (xa - 1 + rows) % rows;
                break;
            case 3:
                ya = (ya - 1 + cols) % cols;
                break;
            default:
                System.out.println("Something went wrong");
        }
        frame.repaint();
    }
    public void drawGrid(int[][] grid)
    {
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++) {
                Color c = grid[i][j] == 1 ? Color.black : Color.white;
                if(i == xa && j == ya)c = Color.red;
                myPanel.drawRect(i * w, j * w, w-1, w-1, c,true);
            }
    }

}
