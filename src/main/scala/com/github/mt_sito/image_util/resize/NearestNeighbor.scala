package com.github.mt_sito.image_util.resize

import java.awt.RenderingHints


/**
 * NearestNeighbor 拡大縮小クラス。
 */
class NearestNeighbor extends ResizeByGraphics2D {
	/** アルゴリズム */
	protected val algorithm: Object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
}
