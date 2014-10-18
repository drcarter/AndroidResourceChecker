package com.egloos.realmove.rscchk;

import java.io.File;
import java.io.FileFilter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

public class AppMain {

	public static ReferenceFinder getClass( File file, File drawable ) {
		if ( file.getName().endsWith( ".xml" ) ) {
			return new XmlReferenceFinder( drawable );
		} else if ( file.getName().endsWith( ".java" ) ) { return new JavaReferenceFinder( drawable ); }
		return null;
	}

	public static AbstractList<ReferenceResult> getReferences( File root, File drawable ) throws Exception {
		AbstractList<ReferenceResult> list = new ArrayList<ReferenceResult>();

		File[] children = root.listFiles( new FileFilter() {
			@Override
			public boolean accept( File pathname ) {
				if ( pathname.getName().startsWith( "." ) ) return false;
				return true;
			}
		} );

		for ( int i = 0; i < children.length; i++ ) {
			File file = children[i];
			if ( file.isDirectory() ) {
				AbstractList<ReferenceResult> results = getReferences( file, drawable );
				if ( results != null ) list.addAll( results );
			} else {
				ReferenceFinder finder = getClass( file, drawable );
				if ( finder != null ) {
					ReferenceResult result = finder.findIn( file );
					if ( result != null ) list.add( result );
				}
			}
		}

//		System.out.println( "getReferences():\t(" + list.size() + ")\t" + root );

		return list;
	}

	public static void main( String args[] ) throws Exception {
		File rootPath = new File( "project path" );

		File drawablePath = new File( "image resource path" );

		File[] drawables = drawablePath.listFiles( new FileFilter() {
			@Override
			public boolean accept( File pathname ) {
				return pathname.getName().endsWith( ".png" );
			}
		} );

		int cnt = 0;
		int totalSize = 0;
		for ( int i = 0; i < drawables.length; i++ ) {
			File drawable = drawables[i];
			AbstractList<ReferenceResult> result = getReferences( rootPath, drawable );
//			if ( result != null ) {
//				Iterator<ReferenceResult> it = result.iterator();
//				while ( it.hasNext() ) {
//					System.out.println( it.next() );
//				}
//			}
			
			if ( result == null || result.size() == 0 ) {
				cnt++;
				System.out.println( drawable.getName() + "( " + (drawable.length() / 1024 ) + " kb )" + " is not used." );
				totalSize += (drawable.length() / 1024 );
				
				//////////////////////////////////////////
				// 찾은 것 바로 지우게 하고 싶으면, 요것 주석제거하면 된다.
				//////////////////////////////////////////
				// drawable.delete();
			}
		}

		System.out.println("Un-used resource count : " + cnt);
		System.out.println("Un-used resource total size : " + totalSize);
	}

}
