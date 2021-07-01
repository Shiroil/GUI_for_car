package com.car.draw;

import com.car.shape.*;
import com.car.action.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {

    private int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
    private Boolean flag1 = true, flag2 = false;
    private String name;
    private Color color;
    private Graphics g;
    private Shape[] shapeArray;
    private position[] positions;
    private int index = 0;
    int k = 0;
    int j = 0;
    Timer timer;

    // 初始化画笔
    public void setGr(Graphics g) {
        this.g = g;
    }

    // 初始化图形数组
    public void setSp(Shape[] shapeArray) {
        this.shapeArray = shapeArray;

    }
    public void setPo(position[] positions) {
        this.positions = positions;

    }
    //鼠标点击
    public void mouseClicked(java.awt.event.MouseEvent e) {
        flag2 = true;
        if (!flag1) {
            x5 = e.getX();
            y5 = e.getY();
            g.drawLine(x4, y4, x5, y5);
            //将直线存入数组
            Shape line = new Line(x4, y4, x5, y5, name, color);
            shapeArray[index++] = line;

            x4 = x5;
            y4 = y5;
        }
        //双击自动完成多边形闭合
        if (e.getClickCount() == 2) {
            g.drawLine(x5, y5, x3, y3);

            Shape line = new Line(x5, y5, x3, y3, name, color);
            shapeArray[index++] = line;
            flag1 = true;
        }

    }
    //鼠标按下
    public void mousePressed(java.awt.event.MouseEvent e) {
        {
            x1 = e.getX();
            y1 = e.getY();
        }
    }
    //鼠标释放
    public void mouseReleased(java.awt.event.MouseEvent e) {

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
            }
            if ("矩形".equals(name)) {
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shape rect = new Rect(x1, y1, x2, y2, name, color);
                int x_min = Math.min(x1, x2);
                int x_max = Math.max(x1, x2);
                int y_min = Math.min(y1, y2);
                int y_max = Math.max(y1, y2);
                if(x1 - x2 < 0 && y1 - y2 < 0){
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
                }
                if(x2 - x1 < 0 && y1 - y2 < 0){

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
                }
                if(x1 - x2 < 0 && y2 - y1 < 0){
                    for(int i = x_min; i < x_max; i++){
                        position pos = new position(i,y_max);
                        positions[j++] = pos;
                    }
                    for(int i = y_max; i > y_min; i--){
                        position pos = new position(x_max,i);
                        positions[j++] = pos;
                    }
                    for(int i = x_max; i > x_min; i--){
                        position pos = new position(i,y_min);
                        positions[j++] = pos;
                    }
                    for(int i = y_min; i < y_max; i++){
                        position pos = new position(x_min,i);
                        positions[j++] = pos;
                    }
                }
                if(x2 - x1 < 0 && y1 - y2 > 0){

                    for(int i = x_max; i > x_min; i--){
                        position pos = new position(i,y_max);
                        positions[j++] = pos;
                    }
                    for(int i = y_max; i > y_min; i--){
                        position pos = new position(x_min,i);
                        positions[j++] = pos;
                    }
                    for(int i = x_min; i < x_max; i++){
                        position pos = new position(i,y_min);
                        positions[j++] = pos;
                    }
                    for(int i = y_min; i < y_max; i++){
                        position pos = new position(x_max,i);
                        positions[j++] = pos;
                    }
                }
                shapeArray[index++] = rect;
                System.out.println("左上:("+ x_min + "," + y_max + ")");
                System.out.println("右上:("+ x_max + "," + y_max + ")");
                System.out.println("左下:("+ x_min + "," + y_min + ")");
                System.out.println("右下:("+ x_max + "," + y_min + ")");
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
                x_pos[0] = x_min;
                int angle = 45;
                double radian = angle*(Math.PI/180);
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
                values = draw_line(x_pos[0], x_pos[1], y_pos[2], y_pos[1]);
//                System.out.println("return"+x_pos[1]);
                values = draw_line(values[0], x_pos[2], values[1], y_pos[0]);
                values = draw_line(values[0], x_pos[3], values[1], y_pos[1]);
                values = draw_line(values[0], x_pos[4], values[1], y_pos[2]);
                values = draw_line(values[0], x_pos[3], values[1], y_pos[3]);
                values = draw_line(values[0], x_pos[2], values[1], y_pos[4]);
                values = draw_line(values[0], x_pos[1], values[1], y_pos[3]);
                values = draw_line(values[0], x_pos[0], values[1], y_pos[2]);

//                while(x_min != x_pos[1]){
//                    if(count1==step[0]){
//                        y_temp--;
//                        count1=0;
//                    }
//                    if(count2==step[0]){
//                        x_min++;
//                        count2=0;
//                    }
//                    count1++;
//                    count2++;
//                    position pos = new position(x_min, y_temp);
//                    System.out.println("y:"+y_min);
//                    positions[j++] = pos;
//                }
//                count1 = 0;
//                count2 = 0;
//                while(x_min != x_pos[2]){//明天改这里 第二段
//                    if(count1==step[1]){
//                        y_temp--;
//                        count1=0;
//                    }
//                    if(count2==step[1]){
//                        x_min++;
//                        count2=0;
//                    }
//                    count1++;
//                    count2++;
//                    position pos = new position(x_min, y_temp);
//                    positions[j++] = pos;
//                }
                shapeArray[index++] = oval;
                System.out.println("左:("+ x_min + "," + mid_y  + ")");
                System.out.println("上:("+ mid_x + "," + y_min + ")");
                System.out.println("右:("+ x_max + "," + mid_y + ")");
                System.out.println("下:("+ mid_x + "," + y_max + ")");
            }

            if ("多边形".equals(name) && flag1) {
                g.drawLine(x1, y1, x2, y2);

                Shape line = new Line(x1, y1, x2, y2, name, color);
                shapeArray[index++] = line;

                x3 = x1;
                y3 = y1;
                x4 = x2;
                y4 = y2;

                flag1 = false;
            }

        }

    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
    }
    //鼠标拖动
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // 画笔重载需注意内存
        if ("画笔".equals(name)) {
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1, y1, x2, y2);
            Shape line = new Line(x1, y1, x2, y2, name, color);
            shapeArray[index++] = line;
            x1 = x2;
            y1 = y2;
        }
        if ("橡皮檫".equals(name)) {
            color = Color.white;
            g.setColor(color);
            //设置线宽
            ((Graphics2D) g).setStroke(new BasicStroke(20));
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1, y1, x2, y2);
            Shape eraser = new Eraser(x1, y1, x2, y2, name, color);
            shapeArray[index++] = eraser;
            x1 = x2;
            y1 = y2;
            color = Color.black;
            g.setColor(color);
            ((Graphics2D) g).setStroke(new BasicStroke(1));
        }
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
                k = 0;
                timer.stop();
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
                x1 = 450;
                y1 = 350;
                x2 = 10;
                y2 = 10;
                color = Color.red;
                g.setColor(color);
                g.fillRect(x1,y1,x2,y2);
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
                                ((Graphics2D) g).setStroke(new BasicStroke(20));
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
            }

        }

        // 多边形切换设置
        flag1 = true;
        // 点击非清屏按钮，先完成多边形绘制
        if (!"".equals(e.getActionCommand()) && flag2) {
            g.drawLine(x5, y5, x3, y3);

            Shape line = new Line(x5, y5, x3, y3, name, color);
            shapeArray[index++] = line;

            flag2 = false;
        }
        // 点击颜色按钮继续画图
        if ("".equals(e.getActionCommand()) && flag2) {
            flag1 = false;
        }
    }
    public int[] draw_line(int x1, int x2, int y1, int y2){
        int x_min = Math.min(x1, x2);
        int x_max = Math.max(x1, x2);
        int y_min = Math.min(y1, y2);
        int y_max = Math.max(y1, y2);
        int x_D_value = x_max - x_min;
        int y_D_value = y_max - y_min;
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
        int jud = 0;//判定45° 为1时接近Y轴 为0时接近X轴
        int max = Math.max(x_D_value, y_D_value);
        if(x_D_value > y_D_value){
            step = max/ y_D_value;
            jud = 0;
        }
        else {
            step = max / x_D_value;
            jud = 1;
        }
        int count = 0;
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

        return return_values;
    }
}