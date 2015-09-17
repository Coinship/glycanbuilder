Damerell D, Ceroni A, Maass K, Ranzinger R, Dell A, Haslam SM. The GlycanBuilder and GlycoWorkbench glycoinformatics tools: updates and new developments. Biol Chem. 2012 Nov;393(11):1357-62. doi: 10.1515/hsz-2012-0135.

Ceroni A, Dell A, Haslam SM. The GlycanBuilder: a fast, intuitive and flexible
software tool for building and displaying glycan structures. Source Code Biol
Med. 2007 Aug 7;2:3. PubMed PMID: 17683623; PubMed Central PMCID: PMC1994674.

# Introduction #

This project provides the scientific community with a Java library that can be used to draw glycan structures.  The drawing library can either draw to an AWT or HTML5 canvas.  The library allows for drawn glycan structures to be exported to an image file.  Supported export formats: SVG, PNG, JPG, BMP,PDF or EPS.  The library includes canvas classes that can be embedded into existing programs (of course you are free to draw to what ever canvas you like).  The library includes a glycan builder applet that can be embedded into a web page: allowing a user to submit a glycan structure.  In addition a standalone glycan builder application has also been created.  The latest version of this library also includes a glycan builder web application that can be embedded in web pages: this version doesn't require the user to have the Java browser plug-in installed (an important consideration if you wish to support iOS devices).

# Glycan Builder [UniCarbKB Integrated](http://115.146.85.164/builder) #
Please note that if you are looking for the live demo of GlycanBuilder (live.glycanbuilder.org) it is currently unavailable as we look to migrate to new servers.

