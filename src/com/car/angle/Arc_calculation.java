package com.car.angle;

import java.util.ArrayList;
import java.util.List;

class Arc_calculation {
    /**
     * 根据参数方程计算点的x坐标
     * @param i
     * @return
     */
    public double getPoint_x(int i){
        double u = (double)i/10;
        //double x = 0.3960*Math.cos(2.65*(u+1.4));
        double x = (-0.8)*((0.4-Math.cos(u))*Math.cos(u)+0.6);//另外一条弧线的x坐标表达式
        return x;
    }

    /**
     * 根据参数方程计算点的y坐标
     * @param i
     * @return
     */
    public double getPoint_y(int i){
        double u = (double)i/10;
        //double y=(-0.99)*Math.sin(u+1.4);
        double y = 0.8*(0.4-Math.cos(u))*Math.sin(u);//另外一条弧线的y坐标表达式
        return y;
    }

//    /**
//     * 计算两点之间的距离
//     * @param p1
//     * @param p2
//     * @return 单位米
//     */
//    public double distancePoint(Point p1, Point p2){
//        double a = p1.getX() - p2.getX();
//        double b = p1.getY() - p2.getY();
//        double line_l = Math.sqrt(a*a+b*b);
//        return line_l;
//    }
//
//    /**
//     * 计算两点之间的夹角
//     * @param p1
//     * @param p2
//     * @return 单位弧度
//     */
//    public double anglePoint(Point p1, Point p2){
//        double fix_x = p1.getX();
//        double fix_y = p1.getY();
//        //double fix_x = 0;
//        //double fix_y = 0;
//        double angle1 = Math.atan2(p1.getY()-fix_y,p1.getX()-fix_x);
//        //System.out.println(angle1);
//        double angle2 = Math.atan2(p2.getY()-fix_y,p2.getX()-fix_x);
//        //double a = p2.getX() - p1.getX();
//        //double b = p2.getY() - p1.getY();
//        //double angle = Math.atan2(b,a);
//        double angle = angle2 - angle1;//最初
//        //double angle = angle1 - angle2;
//        return angle;
//    }

    /**
     * 弧线按要求取n个点，计算每相邻两个点之间的距离。
     * n个点，形成n-1个直线（间隔）
     * @return 距离列表，单位为m。
     */
//    public List<Double> getDBTP(){
//        List<Double> p_dis = new ArrayList<Double>();
//        for(int i=0;i<=32;i++) {
//            Point p1 = new Point();
//            double x1=getPoint_x(i);
//            double y1=getPoint_y(i);
//            p1.setX(x1);
//            p1.setY(y1);
//
//            Point p2 = new Point();
//            int i2 = i+1;
//            double x2=getPoint_x(i2);
//            double y2=getPoint_y(i2);
//            p2.setX(x2);
//            p2.setY(y2);
//
//            //p1.displayPoint();
//            //p2.displayPoint();
//            if(i!=32){
//                p_dis.add(distancePoint(p1,p2));
//                //System.out.println("i1:"+i+" i2:"+i2);
//            }
//        }
//        return p_dis;
//    }

    /**
     * 弧线按要求取n个点，计算每相邻两个点形成的夹角。
     * n个点，形成n-1个直线（间隔）
     * @return 角度列表，单位弧度。
     */
//    public List<Double> getABTP(){
//        List<Double> p_ang = new ArrayList<Double>();
//
//        for(int i=0;i<=32;i++) {
//            Point p1 = new Point();
//            double x1=getPoint_x(i);
//            double y1=getPoint_y(i);
//            p1.setX(x1);
//            p1.setY(y1);
//
//            Point p2 = new Point();
//            int i2 = i+1;
//            double x2=getPoint_x(i2);
//            double y2=getPoint_y(i2);
//            p2.setX(x2);
//            p2.setY(y2);
//
//            if(i!=32){
//                p_ang.add(anglePoint(p1,p2));
//                //System.out.println("i1:"+i+" i2:"+i2);
//            }
//        }
//        return p_ang;
//    }

