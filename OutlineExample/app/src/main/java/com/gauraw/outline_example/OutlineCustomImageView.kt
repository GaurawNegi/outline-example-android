package com.gauraw.outline_example

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi


class OutlineCustomImageView : CustomImageView {


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        updateOutlineProvider()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updateOutlineProvider() {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                view.let {
                    if (!outlinePath.isEmpty)
                        outline?.setConvexPath(outlinePath)
                }
            }
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateOutlineProvider()
    }


}
 