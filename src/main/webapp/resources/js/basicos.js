/*Funciones basicas genericas intercambiables*/
	function chgMenuLat(id,flag){
   		if(flag){
			document.getElementById(id+'_txt').style.color='#FF6600';
			document.getElementById(id+'_flecha').src='../images/flechaover_B.gif';
   		}else{
   			document.getElementById(id+'_txt').style.color='#333333';
			document.getElementById(id+'_flecha').src='../images/flecha_B.gif';
   		}
   	}
   	function chgBtn(id,flag){
		if(flag){
			document.getElementById(id+'_izq').src="images/bcot01_ov.jpg";
			document.getElementById(id+'_cent').background="images/b_cotenm_ov.jpg";
			document.getElementById(id+'_der').src="images/b_cot2_ov.jpg";
		}else{
			document.getElementById(id+'_izq').src="images/bcot01.jpg";
			document.getElementById(id+'_cent').background="images/b_cotenm.jpg";
			document.getElementById(id+'_der').src="images/b_cot2.jpg";
		}
	}

	/*valida que sea numero y regresa el foco*/
	function vnum(obj,msj){
		var regExp = /^\d+$/;
		if(obj.value!=""&&!regExp.test(obj.value)){
			alert(msj+' debe ser numerico.');
			obj.focus();
			obj.select();
			return false;
		}
		return true;
	}
	 /*valida que sean letras o espacios y con longitud minima de 3*/
	var bandera = false;
	function vcar(obj,msj){
	 	var cad = obj.value.toUpperCase();
		if(cad==""){
			bandera = true;
			return;
		}
		if(cad!=""||cad!=null){
			if(cad.length<3){
				alert(msj+" debe ser minimo de tres caracteres.");
					obj.focus();
					obj.select();
					bandera = false;
					return ;
			}
			var expReg = /^[A-Z|—]+(\s[A-Z|—]+)*$/;
			if(!expReg.test(cad)){
				alert(msj+" debe ser alfabetico y sin acento.");
				obj.focus();
				obj.select();
				bandera = false;
				return ;
			}
		}
		bandera = true;
		obj.value = cad;
	}

	/*valida fecha*/
	function vfec(obj, msj){
		var validformat=/^\d{2}\/\d{2}\/\d{4}$/;
		var returnval=false;
		if(obj.value==''){
			returnval = true;
		}else if (!validformat.test(obj.value))
			alert(msj+' debe ser en formato dd/mm/aaaa.')
		else{
			var dayfield=obj.value.split("/")[0];
			var monthfield=obj.value.split("/")[1];
			var yearfield=obj.value.split("/")[2];
			var dayobj = new Date(yearfield, monthfield-1, dayfield);
			if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
				alert(msj+' debe ser en formato dd/mm/aaaan .');
			else
				returnval=true;
		}
		if (returnval==false)
			obj.select();
		return returnval;
	}


	 /*Funciones de pruebas*/
	/*valida que sea numero y regresa el foco*/
	function vnum2(cad,msj,obj){
	 	for(i=0;i<cad.length;i++){
			var ch = cad.charAt(i);
			if(isNaN(ch)){
				alert("Capture un numero valido para "+msj+".");
				obj.focus();
				return "";
			}
		}
		return cad;
	 }

	/*Valida que sea Alfanumerico*/
	function van(obj,msj){
		obj.value = obj.value.toUpperCase();
		var expReg = /\W+/;
		if(expReg.test(obj.value.replace(/[_]/g,''))){
			alert(msj+' debe ser alfanumerico.');
    		obj.focus();
			return false;
		}
		else 
			return true;
	}
	
	/*Valida alfabetico con Ñ, acentos y espacio*/
	function vabl(obj,msj) {
		var expReg= new RegExp("^[A-ZÑÁÉÍÓÚ| ]*$");
		
		obj.value = obj.value.toUpperCase();
		if(obj.value!=""&&!expReg.test(obj.value.replace(/[_]/g,''))){
			alert(msj+ " debe ser alfanumerico.");
			obj.focus();
			return false;
		}
		return true;
	}
	

	/*Valida que sea Alfanumerico con espacio en blaco, Ñ y acentos*/

	function vanbl(obj,msj) {
		var expReg= /^[A-ZÑÁÉÍÓÚ|—|0-9]+(\s[A-ZÑÁÉÍÓÚ|—|0-9]*)*$/;
		obj.value = obj.value.toUpperCase();
		if(obj.value!=""&&!expReg.test(obj.value.replace(/[_]/g,''))){
			alert(msj+ " debe ser alfanumerico.");
			obj.focus();
			return false;
		}
		return true;
	}
	
	/*Para Pasar a Mayusculas*/
	function mayus(obj) {
		obj.value = obj.value.toUpperCase();
		return true;
	}

	/* Redondea un mumero al proximo entero. */
	function rndUp(num){
		var iNum = parseInt(num);
		if(num>iNum)
			return iNum+1;
		return iNum;
	}

	function vmail(cad){
        if(!(cad==null || cad == ''))
            if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(cad))){
                alert("El correo electronico es incorrecto. ");
                return false;
            }
        return true;
	}

	/*Definicion del Objeto ELemento*/
	EL.prototype.nam = null;
	EL.prototype.typ = null;
	EL.prototype.msg = null;
	/*Definicion del Objeto ELemento,
	  los tipos son: 1 - input
	                 2 - select
	                 3 - radio
	  Nota : el 4 podrian ser aquellos opcionales pero relacionados como la cuenta auxiliar.
	  */
	function EL(nombre,tipo,mensaje){
		this.nam=nombre;
		this.typ=tipo;
		this.msj=mensaje;
	}
	/*Validador de existencia de los datos,
	  se pasan los elementos del documento y
	  un arreglo con los elementos a validar*/
	function evalOmision(doc,validators){
			var error = "";
			var resp  =  true;

			for(x in validators){
				val =  validators[x];
				//alert("Elemento:"+x+" nam:"+val.nam+" typ:"+val.typ+" msj:"+val.msj);
				if(val.typ==1){
					value = gtVlG(doc,val.nam);
					//alert("El valor recuperado es:"+value+":");
					if(value == null || value == ''){
						error = error+'Captura '+val.msj+'\n';
						resp =  false;
					}
				}else if(val.typ==2){
					value = gtVlG(doc,val.nam);
					//alert("El valor recuperado es:"+value+":");
					if(value == null || value == '0' || value== '' || value== '_'){
						error = error+'Selecciona '+val.msj+'\n';
						resp =  false;
					}
				}else if(val.typ==3){
					obj = doc.getElementsByName(val.nam);
					flag = true;
					for(i=0;i<obj.length;i++){
						if(obj[i].checked){
							flag=false;
						}
					}
					if(flag){
						error = error+'Selecciona '+val.msj+'\n';
						resp =  false;
					}
				}else if(val.typ==4){
					//alert("entrando");
					obj = doc.getElementsByName(val.nam);
					//alert("entrando el valor es " + obj[0].value);
					vcar(obj[0],val.msj);
					//alert("entrando " + bandera);
					if(bandera==false){return;}
				}
			}
			if(!resp) alert(error);
			return resp;
	}

	/*Obtiene el valor de un elemento del documento con el nombre*/
	function gtVlG(doc,na){
		arr = doc.getElementsByName(na);
		if(arr.length==1){return arr[0].value;}
		else{alert("err: "+arr.length+" objs encontrados");}
	}


	/*Valida RFC*/
	function vrfc(ctr){
		var regExp=/^([A-Z|—]{4})\d{6}([A-Z\w]{3})$/;
		ctr.value=ctr.value.toUpperCase();
		if(ctr.value!=""&&!regExp.test(ctr.value)){
			alert("Capture correctamente el RFC.");
			ctr.focus();
		}
	}

	function vrfcfm(ctr,tipo){
	if(tipo=="Fisica"){
		var regExp=/^([A-Z|—]{4})\d{6}([A-Z\w]{3})?$/;
		ctr.value=ctr.value.toUpperCase();
		if(ctr.value!=""&&!regExp.test(ctr.value)){
			alert("Capture correctamente el RFC.");
			ctr.focus();
			}
		}
		else if (tipo=="Moral"){
			var regExp=/^([A-Z|—]{3})\d{6}([A-Z\w]{3})?$/;
			ctr.value=ctr.value.toUpperCase();
			if(ctr.value!=""&&!regExp.test(ctr.value)){
				alert("Capture correctamente el RFC.");
				ctr.focus();
			}
		}
		else{
				alert("Seleccione tipo de persona");
				ctr.value="";
			}
	}	




	function calcular_edad(fecha){
	    //calculo la fecha de hoy
	    hoy=new Date();
	    //alert(hoy)

	    //calculo la fecha que recibo
	    //La descompongo en un array
	    var array_fecha = fecha.split("/");
	    //si el array no tiene tres partes, la fecha es incorrecta
	    if (array_fecha.length!=3)
	       return false;

	    //compruebo que los ano, mes, dia son correctos
	    var ano;
	    ano = parseInt(array_fecha[2]);
	    if (isNaN(ano) || array_fecha[2].length<4)
	       return false;

	    var mes;
	    mes = parseInt(array_fecha[1]);
	    if (isNaN(mes))
	       return false;

	    var dia;
	    dia = parseInt(array_fecha[0]);
	    if (isNaN(dia))
	       return false;


	    //si el ano de la fecha que recibo solo tiene 2 cifras hay que cambiarlo a 4
	    if (ano<=99)
	       ano +=1900;

	    //resto los anos de las dos fechas
	    edad=hoy.getYear()- ano - 1; //-1 porque no se si ha cumplido anos ya este ano

	    //si resto los meses y me da menor que 0 entonces no ha cumplido anos. Si da mayor si ha cumplido
	    if (hoy.getMonth() + 1 - mes < 0) //+ 1 porque los meses empiezan en 0
	       return edad;
	    if (hoy.getMonth() + 1 - mes > 0)
	       return edad+1;

	    //entonces es que eran iguales. miro los dias
	    //si resto los dias y me da menor que 0 entonces no ha cumplido a&ntildeacoute;os. Si da mayor o igual si ha cumplido
	    if (hoy.getUTCDate() - dia >= 0)
	       return edad + 1;

	    return edad;
	}
	//compara los datos principales de rfc contra el rfc



