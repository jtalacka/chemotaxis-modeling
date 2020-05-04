package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Logic {
    private int xSize=500;
    private int tSize=300000;
    private int size=1500;
    private int stepSize;
    private double B=0.4;
    private double L=25;
    private double dx=0.050;
    private double dt=0.001;
    private double D=0.1;
    private double X=6.2;
    private double r=1;
    private double resultArray[][];
    private ChemoAttractant ca;
    private Density density;

    public Logic()
    {
        stepSize=tSize/size;
        resultArray=new double[size][xSize];
        ca=new ChemoAttractant(tSize,xSize,dx,dt,B);
        density=new Density(tSize,xSize,D,X,r,dx,dt);
        ca.setDensity(density);
        density.setChemoAttractant(ca);
        Action();
    }
    public void Action()
    {
        int t2=0;
        for(int t1=0;t1<tSize-1;t1++){
            for(int x1=0;x1<xSize;x1++){
                density.functionU(0,x1);
                ca.functionV(0,x1);
                density.u[0][x1]=density.u[1][x1];
                ca.v[0][x1]=ca.v[1][x1];
                if(t1%stepSize==0) {
                resultArray[t2][x1]=density.u[0][x1];
                }
                }
            if(t1%stepSize==0) {
                t2++;
            }
        }
            }

    public void results(GraphicsContext gc){
        System.out.print("\n");
        int t=0;
        for(int t1=size-1;t1>0;t1--){
            for(int x1=0;x1<xSize;x1++){
                    gc.setFill(getColor(resultArray[t1][x1]));
                    gc.fillRect(x1, t, 1, 1);
 }
            t++;
           // System.out.print("\n");
        }
    }
    public Color getColor(double number){
        Color color;
            color = new Color(1, 0, 1, number/10);
        return color;
    }
}
