package vairy.tagreplacer;


public class TagReplacer {
	private TagConverter converter;
	private final String format;
	public TagReplacer(final String format){
		this(format, null);
	}
	public TagReplacer(final String format, final TagConverter converter){
		this.format = format;
		this.converter = converter;
	}

	public final void setConverter(final TagConverter converter){
		this.converter = converter;
	}
	public final String doReplace(){
		if (null == converter) {
			return null;
		}

		StringBuilder rtn = new StringBuilder(format);
		String firstmark = converter.getFirstMark();
		String lastmark = converter.getLastMark();
		Integer firstlen = firstmark.length();
		Integer lastlen = lastmark.length();

		Integer readpoint = -1;
		Integer firstindex = -1;
		Integer lastindex = -1;
//		タグがみつからなくなるまで読み込み
//		開始マークと終了マークがそれぞれ、%%%%、%%%%みたいなタグだった時の事を考慮
//		開始インデックスの捜索開始ポイントは、前回の読み込み位置＋1だが、
//		終了インデックスの捜索開始ポイントは、開始インデックス＋開始マーク文字数。
//		それぞれ全然別のタグだった場合のことも考慮
		while((firstindex = rtn.indexOf(firstmark,readpoint + 1)) != -1){
			readpoint = firstindex;
			if((lastindex = rtn.indexOf(lastmark,firstindex + firstlen)) != -1){
				String substring = rtn.substring(firstindex, lastindex + lastlen);

				String replaceStr = converter.getReplaceString(substring);
				if(null != replaceStr){
					rtn.replace(firstindex, lastindex + lastlen, replaceStr);
					readpoint += (replaceStr.length() - 1);
				}
			}
		}

		return rtn.toString();
	}
}
