<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- Тип документа -->
	<xsl:template name="doctitle">
		<font>
			<xsl:value-of select="$doctype"/>&#xA0;<xsl:value-of select="document/fields/vn"/>&#xA0;<xsl:value-of select="document/fields/dvn/@caption"/>&#xA0;<xsl:value-of select="document/fields/dvn"/>
		</font>
	</xsl:template>
	
	<xsl:template name="fields_tooltip">
		<script type="text/javascript">
			$(document).ready(function(){
				$(".td_editable[title],.textarea_editable[title]").tipTip({maxWidth: "300px", defaultPosition: "right", activation: "hover", delay:"200"});
			})
		</script>
	</xsl:template>
	
	<xsl:template name="htmlareaeditor">
		<script type="text/javascript">  
			$(function() {
        		//$("textarea").htmlarea(); 
	        	$("#txtDefaultHtmlArea").htmlarea(); // Initialize jHtmlArea's with all default values
           		$("#btnRemoveCustomHtmlArea").click(function() {
            		$("#txtCustomHtmlArea").htmlarea("dispose");
           		});
        	});
		</script>
	</xsl:template>

	<!-- Набор javascript библиотек -->
	<xsl:template name="cssandjs">
		<link type="text/css" rel="stylesheet" href="classic/css/main.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="classic/css/form.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="classic/css/actionbar.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="classic/css/dialogs.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="/SharedResources/jquery/css/smoothness/jquery-ui-1.8.20.custom.css"/>
		<link type="text/css" rel="Stylesheet" media="screen" href="/SharedResources/jquery/js/tiptip/tipTip.css"/>
		<link rel="Stylesheet" type="text/css" href="/SharedResources/jquery/js/jhtmlarea/style/jHtmlArea.css"/>
		<link type="text/css" rel="stylesheet" href="/SharedResources/jquery/js/hotnav/jquery.hotnav.css"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/jquery-1.4.2.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.core.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.effects.core.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.widget.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/jquery.ui.datepicker.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.mouse.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.slider.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.progressbar.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.autocomplete.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.draggable.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.position.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.dialog.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.tabs.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.button.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/tiptip/jquery.tipTip.js"/>
		<script type="text/javascript" src="classic/scripts/form.js?ver=3"/>
		<script type="text/javascript" src="classic/scripts/coord.js?ver=3"/>
		<script type="text/javascript" src="classic/scripts/dialogs.js?ver=3"/>
		<script type="text/javascript" src="classic/scripts/outline.js?ver=3"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/cookie/jquery.cookie.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/jhtmlarea/scripts/jHtmlArea-0.7.0.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/hotnav/jquery.hotkeys.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/hotnav/jquery.hotnav.js"/>
		<script>
			cancelcaption='<xsl:value-of select="document/captions/cancel/@caption"/>'
			docfilter='<xsl:value-of select="document/captions/docfilter/@caption"/>'
			changeviewcaption='<xsl:value-of select="document/captions/changeview/@caption"/>'
			receiverslistcaption='<xsl:value-of select="document/captions/receiverslist/@caption"/>'
			commentcaption='<xsl:value-of select="document/captions/commentcaption/@caption"/>'
			correspforacquaintance='<xsl:value-of select="document/captions/correspforacquaintance/@caption"/>'
			searchcaption='<xsl:value-of select="document/captions/searchcaption/@caption"/>'
			contributorscoord='<xsl:value-of select="document/captions/contributorscoord/@caption"/>'
			type='<xsl:value-of select="document/captions/type/@caption"/>'
			parcoord='<xsl:value-of select="document/captions/parcoord/@caption"/>'
			sercoord='<xsl:value-of select="document/captions/sercoord/@caption"/>'
			waittime='<xsl:value-of select="document/captions/waittime/@caption"/>'
			coordparam='<xsl:value-of select="document/captions/coordparam/@caption"/>'
			hours='<xsl:value-of select="document/captions/hours/@caption"/>'
			yescaption='<xsl:value-of select="document/captions/yescaption/@caption"/>'
			nocaption='<xsl:value-of select="document/captions/nocaption/@caption"/>'
			warning='<xsl:value-of select="document/captions/warning/@caption"/>'
			documentsavedcaption = '<xsl:value-of select="document/captions/documentsavedcaption/@caption"/>';
			documentmarkread = '<xsl:value-of select="document/captions/documentmarkread/@caption"/>';
			pleasewaitdocsave = '<xsl:value-of select="document/captions/pleasewaitdocsave/@caption"/>';
			docissign = '<xsl:value-of select="document/captions/docissign/@caption"/>';
			docisrejected = '<xsl:value-of select="document/captions/docisrejected/@caption"/>';
			dociscoordyou = '<xsl:value-of select="document/captions/dociscoordyou/@caption"/>';
			docisrejectedyou = '<xsl:value-of select="document/captions/docisrejectedyou/@caption"/>';
			lang='<xsl:value-of select="@lang"/>';
			redirectAfterSave = '<xsl:value-of select="history/entry[@type eq 'page'][last()]"/>&amp;page=<xsl:value-of select="document/@openfrompage"/>'
			showauthor = '<xsl:value-of select="document/captions/showauthor/@caption"/>';
			showrecipient = '<xsl:value-of select="document/captions/showrecipient/@caption"/>';
			noblockcoord = '<xsl:value-of select="document/captions/noblockcoord/@caption"/>';
			nochosenblocktodelete = '<xsl:value-of select="document/captions/nochosenblocktodelete/@caption"/>';
			successfullydeleted = '<xsl:value-of select="document/captions/successfullydeleted/@caption"/>';
			choosemember = '<xsl:value-of select="document/captions/choosemember/@caption"/>';
			choosevalue = '<xsl:value-of select="document/captions/choosevalue/@caption"/>';
			alreadychosen = '<xsl:value-of select="document/captions/alreadychosen/@caption"/>';
			isrecieverofsz = '<xsl:value-of select="document/captions/isrecieverofsz/@caption"/>';
			issignerofsz = '<xsl:value-of select="document/captions/issignerofsz/@caption"/>';
			hourss = '<xsl:value-of select="document/captions/hourss/@caption"/>';
			hours = '<xsl:value-of select="document/captions/hours/@caption"/>';
			day = '<xsl:value-of select="document/captions/day/@caption"/>';
			days = '<xsl:value-of select="document/captions/days/@caption"/>';
			saving = '<xsl:value-of select="document/captions/saving/@caption"/>';
			showfilename = '<xsl:value-of select="document/captions/showfilename/@caption"/>'; 
			addcomment = '<xsl:value-of select="document/captions/addcomment/@caption"/>'; 
			removedfromcontrol = '<xsl:value-of select="document/captions/removedfromcontrol/@caption"/>';
			attention = '<xsl:value-of select="document/captions/attention/@caption"/>';
			add_comment='<xsl:value-of select="document/captions/add_comment/@caption"/>';
			unlimited ='<xsl:value-of select="document/captions/unlimited/@caption"/>';
			newcoord= '<xsl:value-of select="document/captions/newcoord/@caption"/>';
			delete_file= '<xsl:value-of select="document/captions/delete_file/@caption"/>';
			onlynumber= '<xsl:value-of select="document/captions/onlynumber/@caption"/>';
			$(function(){
				$("#tabs").tabs();
				$("button").button();
			});
    	</script>
	</xsl:template>

	<!-- договор на доверительное управление акциями -->
	<xsl:template name="trustmanagements_contract_shareblocks">

	<style type="text/css">
		@page { }
		table { border-collapse:collapse; border-spacing:0; empty-cells:show }
		h1, h2, h3, h4, h5, h6 { clear:both }
		ol, ul { margin:0; padding:0;}
		li { list-style: none; margin:0; padding:0;}
		<!-- "li span.odfLiEnd" - IE 7 issue -->
		li span. { clear: both; line-height:0; width:0; height:0; margin:0;
		padding:0; }
		span.footnodeNumber { padding-right:1em; }
		span.annotation_style_by_filter { font-size:95%; font-family:Arial; background-color:#fff000;
		margin:0; border:0; padding:0; }
		* { margin:0;}
		.P1 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%; text-align:center
		! important; }
		.P2 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%; text-align:right
		! important; }
		.P3 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%;
		text-align:justify ! important; }
		.P4 { font-size:12pt; font-family:Times New Roman; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%; text-align:center
		! important; }
		.P5 { font-size:12pt; font-family:Times New Roman; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%;
		text-align:justify ! important; }
		.P6 { font-size:10pt; font-family:Times New Roman; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%; }
		.P7 { font-size:10pt; font-family:Times New Roman; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%;
		text-align:justify ! important; }
		.P8 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
		margin-top:0in; margin-bottom:0in; line-height:100%; text-align:center
		! important; }
		.T1 { font-family:Times New Roman; font-size:12pt; }
		.T2 { font-family:Times New Roman; font-size:12pt; }
		.T3 { font-family:Times New Roman; font-size:10pt; }
		.T4 { font-family:Times New Roman; font-size:10pt; }
		<!-- ODF styles with no properties representable as CSS -->
		{ }
	</style>
		<p class="P8">
			<span class="T1">ДОГОВОР</span>
		</p>
		<p class="P1">
			<span class="T1">ДОВЕРИТЕЛЬНОГО УПРАВЛЕНИЯ ПАКЕТОМ АКЦИЙ </span>
		</p>
		<p class="P4"> </p>
		<p class="P1">
			<span class="T1">______________________________________________________ </span>
		</p>
		<p class="P1">
			<span class="T3">(наименование объекта)</span>
		</p>
		<p class="P1">
			<span class="T1">_______________________________</span>
			<span class="T2">_________</span>
			<span class="T1">_______________ </span>
		</p>
		<p class="P1">
			<span class="T3">(наименование учреждения)</span>
		</p>
		<p class="P6"> </p>
		<p class="P5"> </p>
		<p class="P2">
			<span class="T1">    г . ________________                    
				 "__"_______________20</span>
			<span class="T2">1</span>
			<span class="T1">__ г .</span>
		</p>
		<p class="P5"> </p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1"> _________________________________________________________________________</span>
		</p>
		<p class="P1">
			<span class="T3">(наименование организации, организационно-правовая
				форма)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">    в лице
				__________________________________________________________________,  дальнейшем </span>
		</p>
		<p class="P1">
			<span class="T3">(должность, Ф.И.О руководителя)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">"Учредитель    управления",  действующего на основании
				____________________________________,</span>
		</p>
		<p class="P3">
			<span class="T3">                               </span>
			<span class="T4">                </span>
			<span class="T3">  (наименование учредительного документа, устава,
				положения)</span>
		</p>
		<p class="P5"></p>
		<p class="P3">
			<span class="T1">     
				 </span>
		</p>
		<p class="P3">
			<span class="T1">  именуемое в являющийся    собственником   акций, с одной стороны, и
				 _________________________________________________, лицензия на право совершения операций</span>
		</p>
		<p class="P3">
			<span class="T3">                                                   
				           </span>
			<span class="T4">                  </span>
			<span class="T3">(наименование организации)</span>
		</p>
		<p class="P3">
			<span class="T1">  с ценными
				бумагами Nо.________________  от ___________,</span>
		</p>
		<p class="P3">
			<span class="T1">    в лице
				_____________________________________________________,  действующего</span>
		</p>
		<p class="P3">
			<span class="T3">                                                   
				            </span>
			<span class="T4">            </span>
			<span class="T3"> (должность, Ф.И.О руководителя)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">    на основании
				______________________________________, именуемое в дальнейшем</span>
		</p>
		<p class="P3">
			<span class="T3">                                (наименование учредительного документа, устава, положения)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">"Доверительный управляющий",    с
				другой стороны,  заключили Договор о нижеследующем:</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1" style="margin-left:280px">1. ПРЕДМЕТ ДОГОВОРА</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         1.1. Учредитель  управления  передает
				принадлежащий ему на праве  собственности пакет акций в доверительное
				 управление  Доверительному управляющему.</span>
		</p>
		<p class="P3">
			<span class="T1">         1.2. Выгодоприобретателем  по   настоящему
				  договору   является</span>
		</p>
		<p class="P3">
			<span class="T1"> 
				  Учредитель доверительного управления - собственник акций -
				_____________________________________________________________________________</span>
		</p>
		<p class="P1">
			<span class="T3">(должность, Ф.И.О руководителя)</span>
		</p>
		<p class="P3">
			<span class="T1">         1.З. Характеристика   передаваемых  в
				 доверительное  управление</span>
		</p>
		<p class="P3">
			<span class="T1">    акций:</span>
		</p>
		<p class="P3">
			<span class="T1">         Эмитент:
				________________________________________________________________;</span>
		</p>
		<p class="P1">
			<span class="T3">(наименование юр.лица/гос.учреждения)</span>
		</p>
		<p class="P3">
			<span class="T1">         Вид акций
				_______________________________________________________________;</span>
		</p>
		<p class="P3">
			<span class="T1">         Номинальная стоимость одной акции
				 _______________тенге;</span>
		</p>
		<p class="P3">
			<span class="T1">         Количество ______(________________________)
				акций, общей номинальной</span>
		</p>
		<p class="P3">
			<span class="T1">    стоимостью _________(________________________)
				тенге;</span>
		</p>
		<p class="P3">
			<span class="T1">         Форма акций -
				___________________________________;</span>
		</p>
		<p class="P3">
			<span class="T1">         Реестродержатель:
				________________________________________________________.</span>
		</p>
		<p class="P1">
			<span class="T3">(наименование юр.лица/гос.учреждения)</span>
		</p>
		<p class="P5"> </p>
		<p class="P5"> </p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         1.4. Право собственности на указанный пакет
				акций подтверждается  выпиской из реестра акционеров</span>
		</p>
		<p class="P3">
			<span class="T1">     от
				_____________________,  выданной Реестродержателем.</span>
		</p>
		<p class="P3">
			<span class="T1">                    (дата)</span>
		</p>
		<p class="P3">
			<span class="T1">         1.5. Доверительный    управляющий  
				 владеет   и   распоряжается переданным  пакетом акций  исключительно в
				интересах собственника.</span>
		</p>
		<p class="P3">
			<span class="T1">         1.6. Передача  пакета акций в доверительное
				управление не влечет  перехода права  собственности на него к
				доверительному управляющему.</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1" style="margin-left:220px">2. ДОВЕРИТЕЛЬНЫЙ ХАРАКТЕР ДОГОВОРА</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         2.I. Заключая настоящий  Договор,
				 Учредитель  оказывает  особое доверие  Доверительному управляющему как лицу,
				 способному наилучшим образом распорядится принадлежащими Учредителю
				акциями.</span>
		</p>
		<p class="P3">
			<span class="T1">         2.2. Доверительный   управляющий,   при  
				осуществлении  прав  и исполнении обязанностей, вытекающих из  Договора,
				 обязан  действовать  добросовестно   и   тем  способом,  который является  наилучшим  для  интересов Учредителя управления
				(выгодоприобретателя).</span>
		</p>
		<p class="P3">
			<span class="T1">         2.З. Доверительный   управляющий   имеет  
				исключительное  право  определять,  какой  способ  его  действия  в
				 отношении  управляемого имущества  является наилучшим  с  точки зрения интересов Учредителя  управления (выгодоприобретателя).</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1" style="margin-left:100px">3. CPOK ДОГОВОРА И УСЛОВИЯ ЕГО
				ДОСРОЧНОГО ПРЕКРАЩЕНИЯ</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         3.I. Настоящий Договор заключен на cpoк до
				___________(________________)</span>
		</p>
		<p class="P3">
			<span class="T3">                                                   
				                                                          </span>
			<span class="T4">                           </span>
			<span class="T3"> (дата)</span>
		</p>
		<p class="P5"></p>
		<p class="P3">
			<span class="T1">         3.2. Права   и   обязанности  
				Доверительного   управляющего  по правлению  пакетом  акций  у  возникают  с  
				момента   передачи   акций Доверительному  управляющему. Передача  акций осуществляется  путем внесения соответствующей записи  в  реестр акционеров  на  основании
			передаточного  распоряжения,  сделанного Учредителем  доверительного  управления.Передаточное распоряжение составляется    в    двух  экземплярах,  один  из  которых  вручается
			Реестродержателю вместе с копией настоящего Договора, а второй - Доверительному  управляющему.
			</span>
		</p>
		<p class="P3">
			<span class="T1">3.З. Договор  может  быть  прекращен  до
				 истечения указанного в пункте 3.I. cpoка при возникновении следующих
				обстоятельств:</span>
		</p>
		<p class="P3">
			<span class="T1">         а) в     случае     признания    
				Доверительного    управляющего несостоятельным   (банкротом) в соответствии  с действующим законодательством;</span>
		</p>
		<p class="P3">
			<span class="T1">         б) в  случае  ликвидации  Доверительного
				 управляющего.</span>
		</p>
		<p class="P3">
			<span class="T1">         в) в случае совершения Доверительным
				управляющим действий,  явно направленных во вред интересам Учредителя;</span>
		</p>
		<p class="P3">
			<span class="T1">         г) при   отказе   Доверительного  
				управляющего  или  Учредителя управления от  осуществления  доверительного
				 управления  в  связи  с  невозможностью  для  Доверительного
				 управляющего  лично осуществлять доверительное управление имуществом;</span>
		</p>
		<p class="P3">
			<span class="T1">         д) по соглашению сторон;</span>
		</p>
		<p class="P3">
			<span class="T1">         е) по   другим   основаниям,   если   такие
				  основания    будут предусмотрены   законом,   настоящим  Договором
				 или  дополнительными соглашениями к нему.</span>
		</p>
		<p class="P3">
			<span class="T1">         3.4. Учредитель  управления  вправе
				 отказаться  в  любое  время  от Договора  доверительного   управления    при
				   условии    выплаты  Доверительному управляющему причитающегося по
				Договору вознаграждения  за весь период действия Договора.</span>
		</p>
		<p class="P3">
			<span class="T1">         3.5. При   отказе   одной  стороны  от
				 Договора  доверительного управления другая сторона должна быть уведомлена
				не менее чем за  три месяца до прекращения Договора.</span>
		</p>
		<p class="P3">
			<span class="T1">         3.6. При отсутствии заявления  одной  из
				 сторон  о  прекращении  договора по окончании cpoка его действия,  он
				считается продленным на тот же cpoк и на тех же условиях.</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1" style="margin-left:100px">4. ОБЯЗАННОСТИ ДОВЕРИТЕЛЬНОГО
				УПРАВЛЯЮЩЕГО</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         4.I. Доверительный управляющий обязан:</span>
		</p>
		<p class="P3">
			<span class="T1">         а) осуществлять  права,  на  участие  в
				 управлении  делами   __________________________________________, именуемое в
				дальнейшем Общество,</span>
		</p>
		<p class="P3">
			<span class="T3">    (наименование учреждения (акционерного
				общества))</span>
		</p>
		<p class="P5"></p>
		<p class="P3">
			<span class="T1">    предоставляемые владельцу  акций  законом,  в
				том числе участвовать в работе Общего собрания Общества,  Совета
				директоров  (наблюдательного совета), Ревизионной комиссии (ревизора) и
				других органах Общества;</span>
		</p>
		<p class="P3">
			<span class="T1">         б) получать дивиденды по акциям, а в случае
				ликвидации Общества, получить причитающуюся  собственнику  акций
				 долю  в имуществе или ее денежный эквивалент;</span>
		</p>
		<p class="P3">
			<span class="T1">         в) перечислять   Учредителю   суммы,  
				полученные   в   качестве дивидендов  по  Акциям  Общества,  и  другие
				 доходы,  полученные   с использованием   ценных   бумаг,   за   вычетом
				 сумм,  причитающихся Доверительному управляющему в виде компенсации
				расходов и в  качестве вознаграждения, предусмотренного Договором;</span>
		</p>
		<p class="P3">
			<span class="T1">         г) учитывать имущество, переданное в
				доверительное управление на отдельном балансе;</span>
		</p>
		<p class="P3">
			<span class="T1">         д) для  расчетов  по  деятельности,
				 связанной  с  доверительным управлением, открыть банковский счет;</span>
		</p>
		<p class="P3">
			<span class="T1">         е) ежеквартально направлять Учредителю
				отчет и баланс,  а  также  справку  о начислении выплаченных дивидендов по
				акциям,  переданным в доверительное управление;</span>
		</p>
		<p class="P3">
			<span class="T1">         ж) обеспечить  проверку  независимым
				 внешним аудитором годового баланса и счетов прибылей и убытков
				Доверительного управляющего;</span>
		</p>
		<p class="P3">
			<span class="T1">         з) выполнять Особые условия доверительного
				управления,  если они будут установлены Учредителем.</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">                              5. ОСОБЫЕ УСЛОВИЯ</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         5.I. Стороны вправе установить особые
				условия управления Пакетом  акций.</span>
		</p>
		<p class="P3">
			<span class="T1">         5.2. Особые условия устанавливаются
				дополнительным соглашением.</span>
		</p>
		<p class="P3">
			<span class="T1">         5.З. Доверительный управляющий вправе
				отказаться в одностороннем порядке от настоящего Договора,  если особые
				 условия  доверительного управления, предложенные Учредителем, будут
				существенно отличаться от условий  настоящего  Договора  и  будут  для
				 явно  невыгодными  либо существенно   затруднят   выполнение   им  
				своих   обязанностей   по доверительному управлению, в том числе в части
				получения дивидендов и других доходов.</span>
		</p>
		<p class="P3">
			<span class="T1">                                                   
				                                          Статья 7. Коллизии
				интересов</span>
		</p>
		<p class="P3">
			<span class="T1">                         6. ПРАВО НА ВОЗНАГРАЖДЕНИЕ</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         6.I. Доверительный   управляющий   имеет  
				право   на  получение вознаграждения в следующих размерах: - _____________(_______________) процентов
				от сумм, полученных в виде (количество)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">    дивидендов по Акциям и в виде других доходов, в
				том числе от передачи  акций в залог в качестве обеспечения исполнения
				обязательств,  выдачи поручительств под акции и т.п.; - кроме того, Доверительный управляющий
				имеет право на получение ежегодного вознаграждения в размере
				_____________(____________) тенге, сумма  за осуществление права на участие в управлении
				делами Общества.</span>
		</p>
		<p class="P3">
			<span class="T1">         6.2. Кроме  того,  Доверительный
				 управляющий  имеет  право   на получение  вознаграждения в виде опциона не
				более чем на ______________ акций Общества,  реализуемого после прекращения
				 Договора  в  случае, если  все  его  условия  были  добросовестно
				 выполнены Доверительным управляющим.</span>
		</p>
		<p class="P3">
			<span class="T1">         6.З. Доверительный управляющий имеет право
				на полное  возмещение  понесенных  им необходимых расходов,  связанных
				с управлением Пакетом акций, за счет доходов от использования этих
				акций.</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">                7. ПРАВА И ОБЯЗАННОСТИ УЧРЕДИТЕЛЯ
				УПРАВЛЕНИЯ</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         7.I. Учредитель управления имеет право:</span>
		</p>
		<p class="P3">
			<span class="T1">         а) проверять  исполнение  Договора
				 Доверительным  управляющим и получать  все  сведения  и   отчеты,  
				представляемые   Доверительным  управляющим  государственным  контролирующим
				органам в соответствии с действующим законодательством;</span>
		</p>
		<p class="P3">
			<span class="T1">         б) сообщать  государственным  органам  и
				 суду любые сведения об управлении Пакетом акций в случаях,
				установленных законом;</span>
		</p>
		<p class="P3">
			<span class="T1">         в) предъявлять Доверительному управляющему
				в случае нарушения им настоящего Договора иски с целью вынесения судом
				решений, обязывающих  его  к  исполнению  Договора и устранению
				неблагоприятных последствий допущенных им нарушений;</span>
		</p>
		<p class="P3">
			<span class="T1">         г) в   случае   утраты  доверия  к
				 Доверительному  управляющему предъявлять ему иск о досрочном прекращении
				настоящего Договора.</span>
		</p>
		<p class="P3">
			<span class="T1">         д) получать дивиденды по переданным в
				управление акциям и другие доходы,  за   вычетом   сумм,   подлежащих  
				выплате   Доверительному управляющему   в   виде  вознаграждения  и
				 компенсации  расходов  по доверительному управлению.</span>
		</p>
		<p class="P3">
			<span class="T1">         7.2. Учредитель   управления   обязан  
				передать  Доверительному управляющему все документы и  сведения,
				 необходимые  для  выполнения обязанностей и осуществления прав по настоящему
				Договору.</span>
		</p>
		<p class="P3">
			<span class="T1">         7.З. Средства,    передаваемые    
				Доверительным     управляющим</span>
		</p>
		<p class="P3">
			<span class="T1"> Учредителю, перечисляются в следующем порядке:
				________________________________________________________</span>
			<br />
		</p>
		<p class="P3">
			<span class="T3">     (указывается: периодичность выплат, номера
				счетов и др. требования)</span>
		</p>
		<p class="P7"> </p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">                          8. ОТВЕТСТВЕННОСТЬ СТОРОН</span>
		</p>
		<p class="P3">
			<span class="T1">         8.I. По    обязательствам,    возникающим  
				 у    Доверительного управляющего   в   связи   с   исполнением  
				Договора,  Доверительный управляющий  несет  при  недостаточности
				 имущества,  переданного   в Доверительное   управление,   неограниченную  
				ответственность  своим имуществом и имущественными правами.s</span>
		</p>
		<p class="P3">
			<span class="T1">         8.2. Доверительный  управляющий  несет
				 ответственность за любой вред или ущерб,  причиненный им интересам
				Учредителя  управления  при управлении   пакетом   акций,   за   исключением
				 вреда  или  ущерба, причиненного действием непреодолимой силы либо
				действиями  Учредителя управления.</span>
		</p>
		<p class="P3">
			<span class="T1">         8.З. Долги по обязательствам,  возникающим
				в связи с управлением пакетом  акций,  погашаются  за  счет этого
				пакета и полученных за по  нему доходов.</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">                            9. РАЗРЕШЕНИЕ СПОРОВ</span>
		</p>
		<p class="P3">
			<span class="T1">         9.1. Споры,  возникающие  из  настоящего
				 Договора,  разрешаются Арбитражным судом, в установленном Законом
				порядке.</span>
		</p>
		<p class="P5"></p>
		<p class="P3">
			<span class="T1">                         10. ДОПОЛНИТЕЛЬНЫЕ УСЛОВИЯ</span>
		</p>
		<p class="P3">
			<span class="T1">         10.1. Настоящий Договор имеет следующие
				 приложения,  являющиеся его неотъемлемыми частями: - выписку из Реестра акционеров
				_________________________________,</span>
		</p>
		<p class="P3">
			<span class="T1">                                                   
				                   </span>
			<span class="T3">  (наименование учреждения)</span>
		</p>
		<p class="P3">
			<span class="T1">    подтверждающую права собственности Учредителя управления; - распоряжение о передаче пакета акций
				Общества в  доверительное управление Доверительному управляющему.</span>
		</p>
		<p class="P3">
			<span class="T1">         10.2. Настоящий Договор заключен  в  двух экземплярах,  имеющих  равную  юридическую  силу  и  хранящихся  у
				 Учредителя  управления и Доверительного управляющего.</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">              11. ПОЧТОВЫЕ АДРЕСА И БАНКОВСКИЕ
				РЕКВИЗИТЫ СТОРОН</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         Учредитель управления:
				________________________________________</span>
		</p>
		<p class="P3">
			<span class="T3">                                                   
				                    (юридический адрес, расчетный счет банка)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">         Доверительный управляющий:
				___________________________________</span>
		</p>
		<p class="P3">
			<span class="T3">                                                   
				                   (юридический адрес, расчетный счет банка)</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">                     ПОДПИСИ СТОРОН:</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">            УЧРЕДИТЕЛЬ                            
				ДОВЕРИТЕЛЬНЫЙ</span>
		</p>
		<p class="P3">
			<span class="T1">            УПРАВЛЕНИЯ                              
				УПРАВЛЯЮЩИЙ</span>
		</p>
		<p class="P5"> </p>
		<p class="P3">
			<span class="T1">      ____________________            
				_________________________</span>
		</p>
		<p class="P3">
			<span class="T1">                     M.  П.                        
				                           M.  П.</span>
		</p>
