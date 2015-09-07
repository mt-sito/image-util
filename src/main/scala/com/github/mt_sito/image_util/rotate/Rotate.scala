package com.github.mt_sito.image_util.rotate

import java.awt.image.BufferedImage


/**
 * ローテーとオブジェクト。
 */
object Rotate {
	/**
	 * 90 度右回転。
	 *
	 * @param image 画像データ
	 * @return 回転画像データ
	 */
	def right(image: BufferedImage): BufferedImage = {
		require(image != null)
		execute(image, Math.PI * 0.5, image.getHeight, image.getWidth)
	}

	/**
	 * 180 度回転。
	 *
	 * @param image 画像データ
	 * @return 回転画像データ
	 */
	def upsideDown(image: BufferedImage): BufferedImage = {
		require(image != null)
		execute(image, Math.PI, image.getWidth, image.getHeight)
	}

	/**
	 * 90 度左回転。
	 *
	 * @param image 画像データ
	 * @return 回転画像データ
	 */
	def left(image: BufferedImage): BufferedImage = {
		require(image != null)
		execute(image, Math.PI * -0.5, image.getHeight, image.getWidth)
	}

	/**
	 * 実行。
	 *
	 * @param image 画像データ
	 * @param theta -回転の角度(ラジアン)
	 * @param width 回転後の幅
	 * @param height 回転後の高さ
	 * @return 回転画像データ
	 */
	def execute(image: BufferedImage, theta: Double, width: Int, height: Int): BufferedImage = {
		require(image != null)
		require(width >= -1)
		require(height >= -1)

		val resImage = new BufferedImage(width, height, image.getType)
		val g2d = resImage.createGraphics()

		g2d.rotate(theta, image.getWidth * 0.5, image.getHeight * 0.5)

		val offset = if (theta < 0.0) -1 else 1

		g2d.drawImage(image, ((image.getWidth - width) * 0.5).toInt * offset,
			((image.getHeight - height) * 0.5).toInt * offset * -1, image.getWidth, image.getHeight, null)

		resImage
	}
}
