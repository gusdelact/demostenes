
	- webapps
	|--- js
		|--- gfi
			|--- icb
				|--- controller		Contiene las clases que controlan el comportamiento de las vistas
				|--- model			Contiene la definicion de los datos
				|--- store			Contiene las conexiones hacia los datos
					|--- tmpMenuStore.js	Archivo de ejemplo de un store
				|--- view			Contiene las vistas [componentes] que se son desplegados
					|--- TestV1.js			Archivo de emplo de una vista
			|--- app.js			Archivo que contiene la estructura general de nuestra aplicacion
			
			
			
			
			
			
			
			
/************    ARCHETYPE      ****************/

Para generar el archetype:
	- ejecutar comando
		
		mvn archetype:create-from-project
	  
	  esto nos crea la carpeta target/generated-sorces/archetype
	
	- copiar los "distributionManagement" del .pom del proyecto al .pom generado en la carpeta antes mencionada
	
	- editar los archivos que estan en la carpeta antes mencionada para colocar los ${property} que seran sustituidos por las propiedades de maven
		(artifactId , groupId ,version) 
		
		*** Cuando se crea el archetype, maven escanea los archivos y en donde encuentra los valores exactos de las propiedades (artifactId, etc)
			los sustiruye automaticamente.
			
	- Por el momento se tienen q crear a mano las carpetas main/java y test/java en archetype-resources porq al estar vacias no las genera
		
		** Si alguna de las carpetas que queremos que se generen estan vacias, se debe de procurar que en el archivo archetype-metadata.xml 
			este la siguiente definicion
		
				<fileSet filtered="true" encoding="UTF-8">
			      <directory>src/main/java</directory>
			    </fileSet>	
		
	- ejecutar mvn deploy en la carpeta antes mencionada
	
Para usar el archetype

	- ejecutar comando
		
		mvn archetype:generate -DarchetypeCatalog=local
		
	- seleccinoar el archetype deseado de la lista que se despliega
	
	- Introducir la informacion necesaria
	
	
	
	
	
CAMBIOA ENTRE V2.0 y V2.1

 - Agregando configuracion para Monitor de Inactividad del lado del cliente (ExtJs) -> actualizar dependencia
 				<dependency>
			  		<groupId>com.gfi.corp.commons</groupId>
			  		<artifactId>commons-web-extjs</artifactId>
			  		<version>1.2-SNAPSHOT</version>
			  		<type>war</type>
			  	</dependency>
 - Actualizacion a spring 3.2.4
 	* Requirio modificaciones al servlet para desactivar una funcion que viene por default
 	* Cambiamos el viewResolver por uno mas "sencillo" que no requiere configuracion extra para los archivos jsp
 		- Se elimino el archivo view.properties (ya no es necesario)
 - Se elimino codigo js de index, ahora todo lo necesario se puso en app.js
 - Actualizando ubicacion del firebugs.js, ahora se encuentra en el root del sitio
 - Se unificaron y actualizaron versiones de plugin maven-war (esto hacia tronar el compilado con la compresion de extjs)
 - Se unificaron y actualizaron versiones de overlays (dependenias war)