</xsl:template>
	<!-- договор на доверительное управление -->
	<xsl:template name="trustmanagements_contract">
		<style type="text/css">
			@page { }
			table { border-collapse:collapse; border-spacing:0; empty-cells:show }
			h1, h2, h3, h4, h5, h6 { clear:both }
			ol, ul { margin:0; padding:0;}
			li { list-style: none; margin:0; padding:0;}
			<!-- "li span.odfLiEnd" - IE 7 issue -->
			li span. { clear: both; line-height:0; width:0; height:0; margin:0;
			padding:0; }
			span.footnodeNumber { padding-right:1em; }
			span.annotation_style_by_filter { font-size:95%; font-family:Arial; background-color:#fff000;
			margin:0; border:0; padding:0; }
			* { margin:0;}
			.P1 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
			margin-top:0in; margin-bottom:0in; line-height:100%; text-align:center
			! important; }
			.P10 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:justify ! important; }
			.P11 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:justify ! important; }
			.P12 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			}
			.P13 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			}
			.P14 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			text-align:center ! important; }
			.P15 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			text-align:justify ! important; }
			.P16 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			text-align:justify ! important; }
			.P17 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			text-align:justify ! important; }
			.P18 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			text-align:justify ! important; }
			.P19 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb; }
			.P2 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
			margin-top:0in; margin-bottom:0in; line-height:100%;
			text-align:justify ! important; }
			.P20 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:center ! important;
			text-indent:0.3937in; }
			.P21 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0.4929in; margin-right:0in; text-align:center ! important;
			text-indent:0in; }
			.P22 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0.4929in; margin-right:0in; text-indent:0in; }
			.P23 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.4917in; }
			.P24 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.4917in; }
			.P25 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P26 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P27 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P28 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P29 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P3 { font-size:12pt; font-family:Times New Roman; writing-mode:lr-tb;
			margin-top:0in; margin-bottom:0in; line-height:100%;
			text-align:justify ! important; }
			.P30 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P31 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P32 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.5in; }
			.P33 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0in; }
			.P34 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:center ! important;
			text-indent:0in; }
			.P35 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:center ! important;
			text-indent:0in; }
			.P36 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.2957in; }
			.P37 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0in; margin-right:0in; text-align:justify ! important;
			text-indent:0.2957in; }
			.P38 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			margin-left:0.25in; margin-right:0in; text-align:justify ! important;
			text-indent:0.25in; }
			.P39 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0.9929in; margin-right:0in; text-indent:0in; }
			.P4 { font-size:12pt; font-family:Liberation Serif; writing-mode:lr-tb;
			margin-top:0in; margin-bottom:0in; line-height:100%; text-align:center
			! important; }
			.P40 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Times New Roman; writing-mode:lr-tb;
			margin-left:0.7429in; margin-right:0in; text-align:justify !
			important; text-indent:0in; }
			.P5 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:center ! important; }
			.P6 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:center ! important; }
			.P7 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:center ! important; }
			.P8 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:center ! important; }
			.P9 { font-size:12pt; line-height:100%; margin-bottom:0.0193in;
			margin-top:0.0193in; font-family:Calibri; writing-mode:lr-tb;
			text-align:justify ! important; }
			.Table1 { width:6.7924in; margin-left:-0.075in; writing-mode:lr-tb; }
			.Table1_A1 { padding-left:0.075in; padding-right:0.075in;
			padding-top:0in; padding-bottom:0in; border-width:0.0175cm;
			border-style:solid; border-color:#000001; }
			.Table1_A { width:3.4444in; }
			.Table1_B { width:3.3472in; }
			.T1 { font-family:Times New Roman; font-size:12pt; }
			.T2 { font-family:Times New Roman; }
			.T3 { font-family:Times New Roman; }
			.T4 { font-family:Times New Roman; font-weight:bold; }
			.T5 { font-family:Times New Roman; font-weight:bold; }
			.T6 { font-family:Times New Roman;}
			<!-- ODF styles with no properties representable as CSS -->
			.Table1.1 { }
		</style>
	<p class="P4">
		<span class="T1">   ТИПОВАЯ ФОРМА</span>
	</p>
	<p class="P3"> </p>
	<p class="P5">
		<span class="T3">ДОГОВОР ДОВЕРИТЕЛЬНОГО УПРАВЛЕНИЯ ИМУЩЕСТВОМ</span>
	</p>
	<p class="P3"> </p>
	<p class="P3"> </p>
	<p class="P1">
		<span class="T1">________________________________________________</span>
	</p>
	<p class="P3"> </p>
	<p class="P1">
		<span class="T1">(наименование объекта)</span>
	</p>
	<p class="P3"> </p>
	<p class="P1">
		<span class="T1">г. ___________     N ___________       " ___ "
			_________ 20 __ г.</span>
	</p>
	<p class="P3"> </p>
	<p class="P9">
		<span class="T5">__________________________________________</span>
		<span class="T3">(наименование учреждения)</span>
		<span class="T2">,
			именуемое в дальнейшем «Учредитель доверительного управления», в лице
			______________________________________________________________(Ф.И.О.,
			должность руководителя), действующего на основании
			____________________________________(наименование, дата, номер
			учредительного документа), с одной стороны, и
			_______________________________________(наименование учреждения),
			именуемое в дальнейшем «Доверительный управляющий», в лице  </span>
		<span class="T6">______________________________________________________________(Ф.И.О.,
			должность руководителя)</span>
		<span class="T2">,
			 действующего на основании _________________________(наименование
			учредительного документа), с другой стороны, далее совместно именуемые
			«Стороны», а каждый в отдельности «Сторона», принимая во внимание Закон
			Республики Казахстан от 1 марта 2011 года «О государственном
			имуществе»,
			Правила передачи коммунального имущества в доверительное управление,
			утвержденные постановлением акимата города Алматы от 17 января 2012
			года
			№1/29, Постановление акимата города Алматы от 7 марта 2013 года № 1/169
			«О некоторых вопросах коммунальной собственности города Алматы»,
			заключили настоящий договор доверительного управления имуществом
			(далее –
			«Договор») о нижеследующем.</span>
	</p>
	<p class="P20"> </p>
	<p class="P21">
		<span class="T5">Предмет Договора</span>
	</p>
	<p class="P12"> </p>
	<p class="P23">
		<span class="T2">1.1. 
			      Учредитель доверительного управления передает Доверительному
			управляющему недвижимое имущество, указанное в Приложении №1 к
			настоящему Договору, именуемое в дальнейшем «Имущество», в
			доверительное
			управление без права последующего выкупа, а Доверительный  управляющий
			обязуется осуществлять управление Имуществом в интересах Учредителя
			доверительного управления, который выступает выгодоприобретателем по
			настоящему Договору.</span>
	</p>
	<p class="P23">
		<span class="T2">1.2.     
			  Имущество передается в доверительное управление Доверительному
			управляющему в порядке и на условиях, предусмотренных
			законодательством
			Республики Казахстан и настоящим Договором.</span>
	</p>
	<p class="P26">
		<span class="T2">1.3. 
			      Доверительный управляющий осуществляет доверительное управление
			Имуществом без права отчуждения и передачи его в залог.</span>
	</p>
	<p class="P27">
		<span class="T2">1.4. 
			      Основанием, удостоверяющим право Доверительного управляющего на
			осуществление доверительного управления Имуществом, является
			настоящий
			Договор.</span>
	</p>
	<p class="P10">
		<span class="T2">        1.5.       
			Учредитель доверительного управления подтверждает, что Имущество на дату
			его передачи Доверительному управляющему:</span>
	</p>
	<ol>
		<li>
			<p class="P33" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">1)</span>
				<span class="T2">не находится в залоге;</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
		<li>
			<p class="P33" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">2)</span>
				<span class="T2">не обременено правами третьих лиц;</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
		<li>
			<p class="P33" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">3)</span>
				<span class="T2">не выставлено на продажу.</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P27">
		<span class="T2">1.6.        Передача Имущества в доверительное
			управление не влечет перехода права собственности к Доверительному
			управляющему.</span>
	</p>
	<p class="P26">
		<span class="T2">1.7. 
			      Права и обязанности Доверительного управляющего по
			доверительному
			управлению Имуществом возникают с момента передачи Имущества
			Доверительному управляющему. Передача Имущества осуществляется путем
			составления акта приема-передачи в течение 5 (пяти) рабочих дней с
			момента подписания настоящего Договора.</span>
	</p>
	<p class="P37"> </p>
	<ol>
		<li>
			<p class="P35" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">1.</span>
				<span class="T5">Права и обязанности сторон</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P13"> </p>
	<p class="P22">
		<span class="T4">2.1. Учредитель доверительного управления имеет
			право:</span>
	</p>
	<p class="P28">
		<span class="T2">1) получать информацию (отчет) о деятельности
			Доверительного управляющего по управлению Имуществом по письменному
			запросу;</span>
	</p>
	<p class="P28">
		<span class="T2">2)
			не вмешиваясь в деятельность Доверительного управляющего,
			контролировать выполнение обязательств Доверительного управляющего по
			настоящему Договору, в том числе путем проведения мониторинга
			эффективности управления Имуществом, заслушивания отчета
			Доверительного
			управляющего по выполнению обязательств по Договору;</span>
	</p>
	<p class="P29">
		<span class="T2">3) совершать иные действия, предусмотренные
			законодательством Республики        Казахстан.</span>
	</p>
	<p class="P11">
		<span class="T2">        </span>
		<span class="T4">2.2. Доверительный управляющий имеет право:</span>
	</p>
	<p class="P38">
		<span class="T2">1) 
			      совершать в отношении переданного в доверительное управление
			Имущества юридические и фактические действия в интересах Учредителя;</span>
	</p>
	<p class="P38">
		<span class="T2">2) 
			      на возмещение необходимых расходов, произведенных им при
			доверительном управлении Имуществом, только за счет доходов от
			использования Имущества, переданного ему в доверительное управление;</span>
	</p>
	<p class="P38">
		<span class="T2">3) 
			      осуществлять иные права, за исключением права на вознаграждение,
			предусмотренные законодательством Республики Казахстан, с учетом
			ограничений, установленных настоящим Договором.</span>
	</p>
	<p class="P23">
		<span class="T4">2.3. Учредитель доверительного управления обязан:</span>
	</p>
	<p class="P25">
		<span class="T2">1)        передать Имущество Доверительному
			управляющему в сроки предусмотренные п. 1.7 настоящего Договора;</span>
	</p>
	<p class="P25">
		<span class="T2">2)        передать Доверительному управляющему
			необходимые документы для осуществления его обязанностей по
			настоящему Договору;</span>
	</p>
	<p class="P25">
		<span class="T2">3) 
			      в течение срока действия настоящего Договора без уведомления
			Доверительного управляющего не принимать решений о передаче Имущества
			в
			доверительное управление третьим лицам;</span>
	</p>
	<p class="P25">
		<span class="T2">4)        не передавать Имущество в залог, не
			обременять правами третьих лиц в течение срока действия настоящего
			Договора.</span>
	</p>
	<p class="P25">
		<span class="T4">2.4. Доверительный управляющий обязан:</span>
	</p>
	<p class="P25">
		<span class="T2">1)        осуществлять эффективное управление
			Имуществом; </span>
	</p>
	<p class="P25">
		<span class="T2">2)        обеспечить сохранность Имущества;</span>
	</p>
	<p class="P25">
		<span class="T2">3) 
			      совершать сделки с переданным в доверительное управление
			Имуществом от своего имени, указывая при этом, что он действует в
			качестве Доверительного управляющего;</span>
	</p>
	<p class="P29">
		<span class="T2">4) 
			      получить все разрешительные документы от уполномоченных
			государственных органов, которые являются необходимыми в соответствии
			с
			законодательством Республики Казахстан;</span>
	</p>
	<p class="P29">
		<span class="T2">5) 
			      не совершать любые юридические и фактические действия, влекущие
			за собой фактическое отчуждение Имущества, в том числе передачу его в
			залог;</span>
	</p>
	<p class="P29">
		<span class="T2">6)        возмещать
			Учредителю доверительного управления убытки, причиненные вследствие
			ненадлежащего исполнения им настоящего Договора;</span>
	</p>
	<p class="P25">
		<span class="T2">7) 
			      исполнять обязанности, возникающие в результате действий по
			доверительному управлению, в целях надлежащего исполнения настоящего
			Договора;</span>
	</p>
	<p class="P25">
		<span class="T2">8)        нести все
			расходы по налогам и иным обязательным платежам в бюджет в отношении
			передаваемого Имущества с предоставлением подтверждающих документов
			до
			окончания срока действия настоящего Договора;</span>
	</p>
	<p class="P25">
		<span class="T2">9)        обособлять переданное ему в доверительное
			управление Имущество от другого своего имущества;</span>
	</p>
	<p class="P25">
		<span class="T2">10)        отражать преданное в доверительное
			управление Имущество на отдельном балансе и по нему вести
			самостоятельный учет;</span>
	</p>
	<p class="P25">
		<span class="T2">11)        ежеквартально предоставлять Учредителю
			доверительного управления отчет о своей деятельности; </span>
	</p>
	<p class="P25">
		<span class="T2">12) 
			      в течение 15 (пятнадцать) календарных дней с даты подписания
			Сторонами настоящего Договора осуществить государственную регистрацию
			настоящего Договора в территориальных органах юстиции, а также нести
			по
			ней все расходы;</span>
	</p>
	<p class="P25">
		<span class="T2">13)        производить самостоятельно текущий ремонт
			Имущества;</span>
	</p>
	<p class="P25">
		<span class="T2">14) 
			      не вправе без согласия Учредителя доверительного управления
			вносить какие-либо изменения в Имущество, включая снос или возведение
			стен, изменение планировки здания, изменение целевого назначения
			здания;</span>
	</p>
	<p class="P25">
		<span class="T2">15)        осуществлять самостоятельно эксплуатацию
			инженерных сетей и электрооборудования;</span>
	</p>
	<p class="P25">
		<span class="T2">16)        производить все платежи, связанные с
			эксплуатацией имущества;</span>
	</p>
	<p class="P25">
		<span class="T2">17) 
			      осуществлять контроль за соблюдением противопожарных, санитарных
			и
			иных норм, установленных действующим законодательством Республики
			Казахстан;</span>
	</p>
	<p class="P25">
		<span class="T2">18)        передать
			Имущество по акту приема-передачи Учредителю доверительного управления
			при прекращении настоящего Договора (истечении срока договора,
			досрочного расторжения) в течение 10 (десяти) рабочих дней;</span>
	</p>
	<p class="P25">
		<span class="T2">19) 
			      осуществлять иные права и обязанности Доверительного
			управляющего
			в соответствии с настоящим Договором и действующим законодательством
			Республики Казахстан.</span>
	</p>
	<p class="P36"> </p>
	<ol>
		<li>
			<p class="P34" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">2.</span>
				<span class="T5">Ответственность сторон</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P12"> </p>
	<p class="P23">
		<span class="T2">3.1. Доверительный управляющий несет ответственность за любой вред или ущерб, причиненный им интересам Учредителя доверительного управления при управлении Имуществом, за исключением вреда или ущерба, причиненного действием непреодолимой силы.</span>
	</p>
	<p class="P23">
		<span class="T2">3.2. Стороны несут ответственность за неисполнение или ненадлежащее исполнение своих обязательств по настоящему Договору в соответствии с законодательством Республики Казахстан.</span>
	</p>
	<p class="P16"> </p>
	<p class="P5">
		<span class="T5">4</span>
		<span class="T2">. </span>
		<span class="T5">Форс-мажор</span>
	</p>
	<p class="P14"> </p>
	<p class="P23">
		<span class="T2">4.1. 
			      Стороны освобождаются от ответственности за полное или частичное
			неисполнение обязательств по настоящему Договору, если оно явилось
			следствием обстоятельств непреодолимой силы (землетрясение,
			наводнение,
			пожар, эмбарго, война или военные действия, издание нормативных правовых
			актов государственными органами, запрещающих или каким-либо иным
			образом препятствующих исполнению обязательств), при условии, что эти
			обязательства не зависели от воли Сторон и сделали невозможным
			исполнение любой из сторон своих обязательств по настоящему Договору.</span>
	</p>
	<p class="P23">
		<span class="T2">4.2. 
			      Срок исполнения обязательств по настоящему Договору отодвигается
			соразмерно времени, в течение которого действовали обстоятельства
			непреодолимой силы, а также последствия, вызванные этими
			обстоятельствами.</span>
	</p>
	<p class="P23">
		<span class="T2">4.3.       
			Любая из Сторон при возникновении обстоятельств непреодолимой силы
			обязана в течение 30 (тридцати) календарных дней письменно информировать
			другую Сторону о наступлении этих обстоятельств.</span>
	</p>
	<p class="P23">
		<span class="T2">4.4. 
			      Не уведомление или несвоевременное уведомление лишает Сторону
			права ссылаться на любое вышеуказанное обстоятельство как на
			основание,
			освобождающее от ответственности за неисполнение обязательства.</span>
	</p>
	<p class="P23">
		<span class="T2">4.5. 
			      Если невозможность полного или частичного исполнения
			обязательства Сторонами будет существовать свыше 2 (двух) календарных
			месяцев, то Стороны вправе расторгнуть настоящий Договор.</span>
	</p>
	<p class="P17"> </p>
	<ol>
		<li>
			<p class="P6" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">5.</span>
				<span class="T5">Конфиденциальность</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P12"> </p>
	<p class="P23">
		<span class="T2">5.1. 
			      Стороны согласились, что вся информация, содержащаяся в
			Договоре,
			является конфиденциальной, и Стороны предпримут все необходимые меры
			для ее защиты.</span>
	</p>
	<p class="P23">
		<span class="T2">5.2.       
			Каждая из Сторон обязуются не разглашать конфиденциальную информацию,
			полученную от другой Стороны, и не вправе раскрывать эту информацию
			третьим лицам без предварительного письменного согласия другой
			Стороны,
			за исключением случаев, прямо предусмотренных действующим
			законодательством Республики Казахстан.</span>
	</p>
	<p class="P24"> </p>
	<ol>
		<li>
			<p class="P6" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">6.</span>
				<span class="T5">Разрешение споров</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P12"> </p>
	<p class="P23">
		<span class="T2">6.1.        Все споры и разногласия, возникающие из
			настоящего Договора, решаются путем переговоров.</span>
	</p>
	<p class="P23">
		<span class="T2">6.2. 
			      В случае, невозможности решения споров и разногласий путем
			переговоров, спор подлежит рассмотрению в судебных органах Республики
			Казахстан в установленном законодательством порядке.</span>
	</p>
	<ol>
		<li>
			<p class="P6" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">7.</span>
				<span class="T5">Срок действия Договора</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P12"> </p>
	<p class="P30">
		<span class="T2">7.1. 
			      Настоящий Договор вступает в силу с даты его регистрации в
			территориальных органах юстиции и действует до
			_______________________(дата).</span>
	</p>
	<p class="P30">
		<span class="T2">7.2. 
			      Настоящий Договор может быть прекращен до истечения указанного
			срока в пункте 7.1. Договора при возникновении следующих
			обстоятельств:</span>
	</p>
	<p class="P30">
		<span class="T2">1)        в случае ликвидации Доверительного
			управляющего;</span>
	</p>
	<p class="P30">
		<span class="T2">2) 
			      в случае совершения Доверительным управляющим действий, явно
			направленных во вред интересам Учредителя доверительного управления;</span>
	</p>
	<p class="P30">
		<span class="T2">3) 
			      по другим основаниям, если такие основания будут предусмотрены
			законом или будут сочтены необходимыми Учредителем доверительного
			управления.
		</span>
	</p>
	<p class="P30">
		<span class="T2">7.3.        По
			основаниям, указанным в п.6.2 настоящего Договора, может быть расторгнут
			немедленно по требованию Учредителя доверительного управления.</span>
	</p>
	<p class="P30">
		<span class="T2">7.4. 
			      При отказе одной стороны от настоящего Договора по другим
			основаниям другая сторона должна быть уведомлена не менее чем за один
			месяц до прекращения Договора.</span>
	</p>
	<p class="P32"> </p>
	<ol>
		<li>
			<p class="P6" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">8.</span>
				<span class="T5">Контроль за выполнением условий Договора</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P12"> </p>
	<p class="P23">
		<span class="T2">8.1. 
			      Контроль за выполнением условий настоящего Договора осуществляет
			Учредитель доверительного управления. С этой целью Учредитель
			доверительного управления также может образовать комиссию с участием
			представителей других заинтересованных государственных органов.
			Доверительный управляющий должен представлять на рассмотрение такой
			комиссии необходимые документы и отчеты по форме и в сроки,
			устанавливаемые самой комиссией.</span>
	</p>
	<p class="P15"> </p>
	<ol>
		<li>
			<p class="P6" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">9.</span>
				<span class="T5">Прочие условия</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P12"> </p>
	<p class="P31">
		<span class="T2">9.1. 
			      Во всем остальном, что не предусмотрено настоящим Договором,
			стороны будут руководствоваться действующим законодательством
			Республики
			Казахстан.</span>
	</p>
	<p class="P31">
		<span class="T2">9.2.       
			Учредитель доверительного управления и Доверительный управляющий имеют
			право по обоюдному согласию вносить изменения и дополнения к настоящему
			Договору посредством заключения письменных дополнительных соглашений.</span>
	</p>
	<p class="P10">
		<span class="T2"> 
			      9.3.        Все дополнительные соглашения и приложения к
			настоящему Договору являются его неотъемлемой частью и должны
			подписываться уполномоченными на то представителями Сторон, а также
			регистрироваться в территориальных органах юстиции.</span>
	</p>
	<p class="P10">
		<span class="T2"> 
			      9.4.        Прекращение срока действия настоящего Договора
			влечет
			за собой прекращение обязательств Сторон по нему, но не освобождает
			Стороны настоящего Договора от ответственности за его нарушения, если
			таковые имели место при исполнении Сторонами условий настоящего
			Договора.</span>
	</p>
	<p class="P10">
		<span class="T2">        9.5.       
			Настоящий Договор составлен в 3 (трех) экземплярах на казахском и
			русском языках, имеющих одинаковую юридическую силу - по одному
			экземпляру на каждом языке для каждой из Сторон, а также один экземпляр
			для регистрации в территориальные органы юстиции. В случае разночтения
			стороны руководствуются русским вариантом настоящего Договора.</span>
	</p>
	<p class="P18"> </p>
	<ol>
		<li>
			<p class="P8" style="margin-left:0cm;">
				<span style="display:block;float:left;min-width:0cm">10.</span>
				<span class="T5">Адрес и реквизиты Сторон:</span>
				<span class="odfLiEnd"></span>
				 
			</p>
		</li>
	</ol>
	<p class="P39"> </p>
	<table cellspacing="0" cellpadding="0" border="0" class="Table1">
		<colgroup>
			<col width="382" />
			<col width="372" />
		</colgroup>
		<tr class="Table11">
			<td style="text-align:left;width:3.4444in; " class="Table1_A1">
				<p class="P7">
					<span class="T5">Учредитель доверительного управления</span>
				</p>
				<p class="P19">
					<span class="T3">Реквизиты</span>
				</p>
				<p class="P19">
					<span class="T2">Должность</span>
				</p>
				<p class="P19">
					<span class="T2">____________________________Ф.И.О</span>
				</p>
				<p class="P19">
					<span class="T2">М.П.</span>
				</p>
			</td>
			<td style="text-align:left;width:3.3472in; " class="Table1_A1">
				<p class="P5">
					<span class="T5">Доверительный управляющий</span>
				</p>
				<p class="P10">
					<span class="T2">Реквизиты</span>
				</p>
				<p class="P9">
					<span class="T2">Должность</span>
				</p>
				<p class="P9">
					<span class="T2">_______________________________Ф.И.О</span>
				</p>
				<p class="P9">
					<span class="T2">М.П.</span>
				</p>
			</td>
		</tr>
	</table>
	<p class="P40"> </p>
	<p class="P40"> </p>
	<p class="P40"> </p>
	<p class="P2"> </p>
