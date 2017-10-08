package com.lqh.lichao.ui_lsn3_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017-10-07.
 */

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * StrokeCap示例
         */
        //drawStrokeCap(canvas);
        /**
         * stokeJoin示例
         */
        //drawStrokeJoin(canvas);
        /**
         * CornerPathEffect示例
         */
        //drawCornerPathEffect(canvas);
        /**
         * CornerPathEffect曲线
         */
        //drawCornerPathEffectDemo(canvas);
        /**
         * DiscretePathEffect DEMO效果
         */
        //drawDiscretePathEffectDemo(canvas);
        /**
         * PathDashPathEffect效果
         */
        //drawPathDashPathEffect(canvas);
        /**
         * PathDashPathEffect效果
         */
        //drawPathDashPathEffectDemo(canvas);
        /**
         * ComposePathEffect与SumPathEffect
         */
        //drawComposePathEffectDemo(canvas);
        /**
         * SubpixelText Demo
         */
        drawSubpixelText(canvas);
    }

    private Paint getPaint(){
        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    private Path getPath(){
        Path path = new Path();
        // 定义路径的起点
        path.moveTo(0, 0);

        // 定义路径的各个点
        for (int i = 0; i <= 40; i++) {
            path.lineTo(i*35, (float) (Math.random() * 150));
        }
        return path;
    }

    private Path getStampPath(){
        Path path  = new Path();
        path.moveTo(0,20);
        path.lineTo(10,0);
        path.lineTo(20,20);
        path.close();

        path.addCircle(0,0,3, Path.Direction.CCW);

        return path;
    }

    //直线
    private void drawStrokeCap(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(80);//设置画笔宽度
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);//设置画笔样式,仅描边
        //paint.setStrokeMiter(); //设置笔画的倾斜度
        paint.setDither(true);//设置图像是否使用抖动处理,会使得绘制出来的图片更加平滑和饱满，图像更加清晰

        paint.setStrokeCap(Paint.Cap.BUTT);//设置线冒样式,无线帽
        canvas.drawLine(100, 200, 400, 200, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);//方形线帽
        canvas.drawLine(100, 400, 400, 400, paint);

        paint.setStrokeCap(Paint.Cap.ROUND);//圆形线帽
        canvas.drawLine(100, 600, 400, 600, paint);
    }

    //设置线段连接处样式
    private void drawStrokeJoin(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(40);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(450,100);
        path.lineTo(100,300);
        paint.setStrokeJoin(Paint.Join.MITER);//设置线段连接处样式,结合处为锐角
        canvas.drawPath(path,paint);

        path.moveTo(100,400);
        path.lineTo(450,400);
        path.lineTo(100,600);
        paint.setStrokeJoin(Paint.Join.BEVEL);//设置线段连接处样式,结合处为直线
        canvas.drawPath(path,paint);

        path.moveTo(100,700);
        path.lineTo(450,700);
        path.lineTo(100,900);
        paint.setStrokeJoin(Paint.Join.ROUND);//设置线段连接处样式,结合处为圆弧
        canvas.drawPath(path,paint);
    }

    //设置绘制路径的效果
    private void drawCornerPathEffect(Canvas canvas){
        Paint paint = getPaint();
        Path path = new Path();
        path.moveTo(100,600);
        path.lineTo(400,100);
        path.lineTo(700,900);

        canvas.drawPath(path,paint);
        paint.setColor(Color.RED);
        paint.setPathEffect(new CornerPathEffect(100));//设置绘制路径的效果,利用半径R=50的圆来代替原来两条直线间的夹角
        canvas.drawPath(path,paint);

        paint.setPathEffect(new CornerPathEffect(200));//设置绘制路径的效果,利用半径R=100的圆来代替原来两条直线间的夹角
        paint.setColor(Color.YELLOW);
        canvas.drawPath(path,paint);
    }

    //画波浪线
    private void drawCornerPathEffectDemo(Canvas canvas){
        Paint paint = getPaint();
        Path path = getPath();
        canvas.drawPath(path,paint);

        paint.setPathEffect(new CornerPathEffect(200));
        canvas.save();
        canvas.translate(0,150);
        canvas.drawPath(path,paint);
    }

    private void drawDiscretePathEffectDemo(Canvas canvas){
        Paint paint = getPaint();
        Path path = getPath();

        canvas.drawPath(path,paint);
        /**
         * 把原有的路线,在指定的间距处插入一个突刺
         * 第一个这些突出的“杂点”的间距,值越小间距越短,越密集
         * 第二个是突出距离
         */
        canvas.translate(0,200);
        paint.setPathEffect(new DiscretePathEffect(2,5));
        canvas.drawPath(path,paint);

        canvas.translate(0,200);
        paint.setPathEffect(new DiscretePathEffect(6,5));
        canvas.drawPath(path,paint);


        canvas.translate(0,200);
        paint.setPathEffect(new DiscretePathEffect(6,15));
        canvas.drawPath(path,paint);
    }

    private void drawPathDashPathEffect(Canvas canvas){
        Paint paint = getPaint();

        Path path  = new Path();
        path.moveTo(100,600);
        path.lineTo(400,150);
        path.lineTo(700,900);
        canvas.drawPath(path,paint);
        canvas.drawPath(path,paint);

        canvas.translate(0,200);

        /**
         * 利用以另一个路径为单位,延着路径盖章.相当于PS的印章工具
         */
        paint.setPathEffect(new PathDashPathEffect(getStampPath(),35,0, PathDashPathEffect.Style.MORPH));
        canvas.drawPath(path,paint);
    }

    //虚线效果
    private void drawPathDashPathEffectDemo(Canvas canvas){
        Paint paint = getPaint();

        Path path = getPath();
        canvas.drawPath(path,paint);

        canvas.translate(0,200);
        //虚线效果
        paint.setPathEffect(new PathDashPathEffect(getStampPath(),35,0, PathDashPathEffect.Style.MORPH));
        canvas.drawPath(path,paint);

        canvas.translate(0,200);
        paint.setPathEffect(new PathDashPathEffect(getStampPath(),35,0, PathDashPathEffect.Style.ROTATE));
        canvas.drawPath(path,paint);

        canvas.translate(0,200);
        paint.setPathEffect(new PathDashPathEffect(getStampPath(),35,0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(path,paint);
    }

    private void drawComposePathEffectDemo(Canvas canvas){
        //画原始路径
        Paint paint = getPaint();
        Path path = getPath();
        canvas.drawPath(path,paint);

        //仅应用圆角特效的路径
        canvas.translate(0,300);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(100);
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path,paint);

        //仅应用虚线特效的路径
        canvas.translate(0,300);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{2,5,10,10},0);
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path,paint);

        //利用ComposePathEffect先应用圆角特效,再应用虚线特效
        canvas.translate(0,300);
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect,cornerPathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path,paint);

        //利用SumPathEffect,分别将圆角特效应用于原始路径,然后将生成的两条特效路径合并
        canvas.translate(0,300);
        paint.setStyle(Paint.Style.STROKE);
        SumPathEffect sumPathEffect = new SumPathEffect(cornerPathEffect,dashPathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path,paint);
    }

    private void drawSubpixelText(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        String text = "学习安卓高级UI";
        paint.setTextSize(100);

        paint.setSubpixelText(false);
        canvas.drawText(text,0,200,paint);

        canvas.translate(0,300);
        paint.setSubpixelText(true);//亚像素开，会影响性能
        canvas.drawText(text,0,200,paint);
    }
}
