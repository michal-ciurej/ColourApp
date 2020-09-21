package com.colorwheelapp.colourapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.blue
import com.apandroid.colorwheel.ColorWheel
import com.apandroid.colorwheel.gradientseekbar.setBlackToColor

import com.wefika.horizontalpicker.HorizontalPicker
import dev.jorgecastillo.androidcolorx.library.*


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HorizontalPicker.OnItemSelected, HorizontalPicker.OnItemClicked  {
    companion object {
        var i = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorWheel = findViewById<ColorWheel>(R.id.colorWheel)
        val slider = gradientSeekBar.argb

        val FirstLocator = findViewById<ImageView>(R.id.GridCol1)
        val SecondLocator = findViewById<ImageView>(R.id.GridCol2)
        val FirstTextLocator = findViewById<TextView>(R.id.gridcol1_overlay)
        var SecondTextLocator = findViewById<TextView>(R.id.gridcol2_overlay)
        var ThirdLocator = findViewById<ImageView>(R.id.GridCol3)
        val ThirdTextLocator = findViewById<TextView>(R.id.gridCol3_overlay)
        val FourthLocator = findViewById<ImageView>(R.id.GridCol4)
        var fourthtextlocator = findViewById<TextView>(R.id.GridCol4_overlay)

        val chosenColour = findViewById<TextView>(R.id.chosen_colour_value)
        val chosenColourrgb = findViewById<TextView>(R.id.chosen_colour_value_rgb)

        SecondLocator.setColorFilter(R.color.eigengrau)
        ThirdLocator.setColorFilter(R.color.eigengrau)
        FourthLocator.setColorFilter(R.color.black)

        var picker = HorizontalPicker(this)
        picker = findViewById<HorizontalPicker>(R.id.relationship_selector)




        var background = findViewById<View>(R.id.background)

//BIG SETTER FUNCTION

        fun setter(offset: Float, argb: Int, i: Int) {

            chosen_colour.setColorFilter(gradientSeekBar.argb)

            chosenColourrgb.text = argb.asRgb().toString()
            chosenColour.text = argb.asHex().toString()





            var comp = argb.complimentary()
            var analogous: Pair<Int, Int> = argb.analogous()
            var tri: Pair<Int, Int> = argb.triadic()



            when (i) {

                0 ->  {
                    FirstLocator.setColorFilter(comp)
                    FirstTextLocator.text = comp.asHex().toString().substring(3)

                    SecondTextLocator.text = " "
                    ThirdTextLocator.text = " "

                    SecondLocator.setColorFilter(R.color.eigengrau)
                    ThirdLocator.setColorFilter(R.color.eigengrau)
                    FourthLocator.setColorFilter(R.color.black)

                }
                1 -> {
                    FirstLocator.setColorFilter(comp)
                    FirstTextLocator.text = comp.asHex().toString().substring(3)

                    SecondTextLocator.text = analogous.first.asHex().toString().substring(3)
                    ThirdTextLocator.text = analogous.second.asHex().toString().substring(3)

                    SecondLocator.setColorFilter(analogous.first)
                    ThirdLocator.setColorFilter(analogous.second)

                }

                2 -> {
                    FirstLocator.setColorFilter(comp)
                    FirstTextLocator.text = comp.asHex().toString().substring(3)

                    SecondTextLocator.text = tri.first.asHex().toString().substring(3)
                    ThirdTextLocator.text = tri.second.asHex().toString().substring(3)

                    SecondLocator.setColorFilter(tri.first)
                    ThirdLocator.setColorFilter(tri.second)


                }

                else -> {
                    println("calling else")
                }




            }





        }

        picker.setOnItemClickedListener(this)
        picker.setOnItemSelectedListener(this)

  //this is the first listener for the wheel, changes the gradientbar triggering the other listener



        colorWheel.colorChangeListener = { rgb: Int ->

            gradientSeekBar.setBlackToColor(rgb)
            //rgbtext.text = rgb.toString()
            var wht = rgb.blue

        }





        //Inside this listener is where I'm gonna do a lot of my code. This is the constantly updating one
        gradientSeekBar.colorChangeListener = { offset: Float, argb: Int->


            setter(offset, argb, i)
        }








    }

    override fun onItemSelected(index:Int) {
        i = index
        println(i)
        colorWheel.rgb = colorWheel.rgb
        return


    }
    override fun onItemClicked(index:Int) {
        i = index
        println(i)
        colorWheel.rgb = colorWheel.rgb

        return


    }

}


