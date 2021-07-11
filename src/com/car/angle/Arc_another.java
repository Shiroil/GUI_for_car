//import java.util.List;
//public class Arc_another {
////import java.util.Scanner;
//    public void printList( List<Double> my_list){
//        for (int i=0;i<my_list.size();i++){
//            System.out.println(my_list.get(i));
//        }
//    }
//
//    public static void main(String[] args) {
//
/////*
//        //测试01：两点形成的直线与X轴的夹角
//        double result;
//        Arc_calculation arc = new Arc_calculation();
//        Point p1 = new Point();
//        p1.setX(2);
//        p1.setY(2);
//        p1.displayPoint();
//        Point p2 = new Point();
//        p2.setX(2);
//        p2.setY(0);
//        p2.displayPoint();
//        result = arc.anglePoint(p1,p2);
//        System.out.println(result);
//        System.out.println(result*(180/Math.PI));
////*/
//
//
//
///*
//        //测试2：小车在的角度变化
//        double result;
//        Arc_calculation arc = new Arc_calculation();
//        move_calculation mc = new move_calculation();
//        result = arc.getChange_Degree(mc.Degree_to_radian(0),mc.Degree_to_radian(-90));
//        System.out.println(result);
//*/
//
///*
//        //测试3：根据曲线函数，计算小车的前进时间列表，和偏转角度列表
//        Arc_calculation arc = new Arc_calculation();
//        move_calculation mc = new move_calculation();
//        List<Double> p_dis = new ArrayList<Double>();
//        List<Double> p_ang = new ArrayList<Double>();
//        List<Double> car_change_Degree = new ArrayList<Double>();
//        p_dis = arc.getDBTP();
//        p_ang = arc.getABTP();
//        car_change_Degree = arc.Car_change_degree(p_ang);
//        if (p_dis.size()==car_change_Degree.size()){
//            List<Integer> result1 = new ArrayList<>();
//            List<Integer> result2 = new ArrayList<>();
//            List<Integer> result3 = new ArrayList<>();
//            for (int i=0;i<p_dis.size();i++){
//                List<Double> result = new ArrayList<Double>();
//                result = mc.getStraight_walking(p_dis.get(i),car_change_Degree.get(i));
//                double answer1 = arc.rounding(result.get(0));
//                double answer2 = arc.rounding(result.get(1));
//                double answer3 = result.get(2);
//
//                result1.add((int) answer1);
//                result2.add((int) answer2);
//                result3.add((int) answer3);
//                System.out.println("Change"+i+":\n"+
//                                    "t_turn:"+result.get(0)+
//                                    " ,t_line:"+result.get(1)+
//                                    " ,car_dir:"+result.get(2));
//            }
//            System.out.println(result1.size()+","+result2.size()+","+result3.size());
//            System.out.println(result1);
//            System.out.println(result2);
//            System.out.println(result3);
//        }else{
//            System.out.println("计算结果有误！");
//        }
//
// */
//        //测试4：小车走基本图形路径，计算小车的前进时间列表，和偏转角度列表
//        //基本图形有：矩形，椭圆，指定直线
//        Arc_calculation arc = new Arc_calculation();
//        move_calculation mc = new move_calculation();
//        Arc_another arcAnother = new Arc_another();
//
//        List<Double> p_dis = new ArrayList<Double>();
//        List<Double> p_ang = new ArrayList<Double>();
//        List<Double> car_change_Degree = new ArrayList<Double>();
//        //double read = 0;
//        int data_num = 5;
//        //arcAnother.printList(p_dis);
//        //arcAnother.printList(p_ang);
//
//        car_change_Degree = arc.Car_change_degree(p_ang);
//        List<Integer> result1 = new ArrayList<>();
//        List<Integer> result2 = new ArrayList<>();
//        List<Integer> result3 = new ArrayList<>();
//
//        for (int i=0;i<p_dis.size();i++){
//            List<Double> result = new ArrayList<Double>();
//            result = mc.getStraight_walking(p_dis.get(i),car_change_Degree.get(i));
//            double answer1 = arc.rounding(result.get(0));
//            double answer2 = arc.rounding(result.get(1));
//            double answer3 = result.get(2);
//
//            result1.add((int) answer1);//旋转时间
//            result2.add((int) answer2);//距离时间
//            result3.add((int) answer3);//方向
//            System.out.println("Change"+i+":\n"+
//                    "t_turn:"+result.get(0)+
//                    " ,t_line:"+result.get(1)+
//                    " ,car_dir:"+result.get(2));
//        }
//        System.out.println(result1.size()+","+result2.size()+","+result3.size());
//        System.out.println(result1);
//        System.out.println(result2);
//        System.out.println(result3);
//    }
//}
