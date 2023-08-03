package com.Luke.Board;
//롬복 : 프로그램 서버 구동하면 그 즉시 얘가 겟셋을 만들어줍니다.
public class PageDTO {
	
	int firstRecordIndex, recordCountPerPage;

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
}
