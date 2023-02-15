package com.popov.customclock.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View


class CustomAnalogClock : View, ICustomAnalogClock, ICustomAnalogClockUpdateTime {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private var mHeight = 0
    private var mWidth = 0

    private val mClockHours = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private var mPadding = 0
    private val mNumeralSpacing = 0

    private var mHandTruncation = 0
    private var mHourHandTruncation = 0

    private var mRadius = 0
    private var mPaint: Paint? = null
    private val mRect: Rect = Rect()
    private var isInit = false


    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            mPaint = Paint()
            mHeight = height
            mWidth = width
            mPadding = mNumeralSpacing + 50
            val minAttr = Math.min(mHeight, mWidth)
            mRadius = minAttr / 2 - mPadding

            mHandTruncation = minAttr / 20
            mHourHandTruncation = minAttr / 17
            isInit = true
        }
        canvas.drawColor(Color.DKGRAY)

        mPaint!!.reset()
        mPaint!!.color = Color.WHITE
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 4f
        mPaint!!.isAntiAlias = true
        canvas.drawCircle(
            (mWidth / 2).toFloat(), (mHeight / 2).toFloat(), (mRadius + mPadding - 10).toFloat(),
            mPaint!!
        )

        mPaint!!.style = Paint.Style.FILL
        canvas.drawCircle(
            (mWidth / 2).toFloat(),
            (mHeight / 2).toFloat(),
            12f,
            mPaint!!
        )

        val fontSize =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14f, resources.displayMetrics)
                .toInt()
        mPaint!!.textSize = fontSize.toFloat()

        for (hour in mClockHours) {
            val tmp = hour.toString()
            mPaint!!.getTextBounds(tmp, 0, tmp.length, mRect)

            val angle = Math.PI / 6 * (hour - 3)
            val x = (mWidth / 2 + Math.cos(angle) * mRadius - mRect.width() / 2).toInt()
            val y = (mHeight / 2 + Math.sin(angle) * mRadius + mRect.height() / 2).toInt()
            canvas.drawText(
                hour.toString(),
                x.toFloat(),
                y.toFloat(),
                mPaint!!
            )
        }

        var hour: Float = times.toFloat()
        hour = if (hour > 12) hour - 12 else hour
        var minutes: Float = times.toFloat()

        drawHandLine(
            canvas,
            (hour / 3600).toDouble() * 5f,
            true,
            false
        )

        drawHandLine(canvas, (minutes / 60).toDouble() * 5f, false, false)

        drawHandLine(canvas, times.toDouble(), false, true)

        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHandLine(canvas: Canvas, moment: Double, isHour: Boolean, isSecond: Boolean) {
        val angle = Math.PI * moment / 30 - Math.PI / 2
        val handRadius =
            if (isHour) mRadius - mHandTruncation - mHourHandTruncation else mRadius - mHandTruncation
        if (isSecond) mPaint!!.color = Color.YELLOW
        canvas.drawLine(
            (mWidth / 2).toFloat(),
            (mHeight / 2).toFloat(),
            (mWidth / 2 + Math.cos(angle) * handRadius).toFloat(),
            (mHeight / 2 + Math.sin(angle) * handRadius).toFloat(),
            mPaint!!
        )
    }

    private var times = 0 // типой таймер
    private var state = TimeState(currentTime(), false)
    private val listeners = mutableListOf<(TimeState) -> Unit>()

    override fun start() {
        state = TimeState(currentTime(), true)
    }

    override fun stop() {
        state = TimeState(currentTime(), false)
    }

    override fun reset() {
        times = 0
        state = TimeState(currentTime(), false)
    }

    override fun currentTime(): Long {
        return times.toLong()
    }

    override fun addUpdateListener(listener: (TimeState) -> Unit) {
        listeners.add(listener)
//        Log.d("AAA", "$listeners")
    }

    override fun removeUpdateListener(listener: (TimeState) -> Unit) {
        listeners.remove(listener)
    }

    override fun updateTime(time: Int) {
        times = time
        state = TimeState(time.toLong(), state.isPlayed)
        listeners.forEach{
            it(state)
        }
    }
}

interface ICustomAnalogClock{
    fun start()

    fun stop()

    fun reset()

    fun currentTime(): Long

    fun addUpdateListener(listener: (TimeState) -> Unit)

    fun removeUpdateListener(listener: (TimeState) -> Unit)
}

interface ICustomAnalogClockUpdateTime {
    fun updateTime(time: Int)
}