/*valida rfc fisicos*/
function ChecaRFC(r,f){
			var rfctst	= r.value;
			var fecha	= f.value;
			var limite 	= 9;
      		var array_fecha = fecha.split("/");      		
      		var anio = array_fecha[2]; 
      		var mes  = array_fecha[1];
      		var dia  = array_fecha[0];
   
      		var aniorfc = rfctst.substring(4,6);      		
      		var mesrfc  = rfctst.substring(6,8);    
      		var diarfc  = rfctst.substring(8,10);   
      		var anio1   = anio.substring(2,4);
      		
      if (rfctst.length >limite){        		
      
			      		if(anio1 != aniorfc){
					    	 //alert("El Anio no coinside en RFC");
						     return false;
					      }else{
					             if(mes != mesrfc){
			      		       		// alert("El Mes no coinside en RFC");
	       							 return false;
					      		 	}else{
					      		    	if(dia != diarfc){
					      		       		//alert("El Dia no coinside en RFC");
							       		    return false;
							      		 }
							      		 else{
							      		 return true;
							      		 }				      		 
					      		     }					      
					      }		               	
	   }
}

/*valida rfc morales*/
function ChecaRFCmoral(r,f){
			var rfctst	= r.value;
			var fecha	= f.value;
			var limite 	= 9;
      		var array_fecha = fecha.split("/");      		
      		var anio = array_fecha[2]; 
      		var mes  = array_fecha[1];
      		var dia  = array_fecha[0];
   
      		var aniorfc = rfctst.substring(3,5);      		
      		var mesrfc  = rfctst.substring(5,7);    
      		var diarfc  = rfctst.substring(7,9);   
      		var anio1   = anio.substring(2,4);
      		
      if (rfctst.length >limite){        		
      
			      		if(anio1 != aniorfc){
					    	 //alert("El Anio no coinside en RFC");
						     return false;
					      }else{
					             if(mes != mesrfc){
			      		       		 //alert("El Mes no coinside en RFC");
	       							 return false;
					      		 	}else{
					      		    	if(dia != diarfc){
					      		       		//alert("El Dia no coinside en RFC");
							       		    return false;
							      		 }
							      		 else{
							      		 return true;
							      		 }				      		 
					      		     }					      
					      }		               	
	   }
}

