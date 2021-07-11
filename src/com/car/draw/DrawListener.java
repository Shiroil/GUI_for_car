package com.car.draw;

import com.car.shape.*;
import com.car.action.Main;
import com.car.angle.move_calculation;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {

    private int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
    private Boolean flag1 = true, flag2 = false;
    private String name;
    private Color color;
    private Graphics g;
    private Shape[] shapeArray;
    private List<Double> angle = new ArrayList<>();
    private List<Double> dis = new ArrayList<>();
    private position[] red;
    private position[] positions;
    private move_calculation Move = new move_calculation();
    private int index = 0;
    int k = 0;
    int j = 0;
    int k1 = 0;
    Timer timer;
    Timer timer1;

    // 初始化画笔
    public void setGr(Graphics g) {
        this.g = g;
    }

    // 初始化图形数组
    public void setSp(Shape[] shapeArray) {
        this.shapeArray = shapeArray;

    }

    public void setRed(position[] red) {
        this.red = red;
    }

    public void setPo(position[] positions) {
        this.positions = positions;

    }
    //鼠标点击
    public void mouseClicked(java.awt.event.MouseEvent e) {
//        flag2 = true;
//        if (!flag1) {
//            x5 = e.getX();
//            y5 = e.getY();
//            g.drawLine(x4, y4, x5, y5);
//            //将直线存入数组
//            Shape line = new Line(x4, y4, x5, y5, name, color);
//            shapeArray[index++] = line;
//
//            x4 = x5;
//            y4 = y5;
//        }
//        //双击自动完成多边形闭合
//        if (e.getClickCount() == 2) {
//            g.drawLine(x5, y5, x3, y3);
//
//            Shape line = new Line(x5, y5, x3, y3, name, color);
//            shapeArray[index++] = line;
//            flag1 = true;
//        }

    }
    //鼠标按下
    public void mousePressed(java.awt.event.MouseEvent e) {
        {
            x1 = e.getX();
            y1 = e.getY();
        }
    }
    //鼠标释放
    public void mouseReleased(MouseEvent e) {

        {
            j = 0;
            x2 = e.getX();
            y2 = e.getY();
            // 绘制直线
            if ("直线".equals(name)) {
                g.drawLine(x1, y1, x2, y2);
                Shape line = new Line(x1, y1, x2, y2, name, color);
                shapeArray[index++] = line;
                draw_line(x1, x2, y1, y2);
                color = Color.red;
                g.setColor(color);
                g.drawLine(450, 350, positions[0].getX1(), positions[0].getY1());
                angle.add(Move.anglePoint(450, 350, positions[0].getX1(), positions[0].getY1()));
                dis.add(Move.disPoint(450,350,positions[0].getX1(),positions[0].getY1()));
                angle.add(Move.anglePoint(x1, y1, x2, y2));
                dis.add(Move.disPoint(x1,y1,x2,y2));
                Move.cal(angle, dis);

            }
            if ("矩形".equals(name)) {
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shape rect = new Rect(x1, y1, x2, y2, name, color);
                int x_min = Math.min(x1, x2);
                int x_max = Math.max(x1, x2);
                int y_min = Math.min(y1, y2);
                int y_max = Math.max(y1, y2);
                
                angle.add(Move.anglePoint(450, 350, x_min, y_min));
                dis.add(Move.disPoint(x1,y1,x2,y2));
                if(x1 - x2 < 0 ){//左上-右上-右下-左下-左上
                    for(int i = x_min; i < x_max; i++){
                        position pos = new position(i,y_min);
                        positions[j++] = pos;
                    }
                    for(int i = y_min; i < y_max; i++){
                        position pos = new position(x_max,i);
                        positions[j++] = pos;
                    }
                    for(int i = x_max; i > x_min; i--){
                        position pos = new position(i,y_max);
                        positions[j++] = pos;
                    }
                    for(int i = y_max; i > y_min; i--){
                        position pos = new position(x_min,i);
                        positions[j++] = pos;
                    }
                    angle.add(Move.anglePoint(x_min, y_min, x_max, y_min));
                    dis.add(Move.disPoint(x_min, y_min, x_max, y_min));

                    angle.add(Move.anglePoint(x_max, y_min, x_max, y_max));
                    dis.add(Move.disPoint(x_max, y_min, x_max, y_max));
                    angle.add(Move.anglePoint(x_max, y_max, x_min, y_max));
                    dis.add(Move.disPoint(x_max, y_max, x_min, y_max));
                    angle.add(Move.anglePoint(x_min, y_max, x_min, y_min));
                    dis.add(Move.disPoint(x_min, y_max, x_min, y_min));
                    for (Double aDouble : angle) {
                        System.out.println("angle:" + aDouble);
                    }
                }
                if(x2 - x1 < 0){//右上-左上-左下-右下-右上

                    for(int i = x_max; i > x_min; i--){
                        position pos = new position(i,y_min);
                        positions[j++] = pos;
                    }
                    for(int i = y_min; i < y_max; i++){
                        position pos = new position(x_min,i);
                        positions[j++] = pos;
                    }
                    for(int i = x_min; i < x_max; i++){
                        position pos = new position(i,y_max);
                        positions[j++] = pos;
                    }
                    for(int i = y_max; i > y_min; i--){
                        position pos = new position(x_max,i);
                        positions[j++] = pos;
                    }
                    angle.add(Move.anglePoint(x_max, y_min, x_min, y_min));
                    dis.add(Move.disPoint(x_max, y_min, x_min, y_min));
                    angle.add(Move.anglePoint(x_min, y_min, x_min, y_max));
                    dis.add(Move.disPoint(x_min, y_min, x_min, y_max));
                    angle.add(Move.anglePoint(x_min, y_max, x_max, y_max));
                    dis.add(Move.disPoint(x_min, y_max, x_max, y_max));
                    angle.add(Move.anglePoint(x_max, y_max, x_max, y_min));
                    dis.add(Move.disPoint(x_max, y_max, x_max, y_min));
                }
//                if(x1 - x2 < 0 && y2 - y1 < 0){//左下-右下-右上-左上-左下
//                    for(int i = x_min; i < x_max; i++){
//                        position pos = new position(i,y_max);
//                        positions[j++] = pos;
//                    }
//                    for(int i = y_max; i > y_min; i--){
//                        position pos = new position(x_max,i);
//                        positions[j++] = pos;
//                    }
//                    for(int i = x_max; i > x_min; i--){
//                        position pos = new position(i,y_min);
//                        positions[j++] = pos;
//                    }
//                    for(int i = y_min; i < y_max; i++){
//                        position pos = new position(x_min,i);
//                        positions[j++] = pos;
//                    }
//                }
//                if(x2 - x1 < 0 && y1 - y2 > 0){//右下-左下-左上-右上-右下
//
//                    for(int i = x_max; i > x_min; i--){
//                        position pos = new position(i,y_max);
//                        positions[j++] = pos;
//                    }
//                    for(int i = y_max; i > y_min; i--){
//                        position pos = new position(x_min,i);
//                        positions[j++] = pos;
//                    }
//                    for(int i = x_min; i < x_max; i++){
//                        position pos = new position(i,y_min);
//                        positions[j++] = pos;
//                    }
//                    for(int i = y_min; i < y_max; i++){
//                        position pos = new position(x_max,i);
//                        positions[j++] = pos;
//
//                }
//                shapeArray[index++] = rect;
//                System.out.println("左上:("+ x_min + "," + y_max + ")");
//                System.out.println("右上:("+ x_max + "," + y_max + ")");
//                System.out.println("左下:("+ x_min + "," + y_min + ")");
//                System.out.println("右下:("+ x_max + "," + y_min + ")");}
                color = Color.red;
                g.setColor(color);
                g.drawLine(450, 350, positions[0].getX1(), positions[0].getY1());
                Move.cal(angle, dis);
            }
            if ("椭圆".equals(name)) {
                g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shape oval = new Oval(x1, y1, x2, y2, name, color);
                int x_min = Math.min(x1, x2);
                int x_max = Math.max(x1, x2);
                int y_min = Math.min(y1, y2);
                int y_max = Math.max(y1, y2);
                int mid_x = (x1 + x2)/2;
                int mid_y = (y1 + y2)/2;
                int[] x_pos = new int[5];
                int[] y_pos = new int[5];
                angle.add(Move.anglePoint(450, 350, x_min, mid_y));
                dis.add(Move.disPoint(450,350, x_min, mid_y));
                x_pos[0] = x_min;
                x_pos[2] = mid_x;
                x_pos[3] = (int) (Math.cos(45) * (mid_x-x_min)) + mid_x;
                x_pos[1] = x_pos[3] - mid_x + x_min;
                x_pos[4] = x_max;
                y_pos[0] = y_min;
                y_pos[1] = (int) (Math.sin(-45) * (mid_y-y_min)) + mid_y;
                y_pos[2] = mid_y;
                y_pos[3] = (int) (Math.sin(45) * (mid_y-y_min)) + mid_y;
                y_pos[4] = y_max;
                for (int x_po : x_pos) {
                    System.out.println("X:"+x_po);
                }
                for (int y_po : y_pos) {
                    System.out.println("Y:"+y_po);
                }

                int[] values = new int[2];

                angle.add(Move.anglePoint(x_pos[0],y_pos[2], x_pos[1], y_pos[1]));
                dis.add(Move.disPoint(x_pos[0],y_pos[2], x_pos[1], y_pos[1]));
                values = draw_line(x_pos[0], x_pos[1], y_pos[2], y_pos[1]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[2], y_pos[0]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[2], y_pos[0]));
//                System.out.println("return"+x_pos[1]);
                values = draw_line(values[0], x_pos[2], values[1], y_pos[0]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[3], y_pos[1]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[3], y_pos[1]));
                values = draw_line(values[0], x_pos[3], values[1], y_pos[1]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[4], y_pos[2]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[4], y_pos[2]));
                values = draw_line(values[0], x_pos[4], values[1], y_pos[2]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[3], y_pos[3]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[3], y_pos[3]));
                values = draw_line(values[0], x_pos[3], values[1], y_pos[3]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[2], y_pos[4]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[2], y_pos[4]));
                values = draw_line(values[0], x_pos[2], values[1], y_pos[4]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[1], y_pos[3]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[1], y_pos[3]));
                values = draw_line(values[0], x_pos[1], values[1], y_pos[3]);

                angle.add(Move.anglePoint(values[0],values[1], x_pos[0], y_pos[2]));
                dis.add(Move.disPoint(values[0],values[1], x_pos[0], y_pos[2]));
                values = draw_line(values[0], x_pos[0], values[1], y_pos[2]);
                for (Double aDouble : angle) {
                    System.out.println(aDouble);
                }
                color = Color.red;
                g.setColor(color);
                g.drawLine(450, 350, positions[0].getX1(), positions[0].getY1());
                shapeArray[index++] = oval;
                System.out.println("左:("+ x_min + "," + mid_y  + ")");
                System.out.println("上:("+ mid_x + "," + y_min + ")");
                System.out.println("右:("+ x_max + "," + mid_y + ")");
                System.out.println("下:("+ mid_x + "," + y_max + ")");
                Move.cal(angle, dis);

            }


        }

    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
    }
    //鼠标拖动
    public void mouseDragged(java.awt.event.MouseEvent e) {
    }

    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {

        if ("".equals(e.getActionCommand())) {
            // 获取当前事件源，并强制转换
            JButton jb = (JButton) e.getSource();
            // 将按钮背景色赋值给color
            color = jb.getBackground();
            // 设置画笔背景色
            // 注意：不能直接写成g.setColor(jb.getBackground());后面重绘时需用到color参数；
            g.setColor(color);
        } else {
            name = e.getActionCommand();

            if ("清屏".equals(name)) {
                color = Color.white;
                g.setColor(color);
                x1 = 0;
                y1 = 0;
                x2 = 900;
                y2 = 700;
                g.fillRect(x1, y1, x2, y2);

                // 重置多边形最后一条线段数据
                x3 = 0;
                y3 = 0;
                x5 = 0;
                y5 = 0;
                x1 = 450;
                y1 = 350;
                x2 = 10;
                y2 = 10;
                color = Color.red;
                g.setColor(color);
                g.fillRect(x1,y1,x2,y2);


                Shape fillrect = new FillRect(x1, y1, x2, y2, name, color);
                shapeArray[index++] = fillrect;
                color = Color.black;
                g.setColor(color);
                positions = new position[5000];
                red = new position[2000];
                angle = new ArrayList<>();
                dis = new ArrayList<>();
                k = 0;
                k1 = 0;
                timer.stop();
                timer1.stop();
            }
            if ("run".equals(name)){
                color = Color.white;
                g.setColor(color);
                x1 = 0;
                y1 = 0;
                x2 = 900;
                y2 = 700;
                g.fillRect(x1, y1, x2, y2);
                // 重置多边形最后一条线段数据
//                x1 = 450;
//                y1 = 350;
//                x2 = 10;
//                y2 = 10;
//                color = Color.red;
//                g.setColor(color);
//                g.fillRect(x1,y1,x2,y2);
                x3 = 0;
                y3 = 0;
                x5 = 0;
                y5 = 0;


                Shape fillrect = new FillRect(x1, y1, x2, y2, name, color);
                shapeArray[index++] = fillrect;
                color = Color.black;
                g.setColor(color);



                color = Color.white;
                g.setColor(color);
                //设置线宽



                ActionListener task = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                                System.out.println("This is on the EDT after 5 seconds, " +
//                                        "well depending on if I'm used with a Timer, and if " +
//                                        "the right options are set to that Timer");
                        // set second jlabel text here
                        position i = positions[k++];
                            if(i!=null){
                                ((Graphics2D) g).setStroke(new BasicStroke(10));
                                g.drawLine(x1, y1, x1, y1);
                                color = Color.black;
                                g.setColor(color);
                                ((Graphics2D) g).setStroke(new BasicStroke(1));
                                x1 = i.getX1();
                                y1 = i.getY1();
//                                System.out.println(x1);

                            }
                        }
                };
                timer = new Timer(10 , task);
                timer.start();
                for (position position : red) {
                    if(position!=null)
                        System.out.println(position.getX1());
                }
                System.out.println(positions[0].getX1());
            }

            if ("函数1".equals(name)) {//函数1的图
                System.out.println("函数1");
            }
            if ("函数2".equals(name)) {//函数2的图
                System.out.println("函数2");
            }

        }
