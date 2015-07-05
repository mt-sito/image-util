package com.github.mt_sito.image_util.trim

import com.github.mt_sito.image_util.trim.HTrimAlign.HTrimAlign
import com.github.mt_sito.image_util.trim.VTrimAlign.VTrimAlign
import java.awt.image.BufferedImage


/**
 * トリムオブジェクト。
 */
object Trim {
	/**
	 * 実行。
	 *
	 * @param image 画像データ
	 * @param width 幅
	 * @param height 高さ
	 * @param hAlign 水平方向調整
	 * @param vAlign 垂直方法調整
	 * @return 画像データ
	 */
	def execute(image: BufferedImage, width: Int, height: Int, hAlign: HTrimAlign, vAlign: VTrimAlign): BufferedImage = {
		require(image != null)
		require(width >= -1)
		require(height >= -1)

		val (sw, sh) = (image.getWidth, image.getHeight)
		val dw = if (width == -1) sw else width
		val dh = if (height == -1) sh else height
		if (dw == sw && dh == sh) return image

		val x = if (sw <= dw) 0
		else {
			hAlign match {
				case HTrimAlign.LEFT => 0
				case HTrimAlign.CENTER => (sw - dw) / 2
				case HTrimAlign.RIGHT => sw - dw
			}
		}

		val y = if (sh <= dh) 0
		else {
			vAlign match {
				case VTrimAlign.TOP => 0
				case VTrimAlign.MIDDLE => (sh - dh) / 2
				case VTrimAlign.BOTTOM => sh - dh
			}
		}

		image.getSubimage(x, y, if (x + dw > sw) sw - x else dw, if (y + dh > sh) sh - y else dh)
	}
}
