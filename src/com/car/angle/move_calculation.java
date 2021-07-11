package com.car.angle;

import java.util.ArrayList;
import java.util.List;

public class move_calculation {
    private Arc_calculation arc_cal = new Arc_calculation();
    double width_Car = 0.165;//车两轮的距离，单位m
    double stepping_angle = 0.01*Math.PI; //电机的步进角
    int n = 4;  //驱动模块细分度

    /**
     *
     * @param n 细分度
     * @return 在n细分度，每一步转动的角度
     */
    public double getScale_division_angle(int n){
        double result = stepping_angle/n;
        return  result;
    }

    /**
     * 计算小车每步行驶的距离
     * @param n 小车电机细分度
     * @return 每步的距离
     */
    public double getEach_distance(int n){
        double result = 32*getScale_division_angle(n);
        return result;
    }

    /**
     * 根据距离计算小车要行驶的总步数
     * @param m 小车行驶距离
     * @param n 小车细分度
     * @return 小车行驶m距离所需要的总步数
     */
    public  double getSum_stepping(double m, int n){
        double sda = getScale_division_angle(n);
        double result = m/(32*sda);
        return result;
    }

    /**
     * 根据距离和时间计算小车每秒行驶的步数
     * @param m 小车移动距离，单位mm
     * @param n 小车细分度
     * @param t 小车行驶时间
     * @return  小车每秒行驶的步数
     */
    public double getSecond_stepping(double m, int n, double t){
        double ss = getSum_stepping(m,n);
        double result = ss/t;
        return result;
    }

    /**
     * 角度转弧度
     * @param angle
     * @return
     */
    public double Degree_to_radian(int angle){
        double radian = angle*(Math.PI/180);
        return radian;
    }

    /**
     *轮子速度转变为每秒步数
     * @param v：轮子的速度
     * @return 轮子每秒的步数
     */
    public double v_to_step(double v){
        double mm_V = v*1000;//米每秒变成毫米每秒
        double result = mm_V/getEach_distance(n);
        return result;

    }

    /**
     * 根据指定直线的要求，计算车要旋转的时间和直走的时间
     * @param m 直走的距离，单位为米
     * @param angle 旋转的角度，注意单位是度
     * @return 带旋转时间和直走时间的列表，单位毫秒
     */
    public List<Double> getStraight_walking(double m, double angle){
        int angle1 = (int)angle;
        double esd = Math.PI/10;//每秒转动的弧度
        double radian = Degree_to_radian(Math.abs(angle1));//需要转动的度数转弧度
        double t_turn = radian/esd*1000;
        double t_line = m/0.1*1000;//单位为毫秒
        double dir = 0;
        if (angle<0){
            dir = -1;
        }else{
            dir = 1;
        }

        List<Double> result = new ArrayList<>();
        result.add(t_turn);
        result.add(t_line);
        result.add(dir);
        return result;
    }

