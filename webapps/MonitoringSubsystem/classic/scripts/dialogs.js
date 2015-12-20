var tableField;
var hiddenField;
var formName;
var entryCollections = new Array();

var queryOpt = {
	queryname:'',
	formname:'',
	fieldname:'',
	isMultiValue:'',
	tablename:'',
	pagenum:'1',
	keyword:'',
	dialogview:'1'
}

function toggleOrgTreeStructure(elid){
	if ($(".treetablestructure"+elid).css("display") != "none"){
		$(".treetablestructure"+elid).css("display", "none")
		$("#treeorgimg"+elid).attr("src","/SharedResources/img/classic/plus.gif")
	}else{
		$(".treetablestructure"+elid).css("display", "block")
		$("#treeorgimg"+elid).attr("src","/SharedResources/img/classic/minus.gif")
	}
}

function toggleDepTreeStructure(countEl, action, dociddoctype){
	if (action == 'close'){
		el = $("#"+dociddoctype)
		for (var i = 0; i < countEl ; i++ ){
			el = $(el).next("div['display' = 'block']").css("display","none")
		}
		$("#treedep"+dociddoctype).attr("href","javascript:toggleDepTreeStructure("+countEl+",'open',"+ dociddoctype +")")
		$("#treedepimg"+dociddoctype).attr("src","/SharedResources/img/classic/plus.gif")
	}else{
		el = $("#"+dociddoctype)
		for (var i = 0; i < countEl ; i++ ){
			el = $(el).next("div['display' = 'none']").css("display","block")
		}
		$("#treedep"+dociddoctype).attr("href","javascript:toggleDepTreeStructure("+countEl+",'close',"+ dociddoctype +")")
		$("#treedepimg"+dociddoctype).attr("src","/SharedResources/img/classic/minus.gif")
	}
}

function checkallOrg(el, tableid){
	el.checked ? $(".treetablestructure"+tableid+ " input[type=checkbox]").attr("checked","true") : $(".treetablestructure"+tableid+ " input[type=checkbox]").removeAttr("checked");
	if(queryOpt.fieldname == "executor"){
		$(".treetablestructure"+tableid+ " input[type=checkbox][id != '']").each(function(indx, element){
			addToCollectExecutor($(element))
		});
	}
}

function checkDepInp(el, countEl){
	elem = $(el).parent("div").next("div")
	for (var i = 0; i < countEl ; i++ ){
		el.checked ? $(elem).children("input[type=checkbox]").attr("checked","true") : $(elem).children("input[type=checkbox]").removeAttr("checked");
		if(queryOpt.fieldname == "executor"){
			addToCollectExecutor($(elem).children("input[type=checkbox]"))
		}
	}
}

/* обработка нажатия клавиш esc и enter */
function keyDown(el){
	if(event.keyCode==27){
		$("#"+el).css("display","none").empty().remove();
		$("#blockWindow").remove();
	}
	if(event.keyCode==13){
		el !='coordParam' ? pickListBtnOk() : coordOk();
	}
}

/* выбор одного корреспондента из структуры */
function pickListSingleOk(docid){
	text=$("#"+docid).attr("value");
	$("input[name="+ queryOpt.fieldname +"]").remove();
	if(queryOpt.fieldname == 'balanceholder'){
		$("input[name=bin]").val($("#"+docid).attr("bin"));
		$("input[name=balanceholdernamekaz]").val($("#"+docid).attr("namekaz"));
	}
	$("#frm").append("<input type='hidden' name='"+ queryOpt.fieldname +"'  id='"+queryOpt.fieldname+"' value='"+docid+"'>")
	newTable="<table id="+ queryOpt.tablename +"><tr><td style='width:600px;' class='td_editable'>"+ text +"</td></tr></table>"
	$("#"+ queryOpt.tablename).replaceWith(newTable)
	pickListClose(); 
}

