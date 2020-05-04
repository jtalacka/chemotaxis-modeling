package sample;

import java.util.Random;

public class Density {
    private Random generator = new Random(10001);
    private double D;
    private double X;
    private double r;
    private double dx;
    private double dt;
    private int xSize;
    private ChemoAttractant ca;

    public double[][] u;
    public Density(int tSize,int xSize,double D,double X,double r,double dx,double dt)
    {   this.xSize=xSize;
        this.D=D;
        this.X=X;
        this.r=r;
        this.dx=dx;
        this.dt=dt;
        u=new double[2][xSize];
        initialNumber();
    }
    public void setChemoAttractant(ChemoAttractant ca){
        this.ca=ca;
    }

    public double initialNumberGenerator(){
        double initial;
        initial=generator.nextDouble();
        return initial;
    }
    private void initialNumber()
    {
        for(int x=0;x<xSize;x++){
            u[0][x]=initialNumberGenerator();
        }
    }
    public void functionU(int t,int x)
    {
        u[t+1][x]=functionU1(t,x)-functionU2(t,x)+functionU3(t,x);
        u[t+1][x]*=dt;
        u[t+1][x]+=u[t][x];
      //  u[t+1][x]=u[t][x]+dt*(D*((u[t][getX(x+1)]-2*u[t][x]+u[t][getX(x-1)])*(1/Math.pow(dx,2)))-X*((u[t][getX(x+1)]+u[t][x])*(ca.v[t][getX(+1)]-ca.v[t][x])-((u[t][getX(x-1)]+u[t][x])*(ca.v[t][x]-ca.v[t][getX(x-1)])))/(4*dx)+u[t][x]*(1-u[t][x]));
    }
    public double functionU1(int t,int x)
    {
        double u1;
        u1=u[t][getX(x+1)]-2*u[t][x]+u[t][getX(x-1)];
        u1/=Math.pow(dx,2);
        u1*=D;
        return u1;

    }
    public double functionU2(int t,int x)
    {
        double u2;
        double u21,u22;
        u21=(u[t][x]+u[t][getX(x+1)])/2;

        u21*=(ca.v[t][getX(x+1)]-ca.v[t][x]);

        u22=(u[t][x]+u[t][getX(x-1)])/2;
        u22*=(ca.v[t][x]-ca.v[t][getX(x-1)]);
        u2=u21-u22;
        u2/=Math.pow(dx,2);
        u2*=X;
       // u2=X*((u[t][getX(x+1)]+u[t][x])*(ca.v[t][getX(+1)]-ca.v[t][x])-((u[t][getX(x-1)]+u[t][x])*(ca.v[t][x]-ca.v[t][getX(x-1)])))/(2);
        return u2;
    }
    public double functionU3(int t,int x)
    {
        double u3;
        u3=1-u[t][x];
        u3*=u[t][x]*r;
        return u3;
    }
    public int getX(int x){
        if(x==xSize){
            x=0;
        }else if(x==-1){
            x=xSize-1;
        }
        return x;
    }
}
