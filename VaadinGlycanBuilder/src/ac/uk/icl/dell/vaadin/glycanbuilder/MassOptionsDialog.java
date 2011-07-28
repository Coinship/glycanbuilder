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
import java.util.Collection;
import java.util.List;

import org.eurocarbdb.application.glycanbuilder.Glycan;
import org.eurocarbdb.application.glycanbuilder.MassOptions;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class MassOptionsDialog extends Panel implements MassOptionsConfigurator.MassInput{
	private static final long serialVersionUID=8707959087794407560L;
	
	GridLayout layout=new GridLayout(12,12);
	Collection<Glycan> glycans;
	
	public MassOptionsDialog(Collection<Glycan> glycans, MassOptions massOptions){
		setContent(layout);
		this.glycans=glycans;
		
		config = new MassOptionsConfigurator(glycans, massOptions, this);
	}

	@Override
	public String getReducingEnd() {
		return (String) reducingEndSelect.getValue();
	}

	@Override
	public String getOtherName() {
		return (String) otherNameField.getValue();
	}

	@Override
	public String getOtherMass() {
		return (String) otherMassField.getValue();
	}

	@Override
	public String getIsotope() {
		return (String) isotopeSelect.getValue();
	}

	@Override
	public String getDerivatization() {
		return (String) derivatizationSelect.getValue();
	}

	@Override
	public boolean isNegativeMode() {
		return (Boolean) negativeModeField.getValue();
	}

	@Override
	public int getHIonCount() {
		return MassOptionsDialog.getValueAsInt(hIonCountSelect);
	}

	@Override
	public int getNAIonCount() {
		return MassOptionsDialog.getValueAsInt(naIonCountSelect);
	}

	@Override
	public int getLIIonCount() {
		return MassOptionsDialog.getValueAsInt(liIonCountSelect);
	}

	@Override
	public int getKIonCount() {
		return MassOptionsDialog.getValueAsInt(kIonCountSelect);
	}

	@Override
	public int getexNAIonCount() {
		return MassOptionsDialog.getValueAsInt(exNAIonCountSelect);
	}

	@Override
	public int getexLIIonCount() {
		return MassOptionsDialog.getValueAsInt(exLIIonCountSelect);
	}

	@Override
	public int getexKIonCount() {
		return MassOptionsDialog.getValueAsInt(exKIonCountSelect);
	}

	@Override
	public void setIsotopValues(String[] list) {
		addAllItems(isotopeSelect,list);
	}

	@Override
	public void setDerivatizationValues(String[] list) {
		addAllItems(derivatizationSelect,list);
	}

	@Override
	public void setReducingEndValues(String[] list) {
		addAllItems(reducingEndSelect,list);
	}

	@Override
	public void setHIonRange(Object[] list) {
		addAllItems(hIonCountSelect,list);
	}

	@Override
	public void setNAIonRange(Object[] list) {
		addAllItems(naIonCountSelect,list);
	}

	@Override
	public void setLIIonRange(Object[] list) {
		addAllItems(liIonCountSelect,list);		
	}

	@Override
	public void setKIonRange(Object[] list) {
		addAllItems(kIonCountSelect,list);
	}

	@Override
	public void setexNAIonRange(Object[] list) {
		addAllItems(exNAIonCountSelect,list);
	}

	@Override
	public void setexLIIonRange(Object[] list) {
		addAllItems(exLIIonCountSelect,list);
	}

	@Override
	public void setexKIonRange(Object[] list) {
		addAllItems(exKIonCountSelect,list);
	}

	@Override
	public void setSelectedIsotope(String isotope) {
		isotopeSelect.setValue(isotope);
	}

	@Override
	public void setSelectedDerivatization(String derivatization) {
		derivatizationSelect.setValue(derivatization);
	}

	@Override
	public void setSelectedReducingEnd(String reducingEnd) {
		reducingEndSelect.setValue(reducingEnd);		
	}

	@Override
	public void setOtherName(String otherName) {
		otherNameField.setValue(otherName);
	}

	@Override
	public void setOtherMass(String otherMass) {
		otherMassField.setValue(otherMass);
	}

	@Override
	public void setNegativeMode(boolean isNegative) {
		negativeModeField.setValue(isNegative);
	}

	@Override
	public void setHIonCount(int count) {
		hIonCountSelect.setValue(count);
	}

	@Override
	public void setNAIonCount(int count) {
		naIonCountSelect.setValue(count);
	}

	@Override
	public void setLIIonCount(int count) {
		liIonCountSelect.setValue(count);
	}

	@Override
	public void setKIonCount(int count) {
		kIonCountSelect.setValue(count);
	}

	@Override
	public void setexNAIonCount(int count) {
		exNAIonCountSelect.setValue(count);
	}

	@Override
	public void setexLIIonCount(int count) {
		exLIIonCountSelect.setValue(count);
	}

	@Override
	public void setexKIonCount(int count) {
		exKIonCountSelect.setValue(count);
	}

	@Override
	public void enableIsotopField(boolean enable) {
		isotopeSelect.setEnabled(enable);
	}

	@Override
	public void enableOtherMassField(boolean enable) {
		otherMassField.setEnabled(enable);
	}

	@Override
	public void enableOtherNameField(boolean enable) {
		otherNameField.setEnabled(enable);
	}
	
	Select isotopeSelect;
	Select derivatizationSelect;
	Select reducingEndSelect;
	
	TextField otherNameField;
	TextField otherMassField;
	
	CheckBox negativeModeField;
	
	Select hIonCountSelect, exHIonCountSelect;
	Select naIonCountSelect, exNAIonCountSelect;
	Select liIonCountSelect, exLIIonCountSelect;
	Select kIonCountSelect, exKIonCountSelect;
	Select clIonCountSelect, exClIonCountSelect;
	private MassOptionsConfigurator config;
	
	@Override
	public void initComponents() {
		isotopeSelect=new Select();
		isotopeSelect.setNullSelectionAllowed(false);
		isotopeSelect.setNewItemsAllowed(false);
		
		derivatizationSelect=new Select();
		derivatizationSelect.setNullSelectionAllowed(false);
		derivatizationSelect.setNewItemsAllowed(false);
		
		reducingEndSelect=new Select();
		reducingEndSelect.setNullSelectionAllowed(false);
		reducingEndSelect.setImmediate(true);
		reducingEndSelect.setNewItemsAllowed(false);
		
		reducingEndSelect.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID=1067195208212460144L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if(((String)reducingEndSelect.getValue()).equals("Other...")){
					otherNameField.setEnabled(true);
					otherMassField.setEnabled(true);
				}else{
					otherNameField.setEnabled(false);
					otherMassField.setEnabled(false);
				}
			}
		});
		
		
		otherNameField=new TextField();
		otherMassField=new TextField();
		
		negativeModeField=new CheckBox();
	
		hIonCountSelect=new Select("#H ions");
		hIonCountSelect.setNewItemsAllowed(false);
		
		naIonCountSelect=new Select("#Na ions");
		naIonCountSelect.setNewItemsAllowed(false);
		exNAIonCountSelect=new Select("ext. #Na ions");
		exNAIonCountSelect.setNewItemsAllowed(false);
		
		liIonCountSelect=new Select("#Li ions");
		liIonCountSelect.setNewItemsAllowed(false);
		exLIIonCountSelect=new Select("ext. #Li ions");
		exLIIonCountSelect.setNewItemsAllowed(false);
		
		kIonCountSelect=new Select("#K ions");
		kIonCountSelect.setNewItemsAllowed(false);
		exKIonCountSelect=new Select("ext. #K ions");
		exKIonCountSelect.setNewItemsAllowed(false);
		
		clIonCountSelect=new Select("#Cl ions");
		clIonCountSelect.setNewItemsAllowed(false);
		exClIonCountSelect=new Select("ext. #Cl ions");
		exClIonCountSelect.setNewItemsAllowed(false);
		
		//column,row
		layout.addComponent(new Label("Isotope"),0,0,0,0);
		layout.addComponent(isotopeSelect,1,0,1,0);
		layout.addComponent(new Label("Derivatization"),0,1,0,1);
		layout.addComponent(derivatizationSelect,1,1,1,1);
		layout.addComponent(new Label("Reducing end"),0,2,0,2);
		layout.addComponent(reducingEndSelect,1,2,1,2);
		
		layout.addComponent(new Label("name"),1,3,1,3);
		layout.addComponent(otherNameField,2,3,2,3);
		
		layout.addComponent(new Label("mass"),1,4,1,4);
		layout.addComponent(otherMassField,2,4,2,4);
		
		layout.addComponent(new Label("Substract mode"),0,5,0,5);
		layout.addComponent(negativeModeField,1,5,1,5);
		
		layout.addComponent(hIonCountSelect,0,6,0,6);
		
		layout.addComponent(naIonCountSelect,0,7,0,7);
		
		layout.addComponent(exNAIonCountSelect,1,7,1,7);
		
		layout.addComponent(liIonCountSelect,0,8,0,8);
		
		layout.addComponent(exLIIonCountSelect,1,8,1,8);
		
		layout.addComponent(kIonCountSelect,0,9,0,9);
		
		layout.addComponent(exKIonCountSelect,1,9,1,9);
		
		layout.addComponent(clIonCountSelect,0,10,0,10);
		
		layout.addComponent(exClIonCountSelect,1,10,1,10);
		
		NativeButton update=new NativeButton("Save");
		update.addListener(new ClickListener(){
			private static final long serialVersionUID=-6188200798103156691L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					config.retrieveData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fireMassOptionsChanged(config.getMassOptions(),glycans);
			}
		});
		
		layout.addComponent(update,0,11,0,11);
	}
	
	public void addAllItems(AbstractSelect select,Object [] objects){
		for(Object obj:objects){
			select.addItem(obj);
		}
	}
	
	public static int getValueAsInt(AbstractSelect select){
		return (Integer) select.getValue();
	}
	
	public interface MassOptionListener {
		public void massOptions(MassOptions massOptions,Collection<Glycan> structures);
	}
	
	List<MassOptionListener> massOptionListeners=new ArrayList<MassOptionListener>();
	
	public void addMassOptionListener(MassOptionListener listener){
		massOptionListeners.add(listener);
	}
	
	public void removeMassOptionListener(MassOptionListener listener){
		massOptionListeners.remove(listener);
	}
	
	public void fireMassOptionsChanged(MassOptions massOptions,Collection<Glycan> structures){
		for(MassOptionListener listener:massOptionListeners){
			listener.massOptions(massOptions,structures);
		}
	}

	@Override
	public int getCLIonCount() {
		return MassOptionsDialog.getValueAsInt(clIonCountSelect);
	}

	@Override
	public int getexCLIonCount() {
		return MassOptionsDialog.getValueAsInt(exClIonCountSelect);
	}

	@Override
	public void setCLIonRange(Object[] list) {
		addAllItems(clIonCountSelect,list);
	}

	@Override
	public void setexCLIonRange(Object[] list) {
		addAllItems(exClIonCountSelect,list);
	}

	@Override
	public void setCLIonCount(int count) {
		clIonCountSelect.setValue(count);
	}

	@Override
	public void setexCLIonCount(int count) {
		exClIonCountSelect.setValue(count);
	}
}
