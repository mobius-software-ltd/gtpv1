package com.mobius.software.telco.protocols.gtp.api.utils;
/*Mobius Software LTD
Copyright 2019, Mobius Software LTD and individual contributors
by the @authors tag.

This program is free software: you can redistribute it and/or modify
under the terms of the GNU Affero General Public License as
published by the Free Software Foundation; either version 3 of
the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>*/
public class StringFunctions 
{
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static byte[] hexToBytes(String value)
	{
		if(value.length()%2!=0)
			return null;
		
		byte[] result=new byte[value.length()/2];
		for(int i=0;i<result.length;i++)
		{
			char currChar=value.charAt(i*2);
			int highValue,lowValue;
			if(currChar>='A' && currChar<='F')				
				highValue=((currChar-'A'+10)<<4);
			else if(currChar>='a' && currChar<='f')	
				highValue=((currChar-'a'+10)<<4);
			else if(currChar>='0' && currChar<='9')				
				highValue=((currChar-'0')<<4);
			else
				return null;
			
			currChar=value.charAt(i*2+1);
			if(currChar>='A' && currChar<='F')				
				lowValue=(currChar-'A'+10);
			else if(currChar>='a' && currChar<='f')				
				lowValue=(currChar-'a'+10);
			else if(currChar>='0' && currChar<='9')				
				lowValue=(currChar-'0');
			else
				return null;
			
			result[i]=(byte)((highValue & 0xF0) | (lowValue & 0x0F));
		}
		
		return result;
	}
	
	public static byte[] octetsToBytes(String value)
	{
		int length=value.length()/2;
		if(value.length()%2==1)
			length+=1;
		
		byte[] result=new byte[length];
		for(int i=0;i<result.length;i++)
		{
			char currChar=value.charAt(i*2);
			int highValue,lowValue;
			if(currChar>='0' && currChar<='9')				
				highValue=(currChar-'0');
			else
				return null;
			
			if(i*2+1==value.length())
				lowValue=(0x0F<<4);
			else
			{
				currChar=value.charAt(i*2+1);
				if(currChar>='0' && currChar<='9')				
					lowValue=((currChar-'0')<<4);
				else
					return null;
			}
			
			result[i]=(byte)((lowValue & 0xF0) | (highValue & 0x0F));
		}
		
		return result;
	}
	
	public static String bytesToHex(byte[] bytes) 
	{
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) 
	    {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	        hexChars[j * 2] = hexArray[v >>> 4];	        
	    }
	    
	    return new String(hexChars);	    
	}
	
	public static String octetsToHex(byte[] bytes) 
	{
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) 
	    {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v & 0x0F];
	        hexChars[j * 2 + 1] = hexArray[v >>> 4];	        
	    }
	    
	    if(hexChars[hexChars.length-1]!='F')
	    	return new String(hexChars);
	    
	    return new String(hexChars,0,hexChars.length-1);
	}		
}
