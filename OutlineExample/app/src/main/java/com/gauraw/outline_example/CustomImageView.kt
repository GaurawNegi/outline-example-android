package com.gauraw.outline_example

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView


open class CustomImageView : AppCompatImageView {

    private var cornerRadiusTopLeft = 0f
    private var cornerRadiusTopRight = 0f
    private var cornerRadiusBottomLeft = 0f
    private var cornerRadiusBottomRight = 0f
    protected var outlinePath: Path = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr);
    }

    private fun init(
        context: Context,
        attrs: AttributeSet?, defStyle: Int
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyle, 0)
        try {
            val cornerRadius = a.getDimension(R.styleable.CustomImageView_civ_corner_radius, 0f)

            cornerRadiusTopLeft = cornerRadius
            cornerRadiusTopRight = cornerRadius
            cornerRadiusBottomLeft = cornerRadius
            cornerRadiusBottomRight = cornerRadius


            cornerRadiusTopLeft = a.getDimension(
                R.styleable.CustomImageView_civ_corner_radius_top_left,
                cornerRadiusTopLeft
            )
            cornerRadiusTopRight = a.getDimension(
                R.styleable.CustomImageView_civ_corner_radius_top_right,
                cornerRadiusTopRight
            )
            cornerRadiusBottomLeft = a.getDimension(
                R.styleable.CustomImageView_civ_corner_radius_bottom_left,
                cornerRadiusBottomLeft
            )
            cornerRadiusBottomRight = a.getDimension(
                R.styleable.CustomImageView_civ_corner_radius_bottom_right,
                cornerRadiusBottomRight
            )
        } catch (e: Exception) {

        } finally {
            a.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        generateOutlinePath()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(outlinePath)
        super.onDraw(canvas)
    }

    private fun generateOutlinePath() {
        outlinePath.rewind()
        val width = width.toFloat()
        val height = height.toFloat()
        val radii = FloatArray(8)

        if (cornerRadiusTopLeft > 0) {
            radii[0] = cornerRadiusTopLeft
            radii[1] = cornerRadiusTopLeft
        }

        if (cornerRadiusTopRight > 0) {
            radii[2] = cornerRadiusTopRight
            radii[3] = cornerRadiusTopRight
        }

        if (cornerRadiusBottomRight > 0) {
            radii[4] = cornerRadiusBottomRight
            radii[5] = cornerRadiusBottomRight
        }

        if (cornerRadiusBottomLeft > 0) {
            radii[6] = cornerRadiusBottomLeft
            radii[7] = cornerRadiusBottomLeft
        }

        outlinePath.addRoundRect(
            RectF(0f, 0f, width, height),
            radii, Path.Direction.CCW
        )
    }
}
 