    /**
     * 在坐标轴上的两条直线，根据每个直线与x轴的夹角，计算两个直线的夹角的变化角度。
     * @param radian_1 前一条直线与X轴的夹角。
     * @param radian_2 后一条直线//单位为弧度制
     * @return 两直线的变化角度，
     * 值为负，逆时针旋转，为正，顺时针旋转.单位为角度。
     */
    public double getChange_Degree(double radian_1,double radian_2){
        double degree_1 = radian_1*(180/Math.PI);
        double degree_2 = radian_2*(180/Math.PI);
        double result = 0;//结果为‘正’小车‘顺时针’旋转，为‘负’小车‘逆时针’旋转
        if (degree_1>=0 && degree_2>=0){//正角度变正角度
            result = degree_1 - degree_2;

        }else if (degree_1>=0 && degree_2<=0){//正角度变负角度
            if (degree_2>=degree_1-180){
                result = degree_1-degree_2;
            }else{
                result = (degree_1-degree_2)-360;
            }

        }else if(degree_1<0 && degree_2>0){//负角度变正角度
            if (degree_2<=180+degree_1){
                result = degree_1-degree_2;
            }else {
                result = 360-(degree_2-degree_1);
            }

        }else {//负角度变负角度
                result = degree_1 - degree_2;
        }

        return result;
    }

    /**
     * 车走一条被分割成的n-1份的直线的弧线，
     * 两相邻直线，车头从上一条直线末端所处的朝向变到到下一条直线的朝向，车头所需旋转的角度。
     * @param p_ang 弧度列表，n-1份直线与x轴的夹角。
     * @return 车头所需旋转的角度，
     * 值为负，车头逆时针旋转，为正，顺时针旋转
     */
    public List<Double> Car_change_degree(List<Double> p_ang){
        List<Double> result = new ArrayList<Double>();
        double Initial_degree = 0;
        if(p_ang.size()>1)
        for (int i=0;i<p_ang.size();i++){
            double change_Degree = 0;
            if (i==0){
                double change_Degree_x = getChange_Degree(Initial_degree,p_ang.get(i));
                result.add(change_Degree_x);
                change_Degree = getChange_Degree(p_ang.get(i),p_ang.get(i+1));
                result.add(change_Degree);
            }else if(i==p_ang.size()-1){//31
                break;
            }else{
                change_Degree = getChange_Degree(p_ang.get(i),p_ang.get(i+1));
                result.add(change_Degree);
            }
        }
        else
        {
            double change_Degree_x = getChange_Degree(Initial_degree,p_ang.get(0));
            result.add(change_Degree_x);
        }
        return result;

    }
    /*
    public List<Double> getChange_Degree(double radian){
        double degree = radian*(180/Math.PI);



    }


     */

    /**
     * 四舍五入，保留5位小数
     * @param number
     * @return
     */
    public double rounding(double number){
        return (double) Math.round(number * 1) / 1;
    }
    /*
    public int rounding_int(double number){
        double result = (double) Math.round(number * 100000) / 100000
    }
*/
    /**
     * 查看数据类型
     * @param a
     * @return
     */
    private static String getType(Object a) {
        return a.getClass().toString();

    }

//    public static void main(String[] args) {
//        Arc_calculation arc = new Arc_calculation();
//        //List<Object> list = new ArrayList<Object>();
//        List<Double> p_dis = new ArrayList<Double>();
//        List<Double> p_ang = new ArrayList<Double>();
//        List<Double> car_change_Degree = new ArrayList<Double>();
//        p_dis = arc.getDBTP();
//        p_ang = arc.getABTP();
//        car_change_Degree = arc.Car_change_degree(p_ang);
//        System.out.println("p_dis:"+p_dis.size()+
//                " p_ang:"+p_ang.size()+
//                " car_change_Degree:"+car_change_Degree.size());
//
//
//        for (int i=0;i<p_dis.size();i++){
//            System.out.println("间隔:"+i);
//            System.out.println(p_dis.get(i)*1000+"mm");
//            System.out.println(p_ang.get(i)*(180/Math.PI));
//        }
//
//
//
//
//        for (int i =0;i<car_change_Degree.size();i++){
//            System.out.println("变化"+i+
//                    ": "+ car_change_Degree.get(i));//arc.rounding
//        }
//
//    }

//    public List<Double> result(){
//
//        result = mc.getStraight_walking(p_dis.get(i),car_change_Degree.get(i));
//        double answer1 = arc.rounding(result.get(0));
//        double answer2 = arc.rounding(result.get(1));
//        double answer3 = result.get(2);
//    }

}