(The Vaadin version of GlycanBuilder has a new home on [bitbucket](https://bitbucket.org/daviddamerell/glycanbuilderv))

Vaadin is a web development framework that functions on top of the Google Web Toolkit (GWT). The GWT allows you to write web applications completely in Java: this includes both the client and server side components.  GWT includes a compiler that takes the Java programmed client side and turns this into a JavaScript library: that the browser is asked to load.  Vaadin takes GWT one step further, almost allowing the developer to forget that their is a separation between the client and server side components: Vaadin development is very similar to Swing and SWT development.  The Vaadin glycan builder application draws glycan structures to an HTML5 canvas element.  This component can be embedded in existing web pages (see below).  Alternatively you can create new Vaadin based web sites and use the Vaadin glycan builder component as a native component.  In addition you can use the component that draws glycan structures outside of the builder application (i.e. you could use a series of canvas elements to show a list of glycan structures).  We had to modify the Vaadin component that talks to the native browser HTML5 canvas component: see the following URL for this new component [CanvasWidgetICL](https://bitbucket.org/daviddamerell/canvaswidgeticl)

The Vaadin glycan builder component requires a modern web browser that supports the HTML5 canvas: Internet Explorer 9, Safari, Firefox 3.5, Chrome.  This component supports image export and glycan structure import from file and string formats: none of these features are supported in the applet version.

The following is a brief set of instructions to install this component and embed it in another web page.  Note that we ask Apache to proxy access to TomCat: this is important as modern web browsers will refuse to load an iframe unless both the hostname and port match that of the host web page (that you are embedding glycan builder into).

Start by putting the following web page somewhere in your web server served directory.
```
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="width:100%;height:100%;border:0;margin:0;">
<head> 
<title>Vaadin embedded GlycanBuilder applet</title> 
<script type="text/javascript"> 
        var vaadin = {
                vaadinConfigurations: {
                        'fb' :{
                                appUri:'/GlycanBuilder', 
                                pathInfo: '/GlycanBuilder', 
                                themeUri: '/GlycanBuilder/VAADIN/themes/ucdb_2011theme', 
                                versionInfo : {vaadinVersion:"6.0.0-INTERNAL-NONVERSIONED-DEBUG-BUILD",applicationVersion:"NONVERSIONED"}
                        }
                        
                }};
</script> 
<script language='javascript' src='GlycanBuilder/VAADIN/widgetsets/ac.uk.icl.dell.vaadin.glycanbuilder.widgetset.GlycanbuilderWidgetset/ac.uk.icl.dell.vaadin.glycanbuilder.widgetset.GlycanbuilderWidgetset.nocache.js'></script> 
<link rel="stylesheet" type="text/css" href="/GlycanBuilder/VAADIN/themes/ucdb_2011theme/styles.css"/> 
</head>
<body>
        <iframe id="__gwt_historyFrame" style="width:0;height:0;border:0;overflow:hidden" src="javascript:false"></iframe>

        <p>Vaadin embedded GlycanBuilder applet</p>

        <div id="fb" style="height:800px;border:2px solid red;margin:0"></div>

        <script type="text/javascript"> 
        var callBack=[];
        callBack.run=function(response){alert(response);}
        </script>
        <form>
        <input type="button" name="Search" value="Search" onclick='window["glycanCanvas"].runCommand("export~glycoct_condensed",callBack);'/>
        </form>

        </body>
</html>
```
Next download the [Vaadin glycan builder war](http://code.google.com/p/glycanbuilder/downloads/detail?name=GlycanBuilder.war&can=2&q=): place this into the webapps directory of TomCat.

Finally setup Apache to proxy access to TomCat (hopefully it's obvious what settings you need to adjust).
```
<VirtualHost *:80>
        ServerAdmin webmaster@localhost
        ServerName glycodb.nixbioinf.org

        DocumentRoot /opt/server/glycodb.nixbioinf.org/

        <Directory /opt/server/glycodb.nixbioinf.org>
                Options Indexes FollowSymLinks MultiViews
                AllowOverride All
                Order deny,allow
                allow from all 
        </Directory>

        RewriteEngine On

        RewriteRule ^/GlycanBuilder/(.*)$ /tomcat/GlycanBuilder/$1 [P]

        ProxyRequests Off

        ProxyPass /tomcat/ http://localhost:8080/
        ProxyPassReverse /tomcat/ http://localhost:8080/

        ProxyPreserveHost On

        HostnameLookups on

        ErrorLog /var/log/apache2/ucdb_2011.error.log

        LogLevel warn

        CustomLog /var/log/apache2/ucdb_2011.access.log combined
</VirtualHost>
```
After you have started both Apache and TomCat you should be able to see glycan builder in action by navigating to the web page you created above.  You can move glycan builder around in a web page by moving the div element where the id equals fb.  Hopefully you can also see how the submit button in the above example used a call back to export the canvas in GlycoCT condensed format.
# [Glycan Builder Applet ](http://applet.glycanbuilder.org) #
Simply download the [Glycan Builder applet](http://code.google.com/p/glycanbuilder/downloads/detail?name=GlycanBuilderApplet.jar&can=2&q=) jar and insert it into any web page using the code shown below.
```
<applet 
 id="GlycanBuilder" 
 name="GlycanBuilder" 
 code="org.eurocarbdb.application.glycanbuilder.GlycanBuilderApplet.class" 
 archive="GlycanBuilderApplet.jar" 
 width="700" 
 height="400"
>
</applet>
```
The Glycan Builder applet supports two-way communication between the host web page and the applet.  You can directly call public methods on the GlycanBuilderApplet class - see examples below.
```
<script type="text/javascript">
var sequence=document.GlycanBuilder.getDocument();
document.GlycanBuilder.setDocument(sequence);
document.GlycanBuilder.setDocument(sequence,"format");
var sequence=document.GlycanBuilder.getDocument("format");
</script>
```
### Options parameters ###
```
<param name="allowUncertainTerminals" value="true">
<param name="allowRepeatingUnits" value="true">
<param name="allowMultipleStructures" value="true">
<param name="residueTypesFile" value="http://glycodb.nixbioinf.org/residue_types">
<param name="coreTypesFile" value="http://glycodb.nixbioinf.org/core_types">
<param name="terminalTypesFile" value="http://glycodb.nixbioinf.org/terminal_types">
<param name="crossRingFragmentTypesFile" value="http://glycodb.nixbioinf.org/cross_ring_fragment_types">
```
Accepted import and export formats are shown below
```
Molecular Framework formats

LINUCS => linucs
bcsdb => bcsdb
CFG => cfg
GlycoCT XML => glycoct_xml
GlycoCT Condensed => glycoct_condensed
OGBI => ogbi
IUPAC condensed => iupac_condenced
IUPAC short version 1 => iupac_short_v1
IUPAC short version 2 => iupac_short_v2
CarbBank => carbbank

GlycoWorkbench internal formats

GlycoCT Condensed -> glycoct_condensed
GlycoCT XML -> glycoct
Glycominds -> glycominds
GlycoWorkbench LINUCS -> gwlinucs
GlycoWorkbench string -> gws
```
# Glycan Builder library #
You can download the whole Glycan Builder library as a jar from the following [here](http://code.google.com/p/glycanbuilder/downloads/detail?name=eurocarb-glycanbuilder-1.0rc.jar&can=2&q=).  This jar includes both the standalone and applet versions of Glycan Builder.
# Building Glycan Builder from Source #
#1 Checkout the UCDB project

hg clone https://code.google.com/p/ucdb/source/checkout

(Alternatively use a tool like TortoiseHG http://tortoisehg.bitbucket.org/)

Note that if you are checking out on Windows be aware that some of the file paths are very close to the Windows API maximum length of 266 characters.  Therefore it's recommended that you checkout to a directory such as C:\UCDB.  Most of the developers in the past have worked on Unix based systems where this didn't cause any problems.

#2 Download Apache Ant or install from System Repository

http://ant.apache.org/bindownload.cgi

Ant needs the environment variable "ANT\_HOME" set to the installation/extracted directory and the directory $ANT\_HOME\bin added to the system PATH environment variable.  You probably already have Ant installed so I won't go into any further details

#3 Dependency Resolution

Within the UCDB project root directory you will notice that there's an IVY script to dynamically pull in dependencies from external repositories.  Although some of the dependencies are located in the pre-fetch directory.  Our IVY import script frequently breaks so it's simpler to just follow the instructions below.

Therefore I wouldn't even both trying to pull the dependencies with Ant.

Instead download the following archive and it extract it into $UCDB\_PROJECT/external-libs

https://code.google.com/p/ucdb/downloads/detail?name=UCDB_DEPS.zip&can=2&q=#makechanges

#5 Make sure that environment variables for Java are correctly configured.

Check that the bin directory of your JDK is appended to the PATH environment variable and that JAVA\_HOME is correctly configured.

You can't build UCDB projects with OpenJDK you must be using a JDK from Oracle to be sure it will build correctly.

#4 Next, start-up a terminal/console/powershell

cd to the UCDB directory and type

ant setup

Ignore the error messages they don't matter

#5 Building GlycanBuilder

cd application/GlycanBuilder

ant build and ant dist-applet

The applet goes into $UCDB\_PROJECT\bin\GlycanBuilderApplet.jar