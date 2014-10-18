package com.egloos.realmove.rscchk;

import java.io.File;

public class XmlReferenceFinder extends ReferenceFinder {

	private String needle = null;

	public XmlReferenceFinder( File drawable ) {
		super( drawable );
		String filename = drawable.getName();
		if ( filename.endsWith( ".9.png") ) this.needle = "@drawable/" + filename.substring( 0, filename.length() - 6 ); 
		else this.needle = "@drawable/" + filename.substring( 0, filename.length() - 4 );
	}

	@Override
	public int match( String line ) {
		return line.indexOf( needle );
	}

}
