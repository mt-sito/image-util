package com.github.mt_sito.image_util.resize

import java.awt.image.BufferedImage


/**
 * 画像拡大縮小トレイト。
 */
trait ImageResize {
	/**
	 * 実行。
	 *
	 * @param image 画像データ
	 * @param scale 拡大縮小率
	 * @return 画像データ
	 */
	def execute(image: BufferedImage, scale: Double): BufferedImage
}
