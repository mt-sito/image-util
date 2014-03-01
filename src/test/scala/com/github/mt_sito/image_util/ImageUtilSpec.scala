package com.github.mt_sito.image_util

import org.scalatest.{FlatSpec, Matchers}


/**
 * ImageUtil クラステストスペック。
 */
class ImageUtilSpec extends FlatSpec with Matchers {
	"mirrorRGB" should "範囲内を指定した場合指定した値を返す" in {
		val actual = ImageUtil.mirrorRGB(Array(
			1, 2, 3,
			4, 5, 6,
			7, 8, 9,
			10, 11, 12
		), 1, 2, 3, 4)

		actual should be (8)
	}

	it should "範囲外を指定した場合鏡像の位置の値を返す" in {
		val actual = ImageUtil.mirrorRGB(Array(
			1, 2, 3,
			4, 5, 6,
			7, 8, 9,
			10, 11, 12
		), -1, -2, 3, 4)

		actual should be (8)
	}

	"weightColor" should "重みの合計を指定した場合、重みで割った値を返す" in {
		ImageUtil.weightColor(100, 3) should be (33)
	}

	it should "重みの合計に 0.0 をした場合、指定した値を返す" in {
		ImageUtil.weightColor(100, 0.0) should be (100)
	}

	it should "値が負の場合 0 を返す" in {
		ImageUtil.weightColor(-100, 0.0) should be (0)
	}

	it should "値が 255 より大きい場合の場合 255 を返す" in {
		ImageUtil.weightColor(300, 0.0) should be (255)
	}
}