/* нажатие кнопки "ок" в форме выбора кореспондента из структуры*/ /* переделать весь код*/
function pickListBtnOk(){
	var k=0;
	var chBoxes = $('input[name=chbox]'); 
	var hidfields = $("#executorsColl").children("input[type=hidden]"); 
	for( var i = 0; i < chBoxes.length; i ++ ){
		if (chBoxes[i].checked && $(chBoxes[i]).attr("id") != ''){ 
			if (k==0){
				newTable="<table id="+ queryOpt.tablename +"/>"
				$("#"+ queryOpt.tablename).replaceWith(newTable)
				$("input[name="+queryOpt.fieldname+"]").remove();
			}
			k=k+1;
			if(queryOpt.fieldname == 'balanceholder'){
				$("input[name=bin]").val($(chBoxes[i]).attr("bin"));
				$("input[name=balanceholdernamekaz]").val($(chBoxes[i]).attr("namekaz"));
			}
			$("#"+ queryOpt.tablename).append("<tr><td style='width:600px;' class='td_editable'>"+chBoxes[i].value+"</td></tr>");
			$("#"+ queryOpt.formname).append("<input type='hidden' name='"+queryOpt.fieldname+"' id='"+queryOpt.fieldname+"' value='"+chBoxes[i].id+"'>")
		}
	}
	
	if (k>0){
		pickListClose();  
	}else{
		if ($.cookie("lang")=="RUS" || !$.cookie("lang"))
			msgtxt ="Выберите значение";
		else if ( $.cookie("lang")=="KAZ")
			msgtxt ="Мәндi таңдаңыз";
		else if ( $.cookie("lang")=="ENG")
			msgtxt ="Select value";
		else if ( $.cookie("lang")=="CHN")
			msgtxt ="选择";
		
		infoDialog(msgtxt);
	}
}

/*закрытие формы выбора корреспондентов из структуры */
function pickListClose(){ 
	$("#picklist").empty().remove();
	$('#blockWindow').remove();
	// разрешаем выделение текста на форме
	$('#picklist').enableSelection();
}

/* выбор одного корреспондента*/
function pickListSingleCoordOk(docid){ 
	text=$("#"+docid).attr("value");
	$("input[name=coorder]").remove();
	$("#frm").append("<input type='hidden' name='coorder'  id='coorder' value='"+docid+"'>")
	newTable="<table id='coordertbl' width='100%'><tr><td>"+ text +"</td></tr></table>"
	$("#coordertbl").replaceWith(newTable);
	closePicklistCoord();  
}

/* закрытие диалогового окна*/
function closePicklistCoord(){
	$("#picklist").remove();
	$("#blockWindow").css('z-index','2');
}

/* вывод окна посередине экрана  */
function centring(id,wh,ww){
	var w=$(window).width(), 
		h=$(window).height(),
		winH=$('#'+id).height(), 
		winW=$('#'+id).width(),
		scrollA=$("body").scrollTop(), 
		scrollB=$("body").scrollLeft();
	htop=scrollA+((h/2)-(winH/2))
	hleft=scrollB+((w/2)-(winW/2))
	$('#'+id).css('top',htop) ;
	$('#'+id).css('left',hleft) ;
}

/*функция для обеспечения rollover*/
function entryOver(cell){
	cell.style.backgroundColor='#FFF1AF';
}

/*функция для обеспечения rollover*/
function entryOut(cell){
	cell.style.backgroundColor='#FFFFFF';
}

function closeDialog(el){
	$("#"+el).empty().remove();
	$("#blockWindow").remove();
}

function fastCloseDialog(){
	$("#picklist").css("display","none");
}

function enableblockform(){
	blockWindow = "<div class='ui-widget-overlay' id = 'blockWindow'/>"; 
	$("body").append(blockWindow);
	$('#blockWindow').css('width',$(window).width()).css('height',$(window).height()).css('display',"block"); 
}

function disableblockform(){
	$('#blockWindow').remove(); 
}