function formatearPesos(num){
	str=num.toString();
    var i=str.length-1;
    res="";
    fin="";
    if(str.lastIndexOf(".") > 0 ){k=str.lastIndexOf(".");fin=str.substring(k,i+1);i=k-1; } // se quita la parte decimal
    
    for(cont=0;i>=0;i--,cont++){
			res = str.charAt(i) +res;
			if(cont==2&&i>0){
				res = "," + res;
				cont=-1;
			}
		}
	return "$"+res+fin;
	}

/*valida que sean letras o espacios y con longitud minima de 3*/
	var bandera = false;
	function vnomBen(obj,msj){
	 	var cad = obj.value.toUpperCase();
		if(cad==""){
			bandera = true;
			return;
		}
		if(cad!=""||cad!=null){
			if(cad.length<3){
				alert(msj+" debe ser minimo de tres caracteres.");
					obj.focus();
					obj.select();
					bandera = false;
					return ;
			}
		//	var expReg = /^[A-Z|—|.]+(\s[A-Z|—|.]+)*$/;
		/*	if(!expReg.test(cad)){
				alert(msj+" debe ser alfabetico y sin acento.");
				obj.focus();
				obj.select();
				bandera = false;
				return ;
			}*/
		}
		bandera = true;
		obj.value = cad;
	}
	
	
	
