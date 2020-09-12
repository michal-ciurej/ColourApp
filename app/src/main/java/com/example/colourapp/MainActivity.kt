package com.example.colourapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.apandroid.colorwheel.ColorWheel
import com.apandroid.colorwheel.gradientseekbar.GradientSeekBar
import com.apandroid.colorwheel.gradientseekbar.setBlackToColor
import dev.jorgecastillo.androidcolorx.library.analogous
import dev.jorgecastillo.androidcolorx.library.complimentary
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.gradientSeekBar
import java.lang.Double.toHexString
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorWheel = findViewById<ColorWheel>(R.id.colorWheel)
        val slider = gradientSeekBar.argb
        var rgbtext = findViewById<TextView>(R.id.rgb_box)
        var argbtext = findViewById<TextView>(R.id.argb_box)


        var background = findViewById<View>(R.id.background)


        colorWheel.colorChangeListener = { rgb: Int ->

            gradientSeekBar.setBlackToColor(rgb)
            //rgbtext.text = rgb.toString()
            var wht = rgb.blue
            rgbtext.text = wht.toString()



        }

        gradientSeekBar.colorChangeListener = { offset: Float, argb: Int ->

            background.setBackgroundColor(gradientSeekBar.argb)
            var comp = argb.complimentary()
            var analogous: Pair<Int, Int> = argb.analogous()
            var anLocator = findViewById<ImageView>(R.id.analogous_col1)
            var an2Locator = findViewById<ImageView>(R.id.analogous_col2)
            anLocator.setBackgroundColor(analogous.first)
            an2Locator.setBackgroundColor(analogous.second)
            var compLocator = findViewById<ImageView>(R.id.comp_colour)
            compLocator.setBackgroundColor(comp)





            //argbtext.text = argb.toString()
            val finalcolor = java.lang.Integer.toHexString(argb)
            argbtext.text = finalcolor.substring(2).toUpperCase()
                    //gradientSeekBar.argb.toString()
        }






    }
}