</xsl:template>
	
	<xsl:template name="formoutline">
		<div id="outline">
			<div id="outline-container" style="width:297px; padding-top:10px">
				<xsl:for-each select="//response/content/outline">
					<div>
						<div>
							<div class="treeentry" style="padding:3px 0px 3px 0px; border:1px solid #F9F9F9; cursor:pointer" onclick="javascript:ToggleCategory(this)">								
								<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:6px; cursor:pointer"/>
								<img src="/SharedResources/img/classic/1/folder_open_view.png" style="margin-left:5px;"/>
								<font style="font-family:arial; font-size:0.9em; margin-left:4px; vertical-align:3px">											
									<xsl:value-of select="@caption"/>
								</font>
							</div>
						</div>
						<div style="clear:both;"/>
						<div id="{@id}">
							<xsl:call-template name="outline-section-state"/>
							<xsl:for-each select="entry">
								<div class="entry treeentry" style="width:298px; padding:3px 0px 3px 0px; border:1px solid #F9F9F9;">
									<xsl:if test="/request/@id = @id">
										<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
									</xsl:if>
									<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
										<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
									</xsl:if>
									<xsl:if test="./entry">
										<div onclick ="javascript:ToggleCategory(this)">
											<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:35px; cursor:pointer; float:left"/>
										</div>
									</xsl:if>
									<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important;" title="{@hint}">
										<div class="viewlink">
											<xsl:if test="/request/@id = @id">
												<xsl:attribute name="class">viewlink_current</xsl:attribute>										
											</xsl:if>
											<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
												<xsl:attribute name="class">viewlink_current</xsl:attribute>										
											</xsl:if>
											<div style="padding-left:35px;">
												<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
													<img src="/SharedResources/img/classic/1/doc_view.png"/>
												</xsl:if>
												<xsl:if test="@id ='favdocs'">
													<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
												</xsl:if>
												<xsl:if test="@id ='recyclebin'">
													<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
												</xsl:if>													 
												<font class="viewlinktitle">												
													 <xsl:value-of select="@caption"/>										
												</font>
											</div>
										</div>
									</a>
								</div>
								<div style="clear:both"/>
								<div class="outlineEntry" id="{@id}">
									<xsl:call-template name="outline-section-state"/>
									<xsl:for-each select="entry">
										<div class="entry treeentry" style="width:298px; padding:3px 0px 3px 0px; border:1px solid #F9F9F9;">
											<xsl:if test="/request/@id = @id">
												<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
											</xsl:if>
											<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
												<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
											</xsl:if>
											<xsl:if test="./entry">
												<div onclick ="javascript:ToggleCategory(this)">
													<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:49px; cursor:pointer; float:left"/>
												</div>
											</xsl:if>
											<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important;" title="{@hint}">
												<div class="viewlink">
													<xsl:if test="/request/@id = @id">
														<xsl:attribute name="class">viewlink_current</xsl:attribute>										
													</xsl:if>
													<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
														<xsl:attribute name="class">viewlink_current</xsl:attribute>										
													</xsl:if>
													<div style="padding-left:65px;">
														<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
															<img src="/SharedResources/img/classic/1/doc_view.png"/>
														</xsl:if>
														<xsl:if test="@id ='favdocs'">
															<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
														</xsl:if>
														<xsl:if test="@id ='recyclebin'">
															<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
														</xsl:if>													 
														<font class="viewlinktitle">												
															 <xsl:value-of select="@caption"/>										
														</font>
													</div>
												</div>
											</a>
										</div>
										<div style="clear:both"/>
										<div class="outlineEntry" id="{@id}">
											<xsl:call-template name="outline-section-state"/>
											<xsl:for-each select="entry">
												<div class="entry treeentry" style="width:283px; padding:3px 0px 3px 15px; border:1px solid #F9F9F9;">
													<xsl:if test="/request/@id = @id">
														<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
													</xsl:if>
													<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
														<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
													</xsl:if>
													<xsl:if test="./entry">
														<div onclick ="javascript:ToggleCategory(this)">
															<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:49px; cursor:pointer; float:left"/>
														</div>
													</xsl:if>
													<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important" title="{@hint}">
														<div class="viewlink">
															<xsl:if test="/request/@id = @id">
																<xsl:attribute name="class">viewlink_current</xsl:attribute>										
															</xsl:if>	
															<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
																<xsl:attribute name="class">viewlink_current</xsl:attribute>										
															</xsl:if>	
															<div style="padding-left:65px;">
																<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
																	<img src="/SharedResources/img/classic/1/doc_view.png"/>
																</xsl:if>
																<xsl:if test="@id ='favdocs'">
																	<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
																</xsl:if>
																<xsl:if test="@id ='recyclebin'">
																	<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
																</xsl:if>													 
																<font class="viewlinktitle">												
																	 <xsl:value-of select="@caption"/>										
																</font>
															</div>
														</div>
													</a>
												</div>
												<div style="clear:both"/>
												<div class="outlineEntry" id="{@id}">
													<xsl:call-template name="outline-section-state"/>
													<xsl:for-each select="entry">
														<div class="entry treeentry" style="width:283px; padding:3px 0px 3px 15px; border:1px solid #F9F9F9;">
															<xsl:if test="/request/@id = @id">
																<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
															</xsl:if>
															<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
																<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
															</xsl:if>
															<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important" title="{@hint}">
																<div class="viewlink">
																	<xsl:if test="/request/@id = @id">
																		<xsl:attribute name="class">viewlink_current</xsl:attribute>										
																	</xsl:if>	
																	<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
																		<xsl:attribute name="class">viewlink_current</xsl:attribute>										
																	</xsl:if>	
																	<div style="padding-left:85px;">
																		<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
																			<img src="/SharedResources/img/classic/1/doc_view.png"/>
																		</xsl:if>
																		<xsl:if test="@id ='favdocs'">
																			<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
																		</xsl:if>
																		<xsl:if test="@id ='recyclebin'">
																			<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
																		</xsl:if>													 
																		<font class="viewlinktitle">												
																			 <xsl:value-of select="@caption"/>										
																		</font>
																	</div>
																</div>
															</a>
														</div>
													</xsl:for-each>
												</div>
											</xsl:for-each>
										</div>
									</xsl:for-each>
								</div>
							</xsl:for-each>
						</div>
					</div>
				</xsl:for-each>
			</div>
		</div>
		<div id="resizer" style="position:absolute; top: 80px; left:1px; background:#E6E6E6; width:12px; bottom:0px; border-radius: 0 6px 6px 0; border: 1px solid #adadad; border-left: ; cursor:pointer" onclick="javascript:openformpanel()">
			<span  id="iconresizer" class="ui-icon ui-icon-triangle-1-e" style="margin-left:-2px; position:relative; top:49%"></span>
		</div>
	</xsl:template>
	
	<xsl:template name="outline-section-state">
		<script>
			if($.cookie("COMMUNAL_<xsl:value-of select='@id'/>") != 'null'){
				$("#<xsl:value-of select='@id'/>").css("display",$.cookie("COMMUNAL_<xsl:value-of select='@id'/>"))
				if($.cookie("COMMUNAL_<xsl:value-of select='@id'/>") == "none"){
					$("#<xsl:value-of select='@id'/>").prev().prev().children().children("img:first").attr("src","/SharedResources/img/classic/1/plus.png")							
				}else{
					$("#<xsl:value-of select='@id'/>").prev().prev().children().children("img:first").attr("src","/SharedResources/img/classic/1/minus.png")							
				}
			}
		</script>	
	</xsl:template>
	
	<xsl:template name="documentheader">
		<div style="position:absolute; top:0px; left:0px; width:100%; background:url(classic/img/yellow_background.jpg); height:70px; border-bottom:1px solid #fcdd76; z-index:2">
			<span style="float:left">
				<font style="font-size:1.5em; vertical-align:50px; color:#555555; margin-left:20px; line-height:60px">АИС 'Коммунальное Имущество'</font>
			</span>
			<span style="float:right; padding:5px 5px 5px 0px">
				<a id="currentuser" title="{document/captions/view_userprofile/@caption}" href=" Provider?type=edit&amp;element=userprofile&amp;id=userprofile" style="text-decoration:none;color: #555555 ; font: 11px Tahoma; font-weight:bold">
					<font>
						<xsl:value-of select="@username"/>
					</font>
				</a>
				<a id="logout" href="Logout" title="{document/captions/logout/@caption}" style="text-decoration:none;color: #555555 ; font: 11px Tahoma; font-weight:bold">
					<font style="margin-left:20px;"><xsl:value-of select="document/captions/logout/@caption"/></font> 
				</a>
				<a id="helpbtn" href="Provider?type=static&amp;id=help_category_list" title="{document/captions/help/@caption}" style="text-decoration:none;color: #555555 ; font: 11px Tahoma; font-weight:bold">
					<font style="margin-left:20px;"><xsl:value-of select="document/captions/help/@caption"/></font> 
				</a>
			</span>
		</div>
	</xsl:template>
	
	<xsl:template name="documentitle-reports">
		<xsl:param name="title"/>
		<font style="font-size:18px">
			<xsl:value-of select="$title"/>
		</font>
	</xsl:template>

	<xsl:template name="ECPsignFields">
		<input type="hidden" name="sign" id="sign" value="{sign}" style="width:100%;" />
		<input type="hidden" name="signedfields" id="signedfields" value="{signedfields}" style="width:100%;" />
		<!-- <APPLET CODE="kz.flabs.eds.applet.EDSApplet" NAME="edsApplet" ARCHIVE="eds.jar, commons-codec-1.3.jar" HEIGHT="1" WIDTH="1">
			<param name="ARCHIVE" value="/eds.jar, /commons-codec-1.3.jar" />
		</APPLET> -->

		<xsl:if test="document/@canbesign='1111'">
			<script type="text/javascript" src="/edsApplet/js/jquery.blockUI.js" charset="utf-8"></script>
        	<script type="text/javascript" src="/edsApplet/js/crypto_object.js" charset="utf-8"></script>
        	<script type="text/javascript">
				edsApp.init();
			</script>
		</xsl:if>
	</xsl:template>

	<xsl:template name="markisread">
		<xsl:if test="document[@isread = 0][@status != 'new']">
			<script>
				markRead(<xsl:value-of select="document/@doctype"/>, <xsl:value-of select="document/@docid"/>);
			</script>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="docinfo">
		<br/>
		<table width="100%" border="0">
			<tr>
				<td class="fc">
					<xsl:value-of select="document/captions/statusdoc/@caption"/> :
				</td>
				<td>
					<font>
						<xsl:choose>
							<xsl:when test="document/@status='new'">
								<xsl:value-of select="document/captions/newdoc/@caption"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="document/captions/saved/@caption"/>
							</xsl:otherwise>
						</xsl:choose>
					</font>		
				</td>
			</tr>
			<tr>
				<td class="fc">
					<xsl:value-of select="document/captions/permissions/@caption"/> :
				</td>
				<td>
					<font>
					<xsl:choose>
					 <xsl:when test ="/request/document/fields/control/allcontrol = '0'"> 
						<xsl:value-of select="document/captions/readonly/@caption" />
					</xsl:when>
						<xsl:otherwise>
							<xsl:choose>
							<xsl:when test="document/@editmode='view'">
								<xsl:value-of select="document/captions/readonly/@caption"/>
							</xsl:when>
							<xsl:when test="document/@editmode='readonly'">
								<xsl:value-of select="document/captions/readonly/@caption" />
							</xsl:when>
							<xsl:when test="document/@editmode='edit'">
								<xsl:value-of select="document/captions/editing/@caption" />
							</xsl:when>
							<xsl:when test="document/@editmode='noaccess'">
								<xsl:value-of select="document/captions/readonly/@caption" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="document/captions/modenotdefined/@caption" />
							</xsl:otherwise>
							</xsl:choose>
						</xsl:otherwise>
					</xsl:choose>
					</font>
				</td>
			</tr>
			<xsl:if test="document/@status !='new'">
				<!-- <tr>
					<td class="fc">
						<xsl:value-of select="document/captions/infofread/@caption"/> :
					</td>
					<td>
						<font>
							<xsl:choose>
								<xsl:when test="document/@isread ='1'">
									Прочтен
								</xsl:when>
								<xsl:otherwise>
									Не прочтен
								</xsl:otherwise>
							</xsl:choose>
						</font>		
					</td>
				</tr> -->
				<tr>
					<td class="fc">
						<xsl:value-of select="document/captions/infofread/@caption"/> :
					</td>
					<td>
						<script type="text/javascript">
							usersWhichReadInTable(this,<xsl:value-of select="document/@doctype"/>,<xsl:value-of select="document/@docid"/>)
						</script>
						<table class="table-border-gray" id="userswhichreadtbl" style="width:600px">
							<tr>
								<td style="width:350px; text-align:center">
									<font><xsl:value-of select="document/captions/whomread/@caption"/></font>
								</td>
								<td style="width:250px; text-align:center">
									<font><xsl:value-of select="document/captions/timeofreading/@caption"/></font>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="fc">
						<xsl:value-of select="document/captions/DS/@caption"/> :
					</td>
					<td>
						<font>
							<xsl:choose>
								<xsl:when test="document/@sign ='0'">
									<xsl:value-of select="document/captions/documentnotsigned/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='1'">
									<xsl:value-of select="document/captions/signistrue/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='2'">
									<xsl:value-of select="document/captions/signisfalse/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='3'">
									<xsl:value-of select="document/captions/invalidkey/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='4'">
									<xsl:value-of select="document/captions/algorithmnotfound/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='5'">
									<xsl:value-of select="document/captions/fillmechanismnotfound/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='6'">
									<xsl:value-of select="document/captions/invalidcharkey/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='7'">
									<xsl:value-of select="document/captions/invalidparalgo/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='8'">
									<xsl:value-of select="document/captions/totalexceptionkey/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='10'">
									<xsl:value-of select="document/captions/filecertnotfound/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='11'">
									<xsl:value-of select="document/captions/onefilenotfound4sign/@caption"/>
								</xsl:when>
								<xsl:otherwise>
								</xsl:otherwise>
							</xsl:choose>
						</font>		
					</td>
				</tr>
			</xsl:if>
		</table>
	</xsl:template>
	
	<xsl:template name="attach">
		<div id="attach" style="display:block;">
			<table style="border:0; border-collapse:collapse" id="upltable" width="99%">
				<xsl:if test="$editmode = 'edit'">
					<tr>
						<td class="fc">
							<xsl:value-of select="document/captions/attachments/@caption"/>:
						</td>
						<td>
							<input type="file" size="60" border="#CCC" name="fname" id="fileInput">
								<xsl:attribute name="onchange">javascript:submitFile('upload', 'upltable', 'fname'); ajaxFunction()</xsl:attribute>
							</input>&#xA0;
							<br/>
							<style>.ui-progressbar .ui-progressbar-value { background-image: url(/SharedResources/jquery/css/base/images/pbar-ani.gif); }</style>
							<div id="progressbar" style="width:370px; margin-top:5px; height:12px"></div>
							<div id="progressstate" style="width:370px; display:none">
								<font style="visibility:hidden; color:#999; font-size:11px; width:70%" id="readybytes"></font>
								<font style="visibility:hidden; color:#999; font-size:11px; float:right;" id="percentready"></font>
								<font style="visibility:hidden; text-align:center; color:#999; font-size:11px; width:30%; text-align:center" id="initializing">Подготовка к загрузке</font>
							</div>
						</td>
						<td></td>
					</tr>
				</xsl:if>
				<xsl:variable name='docid' select="document/@docid"/>
				<xsl:variable name='doctype' select="document/@doctype"/>
				<xsl:variable name='formsesid' select="formsesid"/>
				
				<xsl:for-each select="document/fields/rtfcontent/entry">
					<tr>
						<xsl:variable name='id' select='@hash'/>
						<xsl:variable name='filename' select='@filename'/>
						<xsl:variable name="extension" select="tokenize(lower-case($filename), '\.')[last()]"/>
						<xsl:variable name="resolution"/>
						<xsl:attribute name='id' select="$id"/>
						<td class="fc"></td>
						<td colspan="2">
							<div class="test" style="width:90%; overflow:hidden; display:inline-block">
								<xsl:choose>
									<xsl:when test="$extension = 'jpg' or $extension = 'jpeg' or $extension = 'gif' or $extension = 'bmp' or $extension = 'png'">
										<img class="imgAtt" title="{$filename}" style="border:1px solid lightgray; max-width:800px; max-height:600px; margin-bottom:5px">
											<xsl:attribute name="onload">checkImage(this)</xsl:attribute>
											<xsl:attribute name='src'>Provider?type=getattach&amp;formsesid=<xsl:value-of select="$formsesid"/>&amp;doctype=<xsl:value-of select="$doctype"/>&amp;key=<xsl:value-of select="@id"/>&amp;field=rtfcontent&amp;id=rtfcontent&amp;id=rtfcontent&amp;file=<xsl:value-of select='$filename'/></xsl:attribute>
										</img>
										<xsl:if test="$editmode = 'edit'">
											<xsl:if test="comment =''">
												<a href='' style="vertical-align:top;" title='tect'>
													<xsl:attribute name='href'>javascript:addCommentToAttach('<xsl:value-of select="$id"/>')</xsl:attribute>
													<img id="commentaddimg{$id}" src="/SharedResources/img/classic/icons/comment_add.png" style="width:16px; height:16px" >
														<xsl:attribute name="title" select="//document/captions/add_comment/@caption"/>
													</img>
												</a>
											</xsl:if>
											<a href='' style="vertical-align:top; margin-left:8px">
												<xsl:attribute name='href'>javascript:deleterow('<xsl:value-of select="$formsesid"/>','<xsl:value-of select='$filename'/>','<xsl:value-of select="$id" />')</xsl:attribute>
												<img src="/SharedResources/img/iconset/cross.png" style="width:13px; height:13px">
												<xsl:attribute name="title" select="//document/captions/delete_file/@caption"/>
												</img>
											</a>
										</xsl:if>
									</xsl:when>
									<xsl:otherwise>
										<img src="/SharedResources/img/iconset/file_extension_{$extension}.png" style="margin-right:5px">
											<xsl:attribute name="onerror">javascript:changeAttIcon(this)</xsl:attribute>
										</img>
										<a style="vertical-align:5px">
											<xsl:attribute name='href'>Provider?type=getattach&amp;formsesid=<xsl:value-of select="$formsesid"/>&amp;doctype=<xsl:value-of select="$doctype"/>&amp;key=<xsl:value-of select="@id"/>&amp;field=rtfcontent&amp;id=rtfcontent&amp;file=<xsl:value-of select='$filename'/>	</xsl:attribute>
											<xsl:value-of select='replace($filename,"%2b","+")'/>
										</a>&#xA0;&#xA0;
										<xsl:if test="$editmode = 'edit'">
											<xsl:if test="comment =''">
												<a href='' style="vertical-align:5px;">
													<xsl:attribute name='href'>javascript:addCommentToAttach('<xsl:value-of select="$id"/>')</xsl:attribute>
													<img id="commentaddimg{$id}" src="/SharedResources/img/classic/icons/comment_add.png" style="width:16px; height:16px">
														<xsl:attribute name="title" select="//document/captions/add_comment/@caption"/>
													</img>
												</a>
											</xsl:if>
											<a href='' style="vertical-align:5px; margin-left:5px">
												<xsl:attribute name='href'>javascript:deleterow('<xsl:value-of select="$formsesid"/>','<xsl:value-of select='$filename' />','<xsl:value-of select="$id"/>')</xsl:attribute>
												<img src="/SharedResources/img/iconset/cross.png" style="width:13px; height:13px">
													<xsl:attribute name="title" select="//document/captions/delete_file/@caption"/>
												</img>
											</a>
										</xsl:if>
									</xsl:otherwise>
								</xsl:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td/>
						<td colspan="2" style="color:#777; font-size:12px">
							<xsl:if test="comment !=''">
								<xsl:value-of select="concat(//document/captions/comments/@caption,':',comment)"/>
								<br/><br/>
							</xsl:if>
						</td>
					</tr>
				</xsl:for-each>
			</table>
			<br/>
			<br/>
		</div>
	</xsl:template>
</xsl:stylesheet>