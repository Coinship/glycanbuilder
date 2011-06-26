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
package ac.uk.icl.dell.vaadin.menu;

import com.vaadin.ui.MenuBar;

public class IGGHeaderMenu extends DynamicMenuImpl implements ApplicationMenu{
	private static final long serialVersionUID=4968428219495753712L;
	
	private MenuItem fileMenuItem;
	private MenuItem editMenuItem;
	private MenuItem viewMenuItem;
	
	public IGGHeaderMenu(){
		fileMenuItem=addItem("File",null);
		editMenuItem=addItem("Edit",null);
		viewMenuItem=addItem("View",null);
		
		fileMenuItem.setVisible(false);
		editMenuItem.setVisible(false);
		viewMenuItem.setVisible(false);
	}

	@Override
	public MenuBar.MenuItem getFileMenu(){
		return fileMenuItem;
	}

	@Override
	public MenuItem getEditMenu() {
		return editMenuItem;
	}

	@Override
	public MenuItem getViewMenu() {
		return viewMenuItem;
	}

	@Override
	public MenuBar getMenuBar() {
		return this;
	}
}