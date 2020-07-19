package br.com.psyapp.lib.emotions.ui.base

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout

class FrameGlass(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val downSampleFactor = 4
    private val blurRadius = 10f
    private val overlayColor = Color.WHITE
    private val clearPaint by lazy {
        val it = Paint()

        it.style = Paint.Style.FILL
        it.color = Color.TRANSPARENT

        it
    }
    private val overlayPaint by lazy {
        val it = Paint()

        it.style = Paint.Style.FILL
        it.color = overlayColor
        it.xfermode = PorterDuffXfermode(PorterDuff.Mode.ADD)

        it
    }
    private lateinit var renderScript: RenderScript
    private lateinit var blurScript: ScriptIntrinsicBlur
    private var blurredView: View? = null
    private var blurredCanvas: Canvas? = null
    private var blurredBmpSrc: Bitmap? = null
    private var blurredBmpDst: Bitmap? = null
    private var lastBlurredWidth = 0
    private var lastBlurredHeight = 0
    private var blurInput: Allocation? = null
    private var blurOutput: Allocation? = null
    private val shadowElevation = 10f
    private val roundedCorner = 50f

    constructor(context: Context) : this(context, null)

    init {
        initRenderScript()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setBackgroundColor(Color.TRANSPARENT)
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                view?.let {
                    outline?.setRoundRect(0, 0, view.width, view.height, roundedCorner)
                }
            }
        }
        elevation = shadowElevation

        initBlurredView()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.let {
            if (isInEditMode) {
                it.drawColor(Color.WHITE)
            } else {
                it.drawPaint(clearPaint)

                blurredView?.let { view ->
                    if (canDrawGlass(view)) {
                        drawGlass(view, it)
                    }
                }

                it.drawPaint(overlayPaint)
            }
        }

        super.dispatchDraw(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        renderScript.destroy()
    }

    private fun initRenderScript() {
        renderScript = RenderScript.create(context)
        blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        blurScript.setRadius(blurRadius)
    }

    private fun initBlurredView() {
        blurredView = (parent as View).findViewWithTag("blurred")
    }

    private fun canDrawGlass(view: View): Boolean {
        val width = view.measuredWidth
        val height = view.measuredHeight

        if (blurredCanvas == null || lastBlurredWidth != width || lastBlurredHeight != height) {
            lastBlurredWidth = width
            lastBlurredHeight = height

            var scaledWidth = width / downSampleFactor
            var scaledHeight = height / downSampleFactor

            scaledWidth = scaledWidth - scaledWidth % 4 + 4
            scaledHeight = scaledHeight - scaledHeight % 4 + 4

            if (blurredBmpDst == null || blurredBmpDst!!.width != scaledWidth || blurredBmpDst!!.height != scaledHeight) {
                blurredBmpSrc =
                    Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
                        ?: return false
                blurredBmpDst =
                    Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
                        ?: return false
            }

            blurredCanvas = Canvas(blurredBmpSrc!!)
            blurredCanvas?.apply {
                scale(1f / downSampleFactor, 1f / downSampleFactor)
            }
            blurInput = Allocation.createFromBitmap(
                renderScript,
                blurredBmpSrc,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT
            )
            blurOutput = Allocation.createTyped(renderScript, blurInput?.type)
        }

        return true
    }

    private fun drawGlass(view: View, canvas: Canvas) {
        val bitmapSource = blurredBmpSrc ?: return
        val bitmapDest = blurredBmpDst ?: return
        val input = blurInput ?: return
        val output = blurOutput ?: return
        val scaleFactor = downSampleFactor.toFloat()

        if (view.background != null && view.background is ColorDrawable) {
            bitmapSource.eraseColor((view.background as ColorDrawable).color)
        } else {
            bitmapSource.eraseColor(Color.TRANSPARENT)
        }

        view.draw(blurredCanvas)

        input.copyFrom(bitmapSource)
        blurScript.setInput(input)
        blurScript.forEach(output)
        output.copyTo(bitmapDest)

        val diffX = view.x - x
        val diffY = view.y - y

        canvas.save()
        canvas.rotate(-rotation, measuredWidth / 2f, measuredHeight / 2f)
        canvas.translate(diffX, diffY)
        canvas.scale(scaleFactor, scaleFactor)
        canvas.drawBitmap(bitmapDest, 0f, 0f, null)
        canvas.restore()
    }

}
