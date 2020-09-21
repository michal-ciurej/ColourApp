package com.colorwheelapp.colourapp

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
        var FourthTextLocator = findViewById<TextView>(R.id.GridCol4_overlay)

        val chosenColour = findViewById<TextView>(R.id.chosen_colour_value)
        val chosenColourrgb = findViewById<TextView>(R.id.chosen_colour_value_rgb)

        FirstLocator.setColorFilter(R.color.black)
        SecondLocator.setColorFilter(R.color.black)
        ThirdLocator.setColorFilter(R.color.black)
        FourthLocator.setColorFilter(R.color.black)

        var picker = HorizontalPicker(this)
        picker = findViewById<HorizontalPicker>(R.id.relationship_selector)




        var background = findViewById<View>(R.id.background)

//BIG SETTER FUNCTION

        fun setter(offset: Float, argb: Int, i: Int) {

            chosen_colour.setColorFilter(gradientSeekBar.argb)

            chosenColourrgb.text = argb.asRgb().toString()
            chosenColour.text = argb.asHex().toString()





            var comp = argb.complimentary() //returns one complimentary, will be at array 1
            var analogous: Pair<Int, Int> = argb.analogous() //returns two analogous, will be at array 3
            var tri: Pair<Int, Int> = argb.triadic() //returns two which make triadic combined with the comp, will be ar array2
            var tet: Triple<Int, Int, Int> = argb.tetradic()
            var shadess: List<Int> = argb.tints()




            when (i) {
 // 0 is monochromatic
                0 ->  {
                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = shadess.elementAt(5).asHex().toString().substring(3)
                    ThirdTextLocator.text = shadess.elementAt(7).asHex().toString().substring(3)
                    FourthTextLocator.text = shadess.elementAt(8).darken(50).asHex().toString().substring(3)

                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(shadess.elementAt(5))
                    ThirdLocator.setColorFilter(shadess.elementAt(7))
                    FourthLocator.setColorFilter(shadess.elementAt(8).darken(50))


                }

                // 1 is Complimentary
                1 -> {

                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = comp.asHex().toString().substring(3)
                    ThirdTextLocator.text = shadess.elementAt(3).complimentary().asHex().toString().substring(3)
                    FourthTextLocator.text = shadess.elementAt(5).complimentary().asHex().toString().substring(3)

                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(comp)
                    ThirdLocator.setColorFilter(shadess.elementAt(3).complimentary())
                    FourthLocator.setColorFilter(shadess.elementAt(5).complimentary())


                }
//2 is triadic
                2 -> {
                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = comp.asHex().toString().substring(3)
                    ThirdTextLocator.text = tri.first.asHex().toString().substring(3)
                    FourthTextLocator.text = tri.second.asHex().toString().substring(3)


                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(comp)
                    ThirdLocator.setColorFilter(tri.first)
                    FourthLocator.setColorFilter(tri.second)


                }
                // 3 is analogous
                3 -> {

                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = comp.asHex().toString().substring(3)
                    ThirdTextLocator.text = analogous.first.asHex().toString().substring(3)
                    FourthTextLocator.text = analogous.second.asHex().toString().substring(3)


                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(comp)
                    ThirdLocator.setColorFilter(analogous.first)
                    FourthLocator.setColorFilter(analogous.second)


                }
                //4 is tetradic
                4 -> {

                    FirstTextLocator.text = comp.asHex().toString().substring(3)
                    SecondTextLocator.text = tet.first.asHex().toString().substring(3)
                    ThirdTextLocator.text = tet.second.asHex().toString().substring(3)
                    FourthTextLocator.text = tet.third.asHex().toString().substring(3)


                    FirstLocator.setColorFilter(comp)
                    SecondLocator.setColorFilter(tet.first)
                    ThirdLocator.setColorFilter(tet.second)
                    FourthLocator.setColorFilter(tet.third)


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


