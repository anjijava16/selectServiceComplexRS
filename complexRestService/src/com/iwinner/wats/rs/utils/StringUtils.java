package com.iwinner.wats.rs.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.omg.CORBA.portable.ApplicationException;


/**
 * This utility class provides some useful methods for manuplate String object.
 * Some methods like split is in JDK 1.5.
 *
 *
 */
public final class StringUtils
{
	public static final String COMPOUND_KEY_DELIMITER = ",";
	
	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new StringUtils object.
	 */
	private StringUtils(  )
	{
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Adds num of chars at the end of a given String
	 *
	 * @param c
	 * @param num
	 * @param origVal
	 * @return
	 */
	public static String addCharsInEnd( char c, int num, String origVal )
	{
		StringBuffer sb = new StringBuffer( "" );

		for ( int i = 0; i < num; i++ )
		{
			sb.append( c );
		}

		return origVal + sb.toString(  );
	}


	/**
	 * Adds num of chars in front of a given string.
	 *
	 * @param c Character to prepend
	 * @param num Number of characters to prepend
	 * @param origVal Original String
	 * @return The original string with num number of character c prepended.
	 */
	public static String addCharsInFront( char c, int num, String origVal )
	{
		int bufsize = num;

		if ( null != origVal )
		{
			bufsize += origVal.length(  );
		}

		StringBuffer sb = new StringBuffer( bufsize );

		for ( int i = 0; i < num; i++ )
		{
			sb.append( c );
		}

		sb.append( origVal );

		return sb.toString(  );
	}


	/** Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. Useful for toString() implementations
	 * @param arr array to display. Elements may be of any type (toString() will be
	 * called on each element).
	 * @param delim delimiter to use (probably a ,)
	 * @return delimited string
	 */
	public static String arrayToDelimitedString( Object[] arr, String delim )
	{
		if ( null == arr )
		{
			return "null";
		}
		else
		{
			StringBuffer sb = new StringBuffer( 2 * arr.length );

			for ( int i = 0; i < arr.length; i++ )
			{
				if ( i > 0 )
				{
					sb.append( delim );
				}

				sb.append( arr [i] );
			}

			return sb.toString(  );
		}
	}


	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. Useful for toString() implementations
	 *
	 * @param c Collection to display
	 * @param delim delimiter to use (probably a ,)
	 */
	public static String collectionToDelimitedString( Collection c, String delim )
	{
		if ( null == c )
		{
			return "null";
		}

		return StringUtils.iteratorToDelimitedString( c.iterator(  ), delim );
	}


	/**
	 * Convenience method to convert a CSV string list to a set. Note that
	 * this will suppress duplicates.
	 * @param s CSV String
	 * @return a Set of String entries in the list
	 */
	public static Set commaDelimitedListToSet( String s )
	{
		Set set = new TreeSet(  );
		String[] tokens = StringUtils.commaDelimitedListToStringArray( s );

		for ( int i = 0; i < tokens.length; i++ )
		{
			set.add( tokens [i] );
		}

		return set;
	}


	/**
	 * Convert a CSV list into an array of Strings
	 * @param s CSV list
	 * @return an array of Strings. Returns the empty array if
	 * s is null.
	 */
	public static String[] commaDelimitedListToStringArray( String s )
	{
		return StringUtils.split( s, "," );
	}


	/**
	 * Count the occurrences of the substring in string s
	 * @param s string to search in. Returns 0 if this is null
	 * @param sub string to search for. Return 0 if this is null.
	 */
	public static int countOccurrencesOf( String s, String sub )
	{
		if ( ( null == s ) || ( null == sub ) || "".equals( sub ) )
		{
			return 0;
		}

		int count = 0;
		int pos = 0;
		int idx = 0;

		while ( ( idx = s.indexOf( sub, pos ) ) != -1 )
		{
			++count;
			pos = idx + sub.length(  );
		}

		return count;
	}


	/**
	 * This method will remove a substring for a string.
	 * @param inString The source string to remove pattern from
	 * @param pattern Pattern to delete all occurrences of from inString
	 * @return String will all instance of pattern removed
	 */
	public static String delete( String inString, String pattern )
	{
		return replace( inString, pattern, "" );
	}


	/**
	 * @param chars characters to delete e.g. az\n will delete as, zs and new lines
	 */
	public static String deleteAny( String inString, String chars )
	{
		if ( ( inString == null ) || ( chars == null ) )
		{
			return inString;
		}

		StringBuffer out = new StringBuffer(  );

		for ( int i = 0; i < inString.length(  ); i++ )
		{
			char c = inString.charAt( i );

			if ( chars.indexOf( c ) == -1 )
			{
				out.append( c );
			}
		}

		return out.toString(  );
	}


	/**
	 *Return if the requied sting exists in property values.
	 *@param inputString String
	 *@param propertyValues String
	 *@return boolean
	 *@author srikanth.veturi
	 */
	public static boolean isTokenAvailable( String inputString,
		String propertyValues )
	{
		boolean isTokenAvailable = false;
		StringTokenizer stringTokenizer =
			new StringTokenizer( propertyValues, "," );
		String token = "";
		while ( stringTokenizer.hasMoreTokens(  ) )
		{
			token = stringTokenizer.nextToken(  ).trim(  );

			if ( token.equalsIgnoreCase( inputString ) )
			{
				isTokenAvailable = true;
			}
		}

		return isTokenAvailable;
	}


	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. Useful for toString() implementations
	 *
	 * @param c Collection to display
	 * @param delim delimiter to use (probably a ,)
	 */
	public static String iteratorToDelimitedString( Iterator itr, String delim )
	{
		if ( itr == null )
		{
			return "null";
		}
		else
		{
			StringBuffer sb = new StringBuffer(  );
			int i = 0;

			while ( itr.hasNext(  ) )
			{
				if ( i++ > 0 )
				{
					sb.append( delim );
				}

				sb.append( itr.next(  ) );
			}

			return sb.toString(  );
		}
	}


	/**
	 * Replaces all occurences of a substring within a string with another string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace( String inString, String oldPattern,
		String newPattern )
	{
		// Pick up error conditions
		if ( inString == null )
		{
			return null;
		}

		if ( ( oldPattern == null ) || ( newPattern == null ) )
		{
			return inString;
		}

		StringBuffer sbuf = new StringBuffer(  );

		// Output StringBuffer we'll build up
		int pos = 0; // Our position in the old string
		int index = inString.indexOf( oldPattern );

		// The index of an occurrence we've found, or -1
		int patLen = oldPattern.length(  );

		while ( index >= 0 )
		{
			sbuf.append( inString.substring( pos, index ) );
			sbuf.append( newPattern );
			pos = index + patLen;
			index = inString.indexOf( oldPattern, pos );
		}

		sbuf.append( inString.substring( pos ) );

		// Remember to append any characters to the right of a match
		return sbuf.toString(  );
	}


	/** Take a String which is a delimited list and convert it to a String array.
	 * @param s String
	 * @param delimiter delimiter. This will not be returned
	 * @return an array of the tokens in the list
	 */
	public static String[] split( String s, String delimiter )
	{
		if ( s == null )
		{
			return new String[0];
		}

		if ( delimiter == null )
		{
			return new String[] { s };
		}

		List l = new LinkedList(  );
		int pos = 0;
		int delpos = 0;

		while ( ( delpos = s.indexOf( delimiter, pos ) ) != -1 )
		{
			l.add( s.substring( pos, delpos ) );
			pos = delpos + delimiter.length(  );
		}

		if ( pos <= s.length(  ) )
		{
			// Add rest of String
			l.add( s.substring( pos ) );
		}

		return ( String[] )l.toArray( new String[l.size(  )] );
	}
	
	/** Take a String which contains spaces. Remove the space within the strings
	 * @param s String
	 * @return string with no spaces
	 */
	public static String removeSpaces(String s) 
	{
	  StringTokenizer st = new StringTokenizer(s," ",false);
	  String t="";
	  while (st.hasMoreElements()) t += st.nextElement();
	  return t;
	}
	
	public static String removeWhiteSpace(String aString)
	{
		if (aString != null)
		{
			return aString.replace(" ", "");
		}
		
		return aString;
	}
	
	public static String parseISToString(java.io.InputStream is) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		try
		{
			String line = null;
			while ((line = br.readLine()) != null)
			{
				sb.append(line + "\n");
			}
		}
		catch (Exception ex)
		{
			throw new Exception("An exception occurred in StringUtils.parseISToString message = " + ex.getMessage(), ex);
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (Exception ex)
			{
				//purposely swallowing exception
			}
		}
		return sb.toString();
	}

	/**
	 * This method returns the String between the two separators
	 * @param str - input string
	 * @param start  the first separator
	 * @param end  the end separator
	 * @return string between start and end
	 */
public static String substringBetween(String str, String start, String end) {
		
		int last = str.lastIndexOf(end);
		int first = str.lastIndexOf(start)+ start.length();
		
		if (last == -1 ||first == -1 ) {
			return str;
		}
		return str.substring(first, last);
	}

}