//
//        // 多边形切换设置
//        flag1 = true;
//        // 点击非清屏按钮，先完成多边形绘制
//        if (!"".equals(e.getActionCommand()) && flag2) {
//            g.drawLine(x5, y5, x3, y3);
//
//            Shape line = new Line(x5, y5, x3, y3, name, color);
//            shapeArray[index++] = line;
//
//            flag2 = false;
//        }
    }
    public int[] draw_line(int x1, int x2, int y1, int y2){
        int x_min = Math.min(x1, x2);
        int x_max = Math.max(x1, x2);
        int y_min = Math.min(y1, y2);
        int y_max = Math.max(y1, y2);
        int x_D_value = x_max - x_min;
        int y_D_value = y_max - y_min;//取差值
        int x_if_minus = 0;
        int y_if_minus = 0;//坐标1-坐标2 负为0 正为1
        if(x1 - x2 > 0)
            x_if_minus = 0;
        else
            x_if_minus = 1;
        if(y1 - y2 > 0){
            y_if_minus = 0;
        }
        else
            y_if_minus = 1;
        int step = 0;
        int jud = 0;//判定45° 为1时接近Y轴 为0时接近X轴 判定短长轴
        int max = Math.max(x_D_value, y_D_value);
        if(x_D_value > y_D_value){//对比x轴差值与y轴差值，确定长轴
            step = max/ y_D_value;//取步数(几步到下一个点)
            jud = 0;
        }
        else {
            step = max / x_D_value;
            jud = 1;
        }
        int count = 0;//每次执行+1,等于步数后x/y上走一步
        if(jud == 0 && x_if_minus == 1) {
            for (int i = x_min; i < x_max; i++) {
                if (count == step) {
                    if (y1 < y2)
                        y_min++;
                    else
                        y_max--;
                    count = 0;
                }
                count++;
                if (y1 < y2) {
                    position pos = new position(i, y_min);
                    positions[j++] = pos;
                } else {
                    position pos = new position(i, y_max);
                    positions[j++] = pos;
                }
            }
        }
        if(jud == 0 && x_if_minus == 0) {
            for (int i = x_max; i > x_min; i--) {
                if (count == step) {
                    if (y1 < y2)
                        y_min++;
                    else
                        y_max--;
                    count = 0;
                }
                count++;
                if (y1 < y2) {
                    position pos = new position(i, y_min);
                    positions[j++] = pos;
                } else {
                    position pos = new position(i, y_max);
                    positions[j++] = pos;
                }
            }
        }
        if(jud == 1 && y_if_minus == 0)
            for(int i = y_max; i > y_min; i--){
                if(count==step){
                    if(x1 < x2)
                        x_min++;
                    else
                        x_max--;
                    count=0;
                }
                count++;
                if(x1 < x2) {
                    position pos = new position(x_min, i);
                    positions[j++] = pos;
                }
                else {
                    position pos = new position(x_max, i);
                    positions[j++] = pos;
                }
            }
        if(jud == 1 && y_if_minus == 1)
            for(int i = y_min; i < y_max; i++){
                if(count==step){
                    if(x1 < x2)
                        x_min++;
                    else
                        x_max--;
                    count=0;
                }
                count++;
                if(x1 < x2) {
                    position pos = new position(x_min, i);
                    positions[j++] = pos;
                }
                else {
                    position pos = new position(x_max, i);
                    positions[j++] = pos;
                }
            }
        int[] return_values = new int[2];
        return_values[0] = positions[j-1].getX1();
        return_values[1] = positions[j-1].getY1();

        return return_values;//返回画线后的点
    }


}