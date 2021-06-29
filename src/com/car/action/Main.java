package com.car.action;

import com.car.draw.DrawListener;
import com.car.shape.Shape;
import com.car.shape.position;

import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {
    private static final long serialVersionUID = 1L;
    private Shape[] shapeParameter = new Shape[20000];
    private position[] positions = new position[5000];
    public void initUI() {

        // 新建窗体并命名
        JFrame jf = new JFrame("画板");
        // 设置窗体大小
        jf.setSize(1100, 700);
        // 窗体设置居中
        jf.setLocationRelativeTo(null);
        // 设置窗体关闭
        jf.setDefaultCloseOperation(3);
        // 设置窗体边界布局
        jf.setLayout(new BorderLayout());

        // 添加3个JPanel容器
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();

        JLabel carcar = new JLabel(new ImageIcon("D:/Desktop/wbyt/1.jpg"));
        jf.add(carcar);
        carcar.setBounds(550,350,10,10);
        // 将JPanel布局到窗体中
        jf.add(this, BorderLayout.CENTER);
        jf.add(jp2, BorderLayout.WEST);
        jf.add(jp3, BorderLayout.EAST);

        // 设置jp1
        this.setPreferredSize(new Dimension(900, 700));
        this.setBackground(Color.white);

        // 创建事件监听器对象
        DrawListener dl = new DrawListener();
        // 给画布添加监听器
        this.addMouseListener(dl);
        this.addMouseMotionListener(dl);

        // 设置jp2
        jp2.setPreferredSize(new Dimension(100, 700));
        jp2.setBackground(Color.LIGHT_GRAY);
        // 设置jp3
        jp3.setPreferredSize(new Dimension(100, 700));
        jp3.setBackground(Color.LIGHT_GRAY);

        // 添加图形按钮
        String[] shapeArray = { "直线", "矩形", "椭圆", "多边形", "画笔", "橡皮檫", "清屏" ,"run" };
        for (int i = 0; i < shapeArray.length; i++) {
            // 创建图形按钮
            JButton jbu1 = new JButton(shapeArray[i]);
            // 设置按钮大小
            jbu1.setPreferredSize(new Dimension(100, 40));
            // 将按钮添加到jp2容器中
            jp2.add(jbu1);
            // 给按钮注册监听器
            jbu1.addActionListener(dl);
        }


        // 设置窗体可见
        jf.setVisible(true);
        // 获取画笔
        Graphics g = this.getGraphics();
        // 将画笔传递过去
        dl.setGr(g);
        // 将图形数组传递过去
        dl.setSp(shapeParameter);
        dl.setPo(positions);
    }

    public void init_car(){
    }

    // 重写父类方法
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < shapeParameter.length; i++) {
            Shape shape = shapeParameter[i];
            if (shapeParameter[i] != null) {
                shape.drawShape(g);
            }
        }
    }
    public static void main(String[] args) {
        Main pui = new Main();
        pui.initUI();
    }
}