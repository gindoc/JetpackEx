package com.a3xh1.jetpackex.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.a3xh1.basecore.utils.logger.log
import com.a3xh1.jetpackex.R
import java.util.*

/**
 * Author: GIndoc on 2019/4/26.
 * FOR   :
 */

class RandomPathTan @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val mPath: Path
    private val pos: FloatArray
    private val tan: FloatArray

    private val mPaint: Paint
    internal var currentValue = 0f
    private val mMeasure: PathMeasure

    private val srcBitmap: Bitmap
    private val srcRect: Rect
    private val dstRect: Rect

    init {
        mPath = Path()
        mPath.moveTo(100f, 200f)
        mPath.cubicTo(300f, 100f, 500f, 300f, 700f, 200f)

        mPaint = Paint()
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 4f
        mMeasure = PathMeasure()

        mMeasure.setPath(mPath, false)
        pos = FloatArray(2)
        tan = FloatArray(2)

        srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_arrow_right)
        srcRect = Rect(0, 0, srcBitmap.width, srcBitmap.height)
        dstRect = Rect()

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { valueAnimator ->
            currentValue = valueAnimator.animatedValue as Float
            invalidate()
        }
        animator.duration = 3000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMeasure.getPosTan(mMeasure.length * currentValue, pos, tan)
        val degrees = (Math.atan2(tan[1].toDouble(), tan[0].toDouble()) * 180.0 / Math.PI).toFloat()

        log {
            String.format(
                Locale.CHINA, "thumbPos[0]: %.2f   thumbPos[1]: %.2f   tan[0]: %.2f   tan[1]: %.2f   degree:%.2f",
                pos[0], pos[1], tan[0], tan[1], degrees
            )
        }
        canvas?.drawPath(mPath, mPaint)
        canvas?.drawCircle(pos[0], pos[1], 10f, mPaint)

        dstRect.set(0, -srcBitmap.height/2, srcBitmap.width, srcBitmap.height/2)

        // canvas?.save()到canvas?.restore()之间到操作可以看作是放到一个栈中，所以可以看作是逆序执行的(当调用restore()后)
        canvas?.save()
        canvas?.translate(pos[0], pos[1])
        canvas?.rotate(degrees)
        canvas?.drawBitmap(srcBitmap, srcRect, dstRect, mPaint)
        canvas?.drawLine(0f, 0f, 100f, 0f, mPaint)
        canvas?.restore()
    }

}