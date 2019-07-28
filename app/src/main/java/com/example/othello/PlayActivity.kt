package com.example.othello

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


@Suppress("IMPLICIT_CAST_TO_ANY")
class PlayActivity : AppCompatActivity() {

    var chance: Int = 0
    var black: Int = 2
    var white: Int = 2

    lateinit var grid: Array<Array<Button>>

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_play)

        grid = Array(8) {
            Array(8) {
                Button(this)
            }
        }

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL  //Can also be done in xml by android:orientation="vertical"

        val innerLayout = LinearLayout(this)
        innerLayout.orientation = LinearLayout.HORIZONTAL
        innerLayout.gravity = Gravity.CENTER
        layout.addView(innerLayout)

        val t1white = TextView(this)
        val t2white = TextView(this)
        val t1black = TextView(this)
        val t2black = TextView(this)
        val turn = TextView(this)
        val turnText = TextView(this)

        turn.text = "Turn = "
        turnText.text = "\u26AB" + "            "
        t1white.text = "\u26AA" + " = "
        t2white.text = "2    "
        t1black.text = "\u26AB" + " = "
        t2black.text = "2"

        turn.textSize = 25f
        turnText.textSize = 25f
        t1white.textSize = 25f
        t2white.textSize = 25f
        t1black.textSize = 25f
        t2black.textSize = 25f

        turn.setTextColor(parseColor("#FAD6D6"))
        t1white.setTextColor(parseColor("#FAD6D6"))
        t2white.setTextColor(parseColor("#FAD6D6"))
        t1black.setTextColor(parseColor("#FAD6D6"))
        t2black.setTextColor(parseColor("#FAD6D6"))

        turn.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        innerLayout.addView(turn)
        turnText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        innerLayout.addView(turnText)
        //white count
        t1white.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        innerLayout.addView(t1white)
        t2white.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        //black count
        innerLayout.addView(t2white)
        t1black.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        innerLayout.addView(t1black)
        t2black.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        innerLayout.addView(t2black)


        for (i in 0..7) {
            val row = LinearLayout(this)
            row.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (j in 0..7) {
                grid[i][j].layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    140,
                    1f
                )
                val param = grid[i][j].layoutParams as LinearLayout.LayoutParams
                param.setMargins(6, 6, 0, 0)
                grid[i][j].layoutParams = param
                grid[i][j].text = ""
                grid[i][j].setBackgroundColor(parseColor("#F76262"))
                grid[i][j].textSize = 27.8f
                row.addView(grid[i][j])
            }
            layout.addView(row)
        }
        setContentView(layout)
        layout.setBackgroundColor(parseColor("#3D3D3D"))

        val newGameBtn = Button(this)
        newGameBtn.text = "NEW GAME"
        newGameBtn.textSize = 30f
        newGameBtn.setBackgroundColor(parseColor("#1a1a1a"))
        newGameBtn.setTextColor(parseColor("#FAD6D6"))

        newGameBtn.setPadding(30, 10, 30, 10)

        newGameBtn.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val param2 = newGameBtn.layoutParams as LinearLayout.LayoutParams
        param2.setMargins(0, 50, 0, 0)
        newGameBtn.layoutParams = param2
        layout.addView(newGameBtn)
        layout.gravity = Gravity.CENTER

        for (i in 0..7) {
            for (j in 0..7) {

                grid[3][3].text = "\u26AA"
                grid[3][4].text = "\u26AB"
                grid[4][4].text = "\u26AA"
                grid[4][3].text = "\u26AB"

                grid[i][j].setOnClickListener {
                    //write logic here

                    if (grid[i][j].text == "") {
                        //&& (grid[i - 1][j - 1].text == "\u26AA" || grid[i - 1][j].text == "\u26AA" || grid[i - 1][j + 1].text == "\u26AA" || grid[i][j - 1].text == "\u26AA" || grid[i][j + 1].text == "\u26AA" || grid[i + 1][j - 1].text == "\u26AA" || grid[i + 1][j].text == "\u26AA" || grid[i + 1][j + 1].text == "\u26AA")
                        if (chance == 0 && validMove(0, i, j)) {
                            turnText.text = "\u26AA" + "            "
                            turnText.setTextColor(parseColor("#FAD6D6"))
                            grid[i][j].text = "\u26AB"
                            black++
                            chance = 1

                            //loop for vertical down for black
                            for (m in 0 until i - 1) {
                                if (grid[m][j].text == "\u26AB") {
                                    if (grid[m + 1][j].text == "\u26AA") {
                                        grid[m + 1][j].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                }
                            }

                            // loop for vertical up for black
                            for (n in 7 downTo i + 1) {
                                if (grid[n][j].text == "\u26AB") {
                                    if (grid[n - 1][j].text == "\u26AA") {
                                        grid[n - 1][j].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                }
                            }

                            //loop for horizontal right for black
                            for (m in 0 until j - 1) {
                                if (grid[i][m].text == "\u26AB") {
                                    if (grid[i][m + 1].text == "\u26AA") {
                                        grid[i][m + 1].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                }
                            }

                            //loop for horizontal left for black
                            for (n in 7 downTo j + 1) {
                                if (grid[i][n].text == "\u26AB") {
                                    if (grid[i][n - 1].text == "\u26AA") {
                                        grid[i][n - 1].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                }
                            }

                            //loop for left diagonal  downwards in black
                            var n = j + 1
                            var m = i + 1
                            while (m < 8 && n < 8 && m > i && n > j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AB") {
                                    if (grid[m - 1][n - 1].text == "\u26AA") {
                                        grid[m - 1][n - 1].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                    m--
                                    n--
                                } else {
                                    n++
                                    m++
                                }
                            }

                            //loop for left diagonal  upwards in black
                            m = i - 1
                            n = j - 1
                            while (m >= 0 && n >= 0 && m < i && n < j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AB") {
                                    if (grid[m + 1][n + 1].text == "\u26AA") {
                                        grid[m + 1][n + 1].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                    m++
                                    n++
                                } else {
                                    n--
                                    m--
                                }
                            }

                            //loop for right diagonal downwards in black
                            n = j + 1
                            m = i - 1
                            while (m >= 0 && n < 8 && m < i && n > j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AB") {
                                    if (grid[m + 1][n - 1].text == "\u26AA") {
                                        grid[m + 1][n - 1].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                    m++
                                    n--
                                } else {
                                    n++
                                    m--
                                }
                            }

                            //loop for right diagonal upwards in black
                            m = i + 1
                            n = j - 1
                            while (m < 8 && n >= 0 && m > i && n < j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AB") {
                                    if (grid[m - 1][n + 1].text == "\u26AA") {
                                        grid[m - 1][n + 1].text = "\u26AB"
                                        black++
                                        white--
                                    }
                                    m--
                                    n++
                                } else {
                                    n--
                                    m++
                                }
                            }
                        }



                        //FOR WHITE BUTTON

                        // && (grid[i - 1][j - 1].text == "\u26AB" || grid[i - 1][j].text == "\u26AB" || grid[i - 1][j + 1].text == "\u26AB" || grid[i][j - 1].text == "\u26AB" || grid[i][j + 1].text == "\u26AB" || grid[i + 1][j - 1].text == "\u26AB" || grid[i + 1][j].text == "\u26AB" || grid[i + 1][j + 1].text == "\u26AB")
                        else if (chance == 1 && validMove(1, i, j)) {
                            turnText.text = "\u26AB" + "            "
                            grid[i][j].text = "\u26AA"
                            white++
                            chance = 0
                            // loop for vertical down for white
                            for (m in 0 until i - 1) {
                                if (grid[m][j].text == "\u26AA") {
                                    if (grid[m + 1][j].text == "\u26AB") {
                                        grid[m + 1][j].text = "\u26AA"
                                        white++
                                        black--
                                    }
                                }
                            }

                            // loop for vertical up for white
                            for (n in 7 downTo i + 1) {
                                if (grid[n][j].text == "\u26AA") {
                                    if (grid[n - 1][j].text == "\u26AB") {
                                        grid[n - 1][j].text = "\u26AA"
                                        black--
                                        white++
                                    }
                                }
                            }

                            //loop for horizontal right for white
                            for (m in 0 until j - 1) {
                                if (grid[i][m].text == "\u26AA") {
                                    if (grid[i][m + 1].text == "\u26AB") {
                                        grid[i][m + 1].text = "\u26AA"
                                        white++
                                        black--
                                    }
                                }
                            }

                            //loop for horizontal left for white
                            for (n in 7 downTo j + 1) {
                                if (grid[i][n].text == "\u26AA") {
                                    if (grid[i][n - 1].text == "\u26AB") {
                                        grid[i][n - 1].text = "\u26AA"
                                        black--
                                        white++
                                    }
                                }
                            }

                            //loop for left diagonal downwards in white
                            var n = j + 1
                            var m = i + 1
                            while (m < 8 && n < 8 && m > i && n > j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AA") {
                                    if (grid[m - 1][n - 1].text == "\u26AB") {
                                        grid[m - 1][n - 1].text = "\u26AA"
                                        black--
                                        white++
                                    }
                                    m--
                                    n--
                                } else {
                                    n++
                                    m++
                                }
                            }

                            //loop for left diagonal  upwards in white
                            m = i - 1
                            n = j - 1
                            while (m >= 0 && n >= 0 && m < i && n < j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AA") {
                                    if (grid[m + 1][n + 1].text == "\u26AB") {
                                        grid[m + 1][n + 1].text = "\u26AA"
                                        black--
                                        white++
                                    }
                                    m++
                                    n++
                                } else {
                                    n--
                                    m--
                                }
                            }

                            //loop for right diagonal downwards in white
                            n = j + 1
                            m = i - 1
                            while (m >= 0 && n < 8 && m < i && n > j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AA") {
                                    if (grid[m + 1][n - 1].text == "\u26AB") {
                                        grid[m + 1][n - 1].text = "\u26AA"
                                        black--
                                        white++
                                    }
                                    m++
                                    n--
                                } else {
                                    n++
                                    m--
                                }
                            }

                            //loop for right diagonal upwards in white
                            m = i + 1
                            n = j - 1
                            while (m < 8 && n >= 0 && m > i && n < j && grid[m][n].text != "") {
                                if (grid[m][n].text == "\u26AA") {
                                    if (grid[m - 1][n + 1].text == "\u26AB") {
                                        grid[m - 1][n + 1].text = "\u26AA"
                                        black--
                                        white++
                                    }
                                    m--
                                    n++
                                } else {
                                    n--
                                    m++
                                }
                            }

                        } else
                            Toast.makeText(this,"Invalid Move",Toast.LENGTH_SHORT).show()
                    }

                    t2black.text = black.toString()
                    t2white.text = white.toString() + "    "

                    if (black + white == 64 || black == 0 || white == 0) {
                        when {
                            black > white -> Toast.makeText(this, "Black won the match !!!", Toast.LENGTH_LONG).show()
                            black < white -> Toast.makeText(this, "White won the match !!!", Toast.LENGTH_LONG).show()
                            black == white -> Toast.makeText(this, "Match Tied !!!", Toast.LENGTH_LONG).show()
                        }
                    }
                }


            }
        }

        newGameBtn.setOnClickListener {
            chance = 0
            black = 2
            white = 2
            turnText.text = "\u26AB" + "            "
            t2black.text = "2"
            t2white.text = "2    "

            for (i in 0..7) {
                for (j in 0..7) {
                    grid[i][j].text = ""
                    grid[3][3].text = "\u26AA"
                    grid[3][4].text = "\u26AB"
                    grid[4][4].text = "\u26AA"
                    grid[4][3].text = "\u26AB"
                }
            }
        }

    }



        private fun validMove(turn: Int, row: Int, col: Int): Boolean {
            // ADD CODE HERE
            var result = false
            val oppCol = when (turn) {
                0 -> "\u26AA"
                1 -> "\u26AB"
                else -> ""
            }
            //current
            if (grid[row][col].text == "") {
                var m = row
                var n = col

                if (m + 1 < 8 && n + 1 < 8 && grid[m + 1][n + 1].text == oppCol) {
                    n = col + 1
                    m = row + 1
                    while (m < 8 && n < 8 && m > row && n > col && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AA") {
                            if (grid[m + 1][n + 1].text == "\u26AB") {
                                result = true
                            }
                            m--
                            n--
                        } else if (grid[m][n].text == "\u26AB") {
                            if (grid[m + 1][n + 1].text == "\u26AA") {
                                result = true
                            }
                            m--
                            n--
                        } else {
                            result = false
                            n++
                            m++
                        }
                    }
                }

                m = row
                n = col
                if (m - 1 > -1 && n - 1 > -1 && grid[m - 1][n - 1].text == oppCol) {
                    n = col - 1
                    m = row - 1
                    while (m >= 0 && n >= 0 && m < row && n < col && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m - 1][n - 1].text == "\u26AA") {
                                result = true
                            }
                            m++
                            n++
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m - 1][n - 1].text == "\u26AB") {
                                result = true
                            }
                            m++
                            n++
                        } else {
                            result = false
                            n--
                            m--
                        }
                    }
                }

                m = row
                n = col
                if (m - 1 > -1 && n + 1 < 8 && grid[m - 1][n + 1].text == oppCol) {
                    n = col + 1
                    m = row - 1
                    while (m >= 0 && n < 8 && m < row && n > col && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m - 1][n + 1].text == "\u26AA") {
                                result = true
                            }
                            m++
                            n--
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m - 1][n + 1].text == "\u26AB") {
                                result = true
                            }
                            m++
                            n--
                        } else {
                            result = false
                            n++
                            m--
                        }
                    }
                }

                m = row
                n = col
                if (m + 1 < 8 && n - 1 > -1 && grid[m + 1][n - 1].text == oppCol) {
                    m = row + 1
                    n = col - 1
                    while (m < 8 && n >= 0 && m > row && n < col && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m + 1][n - 1].text == "\u26AA") {
                                result = true
                            }
                            m--
                            n++
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m + 1][n - 1].text == "\u26AB") {
                                result = true
                            }
                            m--
                            n++
                        } else {
                            result = false
                            n--
                            m++
                        }
                    }
                }

                m = row
                n = col
                if (m - 1 > -1 && grid[m - 1][n].text == oppCol) {
                    m = row - 1
                    n = col
                    while (m in 0..(row - 1) && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m - 1][n].text == "\u26AA") {
                                result = true
                            }
                            m++
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m - 1][n].text == "\u26AB") {
                                result = true
                            }
                            m++
                        } else {
                            result = false
                            m--
                        }
                    }
                }

                m = row
                n = col
                if (m + 1 < 8 && grid[m + 1][n].text == oppCol) {
                    m = row + 1
                    n = col
                    while(m in (row + 1)..7 && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m + 1][n].text == "\u26AA") {
                                result = true
                            }
                            m--
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m + 1][n].text == "\u26AB") {
                                result = true

                            }
                            m--
                        } else {
                            m++
                            result = false
                        }
                    }
                }

                m = row
                n = col
                if (n + 1 < 8 && grid[m][n + 1].text == oppCol) {
                    m = row
                    n = col + 1
                    while(n in (col + 1)..7 && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m][n + 1].text == "\u26AA") {
                                result = true

                            }
                            n--
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m][n + 1].text == "\u26AB") {
                                result = true
                            }
                            n--
                        } else {
                            n++
                            result = false
                        }
                    }
                }

                m = row
                n = col
                if (n - 1 > -1 && grid[m][n - 1].text == oppCol) {
                    m = row
                    n = col - 1
                    while (n in 0..(col - 1) && grid[m][n].text != "") {
                        if (grid[m][n].text == "\u26AB") {
                            if (grid[m][n - 1].text == "\u26AA") {
                                result = true
                            }
                            n++
                        } else if (grid[m][n].text == "\u26AA") {
                            if (grid[m][n - 1].text == "\u26AB") {
                                result = true
                            }
                            n++
                        } else {
                            n--
                            result = false
                        }
                    }
                }

            }
          return result
        }


}
