/*
Copyleft (C) 2005 Hélio Perroni Filho
xperroni@yahoo.com
ICQ: 2490863

This file is part of ChatterBean.

ChatterBean is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

ChatterBean is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with ChatterBean (look at the Documents/ directory); if not, either write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA, or visit (http://www.gnu.org/licenses/gpl.txt).
*/

package bitoflife.chatterbean.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Method;

public class ReflectionHandler extends DefaultHandler
{
  /*
  Attributes
  */
  
  private ReflectionBuilder builder = null;
  
  /*
  Constructor
  */
  
  public ReflectionHandler()
  {
  }
  
  public ReflectionHandler(ReflectionBuilder builder)
  {
    this.builder = builder;
  }
  
  /*
  Methods
  */

  public void characters(char[] chars, int start, int length)
  {
    builder.characters(chars, start, length);
  }
  
  public void endElement(String namespace, String name, String qname)
  {
    try
    {
      String methodName = "end" + qname.substring(0, 1).toUpperCase() + qname.substring(1); 
      Method event = builder.getClass().getMethod(methodName);
      event.invoke(builder);
    }
    catch (NoSuchMethodException e)
    {
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void startElement(String namespace, String name, String qname, Attributes attributes)
  {
    try
    {
      String methodName = "start" + qname.substring(0, 1).toUpperCase() + qname.substring(1); 
      Method event = builder.getClass().getMethod(methodName, Attributes.class);
      event.invoke(builder, attributes);
    }
    catch (NoSuchMethodException e)
    {
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  /*
  Properties
  */
  
  public ReflectionBuilder getReflectionBuilder()
  {
    return builder;
  }
  
  public void setReflectionBuilder(ReflectionBuilder builder)
  {
    this.builder = builder;
  }
}
