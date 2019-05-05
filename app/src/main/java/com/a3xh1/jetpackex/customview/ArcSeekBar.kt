package com.a3xh1.jetpackex.customview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.a3xh1.basecore.utils.logger.log
import com.a3xh1.jetpackex.utils.dp2px
import com.a3xh1.jetpackex.R


/**
 * Author: GIndoc on 2019/4/26.
 * FOR   :
 */
class ArcSeekBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private var defWidth: Int = dp2px(100f)                 // 默认宽高
    private var defHeight = dp2px(100f)

    private val arcPath: Path                                       // 弧线(实线)
    private val borderPath = Path()                                 // 弧线的边(border)
    private val arcPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)      // 弧线(实线)的画笔
    private val borderPaint: Paint                                  // 弧线的边(border)的画笔
    private val thumbPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)    // 滑块画笔
    private val arcRectF: RectF                                     // 弧线(实线)的绘制区域
    private val pathMeasure: PathMeasure                            // Path测量类，用于获取path上的点和切线值

    private val thumbShadowColor: Int                               // 滑块阴影颜色
    private val thumbShadowRadius: Float                            // 滑块阴影半径
    private var mArcColors: IntArray                                // Seek 颜色

    private var mProgressPresent = 0f                               // 当前进度百分比
    private var startAngle = 135f                                   // 开始的角度
    private var sweepAngle = 270f                                   // 滑块滑动的最大角度

    private var mCenterX: Float = 0f                                // 圆弧中心点坐标
    private var mCenterY: Float = 0f
    val thumbPos = FloatArray(2)                               // 滑块的坐标

    private val mArcRegion: Region                                  // ArcPath的实际区域大小,用于判定单击事件
    private var mDetector: GestureDetector                          // 处理单击事件

    private var canDrag = false                                     // 是否可以拖动滑块

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)//对单独的View在运行时阶段禁用硬件加速

        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ArcSeekBar)
        val arcWidth = ta.getDimension(R.styleable.ArcSeekBar_asb_arc_width, dp2px(10f).toFloat())
        mArcColors = getArcColors(context, ta)
        val borderWidth = ta.getDimension(R.styleable.ArcSeekBar_asb_border_width, dp2px(3f).toFloat())
        val borderColor = ta.getColor(R.styleable.ArcSeekBar_asb_border_color, Color.WHITE)
        val thumbRadius = ta.getDimension(R.styleable.ArcSeekBar_asb_thumb_radius, dp2px(20f).toFloat())
        thumbShadowColor = ta.getColor(R.styleable.ArcSeekBar_asb_thumb_shadow_color, Color.BLACK)
        thumbShadowRadius = ta.getDimension(R.styleable.ArcSeekBar_asb_thumb_shadow_radius, dp2px(3f).toFloat())
        startAngle = ta.getFloat(R.styleable.ArcSeekBar_asb_start_angle, startAngle)
        sweepAngle = ta.getFloat(R.styleable.ArcSeekBar_asb_sweep_angle, sweepAngle)
        ta.recycle()

        arcPaint.isDither = true
        arcPaint.style = Paint.Style.STROKE
        arcPaint.strokeWidth = arcWidth
        arcPaint.strokeCap = Paint.Cap.ROUND

        borderPaint = Paint(arcPaint)
        borderPaint.strokeWidth = borderWidth
        borderPaint.color = borderColor

        thumbPaint.isDither = true
        thumbPaint.style = Paint.Style.FILL
        thumbPaint.strokeCap = Paint.Cap.ROUND
        thumbPaint.strokeWidth = thumbRadius

        arcPath = Path()
        arcRectF = RectF()
        pathMeasure = PathMeasure()
        mArcRegion = Region()

        mDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                e?.let {
                    // 判断是否点击在了进度区域
                    if (!isInArcProgress(it.x, it.y)) return false
                    // 点击允许突变
                    mProgressPresent = getProgress(it.x, it.y)
                    computeThumbPos()

                } ?: return false
                return true
            }
        })

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = width / 2f
        mCenterY = height / 2f

        val contentWidth = w - paddingLeft - paddingRight
        val contentHeight = h - paddingTop - paddingBottom

        var fix = arcPaint.strokeWidth / 2 + borderPaint.strokeWidth / 2     // 修正距离,画笔宽度的修正
        fix = Math.max(fix, thumbPaint.strokeWidth / 2) + thumbShadowRadius

        val startX: Float
        val startY: Float
        val contentLength: Float
        if (contentWidth < contentHeight) {
            startX = paddingLeft.toFloat()
            startY = (h - w) / 2f + paddingTop
            contentLength = contentWidth - fix
        } else {
            startX = (w - h) / 2f + paddingLeft
            startY = paddingTop.toFloat()
            contentLength = contentHeight - fix
        }
        arcRectF.set(
            startX + fix,
            startY + fix,
            startX + contentLength,
            startY + contentLength
        )
        arcPath.addArc(arcRectF, startAngle, sweepAngle)                            // 设置弧线path
        pathMeasure.setPath(arcPath, false)                             // 设置弧线path测量类

        computeThumbPos()                                                           // 计算滑块位置

        resetShaderColor()                                                          // 设置shader颜色，用于绘制弧线和thumb

        arcPaint.getFillPath(arcPath, borderPath)                                   // 获取弧线对应的border
        borderPath.close()
        mArcRegion.setPath(borderPath, Region(0, 0, width, height))       // 设置弧线的点击区域
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(borderPath, borderPaint)                                       // 画边框
        canvas?.drawPath(arcPath, arcPaint)                                             // 画弧线
        thumbPaint.color = getColor()
        thumbPaint.setShadowLayer(thumbShadowRadius, 0f, 0f, thumbShadowColor) // 设置滑块阴影
        canvas?.drawPoint(thumbPos[0], thumbPos[1], thumbPaint)                         // 画滑块
        thumbPaint.clearShadowLayer()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> judgeCanDrag(it)                             // 点击时，判断是否可以拖动滑块
                MotionEvent.ACTION_MOVE -> {
                    if (!canDrag) return true
                    val tmpProgress = getProgress(it.x, it.y)
                    log { "progress: $tmpProgress" }
                    log { "progress      " }

                    // 处理突变,当进度突变大于0.5时,不处理进度(出现场景: 从进度0 在弧线外滑动到 进度1)
                    if (Math.abs(tmpProgress - mProgressPresent) > 0.5f) return true
                    mProgressPresent = tmpProgress
                    computeThumbPos()
                }
            }
        }
        mDetector.onTouchEvent(event)
        invalidate()
        return true
    }

    // 是否可以拖动滑块
    private fun judgeCanDrag(event: MotionEvent) {
        val x = event.x - thumbPos[0]
        val y = event.y - thumbPos[1]
        val distance = Math.sqrt((x * x + y * y).toDouble())
        canDrag = distance <= thumbPaint.strokeWidth / 2 * 1.5
    }

    // 计算滑块位置
    private fun computeThumbPos() {
        val distance = pathMeasure.length * mProgressPresent
        pathMeasure.getPosTan(distance, thumbPos, null)
    }

    // 判断该点是否在进度条上面
    fun isInArcProgress(px: Float, py: Float): Boolean {
        val pos = floatArrayOf(px, py)
        return mArcRegion.contains(pos[0].toInt(), pos[1].toInt())
    }

    // 获取滑动进度
    private fun getProgress(px: Float, py: Float): Float {
        val diffAngle = getDiffAngle(px, py)
        var progress = diffAngle / sweepAngle
        log { "real progress: $progress    diffAngle:$diffAngle" }
        if (progress < 0) {
            progress = 0f
        } else if (progress > 1) {
            progress = 1f
        }
        return progress
    }

    private fun getDiffAngle(px: Float, py: Float): Float {
        val touchAngle = getAngle(px, py)
        val diffAngle = touchAngle - startAngle

        log { "progress touchAngle:$touchAngle      diffAngle:$diffAngle " }
        log { "progress diffAngleForStart:${Math.abs(diffAngle)}    diffAngleForEnd:${Math.abs((diffAngle + 360) % 360 - sweepAngle)}" }

        // 判断当前 触摸点 离 起点 的角度近，还是离 终点 的角度近
        if (Math.abs(diffAngle) < Math.abs((diffAngle + 360) % 360 - sweepAngle)) {
            return diffAngle
        } else {
            return (diffAngle + 360) % 360
        }

//        if (diffAngle < 0) {
//            diffAngle += 360
//        }
//        return diffAngle
    }

    // 计算指定位置与内容区域中心点的夹角
    private fun getAngle(px: Float, py: Float): Float {
        var angle = (Math.atan2((py - mCenterY).toDouble(), (px - mCenterX).toDouble()) * 180 / Math.PI).toFloat()
        if (angle < 0) {
            angle += 360f
        }
        return angle
    }


    // 重置 shader 颜色
    private fun resetShaderColor() {
        // 计算渐变数组
        val stopPos = sweepAngle / 360
        if (mArcColors.isNotEmpty()) {
            val len = mArcColors.size - 1
            val distance = stopPos / len
            val pos = FloatArray(mArcColors.size)
            for (i in mArcColors.indices) {
                pos[i] = distance * i
            }
            val gradient = SweepGradient(mCenterX, mCenterY, mArcColors, pos)
            val matrix = Matrix()
            matrix.setRotate(startAngle - getStrokeDegree(), mCenterX, mCenterY)
            gradient.setLocalMatrix(matrix)
            arcPaint.shader = gradient
        }
    }

    /**
     * 获取线宽占用的角度，因为画弧时线宽会导致突出一小部分
     */
    private fun getStrokeDegree(): Float {
        val path = Path()
        path.addArc(arcRectF, 0f, 360f)
        val tan = FloatArray(2)
        val pos = FloatArray(2)
        val pathMeasure = PathMeasure(path, false)
        pathMeasure.getPosTan(arcPaint.strokeWidth / 2, pos, tan)
        val degree = Math.atan2((tan[1]).toDouble(), (tan[0]).toDouble()) * 180.0 / Math.PI
        return Math.abs(degree - 90).toFloat()//(90 - (180 - degree)).toFloat()       // 不知道为什么是从90度开始的
    }

    //--- 线性取色 ---------------------------------------------------------------------------------

    /**
     * 获取当前进度的具体颜色
     *
     * @return 当前进度在渐变中的颜色
     */
    fun getColor(): Int {
        return getColor(mProgressPresent)
    }

    /**
     * 获取某个百分比位置的颜色
     *
     * @param radio 取值[0,1]
     * @return 最终颜色
     */
    private fun getColor(radio: Float): Int {
        val diatance = 1.0f / (mArcColors.size - 1)
        val startColor: Int
        val endColor: Int
        if (radio >= 1) {
            return mArcColors[mArcColors.size - 1]
        }
        for (i in mArcColors.indices) {
            if (radio <= i * diatance) {
                if (i == 0) {
                    return mArcColors[0]
                }
                startColor = mArcColors[i - 1]
                endColor = mArcColors[i]
                val areaRadio = getAreaRadio(radio, diatance * (i - 1), diatance * i)
                return getColorFrom(startColor, endColor, areaRadio)
            }
        }
        return -1
    }

    /**
     * 计算当前比例在子区间的比例
     *
     * @param radio         总比例
     * @param startPosition 子区间开始位置
     * @param endPosition   子区间结束位置
     * @return 自区间比例[0, 1]
     */
    private fun getAreaRadio(radio: Float, startPosition: Float, endPosition: Float): Float {
        return (radio - startPosition) / (endPosition - startPosition)
    }

    /**
     * 取两个颜色间的渐变区间 中的某一点的颜色
     *
     * @param startColor 开始的颜色
     * @param endColor   结束的颜色
     * @param radio      比例 [0, 1]
     * @return 选中点的颜色
     */
    private fun getColorFrom(startColor: Int, endColor: Int, radio: Float): Int {
        val redStart = Color.red(startColor)
        val blueStart = Color.blue(startColor)
        val greenStart = Color.green(startColor)
        val redEnd = Color.red(endColor)
        val blueEnd = Color.blue(endColor)
        val greenEnd = Color.green(endColor)

        val red = (redStart + ((redEnd - redStart) * radio + 0.5)).toInt()
        val greed = (greenStart + ((greenEnd - greenStart) * radio + 0.5)).toInt()
        val blue = (blueStart + ((blueEnd - blueStart) * radio + 0.5)).toInt()
        return Color.argb(255, red, greed, blue)
    }

    // 获取 Arc 颜色数组
    private fun getArcColors(context: Context, ta: TypedArray): IntArray {
        val ret: IntArray
        var resId = ta.getResourceId(R.styleable.ArcSeekBar_asb_arc_colors, 0)
        if (0 == resId) {
            resId = R.array.arc_colors_default
        }
        ret = getColorsByArrayResId(context, resId)
        return ret
    }

    // 根据 resId 获取颜色数组
    private fun getColorsByArrayResId(context: Context, resId: Int): IntArray {
        val ret: IntArray
        val colorArray = context.resources.obtainTypedArray(resId)
        ret = IntArray(colorArray.length())
        for (i in 0 until colorArray.length()) {
            ret[i] = colorArray.getColor(i, 0)
        }
        colorArray.recycle()
        return ret
    }
}