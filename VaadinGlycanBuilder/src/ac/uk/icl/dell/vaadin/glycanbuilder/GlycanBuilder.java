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
package ac.uk.icl.dell.vaadin.glycanbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eurocarbdb.application.glycanbuilder.Residue;
import org.vaadin.damerell.canvas.BasicCanvas.ExportListener;

import ac.uk.icl.dell.vaadin.LocalResourceWatcher;
import ac.uk.icl.dell.vaadin.glycanbuilder.GlycanCanvas.GlycanCanvasUpdateListener;
import ac.uk.icl.dell.vaadin.glycanbuilder.GlycanCanvas.NotationChangedListener;
import ac.uk.icl.dell.vaadin.glycanbuilder.GlycanCanvas.ResidueHistoryListener;
import ac.uk.icl.dell.vaadin.menu.ApplicationMenu;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window.ResizeEvent;

/**
 * callBack=[]
 * callBack.run=function(response){alert(response);}
 * window.glycanCanvasExport("glycoct_condensed",callBack)
 * 
 * 
 * @author david
 *
 */
public class GlycanBuilder extends CssLayout implements com.vaadin.ui.Window.ResizeListener, ResidueHistoryListener{
	private static final long serialVersionUID=-1310424874910700087L;
	
	protected VaadinGlycanCanvas theCanvas;
	protected VaadinGlycanCanvas theResidueCanvas;
	
	public org.eurocarbdb.application.glycanbuilder.BuilderWorkspace theWorkspace;
	
	private Panel theToolBarPanel;
	private List<MenuBar.MenuItem> menuItems=new ArrayList<MenuBar.MenuItem>();
	
	
	public enum GlycanBuilderMode{
		STANDALONE(),DOCKED();
	}
	
	GlycanBuilderMode glycanBuilderMode=GlycanBuilderMode.DOCKED;
	
	protected ApplicationMenu theAppMenu;
	
	public GlycanBuilder(ApplicationMenu appMenu){
		theAppMenu=appMenu;
		
		setSizeUndefined();
		
		addStyleName("igg-glycan-builder");
		
		//final Panel panel=new Panel();
		//panel.setScrollable(true);
		
		CssLayout layout=new CssLayout();
		layout.addStyleName("igg-glycan-builder-canvas-container");
		
		theCanvas=new VaadinGlycanCanvas();
		theCanvas.setBackgroundColor("#CCF");
		theCanvas.addStyleName("igg-glycan-builder-glycan-canvas");
		theCanvas.setWidth("100%");
		theCanvas.setHeight("100%");
		theCanvas.enableMouseSelectionRectangle(true);
		theCanvas.theCanvas.addResidueHistoryListener(this);	
//		
//		panel.setSizeFull();
//		panel.getContent().setWidth("100%");
//		panel.getContent().setHeight("100%");
//		panel.getContent().addComponent(theCanvas);
//		panel.setStyleName("glycan_canvas");
		
		initToolBars();
		
		layout.addComponent(theCanvas);
		
		addComponent(layout);
		
		////((VerticalLayout)getContent()).setExpandRatio(panel, 1f);
		
		theCanvas.theCanvas.addNotationListener(new NotationChangedListener() {
			@Override
			public void notationChanged(String notation) {
				theResidueCanvas.theCanvas.setNotation(notation);
			}
		});
		
		theCanvas.setName("glycanCanvas");
		
		theCanvas.addExportListener(new ExportListener(){
			@Override
			public void exportRequest(String type) {
				if(type.equals("glycoct_condensed")){
					theCanvas.respondToExportRequest(theCanvas.theCanvas.theDoc.toGlycoCTCondensed());
				}else if(type.equals("glycoct")){
					theCanvas.respondToExportRequest(theCanvas.theCanvas.theDoc.toGlycoCT());
				}
			}
		});
		
		initFileMenu();
		initViewMenu();
		initStructureMenu();
	}
	
	public GlycanCanvas getGlycanCanvas(){
		return theCanvas.theCanvas;
	}
	
	public void registerLocalResourceWatcher(LocalResourceWatcher watcher){
		theCanvas.addLocalResourceWatcher(watcher);
	}
	