function dialogBoxStructure(query,isMultiValue, field, form, table) {
	enableblockform()
	queryOpt.fieldname = field;
	queryOpt.formname = form;
	queryOpt.isMultiValue = isMultiValue;
	queryOpt.queryname = query;
	queryOpt.tablename = table;
	el='picklist'
	divhtml ="<div class='picklist' id='picklist' onkeyUp='keyDown(el);'>";
	divhtml +="<div class='header'><font id='headertext' class='headertext'></font>";
	divhtml +="<div class='closeButton'><img style='width:15px; height:15px; margin-left:3px; margin-top:1px' src='/SharedResources/img/classic/icons/cross.png' onclick='pickListClose();'/>";
	divhtml +="</div></div><div id='divChangeView' style='margin-top:-10px'><div id='divSearch' class='divSearch' style='display:inline-block; float:left; text-align: center'></div>"
	divhtml +="<div  style='display:inline-block; float:right; width:100px; '></div></div>";
	divhtml +="<div id='contentpane' class='contentpane'>Загрузка данных...</div>";  
	divhtml += "<div id='btnpane' class='button_panel' style='margin-top:8%; margin:15px 15px 0; padding-bottom:15px;text-align:right;'>";
	divhtml += "<button onclick='pickListBtnOk()' style='margin-right:5px'><font class='button_text'>ОК</font></button>" 
	divhtml += "<button onclick='pickListClose()'><font class='button_text'>"+cancelcaption+"</font></button>";    
	divhtml += "</div><div id='executorsColl' display='none'></div></div>";
	$("body").append(divhtml);
	$("#picklist #btnpane").children("button").button();
	$("#picklist").draggable({handle:"div.header"});
	centring('picklist',500,500);
	$("#picklist").focus().css('display', "none");
	$("#headertext").text($("#"+field+"caption").val());
	$("body").css("cursor","wait")
	$.ajax({
		type: "get",
		url: 'Provider?type=page&id='+query+'&keyword='+queryOpt.keyword+'&page='+queryOpt.pagenum,
		success:function (data){
			if (data.match("login") && data.match("password")){
				text="Cессия пользователя была закрыта, для продолжения работы необходима повторная авторизация"
				func = function(){window.location.reload()};
				dialogAndFunction (text,func)
			}else{
				if(isMultiValue=="false"){
					if($.browser.msie){
						while(data.match("checkbox")){
							data=data.replace("checkbox", "radio");
						}
					}else{
						elem=$(data);
						$(elem).find("input[type=checkbox]").prop("type","radio")
						data= elem;
					}
				}
				$("#contentpane").html(data);
				$("#searchTable").remove();
				if (isMultiValue=="false"){
					$("#contentpane").append("<input type='hidden' id='radio' value='true'/>");
				}else{
					$("#contentpane").append("<input type='hidden' id='radio' value='false'/>");
					$("div[name=itemStruct] input[type=checkbox][id != '']").click(function(){
						addToCollectExecutor(this)
					});
				}
                if((queryOpt.fieldname == 'organization' || queryOpt.fieldname == 'balanceholder') && queryOpt.queryname == 'balanceholder'){
                    searchTbl ="<font style='vertical-align:3px;  color:#555555'>"+searchcaption+":</font>";
					if($.browser.msie){
						searchTbl +=" <input type='text' id='searchCor'  margin-left:3px;' size='40' onpropertychange='findOrganization()'/>";
					}else{
						searchTbl +=" <input type='text' id='searchCor'  margin-left:3px;' size='40' oninput='findOrganization()'/>";
					}
                    $("#divSearch").append(searchTbl);
                }

				// запрещаем выделение текста
				$(document).ready(function(){
					$('#picklist').disableSelection();
				});
				$('#blockWindow').css('display',"block")
				$('#picklist').css('display', "inline-block").focus();
			}
		},
		error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
         	  $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'/>")
         	  $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
         	  $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
         }  
	});
	$("body").css("cursor","default")
}

function addToCollectExecutor(el){
	if($(el).is(':checked') && $(el).attr("id") != ''){
		$("#executorsColl").append("<input type='hidden' id='"+$(el).attr("id")+"' value='"+$(el).val() +"'/>")
	}else{
		$("#executorsColl").children("#"+$(el).attr("id")).remove();
	}
	$("#executorsColl input[type=hidden]:first").attr("class","otv")
	//$("#frm").append("<input type='hidden' name='responsible' id='responsible' value='"+ $("#executorsColl input[type=hidden]:first").val() +"'/>")
}

