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
import dev.jorgecastillo.androidcolorx.library.asHex
import dev.jorgecastillo.androidcolorx.library.complimentary
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.gradientSeekBar
import org.w3c.dom.Text
import java.lang.Double.toHexString
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorWheel = findViewById<ColorWheel>(R.id.colorWheel)
        val slider = gradientSeekBar.argb



        var background = findViewById<View>(R.id.background)


        colorWheel.colorChangeListener = { rgb: Int ->

            gradientSeekBar.setBlackToColor(rgb)
            //rgbtext.text = rgb.toString()
            var wht = rgb.blue




        }

        gradientSeekBar.colorChangeListener = { offset: Float, argb: Int ->

            chosen_colour.setColorFilter(gradientSeekBar.argb)
            var comp = argb.complimentary()
            var analogous: Pair<Int, Int> = argb.analogous()
            val anLocator = findViewById<ImageView>(R.id.analogous_col1)
            val an2Locator = findViewById<ImageView>(R.id.analogous_col2)
            val anTextLocator = findViewById<TextView>(R.id.analogous_col1_overlay)
            var an2TextLocator = findViewById<TextView>(R.id.analogous_col2_overlay)
            val compTextLocator = findViewById<TextView>(R.id.comp_colour_overlay)


            anTextLocator.text = analogous.first.asHex().toString().substring(3)
            an2TextLocator.text = analogous.second.asHex().toString().substring(3)
            compTextLocator.text = comp.asHex().toString().substring(3)
            anLocator.setColorFilter(analogous.first)
            an2Locator.setColorFilter(analogous.second)
            var compLocator = findViewById<ImageView>(R.id.comp_colour)
            compLocator.setColorFilter(comp)





                    //gradientSeekBar.argb.toString()
        }






    }
}
