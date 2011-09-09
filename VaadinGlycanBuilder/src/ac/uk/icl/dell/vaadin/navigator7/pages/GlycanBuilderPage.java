/*
*   EuroCarbDB, a framework for carbohydrate bioinformatics
*
*   Copyright (c) 2006-2011, Eurocarb project, or third-party contributors as
*   indicated by the @author tags or express copyright attribution
*   statements applied by the authors.  
*
*   This copyrighted material is made available to anyone wishing to use, modify,
*   copy, or redistribute it subject to the terms and conditions of the GNU
*   Lesser General Public License, as published by the Free Software Foundation.
*   A copy of this license accompanies this distribution in the file LICENSE.txt.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
*   or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
*   for more details.
*   
*   @author David R. Damerell (david@nixbioinf.org)
*/
package ac.uk.icl.dell.vaadin.navigator7.pages;

import org.vaadin.navigator7.Page;
import org.vaadin.navigator7.window.PageWrapper;

import ac.uk.icl.dell.vaadin.glycanbuilder.GlycanBuilder;
import ac.uk.icl.dell.vaadin.navigator7.IGGAppLevelWindow;
import ac.uk.icl.dell.vaadin.navigator7.IGGApplication;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;

@Page
public class GlycanBuilderPage implements PageWrapper{
	//CssLayout layout;
	GlycanBuilder theGlycanBuilder;
	
	public GlycanBuilderPage(){
	//	layout=new CssLayout();
//		
		IGGAppLevelWindow window=(IGGAppLevelWindow)IGGApplication.getCurrentNavigableAppLevelWindow();
		theGlycanBuilder=new GlycanBuilder(window.getApplicationMenu());
		
		theGlycanBuilder.getVaadinGlycanCanvas().addLocalResourceWatcher(((IGGApplication)IGGApplication.getCurrent()));
		
		//layout.setSizeUndefined();
		//layout.addComponent(theGlycanBuilder);
	}

	@Override
	public Component getComponent(){
		//Panel panel=new Panel();
		
		
		return theGlycanBuilder;
		//return panel;
	}
}