/* Функция  запрета и разрешения выделения текста   */
jQuery.fn.extend({
    disableSelection : function() {
    	this.each(function() {
    		this.onselectstart = function(){return false;};
    		this.unselectable = "on";
    		jQuery(this).css('-moz-user-select', 'none');
    	});
    },
    enableSelection : function() {
    	this.each(function() {
    		this.onselectstart = function() {};
    		this.unselectable = "off";
    		jQuery(this).css('-moz-user-select', 'auto');
    	});
    }
});

/* поиск организации */
var xhr_req =null;
function findOrganization(){
	var value=$('#searchCor').val();
	queryOpt.keyword = value;
	if(value.length > 2  || value.length == 0){
		$("#picklist").css("cursor","wait");
		$("#contentpane").html("<img id='loadingimg' src='/SharedResources/img/classic/loading9.gif' style='margin-top:170px; margin-left:10px'/>");
		if(xhr_req){
			xhr_req.abort()
		}
		xhr_req = $.ajax({
			type: "get",
			url: 'Provider?type=page&id='+queryOpt.queryname+'&keyword='+value+'&page=1',
			success:function (data){
				if(queryOpt.isMultiValue=="false"){
					if($.browser.msie){
						while(data.match("checkbox")){
							data=data.replace("checkbox", "radio");
						}
					}else{
						elem=$(data);
						$(elem).find("input[type=checkbox]").prop("type","radio");
						data= elem;
					}
				}
				$("#contentpane").html(data);
			},
			error:function (xhr, ajaxOptions, thrownError){
				if (xhr.status == 400){
					$("body").children().wrapAll("<div id='doerrorcontent' style='display:none'/>");
					$("body").append("<div id='errordata'>"+xhr.responseText+"</div>");
					$("li[type='square'] > a").attr("href","javascript:backtocontent()")
				}
			},
			complete:function(){
				$("#picklist").css("cursor","default")
			}
		});
	}
}

/* функция поиска в структуре*/
function findCorStructure(){
	var value=$('#searchCor').val();
	var len=value.length;
	if (len > 0){
		$("div[name=itemStruct]").css("display","none");
		$("#contentpane").find("div[name=itemStruct]").each(function(){
			if ($(this).text().substring(0,len).toLowerCase() == value.toLowerCase()){
				$(this).css("display","block")
			}
		});
	}else{
		$("div[name=itemStruct]").css("display","block");
	}
}
/* функция выбора страницы в диалогах*/
function selectPage(page){
	queryOpt.pagenum = page;
	$("#picklist").css("cursor","wait");
	$("#contentpane").html("<img id='loadingimg' src='/SharedResources/img/classic/loading9.gif' style='margin-top:170px; margin-left:10px'/>");
	$.ajax({
		type: "get",
		url: 'Provider?type=page&id='+ queryOpt.queryname +'&page='+ queryOpt.pagenum +"&keyword="+queryOpt.keyword,
		success:function (data){
			if (queryOpt.isMultiValue == "false"){
				while(data.match("checkbox")){
					data=data.replace("checkbox", "radio");
				}
			}
			$("#contentpane").html(data);
			$('#btnChangeView').attr("href","javascript:changeViewStructure("+queryOpt.dialogview+")");
			$('#searchCor').focus();
			$("#picklist").css("cursor","default");
		}
	});
}

