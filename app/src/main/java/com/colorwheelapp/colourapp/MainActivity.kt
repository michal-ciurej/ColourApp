package com.colorwheelapp.colourapp

import android.graphics.Color
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
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
        val clear = findViewById<Button>(R.id.clear)

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





//BIG SETTER FUNCTION

        fun setter(argb: Int, i: Int, rgb: Int) {

            chosen_colour.setColorFilter(argb)
            chosenColourrgb.text = argb.asRgb().toString()

            var hash = getString(R.string.hash)
            var hexx = argb.asHex().toString().substring(3)
            chosenColour.text = hash + hexx




            var shadess: List<Int> = argb.tints()

            var comp = argb.complimentary() //returns one complimentary, will be at array 1
            var analogous: Pair<Int, Int> = argb.analogous() //returns two analogous, will be at array 3
            var tri: Pair<Int, Int> = argb.triadic() //returns two which make triadic combined with the comp, will be ar array2
            var tet: Triple<Int, Int, Int> = argb.tetradic()




            when (i) {
 // 0 is monochromatic
                0 ->  {
                  //  var shadess: List<Int> = argb.tints()

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
                   // var shadess: List<Int> = argb.tints()

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
                   // var shadess: List<Int> = argb.tints()

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
                   // var shadess: List<Int> = argb.tints()

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

        //how do these work again

        picker.setOnItemClickedListener(this)
        picker.setOnItemSelectedListener(this)

  //this is the first listener for the wheel, changes the gradientbar triggering the other listener



        colorWheel.colorChangeListener = { rgb: Int ->

            gradientSeekBar.setBlackToColor(rgb)
            var wht = rgb.blue


        }





        //Inside this listener is where I'm gonna do a lot of my code. This is the constantly updating one
        gradientSeekBar.colorChangeListener = { offset: Float, argb: Int->




            var rgb = colorWheel.rgb
            setter(argb, i, rgb)
        }






        fun jebsieszmato(r1:Int, g1: Int, b1: Int){

            var argb = Color.rgb(r1,g1,b1)
            var rgb = Color.rgb(r1,g1,b1)
            chosen_colour.setColorFilter(argb)
            colorWheel.rgb = argb


            setter(argb, i, rgb)




        }



        var setButton = findViewById<Button>(R.id.setbutton)

            setButton.setOnClickListener {



                val hex = findViewById<EditText>(R.id.hexinput)
                val red = findViewById<EditText>(R.id.r)
                val green = findViewById<EditText>(R.id.g)
                val blue = findViewById<EditText>(R.id.b)

                try {
                    var hexint = hex.text.toString()

                    //this bit is added
                    var first = hexint.substring(0,1)
                    if (first != "#"){
                        hexint = "#$hexint"

                    }
                    else{
                        println("skipping")
                    }


                    //added bit ends


                    var argb = parseColor(hexint)
                    var rgb = parseColor(hexint)



                    println("executing try at end")
                    colorWheel.rgb = argb
                    setter(argb, i, rgb)
                    return@setOnClickListener

                }

                catch (t: Throwable ){
                    //Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show()

                    println("throwing hex error")
                }




                try {
                    if (red.text.toString().isEmpty()) {
                        bruh.performClick()
                        return@setOnClickListener

                    }
                    if (green.text.toString().isEmpty()) {
                        bruh.performClick()

                        return@setOnClickListener


                    }
                    if (blue.text.toString().isEmpty()) {
                        bruh.performClick()
                        return@setOnClickListener


                    }




                    else {


                        var g1 = green.text.toString().toInt()
                        if (g1 > 255){
                            g1 = 255
                            green.setText(g1.toString())
                        }
                        var r1 = red.text.toString().toInt()
                        if (r1 > 255){
                            r1 = 255
                            red.setText(r1.toString())

                        }
                        var b1 = blue.text.toString().toInt()
                        if (b1 > 255){
                            b1 = 255
                            blue.setText(b1.toString())
                        }

                        jebsieszmato(r1, g1, b1)


                    }
                }

                catch (T: Throwable){

                    Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show()

                }




            }

        bruh.setOnClickListener {

            var argb = gradientSeekBar.argb
            var rgb = gradientSeekBar.argb


            setter(argb, i, rgb)
        }


        clear.setOnClickListener {
            val hex = findViewById<EditText>(R.id.hexinput)
            val red = findViewById<EditText>(R.id.r)
            val green = findViewById<EditText>(R.id.g)
            val blue = findViewById<EditText>(R.id.b)
            hex.setText(R.string.hexprompt)
            red.text.clear()
            green.text.clear()
            blue.text.clear()
        }





    }

    override fun onItemSelected(index:Int) {
        i = index
        println(i)
        val setter = findViewById<Button>(R.id.setbutton)


        setter.performClick()

    }
    override fun onItemClicked(index:Int) {
        i = index
        println(i)
        val setter = findViewById<Button>(R.id.setbutton)



        setter.performClick()



    }

}


