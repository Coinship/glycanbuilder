# GlycoCT Condensed to PNG #
#Argument 1 is the file containing the GlycoCT Condensed String
#Argument 2 is the output image file name

Please add all resources in the ZIP file located [here](http://code.google.com/p/glycanbuilder/downloads/detail?name=ExampleCodeResources.zip&can=2&q=#makechanges) to your build path

```
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eurocarbdb.application.glycanbuilder.BuilderWorkspace;
import org.eurocarbdb.application.glycanbuilder.Glycan;
import org.eurocarbdb.application.glycanbuilder.GlycanRenderer;
import org.eurocarbdb.application.glycanbuilder.GlycanRendererAWT;
import org.eurocarbdb.application.glycanbuilder.GlycoCTCondensedParser;
import org.eurocarbdb.application.glycanbuilder.MassOptions;
import org.eurocarbdb.application.glycanbuilder.SVGUtils;

public class TestGlycanExport {
	public static void main(String[] args) throws Exception {
		//The workspace will initialise dictionaries and placement libraries for us
		BuilderWorkspace workspace=new BuilderWorkspace(new GlycanRendererAWT());
		workspace.setNotation("cfg"); //cfgbw | uoxf | uoxfcol | text
		
		//Get a reference to the renderer
		GlycanRenderer renderer=workspace.getGlycanRenderer();
		
		//Get an instance of the GlycoCT Parser
		GlycoCTCondensedParser parser=new GlycoCTCondensedParser(false);
		MassOptions options=MassOptions.empty();
		
		//Parse in a GlycoCT condensed string from text and pass in empty MassOptions
		Glycan glycan=parser.fromGlycoCTCondensed(readGlycan(args[0]), options);
		
		//Generate a list of glycans to render
		List<Glycan> glycanList=new ArrayList<>();
		glycanList.add(glycan);
		
		//Render glycans as a PNG
		SVGUtils.export((GlycanRendererAWT) renderer, args[1], glycanList, false, true, "png");
	}
	
	public static String readGlycan(String fileName) throws IOException{
		try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
			StringBuilder glycanSeq=new StringBuilder();
			String line=null;

			while((line=reader.readLine())!=null){
				glycanSeq.append(line+"\n");
			}

			return glycanSeq.toString();
		}
	}
}

```