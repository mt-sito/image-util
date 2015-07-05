package com.github.mt_sito.image_util.trim

import java.awt.image.BufferedImage
import java.io.{File, FileOutputStream}
import java.util.Locale
import javax.imageio.{IIOImage, ImageIO, ImageWriteParam, ImageWriter}
import javax.imageio.plugins.jpeg.JPEGImageWriteParam
import org.scalatest.{FlatSpec, Matchers}


/**
 * Trim オブジェクトテストスペック。
 */
class TrimSpec extends FlatSpec with Matchers {
	"execute" should "image が null なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			Trim.execute(null, 100, 100, HTrimAlign.LEFT, VTrimAlign.TOP)
		}
	}

	it should "width が -1 未満なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			Trim.execute(null, -2, 100, HTrimAlign.LEFT, VTrimAlign.TOP)
		}
	}

	it should "height が -1 未満なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			Trim.execute(null, 100, -3, HTrimAlign.LEFT, VTrimAlign.TOP)
		}
	}

	it should "width, height が元画像と同じならソース画像が返る" in {
		val expect = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)
		Trim.execute(expect, 100, 100, HTrimAlign.LEFT, VTrimAlign.TOP) should be theSameInstanceAs expect
	}

	it should "HTrimAlign.LEFT VTrimAlign.MIDLE なら左中段からトリムされる" in {
		val img = Trim.execute(ImageIO.read(new File("src/test/data/img01.jpg")), 120, 90, HTrimAlign.LEFT,
			VTrimAlign.MIDDLE)
		img.getWidth should be (120)
		img.getHeight should be (90)

		val out = new FileOutputStream(new File("target/img01_trim_left_middle.jpg"))
		val imageStream = ImageIO.createImageOutputStream(out)

		try {
			val writer = ImageIO.getImageWritersByFormatName("jpg").next()
			writer.setOutput(imageStream)
			val param = new JPEGImageWriteParam(Locale.getDefault())
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT)
			param.setCompressionQuality(0.85f)
			param.setOptimizeHuffmanTables(true)
			writer.write(null, new IIOImage(img, null, null), param)
			writer.dispose()
		} finally {
			out.close()
		}
	}

	it should "HTrimAlign.CENTER VTrimAlign.BOTTOM なら真ん中下段からトリムされる" in {
		val img = Trim.execute(ImageIO.read(new File("src/test/data/img01.jpg")), 120, 90, HTrimAlign.CENTER,
			VTrimAlign.BOTTOM)
		img.getWidth should be (120)
		img.getHeight should be (90)

		val out = new FileOutputStream(new File("target/img01_trim_center_bottom.jpg"))
		val imageStream = ImageIO.createImageOutputStream(out)

		try {
			val writer = ImageIO.getImageWritersByFormatName("jpg").next()
			writer.setOutput(imageStream)
			val param = new JPEGImageWriteParam(Locale.getDefault())
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT)
			param.setCompressionQuality(0.85f)
			param.setOptimizeHuffmanTables(true)
			writer.write(null, new IIOImage(img, null, null), param)
			writer.dispose()
		} finally {
			out.close()
		}
	}

	it should "HTrimAlign.RIGHT VTrimAlign.TOP なら右上段からトリムされる" in {
		val img = Trim.execute(ImageIO.read(new File("src/test/data/img01.jpg")), 120, 90, HTrimAlign.RIGHT,
			VTrimAlign.TOP)
		img.getWidth should be (120)
		img.getHeight should be (90)

		val out = new FileOutputStream(new File("target/img01_trim_right_top.jpg"))
		val imageStream = ImageIO.createImageOutputStream(out)

		try {
			val writer = ImageIO.getImageWritersByFormatName("jpg").next()
			writer.setOutput(imageStream)
			val param = new JPEGImageWriteParam(Locale.getDefault())
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT)
			param.setCompressionQuality(0.85f)
			param.setOptimizeHuffmanTables(true)
			writer.write(null, new IIOImage(img, null, null), param)
			writer.dispose()
		} finally {
			out.close()
		}
	}
}
