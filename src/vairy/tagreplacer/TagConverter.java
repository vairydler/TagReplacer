package vairy.tagreplacer;



public interface TagConverter {
	/**
	 * @return タグのヘッダー文字列を返す。
	 */
	public String getFirstMark();
	/**
	 * @return タグのフッター文字列を返す。
	 */
	public String getLastMark();
	/**
	 * 検査対象の文字がタグならば対応する文字列を返す。
	 * @param tag 検査対象文字
	 * @return 文字列：タグとして認識された。
	 * 			null：タグとして認識されなかった。
	 */
	public String getReplaceString(final String tag);
}
