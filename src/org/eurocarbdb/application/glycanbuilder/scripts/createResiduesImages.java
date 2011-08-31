package org.eurocarbdb.application.glycanbuilder.scripts;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eurocarbdb.application.glycanbuilder.BuilderWorkspace;
import org.eurocarbdb.application.glycanbuilder.GlycanRendererAWT;
import org.eurocarbdb.application.glycanbuilder.GraphicOptions;
import org.eurocarbdb.application.glycanbuilder.ICON_SIZE;
import org.eurocarbdb.application.glycanbuilder.ResidueDictionary;
import org.eurocarbdb.application.glycanbuilder.ResidueRenderer;
import org.eurocarbdb.application.glycanbuilder.ResidueRendererAWT;
import org.eurocarbdb.application.glycanbuilder.ResidueType;

import sun.awt.image.ImageRepresentation;
import sun.awt.image.ToolkitImage;

public class createResiduesImages {
	public static void main(String args[]){
		GlycanRendererAWT renderer=new GlycanRendererAWT();
		BuilderWorkspace workspace=new BuilderWorkspace(renderer);
		
		String notations[]=new String[]{
				GraphicOptions.NOTATION_CFG,
				GraphicOptions.NOTATION_CFGBW,
				GraphicOptions.NOTATION_TEXT,
				GraphicOptions.NOTATION_UOXF,
				GraphicOptions.NOTATION_UOXFCOL
		};
		
		for(String notation:notations){
			workspace.setNotation(notation);
			
			File dir=new File("/tmp/residues/"+notation+"/");
			dir.mkdirs();

			ResidueRendererAWT rr = (ResidueRendererAWT) renderer.getResidueRenderer();
			for (ResidueType t : ResidueDictionary.allResidues()) {
				try {
					ImageIO.write(rr.getBufferedImage(t, 22), "png", new File(dir.getPath()+File.separator+t.getName()+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
