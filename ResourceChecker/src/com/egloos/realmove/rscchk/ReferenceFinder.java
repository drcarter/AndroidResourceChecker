package com.egloos.realmove.rscchk;

import java.io.*;
import java.util.*;

public abstract class ReferenceFinder {

	File drawable = null;
	
	public ReferenceFinder( File drawable ) {
		this.drawable = drawable;
	}
	
	public ReferenceResult findIn( File file ) throws Exception {
		BufferedReader br = null;
		ReferenceResult result = null;

		try {
			br = new BufferedReader( new FileReader( file ) );
			for ( int lineCnt = 0; ; lineCnt++ ) {
				String line = br.readLine();
				if ( line == null ) break;
				int idx = this.match( line );
				if ( idx > -1 ) {
					if ( result == null ) {
						result = new ReferenceResult();
						result.setCode( file );
						result.setDrawable( drawable );
						result.setResults( new ArrayList<ReferenceResultRecord>() );
					}
					result.getResults().add( new ReferenceResultRecord( lineCnt, idx, line ) );
				}
			}
			
		} catch( Exception ex ) {
			throw ex;
		} finally {
			if ( br != null ) {
				try {
					br.close();
				} catch( Exception ex ) {
					//do nothing
				}
			}
		}
		return result;
	}

	public abstract int match( String line );

}
