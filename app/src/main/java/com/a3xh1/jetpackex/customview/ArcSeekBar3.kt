package com.a3xh1.jetpackex.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.a3xh1.basecore.utils.logger.log
import com.a3xh1.jetpackex.utils.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * Author: GIndoc on 2019/4/24.
 * FOR   :
 */
class ArcSeekBar3 @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private var defWidth: Int = dp2px(100f)
    private var defHeight = dp2px(100f)
    private val arcPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pointPaint: Paint
    private val rectF = RectF()

    init {
        arcPaint.isDither = true
        arcPaint.style = Paint.Style.STROKE
        arcPaint.strokeWidth = dp2px(10f).toFloat()
        arcPaint.color = Color.RED
        arcPaint.strokeCap = Paint.Cap.ROUND

        pointPaint = Paint(arcPaint)
        pointPaint.color = Color.BLUE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width: Int
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        width = if (widthMode == MeasureSpec.AT_MOST) defWidth else MeasureSpec.getSize(widthMeasureSpec)

        val height: Int
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        height = if (heightMode == MeasureSpec.AT_MOST) defHeight else MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(width, height)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val halfStrokeWidth = arcPaint.strokeWidth / 2

        centerX = width / 2f
        centerY = height / 2f
        radius = Math.min(centerX, centerY) - halfStrokeWidth

        rectF.set(
            halfStrokeWidth,
            halfStrokeWidth,
            width - halfStrokeWidth,
            height - halfStrokeWidth
        )
        canvas?.drawArc(rectF, 180f, 180f, false, arcPaint)


        canvas?.drawLine(centerX, centerY, drawX, drawY, arcPaint)

        canvas?.drawPoint(drawX, drawY, pointPaint)
    }

    //    private var downX: Float = 0f
//    private var downY: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius = 0f

    private var drawX: Float = 0f
    private var drawY: Float = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            val degree = getRotationBetweenLines(centerX, centerY, event.x, event.y)
            getDrawPoint(degree)
            invalidate()
        }
        return true
    }

    private fun getDrawPoint(degree: Float) {
        if (degree > 0 && degree < 90) {
            val _degree = degree / 180f * Math.PI
            val sin = sin(_degree)
            val cos = cos(_degree)
            drawY = centerY - (sin * radius).toFloat()
            drawX = centerX + (cos * radius).toFloat()
            log {
                "degree: $degree   sin: $sin    cos: $cos   drawX: $drawX    " +
                        "drawY: $drawY   centerX:$centerX    centerY:$centerY   radius:$radius"
            }
        } else if (degree > 90 && degree < 180) {
            val _degree = (degree - 90) / 180f * Math.PI
            val sin = sin(_degree)
            val cos = cos(_degree)
            drawX = (centerX - sin * radius).toFloat()
            drawY = (centerY - cos * radius).toFloat()
        } else if (degree > 180 && degree < 270) {
            val _degree = (degree - 180) / 180f * Math.PI
            val sin = sin(_degree)
            val cos = cos(_degree)
            drawY = (centerY + sin * radius).toFloat()
            drawX = (centerX - cos * radius).toFloat()
        } else if (degree > 270 && degree < 360) {
            val _degree = (360 - degree) / 180f * Math.PI
            val sin = sin(_degree)
            val cos = cos(_degree)
            drawY = (centerY + sin * radius).toFloat()
            drawX = (centerX + cos * radius).toFloat()
        }
    }

    /**
     * 获取两条线的夹角
     * @param centerX
     * @param centerY
     * @param xInView
     * @param yInView
     * @return
     */
    fun getRotationBetweenLines(centerX: Float, centerY: Float, xInView: Float, yInView: Float): Float {
        var rotation = 0.0

        val k3 = Math.abs((xInView - centerX) / (yInView - centerY).toDouble())
        val tmpDegree = Math.atan(k3) / Math.PI * 180

        if (xInView > centerX && yInView < centerY) {  //第一象限
            rotation = 90 - tmpDegree
        } else if (xInView < centerX && yInView < centerY) { //第二象限
            rotation = 90 + tmpDegree
        } else if (xInView < centerX && yInView > centerY) { //第三象限
            rotation = 180 + 90 - tmpDegree
        } else if (xInView > centerX && yInView > centerY) //第四象限
        {
            rotation = 270 + tmpDegree
        } else if (yInView == centerY && xInView > centerX) {
            rotation = 0.0
        } else if (yInView == centerY && xInView < centerX) {
            rotation = 180.0
        } else if (xInView == centerX && yInView < centerY) {
            rotation = 90.0
        } else if (xInView == centerX && yInView > centerY) {
            rotation = 270.0
        }

        log { "centerX:$centerX   centerY:$centerY      tmpDegree:$tmpDegree       rotation:$rotation" }

        return rotation.toFloat()
    }
}