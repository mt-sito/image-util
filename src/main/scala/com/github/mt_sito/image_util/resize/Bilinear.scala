package com.github.mt_sito.image_util.resize

import java.awt.RenderingHints


/**
 * Bilinear 拡大縮小クラス。
 */
class Bilinear extends ResizeByGraphics2D {
	/** アルゴリズム */
	protected val algorithm: Object = RenderingHints.VALUE_INTERPOLATION_BILINEAR
}
