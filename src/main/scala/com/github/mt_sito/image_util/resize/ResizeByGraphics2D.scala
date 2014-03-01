package com.github.mt_sito.image_util.resize

import java.awt.RenderingHints
import java.awt.image.BufferedImage


/**
 * Graphics2D クラスを使用した画像拡大縮小クラス。
 */
protected abstract class ResizeByGraphics2D extends ImageResize {
	/** アルゴリズム */
	protected val algorithm: Object


	/**
	 * 実行。
	 *
	 * @param image 画像データ
	 * @param scale 拡大縮小率
	 * @return 画像データ
	 */
	def execute(image: BufferedImage, scale: Double): BufferedImage = {
		require(image != null)
		require(scale >= 0.0)
		if (scale == 1.0) image
		else execute(image, scale, algorithm)
	}


	/**
	 * 実行。
	 *
	 * @param image 画像データ
	 * @param scale 拡大縮小率
	 * @return 画像データ
	 */
	protected def execute(image: BufferedImage, scale: Double, resizeAlgorithm: Object): BufferedImage = {
		val (dw, dh) = ((image.getWidth * scale).toInt, (image.getHeight * scale).toInt)
		val destImage = new BufferedImage(dw, dh, image.getType())
		val g2d = destImage.createGraphics()
		try {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, resizeAlgorithm)
			g2d.drawImage(image, 0, 0, dw, dh, null)
		} finally {
			g2d.dispose()
		}
		destImage
	}
}
