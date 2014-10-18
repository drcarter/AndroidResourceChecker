package com.egloos.realmove.rscchk;

import java.io.*;
import java.util.*;

public class ReferenceResult {

	File drawable = null;
	File code = null;
	AbstractList<ReferenceResultRecord> results = null;

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append( drawable.getName() );
		sb.append( " in " );
		sb.append( code.getAbsolutePath() );
		sb.append( "(" );
		if ( results != null ) sb.append( results );
		sb.append( ")" );

		return sb.toString();
	}

	public File getDrawable() {
		return drawable;
	}

	public void setDrawable( File drawable ) {
		this.drawable = drawable;
	}

	public File getCode() {
		return code;
	}

	public void setCode( File code ) {
		this.code = code;
	}

	public AbstractList<ReferenceResultRecord> getResults() {
		return results;
	}

	public void setResults( AbstractList<ReferenceResultRecord> results ) {
		this.results = results;
	}

}
