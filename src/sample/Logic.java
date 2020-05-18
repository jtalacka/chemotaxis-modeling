package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Logic {
    private int xSize=500;
    private int tSize=800000;
    private int size=400;
    private int stepSize;
    private double B=0.83;
    private double L=20;
    private double dx=0.050;
    private double dt=0.0005;
    private double D=0.1;
    private double X=7;
    private double alpha=1;
    private double resultArray[][];
    private double resultArrayca[][];
    private ChemoAttractant ca;
    private Density density;

    public Logic()
    {
        stepSize=tSize/size;
        resultArray=new double[size][xSize];
        resultArrayca=new double[size][xSize];
        ca=new ChemoAttractant(tSize,xSize,dx,dt,B);
        density=new Density(tSize,xSize,D,X,alpha,dx,dt);
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
                resultArrayca[t2][x1]=ca.v[0][x1];
               // density.alphaRandom();
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
                    gc.setFill(getColord(resultArray[t1][x1]));
                    gc.fillRect(x1, t*2, 1, 2);
 }
            t++;
           // System.out.print("\n");
        }
        t=0;
        for(int t1=size-1;t1>0;t1--){
            for(int x1=0;x1<xSize;x1++){
                gc.setFill(getColorca(resultArrayca[t1][x1]));
                gc.fillRect(x1+500, t*2, 1, 2);
            }
            t++;
            // System.out.print("\n");
        }
        System.out.println(Density.maxNumber);
    }
    public Color getColord(double number){
        Color color;
            color = new Color(1, 1, 1, number/density.maxNumber);
        return color;
    }
    public Color getColorca(double number){
        Color color;
        color = new Color(1, 1, 1, number/(ca.maxNumber));
        return color;
    }
}
