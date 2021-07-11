public class Point {
    private double x;
    private double y;

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void displayPoint (){
        //输出坐标
        System.out.println("x:"+x+",y:"+y);
    }


}