	private void initToolBars(){
		theToolBarPanel=new Panel();
		theToolBarPanel.setContent(new CssLayout());
		theToolBarPanel.addStyleName("igg-glycan-builder-toolbar-panel");
		
		
		theCanvas.appendGeneralToolBar(theToolBarPanel);
		
		theToolBarPanel.setScrollable(false);
		
		addComponent(theToolBarPanel);
		
		Panel theLinkageToolBarPanel=new Panel();
		theLinkageToolBarPanel.setContent(new CssLayout());
		theLinkageToolBarPanel.addStyleName("igg-glycan-builder-linkage-toolbar-panel");
		theCanvas.appendLinkageToolBar(theLinkageToolBarPanel);
		
		theLinkageToolBarPanel.setScrollable(false);
		
		addComponent(theLinkageToolBarPanel);
		
		//final Panel panel=new Panel();
		
		HorizontalLayout canvasLayout=new HorizontalLayout();
		canvasLayout.setWidth("100%");
		canvasLayout.setHeight("25px");
		
		theResidueCanvas=new VaadinGlycanCanvas();
		theResidueCanvas.setBackgroundColor("#CCF");
		theResidueCanvas.setWidth("100%");
		theResidueCanvas.setHeight("25px");
		theResidueCanvas.enableMouseSelectionRectangle(false);
		theResidueCanvas.automaticHeightAdjust(false);
		theResidueCanvas.theCanvas.theGlycanRenderer.getGraphicOptions().MARGIN_TOP=2;
		
		canvasLayout.addComponent(theResidueCanvas);
		
		//panel.setContent(canvasLayout);
		//panel.setScrollable(false);
		
		addComponent(theResidueCanvas);
		
		final VaadinGlycanCanvas finalCanvas=theResidueCanvas;
		finalCanvas.enableResidueToolBarMode();
		
		theResidueCanvas.theCanvas.addGlycanCanvasUpdateListener(new GlycanCanvasUpdateListener(){
			@Override
			public void glycanCanvasUpdated() {
				Residue selectedResidues[]=theResidueCanvas.theCanvas.selectedResidues.toArray(new Residue[0]);

				theResidueCanvas.theCanvas.selectedResidues.clear();
				
				theCanvas.theCanvas.setDocumentChangedEventFiring(false);
				
				
				for(Residue toinsert:selectedResidues){
					System.err.println("Selected residue: "+toinsert.getTypeName());
					theCanvas.theCanvas.addResidueByNameToSelected(toinsert.getTypeName());
				}
				
				theCanvas.theCanvas.setDocumentChangedEventFiring(true);
				theCanvas.theCanvas.documentUpdated();
			}
		});
	}
	
	private void initFileMenu(){
		MenuBar.MenuItem fileMenu=theAppMenu.getFileMenu();
		
		theAppMenu.saveState(fileMenu, this);
		
		theCanvas.appendExportMenu(fileMenu);
		theCanvas.appendImportMenu(fileMenu);
		
		fileMenu.setVisible(true);
	}
	
	private void initStructureMenu(){
		MenuBar dockMenuBar=theAppMenu.getMenuBar();
		
		//tmp
		MenuBar.MenuItem structureItem;
		if(dockMenuBar.getItems().size()>3){
			structureItem=dockMenuBar.addItemBefore("Structure", null, null, dockMenuBar.getItems().get(2));
		}else{
			structureItem=dockMenuBar.addItem("Structure", null, null);
		}
		
		theCanvas.appendStructureMenu(structureItem);
		menuItems.add(structureItem);
	}
	
	private void initViewMenu(){
		MenuBar dockMenuBar=theAppMenu.getMenuBar();
		
		//tmp
		MenuBar.MenuItem structureItem;
		if(dockMenuBar.getItems().size()>2){
			structureItem=dockMenuBar.addItemBefore("View", null, null, dockMenuBar.getItems().get(1));
		}else{
			structureItem=dockMenuBar.addItem("View", null, null);
		}
		
		theCanvas.appendNotationMenu(structureItem);
		menuItems.add(structureItem);
	}

	@Override
	public void windowResized(ResizeEvent e) {
		//getWindow().executeJavaScript("$('.gwt-PopupPanel canvas')[0].style.height=$('.v-panel-content-glycan_canvas_1')[0].style.height;");
		//getWindow().executeJavaScript("$('.gwt-PopupPanel canvas')[0].style.width=$('.v-panel-content-glycan_canvas_1')[0].style.width;");
	}

	@Override
	public void respondToResidueHistoryChanged(Vector<String> residueHistoryList) {
		theResidueCanvas.setResidueHistoryList(residueHistoryList);
		theResidueCanvas.appendResidueToolBar();
	}
	
	public VaadinGlycanCanvas getVaadinGlycanCanvas(){
		return theCanvas;
	}
	
	public void onUndock(){
		theAppMenu.restoreState(theAppMenu.getFileMenu(), this);
		theAppMenu.getFileMenu().setVisible(false);
		
		for(MenuBar.MenuItem item:menuItems){
			theAppMenu.removeMenuItem(item);
		}
	}
}
