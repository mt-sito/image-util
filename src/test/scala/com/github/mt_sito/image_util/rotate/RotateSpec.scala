package com.github.mt_sito.image_util.rotate

import java.awt.image.BufferedImage
import java.io.{File, FileOutputStream}
import java.util.Locale
import javax.imageio.{IIOImage, ImageIO, ImageWriteParam, ImageWriter}
import javax.imageio.plugins.jpeg.JPEGImageWriteParam
import org.scalatest.{FlatSpec, Matchers}


/**
 * Rotate オブジェクトテストスペック。
 */
class RotateSpec extends FlatSpec with Matchers {
	"right" should "image が null なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			Rotate.right(null)
		}
	}

	it should "正しい横長画像ならば右に回転される" in {
		val img = Rotate.right(ImageIO.read(new File("src/test/data/img01.jpg")))
		img.getWidth should be (1200)
		img.getHeight should be (1600)

		val out = new FileOutputStream(new File("target/img01_rotate_right.jpg"))
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

	it should "正しい縦長画像ならば右に回転される" in {
		val img = Rotate.right(ImageIO.read(new File("src/test/data/img02.jpg")))
		img.getWidth should be (1800)
		img.getHeight should be (1200)

		val out = new FileOutputStream(new File("target/img02_rotate_right.jpg"))
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

	"upsideDown" should "image が null なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			Rotate.upsideDown(null)
		}
	}

	it should "正しい横長画像ならば 180 度回転される" in {
		val img = Rotate.upsideDown(ImageIO.read(new File("src/test/data/img01.jpg")))
		img.getWidth should be (1600)
		img.getHeight should be (1200)

		val out = new FileOutputStream(new File("target/img01_rotate_upsideDown.jpg"))
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

	it should "正しい縦長画像ならば 180 度回転される" in {
		val img = Rotate.upsideDown(ImageIO.read(new File("src/test/data/img02.jpg")))
		img.getWidth should be (1200)
		img.getHeight should be (1800)

		val out = new FileOutputStream(new File("target/img02_rotate_upsideDown.jpg"))
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

	"left" should "image が null なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			Rotate.left(null)
		}
	}

	it should "正しい横長画像ならば左に回転される" in {
		val img = Rotate.left(ImageIO.read(new File("src/test/data/img01.jpg")))
		img.getWidth should be (1200)
		img.getHeight should be (1600)

		val out = new FileOutputStream(new File("target/img01_rotate_left.jpg"))
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

	it should "正しい縦長画像ならば左に回転される" in {
		val img = Rotate.left(ImageIO.read(new File("src/test/data/img02.jpg")))
		img.getWidth should be (1800)
		img.getHeight should be (1200)

		val out = new FileOutputStream(new File("target/img02_rotate_left.jpg"))
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
