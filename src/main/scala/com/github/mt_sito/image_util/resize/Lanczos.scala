package com.github.mt_sito.image_util.resize

import com.github.mt_sito.image_util.ImageUtil
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import scala.math.{Pi, abs, sin, sqrt}


/**
 * Lanczos 拡大縮小クラス。
 */
class Lanczos(maxDistance: Int) extends ImageResize {
	/**
	 * 重みクラス。
	 *
	 * @param offsetX ソース画像の X オフセット
	 * @param offsetY ソース画像の Y オフセット
	 * @param value 重み
	 */
	private class Weight(val offsetX: Int, val offsetY: Int, val value: Double)

	/**
	 * 重みフィルタークラス。
	 */
	private class WeightFilter(val totalWeight: Double, val weights: Array[Weight])


	/** {@inheritDoc} */
	def execute(image: BufferedImage, scale: Double): BufferedImage = {
		require(image != null)
		require(scale >= 0.0)
		if (scale == 1.0) image
		else if (scale < 1.0) execute(image, scale, reduceFilter(scale, maxDistance))
		else execute(image, scale, enlargeFilter(scale, maxDistance))
	}


	/**
	 * リサイズ。
	 *
	 * @param image 画像データ
	 * @param scale 拡大縮小率
	 * @return 画像データ
	 */
	protected def execute(image: BufferedImage, scale: Double, filter: WeightFilter): BufferedImage = {
		val start = System.nanoTime()
		val (sw, sh) = (image.getWidth, image.getHeight)
		val (dw, dh) = ((sw * scale).toInt, (sh * scale).toInt)
		val destImage = new BufferedImage(dw, dh, image.getType())
		val totalWeight = filter.totalWeight
		val colors = image.getRGB(0, 0, sw, sh, null, 0, sw)
		println("Lanczos init: " + (System.nanoTime - start) / 1000000.0 + "ms")

		for (dy <- 0 until dh; dx <- 0 until dw) {
			var dr = 0.0
			var db = 0.0
			var dg = 0.0
			val bsx = (dx / scale).toInt
			val bsy = (dy / scale).toInt

			filter.weights.foreach { weight =>
				val w = weight.value
				val rgb = ImageUtil.mirrorRGB(colors, bsx + weight.offsetX, bsy + weight.offsetY, sw, sh)
				dr += ((rgb >> 16) & 0xFF) * w
				dg += ((rgb >> 8) & 0xFF) * w
				db += (rgb & 0xFF) * w
			}
			destImage.setRGB(dx, dy, (ImageUtil.weightColor(dr, totalWeight) << 16) |
				(ImageUtil.weightColor(dg, totalWeight) << 8) | ImageUtil.weightColor(db, totalWeight))
		}
		destImage
	}


	/**
	 * Lanczos 関数。
	 *
	 * @param x 距離
	 * @param n 範囲
	 * @return 重み
	 */
	private def lanczos(x: Double, n: Double): Double = {
		if (x == 0.0) return 1.0
		if (x >= n) return 0.0
		sin(Pi * x) / Pi / x * (sin(Pi * x / n) / Pi / x * n)
	}

	/**
	 * 縮小用フィルター取得。
	 *
	 * @param scale 縮小率
	 * @param distance 対象距離
	 */
	private def reduceFilter(scale: Double, distance: Int): WeightFilter = {
		val (basedx0, basedy0) = (0.5, 0.5)
		val (ss, se) = (((basedx0 - distance) / scale).toInt, ((basedx0 + distance) / scale).toInt)
		val size = se - ss + 1
		val weights = new ArrayBuffer[Weight](size * size)
		var totalWeight = 0.0

		for (sy <- ss until se; sx <- ss until se) {
			val xl = (sx + 0.5) * scale - basedx0
			val yl = (sy + 0.5) * scale - basedy0
			val w = lanczos(sqrt(xl * xl + yl * yl), distance)
			if (w != 0.0) {
				weights += new Weight(sx, sy, w)
				totalWeight += w
			}
		}
		new WeightFilter(totalWeight, weights.toArray)
	}

	/**
	 * 拡大用フィルター取得。
	 *
	 * @param scale 拡大率
	 * @param distance 対象距離
	 */
	private def enlargeFilter(scale: Double, distance: Int): WeightFilter = {
		val (basedx0, basedy0) = (0.5, 0.5)
		val (basesx0, basesy0) = (basedx0 / scale, basedy0 / scale)
		val (ss, se) = ((basesx0 - distance).toInt, (basesx0 + distance).toInt)
		val size = se - ss + 1
		val weights = new ArrayBuffer[Weight](size * size)
		var totalWeight = 0.0

		for (sy <- ss until se; sx <- ss until se) {
			val xl = sx + 0.5 - basesx0
			val yl = sy + 0.5 - basesy0
			val w = lanczos(sqrt(xl * xl + yl * yl), distance)
			if (w != 0.0) {
				weights += new Weight(sx, sy, w)
				totalWeight += w
			}
		}
		new WeightFilter(totalWeight, weights.toArray)
	}
}
