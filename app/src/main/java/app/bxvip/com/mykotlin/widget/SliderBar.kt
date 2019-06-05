package app.bxvip.com.mykotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import app.bxvip.com.mykotlin.R
import org.jetbrains.anko.sp

class SliderBar(context: Context?, attrs:AttributeSet?=null): View(context, attrs) {
    var sectionHeight = 0f
    var textbaseLine = 0f
    var paint = Paint()
    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12f).toFloat()
            //对齐剧中
            textAlign = Paint.Align.CENTER
        }
    }
    companion object {
        private val SECTIONS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L","M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //计算每个字符分配的高度
        sectionHeight   = h / SECTIONS.size*1.0f
        val fontMetrics = paint.fontMetrics
        val textHeight = fontMetrics.descent-fontMetrics.ascent
        //计算基准线
        textbaseLine = sectionHeight/2+(textHeight/2-fontMetrics.descent)

    }
    override fun onDraw(canvas: Canvas?) {
        var x  = width*1.0f/2//绘制字母的起始位置
        var y  = sectionHeight //绘制字母的起始位置y
        //绘制所有的字母
        SECTIONS.forEach {
            canvas?.drawText(it,x,y,paint)
            //更新y，绘制下一个字母
            y += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                //找到点击的字母
                val index = getTouchIndex(event)
                val firstLetter = SECTIONS[index]
            }
            MotionEvent.ACTION_UP-> setBackgroundColor(Color.TRANSPARENT)
        }
        return true //消费事件
    }
    //获取点击位置的字母的下标
    private fun getTouchIndex(event: MotionEvent): Int {
        var index = event.y/sectionHeight
        //越界的检查
        if(index<0){
            index = 0f
        }else if(index>= SECTIONS.size){
            index = (SECTIONS.size-1).toFloat()
        }
        return index.toInt()
    }
}