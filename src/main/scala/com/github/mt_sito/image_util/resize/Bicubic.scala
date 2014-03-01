package com.github.mt_sito.image_util.resize

import java.awt.RenderingHints


/**
 * Bicubic 拡大縮小クラス。
 */
class Bicubic extends ResizeByGraphics2D {
	/** アルゴリズム */
	protected val algorithm: Object = RenderingHints.VALUE_INTERPOLATION_BICUBIC
}