/* функция изменения вида диалога (список или структура)*/
function changeViewStructure (viewType){
	if (viewType==1){
		queryOpt.queryname="structurefullpicklist";
		queryOpt.dialogview = 2;
	}else{
		queryOpt.queryname="bossandemppicklist";
		queryOpt.dialogview = 1;
	}
	$.ajax({
		type: "get",
		url: 'Provider?type=view&id='+queryOpt.queryname+'&keyword='+queryOpt.keyword+'&page='+queryOpt.pagenum,
		success:function (data){
			if (queryOpt.isMultiValue =="false"){
				while(data.match("checkbox")){
					data=data.replace("checkbox", "radio");
				}
			}
			$("#contentpane").html(data);
			// по клику добавляем в коллекцию 
			$("#contentpane input[type=checkbox]").click(function(){
				addToCollectExecutor(this)
			});
			if(viewType == 2){
				searchTbl ="<font style='vertical-align:3px; float:left; margin-left:4%'><b>"+searchcaption+":</b></font>";
				if (queryOpt.queryname == 'n'){
					searchTbl +=" <input type='text' id='searchCor' style='float:left; margin-left:3px;' size='34' onKeyUp='ajaxFind()'/>"; 
				}else{
					searchTbl +=" <input type='text' id='searchCor' style='float:left; margin-left:3px;' size='34' onKeyUp='findCorStructure()'/>"; 
				}
				$("#divSearch").append(searchTbl);
				//$('#searchCor').focus()
			}else{
				$("#divSearch").empty()
			}
			$('#btnChangeView').attr("href","javascript:changeViewStructure("+queryOpt.dialogview+")");
		},
		error:function (xhr, ajaxOptions, thrownError){
			if (xhr.status == 400){
				$("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
				$("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
				$("li[type='square'] > a").attr("href","javascript:backtocontent()")
			}
		}  
	});
}

function ajaxFind(){
	queryOpt.keyword=$("#searchCor").val()+"*";
	$.ajax({
		type: "get",
		url: 'Provider?type=view&id='+queryOpt.queryname+'&keyword='+queryOpt.keyword+"&page=1",
		success:function (data){
			if (queryOpt.isMultiValue == "false"){
				while(data.match("checkbox")){
					data=data.replace("checkbox", "radio");
				}
			}
			$("#contentpane").html(data);
			$('#btnChangeView').attr("href","javascript:changeViewStructure(2)");
			$('#searchCor').focus()
		},
		error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
         	  $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
         	  $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
         	  $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
         }    
	});
}

/* функция открытия категорий в структуре*/
function expandChapter(query,id,doctype) {
	$.ajax({
		url: 'Provider?type=view&id='+query+'&parentdocid='+id+'&parentdoctype='+doctype+'&command=expand`'+id+'`'+doctype,
		success: function(data) {
			if(queryOpt.isMultiValue == "false"){
				while(data.match("checkbox")){
					data=data.replace("checkbox", "radio");
				}
			}
			$(".tbl"+id+doctype).append(data).css("margin-left","15px");	
			if (doctype == "891"){
				$("input[name=chbox]").removeAttr("style");	
			}
		}
	});	
	$("#a"+id+doctype).attr("href","javascript:collapseChapter('"+query+"','"+id+"','"+ doctype+"')");
	$("#img"+id+doctype).attr("src","/SharedResources/img/classic/minus.gif");
}

/* функция закрытия категорий в структуре*/
function collapseChapter(query,id,doctype){
	$.ajax({
		url: 'Provider?type=view&id='+query+'&parentdocid='+id+'&parentdoctype='+doctype+'&command=collaps`'+id+'`'+doctype,
		success: function(data) {
			$(".tbl"+id+doctype+" tr:[name=child]").remove()
		}
	});
	$("#img"+id+doctype).attr("src","/SharedResources/img/classic/plus.gif");
	$("#a"+id+doctype).attr("href","javascript:expandChapter('"+query+"','"+id+"','"+ doctype+"')");
}

/* функция открытия категорий корреспондентов*/
function expandChapterCorr(docid,num,url,doctype, page) {
	el = $(".tblCorr:eq("+num+")");
	$.ajax({
		url:url+"&page="+queryOpt.pagenum,
		dataType:'html',
		success: function(data) {
			$("#contentpane").html(data)
			$("#img"+docid+doctype).attr("src","/SharedResources/img/classic/minus.gif");
			$("#a"+docid+doctype).attr("href","javascript:collapsChapterCorr('"+docid+"','"+num+"','"+ url+"','"+doctype+"','"+page+"')");
		}
	});	
}

/* функция закрытия категорий корреспондентов*/
function collapsChapterCorr(docid,num,url,doctype, page) {
	el = $(".tblCorr:eq("+num+")");
	$.ajax({
		url:"Provider?type=view&id=corrcat&command=collaps`"+docid+"&page="+ queryOpt.pagenum ,
		dataType:'html',
		success: function(data) {
			$("#contentpane").html(data)
			$("#img"+docid+doctype).attr("src","/SharedResources/img/classic/plus.gif");
			$("#a"+docid+doctype).attr("href","javascript:expandChapterCorr('"+docid+"','"+num+"','"+ url+"','"+doctype+"','"+page+"')");
		}
	});	
}