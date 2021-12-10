package net.l1ngdtkh3.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.SweepGradient
import android.os.Build
import android.text.DynamicLayout
import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import net.l1ngdtkh3.ui.R

class RatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundWidth = 12f
    private val progressWidth = 12f
    private val _textSize = 30f

    private val textPaint = TextPaint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = _textSize
        isAntiAlias = true
    }

    private val backgroundPaint = Paint().apply {
        color = Color.GRAY
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val backgroundProgressPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = backgroundWidth
        isAntiAlias = true
    }

    private val progressPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = progressWidth
        isAntiAlias = true
    }

    private val oval = RectF()
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius: Float = 0f
    private var progress: Float = 0f
    private var value: Double = 0.0
    private val shaderGradient by lazy {
        SweepGradient(measuredWidth / 2F, measuredHeight / 2F, Color.BLUE, Color.GREEN).apply {
            setLocalMatrix(Matrix().apply { preRotate(270f, measuredWidth / 2F, measuredHeight / 2F) })
        }
    }
    private val spannableStringBuilder = SpannableStringBuilder("")
    private val dynamicLayout by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            DynamicLayout.Builder
                .obtain(spannableStringBuilder, textPaint, measuredWidth)
                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                .build()
        } else {
            DynamicLayout(
                spannableStringBuilder,
                textPaint,
                measuredWidth,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                true
            )
        }
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.RatingView) {
            progress = getInteger(R.styleable.RatingView_rating_progress, 0).toFloat()
        }
    }

    fun setProgress(value: Double) {
        progress = (value * 10).toFloat()
        this.value = value
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w.toFloat() / 2
        centerY = h.toFloat() / 2
        radius = w.toFloat() / 2 - progressWidth
        oval.set(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawCircle(centerX, centerY, radius + 10f, backgroundPaint)
            drawCircle(centerX, centerY, radius, backgroundProgressPaint)
            progressPaint.shader = shaderGradient
            drawArc(oval, 270f, 360f / (100 / progress), false, progressPaint)
            val text = value.toString()
            val offset = when (text.length) {
                1 -> 2f
                2 -> 1.75f
                else -> 1.5f
            }
            translate(centerX - (_textSize * offset), centerY - (_textSize / offset))
            spannableStringBuilder.clear()
            spannableStringBuilder.append(value.toString())
            dynamicLayout.draw(this)
        }
    }
}