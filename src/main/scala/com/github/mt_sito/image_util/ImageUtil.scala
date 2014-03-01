package com.github.mt_sito.image_util

import scala.math.abs


/**
 * 画像ユーティリティオブジェクト。
 */
object ImageUtil {
	/**
	 * 画像データより指定された座標の RGB 取得。<br />
	 * 範囲外は鏡像。
	 *
	 * @param image 画像データ
	 * @param x X 座標
	 * @param y Y 座標
	 * @param w 幅
	 * @param h 高さ
	 * @return RGB 値
	 */
	def mirrorRGB(image: Array[Int], x: Int, y: Int, w: Int, h: Int): Int = {
		var targetX = abs(x)
		var targetY = abs(y)
		if (targetX >= w) targetX = w + w - targetX - 1
		if (targetY >= h) targetY = h + h - targetY - 1
		image(targetY * w + targetX)
	}

	/**
	 * 重みを計算して 0 以上 255 以下の色の値取得。
	 *
	 * @param color 色の値
	 * @param totalWeight 重みの合計
	 * @return 色の値
	 */
	def weightColor(color: Double, totalWeight: Double): Int = {
		var result = color
		if (totalWeight != 0.0) result /= totalWeight;
		if (result < 0.0) return 0
		if (result > 255.0) return 255
		return result.toInt
	}
}
