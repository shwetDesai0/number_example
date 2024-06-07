package com.example.mybaseapplication.activity.example1.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class VennDiagramView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint1 = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        alpha = 100
    }

    private val paint2 = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
        alpha = 100
    }

    private val paint3 = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        alpha = 100
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    var set1 = emptySet<Int>()
    var set2 = emptySet<Int>()
    var set3 = emptySet<Int>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw the circles
        val radius = width / 5f
        val centerX1 = width / 4f
        val centerX2 = 2 * width / 4f
        val centerX3 = 3 * width / 4f
        val centerY = height / 2f

        canvas.drawCircle(centerX1, centerY, radius, paint1)
        canvas.drawCircle(centerX2, centerY, radius, paint2)
        canvas.drawCircle(centerX3, centerY, radius, paint3)

        // Calculate intersections
        val intersection12 = set1.intersect(set2)
        val intersection13 = set1.intersect(set3)
        val intersection23 = set2.intersect(set3)
        val intersectionAll = set1.intersect(set2).intersect(set3)

        // Draw the labels
        canvas.drawText("Set 1: ${set1.size}", centerX1, centerY - radius - 20, textPaint)
        canvas.drawText("Set 2: ${set2.size}", centerX2, centerY - radius - 20, textPaint)
        canvas.drawText("Set 3: ${set3.size}", centerX3, centerY - radius - 20, textPaint)
        canvas.drawText("Intersection 1&2: ${intersection12.size}", (centerX1 + centerX2) / 2, centerY, textPaint)
        canvas.drawText("Intersection 1&3: ${intersection13.size}", (centerX1 + centerX3) / 2, centerY + radius / 2, textPaint)
        canvas.drawText("Intersection 2&3: ${intersection23.size}", (centerX2 + centerX3) / 2, centerY - radius / 2, textPaint)
        canvas.drawText("All Intersections: ${intersectionAll.size}", width / 2f, height.toFloat() - 50, textPaint)
    }
}