package top.thttnt.serviceoutsourcing.user.utils

import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import kotlin.random.Random

class CodeImage {

    val code: String
    val image: BufferedImage

    init {
        //生成验证码
        var temp = ""
        repeat(4) {
            temp += generateRandomChar()
        }
        code = temp

        val img = BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB)
        val graphics = img.graphics
        graphics.color = Color.WHITE
        graphics.fillRect(0, 0, 100, 50)
        repeat(4) {
            graphics.color = getRandomColor()
            graphics.font = Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 19)
            graphics.drawString(code[it] + "", 10 + (it * 20), 20)
        }
        repeat(8) {
            val x1: Int = Random.nextInt(100)
            val y1: Int = Random.nextInt(50)
            val x2: Int = Random.nextInt(100)
            val y2: Int = Random.nextInt(50)
            graphics.color = getRandomColor()
            graphics.drawLine(x1, y1, x2, y2)
        }
        image = img
    }

    private fun getRandomColor(): Color? {
        val red: Int = Random.nextInt(256)
        val green: Int = Random.nextInt(256)
        val blue: Int = Random.nextInt(256)
        return Color(red, green, blue)
    }

    private fun generateRandomChar(): Char {
        return Random.nextInt(65, 90).toChar()
    }
}