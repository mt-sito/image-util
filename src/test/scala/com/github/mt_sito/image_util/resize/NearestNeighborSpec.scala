package com.github.mt_sito.image_util.resize

import java.awt.image.BufferedImage
import java.io.{File, FileOutputStream}
import java.util.Locale
import javax.imageio.{IIOImage, ImageIO, ImageWriteParam, ImageWriter}
import javax.imageio.plugins.jpeg.JPEGImageWriteParam
import org.scalatest.{FlatSpec, Matchers}


/**
 * NearestNeighbor クラステストスペック。
 */
class NearestNeighborSpec extends FlatSpec with Matchers {
	"execute" should "image が null なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			new NearestNeighbor().execute(null, 0.5)
		}
	}

	it should "scale が負なら例外を投げる" in {
		a [IllegalArgumentException] should be thrownBy {
			new NearestNeighbor().execute(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), -0.5)
		}
	}

	it should "scale が 1.0 ならソース画像が返る" in {
		val expect = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)
		new NearestNeighbor().execute(expect, 1.00) should be theSameInstanceAs expect
	}

	it should "画像を指定して scale が 0.5 なら 50 % に縮小される" in {
		val start = System.nanoTime()
		val img = new NearestNeighbor().execute(ImageIO.read(new File("src/test/data/img01.jpg")), 0.5)
		println("NearestNeighbor reduce: " + (System.nanoTime - start) / 1000000.0 + "ms")
		img.getWidth should be (800)
		img.getHeight should be (600)

		val out = new FileOutputStream(new File("target/img01_nearest_neighbor_50.jpg"))
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

	it should "画像を指定して scale が 1.5 なら 150 % に拡大される" in {
		val start = System.nanoTime()
		val img = new NearestNeighbor().execute(ImageIO.read(new File("src/test/data/img01.jpg")), 1.5)
		println("NearestNeighbor enlarge: " + (System.nanoTime - start) / 1000000.0 + "ms")
		img.getWidth should be (2400)
		img.getHeight should be (1800)

		val out = new FileOutputStream(new File("target/img01_nearest_neighbor_150.jpg"))
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
