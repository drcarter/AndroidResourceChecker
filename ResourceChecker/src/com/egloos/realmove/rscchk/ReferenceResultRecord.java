package com.egloos.realmove.rscchk;

public class ReferenceResultRecord {

	private int line = -1;
	private int column = -1;
	private String lineStr = null;

	public ReferenceResultRecord( int line, int column, String lineStr ) {
		this.line = line;
		this.column = column;
		this.lineStr = lineStr;
	}

	public int getLine() {
		return line;
	}

	public void setLine( int line ) {
		this.line = line;
	}

	public String getLineStr() {
		return lineStr;
	}

	public void setLineStr( String lineStr ) {
		this.lineStr = lineStr;
	}

	public String toString() {
		return "" + this.lineStr;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn( int column ) {
		this.column = column;
	}

}