    /**
     * 动作：小车向左旋转一周
     * 注意：小车线速度定死为 0.1m/s
     * @param r 路径圆的半径,单位为m
     * @return 返回包含左右轮速度的数组,单位为m/s
     */
    public List<Double> getCircle_walking(double r){
        double circumference = 2*Math.PI*r;//圆的周长
        double T = circumference/0.1;//一圈的周期
        double angular_v = 2*Math.PI/T;//车的角速度
        double left_car_l = r-width_Car/2;//车的左轮距离原点的距离
        double right_car_l = r+width_Car/2;//车的左轮距离原点的距离
        double v_left = angular_v*left_car_l;
        double v_right = angular_v*right_car_l;
        List<Double> result = new ArrayList<>();
        result.add(v_left);
        result.add(v_right);
        return result;

    }
    /**
     * 计算两点之间的夹角
     * @param p1
     * @param p2
     * @return 单位弧度
     */
//    public double anglePoint(int x1, int y1, int x2, int y2){
//        System.out.println(x1);
//        System.out.println(x2);
//        System.out.println(y1);
//        System.out.println(y2);
//        double x1_ = (double) x1;
//        double x2_ = (double) x2;
//        double y1_ = (double) y1;
//        double y2_ = (double) y2;
//        System.out.println("x:"+(x2_ - x1_));
//        System.out.println("y:"+(y2_ - y1_));
//        System.out.println(Math.toDegrees(Math.atan((x2_ - x1_) / (y2_ - y1_))));
//        return Math.toDegrees(Math.atan((x2_ - x1_) / (y2_ - y1_)));
//    }
    /**
     * 计算两点之间的夹角
     * @param p1
     * @param p2
     * @return 单位弧度
     */
    public double anglePoint(int x1, int y1, int x2, int y2){
//        double x1_ = x1/100;
//        double x2_ = x2/100;
//        double y1_ = y1/100;
//        double y2_ = y2/100;
//        System.out.println(x1);
//        System.out.println(x2);
//        System.out.println(y1);
//        System.out.println(y2);
        double fix_x =  x1;
        double fix_y =  y1;
        double angle1 = Math.atan2(y1-fix_y,x1-fix_x);
        double angle2 = Math.atan2(y2-fix_y,x2-fix_x);
        //double a = p2.getX() - p1.getX();
        //double b = p2.getY() - p1.getY();
        //double angle = Math.atan2(b,a);
//        System.out.println(angle1);
//        System.out.println(angle2);
        double angle = angle2 - angle1;
        return angle;
    }
    /**
     * 两点之间距离
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public double disPoint(int x1, int y1, int x2, int y2){
        int x_sub = x2-x1;
        int y_sub = y2-y1;
//        System.out.println(x_sub);
//
        return (Math.sqrt(x_sub*x_sub+y_sub*y_sub)) / 100;
    }

    public List<List<Integer>> cal(List<Double> angle, List<Double> dis){
        List<Double> car_change_Degree = new ArrayList<Double>();
        List<List<Integer>> result_list = new ArrayList<List<Integer>>();
        car_change_Degree = arc_cal.Car_change_degree(angle);
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        List<Integer> result3 = new ArrayList<>();
        for (int i=0;i<dis.size();i++){
            List<Double> result = new ArrayList<Double>();
            result = getStraight_walking(dis.get(i),car_change_Degree.get(i));
            double answer1 = arc_cal.rounding(result.get(0));
            double answer2 = arc_cal.rounding(result.get(1));
            double answer3 = result.get(2);
            result1.add((int) answer1);//旋转时间
            result2.add((int) answer2);//距离时间
            result3.add((int) answer3);//方向
            System.out.println("Change"+i+":\n"+
                    ""+Math.round(result.get(0))+
                    " ,"+Math.round(result.get(1))+
                    " ,"+Math.round(result.get(2)));
        }
        result_list.add(result1);
        result_list.add(result2);
        result_list.add(result3);
        return result_list;
    }

//    public static void main(String[] args) {
//        move_calculation mc = new move_calculation();
//        double m = 1000;   //小车移动距离，单位mm
//        double t = 10;  //小车所需时间
//        //int angle = 180;
//        //System.out.println(angle+"to"+mc.getDegree_to_radian(angle));
//        List<Double> circle_walking = mc.getCircle_walking(0.5);
//        System.out.println("left:"+circle_walking.get(0)+"； right:"+circle_walking.get(1));
//        System.out.println("左轮每秒移动的步数："+mc.v_to_step(circle_walking.get(0)));
//        System.out.println("右轮轮每秒移动的步数："+mc.v_to_step(circle_walking.get(1)));
//        List<Double> line_walking = mc.getStraight_walking(1,90);
//        System.out.println("小车走指定直线的参数："+"t_turn:"+line_walking.get(0)+", t_line:"+line_walking.get(1));
//        //System.out.println("小车每步的移动距离："+mc.getEach_distance(n)+"mm");
//        //System.out.println("小车移动"+m+"毫米所需的总步数："+mc.getSum_stepping(m,n));
//        System.out.println("小车每秒移动的步数："+mc.getSecond_stepping(m,mc.n,t));
//
//
//    }
}