/*Agregado por GGC*/
	function getRadioValueByName(name){
	   	var i ;
	   	var arr = document.getElementsByName(name);
	   	for (i=0;i<arr.length;i++){ 
	      	 if (arr[i].checked) 
	      		return arr[i].value; 
	   	} 
	   	return "";
	}
	
	function NumericPart(sText){
		var ValidChars = "0123456789.";
		var IsNumber=true;
		var Char;
		var numericPart ='';
		for (i = 0; i < sText.length && IsNumber == true; i++){ 
			Char = sText.charAt(i); 
			if (ValidChars.indexOf(Char) == -1){
				IsNumber = false;
			}else{
				numericPart += Char;
			}
		}
		return numericPart;
	}
	
	function setDivMaxSize(name, maxsize){
		var bodyDiv = document.getElementById(name);
		
		if(bodyDiv){
			var h = NumericPart(bodyDiv.clientHeight+'');
			if(h>maxsize){
				bodyDiv.style.height = maxsize+'px';
			}
		}
	}
	
	
	/* LOADING .... */	
/*
 * 	NO FUNCIONA PARA IE
	//Redefiniendo el metodo submit
		function newsubmit(event) {

    		var target = event ? event.target : this;
    		// do anything you like here
			showLoading();
			alert('showing');
		    // call real submit function
			this._submit();
 		}
		// capture the onsubmit event on all forms
		window.addEventListener('submit', newsubmit, true);

		// If a script calls someForm.submit(), the onsubmit event does not fire,
		// so we need to redefine the submit method of the HTMLFormElement class.
		HTMLFormElement.prototype._submit = HTMLFormElement.prototype.submit;
		HTMLFormElement.prototype.submit = newsubmit;
*/
	//Para mostrar la imagen de loading...		
	
		function getScrollXY() {
			var scrOfX = 0, scrOfY = 0;
			if( typeof( window.pageYOffset ) == 'number' ) {
			    //Netscape compliant
			    scrOfY = window.pageYOffset;
			    scrOfX = window.pageXOffset;
			} else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
			   //DOM compliant
			    scrOfY = document.body.scrollTop;
			    scrOfX = document.body.scrollLeft;
			} else if( document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
			    //IE6 standards compliant mode
			    scrOfY = document.documentElement.scrollTop;
			    scrOfX = document.documentElement.scrollLeft;
			}
			return [ scrOfX, scrOfY ];
		}
	
		var ld=(document.all);
	  	var ns4=document.layers;
		var ns6=document.getElementById&&!document.all;
	 	var ie4=document.all;
		
	 	function showLoading(flag){
	 		if(flag){
	 			
	 			//alert(getScrollXY());
	 			//Ocultando barra de desplazamiento
	 			
	 			document.body.style.overflow = 'hidden';
	 			
			  	if (ns4){
		 			ld=document.loading;
		 		}else if (ns6){
	 				ld=document.getElementById("loading").style;
			 	}else if (ie4){
	 				ld=document.all.loading.style;
	 			}
	 			if(ns4){
	 				ld.visibility="visible";
	 			}else if (ns6||ie4) {
	 				ld.display="block";
	 				//alert('IE');
		 			ld.top = getScrollXY()[1];
	 			}
	 			
	 		}
 			return flag;
	 	}
	 	
	 	function blockPant(flag){
	 		if(flag){
	 			document.getElementById('loadingImg').style.display='none';
	 			showLoading(flag);
	 		}
	 	}
	 	
	 	function unblockPant(flag){
	 		
	 		if(flag){
			  	if (ns4){
		 			ld=document.loading;
		 		}else if (ns6){
	 				ld=document.getElementById("loading").style;
			 	}else if (ie4){
	 				ld=document.all.loading.style;
	 			}
	 			if(ns4){
	 				ld.visibility="hidden";
	 			}else if (ns6||ie4) {
	 				ld.display="none";
	 			}
	 			document.getElementById('loadingImg').style.display='block';
	 		}
 			return flag;
	 	}
	
	//	Creando div q contiene la imagen de loading ....
		document.write('<div id="loading" style="position:absolute; height:98%; width:98%;left: 0;  text-align:center; display:none; background-color:#FFFFFF; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity: 0.5; z-index:100;">');
		document.write('<table align="center" style="height:100%">');
		document.write('<tr><td></td></tr>');
		document.write('</table></div>');
		
/* FIN LOADING .... */
		
/* Obtiene valores de un grupo de check*/
		function getCheckGroupVals(name){
			var ob = document.getElementsByName(name);
			//var selected = "";
			var selected = new Array();
			for (var i = 0; i < ob.length; i++){
				if (ob[ i ].checked){
					//selected+=ob[ i ].value+",";
					selected.push(ob[ i ].value);
				}
			}
			//return selected.substring(0,selected.length-1);
			return selected;
		}