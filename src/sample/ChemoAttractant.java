package sample;

public class ChemoAttractant {
    public double[][] v;
    public Density density;
    private double dx;
    private double dt;
    private double B;
    private int xSize;

    public ChemoAttractant(int tSize,int xSize,double dx,double dt,double B)
    {   this.xSize=xSize;
        this.dx=dx;
        this.dt=dt;
        this.B=B;
        v=new double[2][xSize];
        initialNumber();
    }
    public void setDensity(Density density){
        this.density=density;
    }
    private void initialNumber()
    {
        for(int x=0;x<xSize;x++){
            v[0][x]=0;
        }
    }

    public void functionV(int t,int x)
    {
        v[t+1][x]=functionV1(t,x)+functionV2(t,x)-v[t][x];
        v[t+1][x]*=dt;
        v[t+1][x]+=v[t][x];
    }
    public double functionV1(int t,int x)
    {
        double v1;
        v1=v[t][getX(x+1)]-2*v[t][x]+v[t][getX(x-1)];
        v1/=Math.pow(dx,2);
        return v1;

    }
    public double functionV2(int t,int x)
    {
        double v2;
        double temp;
        v2=density.u[t][x];
        temp=B*density.u[t][x];
        temp+=1;
        v2/=temp;

        return  v2;
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
